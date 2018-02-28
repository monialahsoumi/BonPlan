/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Promotion;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.GestionPromo;

/**
 *
 * @author Meyssa
 */
public class ModificationPromoContoller implements Initializable {

    @FXML
    private Button modifBtn;
    @FXML
    private Button retour;
    @FXML
    private TextArea descPromo;
    @FXML
    private DatePicker debPromo;
    @FXML
    private DatePicker finPromo;
    @FXML
    private ImageView newImg;
    static Promotion aModifier;
    private int image_changed = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        descPromo.setText(aModifier.getDescription());
        debPromo.setValue(aModifier.getDateDebut().toLocalDate());
        finPromo.setValue(aModifier.getDateFin().toLocalDate());
        Image image = new Image(aModifier.getUrlPromo());
        newImg.setImage(image);

    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {

        aModifier.setDescription(descPromo.getText());
        if(image_changed == 1){
            aModifier.setUrlPromo(this.upload(selectedImage));
        }
        aModifier.setDateDebut(java.sql.Date.valueOf(debPromo.getValue()));
        aModifier.setDateFin(java.sql.Date.valueOf(finPromo.getValue()));
        GestionPromo gp = new GestionPromo();
        gp.modifierPromo(aModifier);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PagePromoPane.fxml"));
        PagePromoController pagepromo = loader.getController();
        Parent root = loader.load();
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();

    }

    @FXML
    private void btnRetour(ActionEvent event) {
    }

    public String upload(File file) throws FileNotFoundException, IOException {
        String imageName = file.getName();

        BufferedOutputStream stream = null;

        String storagePath = "C:\\wamp64\\www\\images";
        String localPath = "http://localhost/images";
        String fileName = file.getName();
        fileName = fileName.replace(" ", "_");

        try {
            Path p = file.toPath();
            byte[] bytes = Files.readAllBytes(p);
            File Storage = new File(storagePath);

            if (!Storage.exists()) {
                Storage.mkdir();//creer le dossier de recuperation des imagess
            }
            File serverFile = new File(Storage.getAbsolutePath() + File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            return localPath + File.separator + fileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return " file not found";
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return " IO Exception";
        }
    }

    File selectedImage = null;

    @FXML
    private void addImage(MouseEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("selectionner une image");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));

        selectedImage = chooser.showOpenDialog(null);
        if (selectedImage != null) {
            String newpath = this.upload(selectedImage);
            //path=selectedImage.getAbsolutePath();
            Image newImage = new Image(newpath);
            File f = new File(newpath);
            newImg.setImage(newImage);
            image_changed = 1;
        } else {
            System.out.println("erreuuur image");
        }

    }
}

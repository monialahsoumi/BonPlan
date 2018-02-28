/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import entité.Promotion;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javafx.stage.FileChooser;
import service.GestionPromo;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Meyssa
 */
public class PromotionPaneController {

    private final static String ALERT_DATE_DEBUT_TXT = "La date debut promo doit être renseignée";
    private final static String ALERT_DATE_FIN_TXT = "La date fin promo doit être renseignée";
    private final static String ALERT_HEADER = "Alerte";

    @FXML
    private TextArea promoDesc;
    @FXML
    private DatePicker debutPromo;
    @FXML
    private DatePicker finPromo;
    @FXML
    private ImageView promoImg;
    @FXML
    private Button btnAddPromo;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }

    @FXML
    private void ajouterPromo(ActionEvent event) throws ParseException, IOException {
        String descpromo = promoDesc.getText();
        String urlPromo;
        try {
            urlPromo = this.upload(selectedImage);
        } catch (IOException ex) {
            urlPromo = "pas de photo";
        }
        int idProp = 11;

        Promotion promo = new Promotion(descpromo, urlPromo, idProp);
        if (Objects.isNull(debutPromo.getValue())) {
            Alert debutPromoAlert = new Alert(Alert.AlertType.WARNING);
            debutPromoAlert.setTitle(ALERT_HEADER);
            debutPromoAlert.setContentText(ALERT_DATE_DEBUT_TXT);
            debutPromoAlert.setHeaderText(null);
            debutPromoAlert.show();
            return;
        } else {
            promo.setDateDebut(java.sql.Date.valueOf(debutPromo.getValue()));
        }

        if (Objects.isNull(finPromo.getValue())) {
            Alert finPromoAlert = new Alert(Alert.AlertType.WARNING);
            finPromoAlert.setTitle(ALERT_HEADER);
            finPromoAlert.setContentText(ALERT_DATE_FIN_TXT);
            finPromoAlert.setHeaderText(null);
            finPromoAlert.show();
            return;
        } else {
            promo.setDateFin(java.sql.Date.valueOf(finPromo.getValue()));
        }

        GestionPromo gPromo = new GestionPromo();

        try {
            gPromo.ajouterPromotion(promo);
            System.out.println("promo ajouté avec succès !! ");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ConsultationPromosPane.fxml"));
        Parent root = loader.load();
        btnAddPromo.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
            promoImg.setImage(newImage);

        } else {
            System.out.println("erreuuur image");
        }

    }

}

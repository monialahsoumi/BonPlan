
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import GUI.Upload;
import entité.Utilisateur;
import entité.Visiteur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.GestionProprietaire;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ModificationVisiteurController implements Initializable {

    private TextField Pseudo;
    @FXML
    private TextField mail;
    @FXML
    private TextField PrenomV;
    @FXML
    private TextField nomV;
    @FXML
    private PasswordField pwd;
    @FXML
    private PasswordField confirmePwd;
    @FXML
    private Button modifBtn;
    private TextField Psuedo;
    @FXML
    private ImageView pdp;
    @FXML
    private Button btnUpload;

    @FXML
    private TextField texrpath;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            this.initialiser();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ModificationVisiteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialiser() throws SQLException {

        System.out.println(Utilisateur.pseudo);
        ObservableList<Visiteur> ListV;
        GestionVisiteur gp = new GestionVisiteur();
        ListV = FXCollections.observableArrayList(gp.afficherVisiteur(Utilisateur.pseudo));
        for (Visiteur v : ListV) {
            mail.setText(v.getEmail());
            nomV.setText(v.getNom());
            PrenomV.setText(v.getPrenom());
            pwd.setText(v.getPassword());
            String pathimage = "file:///" + v.getPdp();
            Image image = new Image(pathimage);
            pdp.setImage(image);
            pdp.setFitHeight(300);
            pdp.setFitWidth(300);
             
        }
    }

    @FXML
    private void Modif_Visiteur(ActionEvent event) throws SQLException, IOException {
        GestionVisiteur gp = new GestionVisiteur();
        String mailc = mail.getText();

        String pass = pwd.getText();
        String nom = nomV.getText();
        String prenom = PrenomV.getText();
        String confi = confirmePwd.getText();
        String password = pwd.getText();
        String pdp = texrpath.getText();
        if (!password.equals(confi)) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier la confirmation votre pseudo ou mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
        } else {
            Visiteur v = new Visiteur();
            v.setEmail(mailc);
            v.setNom(nom);

            v.setPassword(password);

            v.setPrenom(prenom);
            v.setPdp(pdp);
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Succes");
            alert2.setContentText("Utilisateur Modifié avec Succes ");
            alert2.setHeaderText(null);
            alert2.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
            Parent root = loader.load();
            modifBtn.getScene().setRoot(root);
            try {
                gp.ModifierVisiteur(v);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    @FXML
    private void UploadPdp(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("file", "*.jpg", "*.png"));
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            Upload u = new Upload();
            u.upload(selectedfile);
            texrpath.setText(u.upload(selectedfile));
            Image image = new Image(new FileInputStream(selectedfile));
            pdp.setImage(image);
            
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ProfilVisiteur.fxml"));

        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import GUI.Upload;
import entité.Visiteur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class InscritVisiteurController {

    @FXML
    private TextField pseudo;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField pass;

    @FXML
    private Button retourbtn;
    @FXML
    private Button btnincrit;
    @FXML
    private PasswordField pass1;
    @FXML
    private ImageView pdp;
    @FXML
    private Button Upload;
    @FXML
    private TextField txtpath;
    boolean test;

    /**
     * Initializes the controller class.
     */
    public void initialize() {

    }

    @FXML
    private void AjouterV(ActionEvent event) throws IOException, SQLException {
        GestionVisiteur g = new GestionVisiteur();
        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Erreur");
        String pseudov = pseudo.getText();
        String mailv = mail.getText();
        String password = pass.getText();
        String nomv = nom.getText();
        String prenomv = prenom.getText();
        String photo = txtpath.getText();
        String existe = g.ExisteOuNN(pseudov);
        System.out.println(existe);
        //GestionVisiteur gv = new GestionVisiteur ();
//      Visiteur v1 = gv.recupererVisiteur(pseudov);
        //String ps= v1.getPseudo();
        //if (ps.equals(pseudov)){
        //   alert2.setContentText("Le pseudo deja Existe ");
        //  alert2.setHeaderText(null);
        //   alert2.show();

        // }
        if (pseudo.getText().equals("")) {

            alert2.setContentText("Saisir un Pseudo");

            alert2.show();

        }
      else if (existe.equals(pseudov)) {
           alert2.setContentText("Pseudo deja Existe");
           alert2.setHeaderText(null);
           alert2.show();
           throw new SQLException();

        }
       else if (nom.getText().equals("")) {

            alert2.setContentText("Saisir un Nom");

            alert2.show();

        }
       else if (prenom.getText().equals("")) {

            alert2.setContentText("Saisir un Prenom");

            alert2.show();
        }
     else   if (((pass.getText().length() == 0) || (pass1.getText().length() == 0)) || (!pass.getText().equals(pass1.getText()))) {

            alert2.setContentText("saisir un mot de passe Ou Verifier la Confirmation");
            alert2.setHeaderText(null);
            alert2.show();

        }
     else if (mail.getText().length() == 0 || (!mail.getText().contains("@"))) {

            alert2.setContentText("verifier Votre Mail");
            alert2.setHeaderText(null);
            alert2.show();

        } else {

            Visiteur v = new Visiteur(pseudov, mailv, password, nomv, prenomv, "Visiteur", photo);

            GestionVisiteur sp = new GestionVisiteur();

            try {
                sp.ajouterVisiteur(v);

                System.out.println("visiteur ajouté avec succès !! ");
                JOptionPane.showMessageDialog(null, "visiteur ajouté avec succès ");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
                Parent root = loader.load();
                btnincrit.getScene().setRoot(root);

            } catch (SQLException ex) {

                alert2.setContentText("Inscription impossible");
                alert2.setHeaderText(null);
                alert2.show();

            }
        }
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
        Parent root = loader.load();
        retourbtn.getScene().setRoot(root);
    }

    @FXML
    private void UploadPdp(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("file", "*.jpg", "*.png"));
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            Upload u = new Upload();
            u.upload(selectedfile);
            txtpath.setText(u.upload(selectedfile));
            Image image = new Image(new FileInputStream(selectedfile));
            pdp.setImage(image);
        }
    }

}

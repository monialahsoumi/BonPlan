/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Proprietaire;
import entité.Visiteur;
import entité.Utilisateur;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ProfilVisiteurController implements Initializable {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private Button event;
    @FXML
    private Button reservation;
    @FXML
    private Button modif;
    @FXML
    private ImageView pdp;
    @FXML
    private Label pseudo;
    @FXML
    private Label mail;
    @FXML
    private Label nomV;
    @FXML
    private Label prenomV;
    @FXML
    private Button supp;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            consulter_profil();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilVisiteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consulter_profil() throws SQLException {

        accueilController ac = new accueilController();
        ObservableList<Visiteur> ListV;
        GestionVisiteur gp = new GestionVisiteur();
        ListV = FXCollections.observableArrayList(gp.afficherVisiteur(Utilisateur.pseudo));
       
        for (Visiteur vis : ListV) {
            pseudo.setText(vis.getPseudo());
            mail.setText(vis.getEmail());
            nomV.setText(vis.getNom());
            prenomV.setText(vis.getPrenom());
            System.out.println(vis.getPdp());
            String pathimage = "file:///" + vis.getPdp();
            Image image = new Image(pathimage);
            pdp.setImage(image);
            pdp.setFitHeight(300);
            pdp.setFitWidth(300);
        }

    }

    @FXML
    public void modification(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModificationVisiteur.fxml"));

        Parent root = loader.load();
        modif.getScene().setRoot(root);
    }

    @FXML
    private void deleteVisiteur(ActionEvent event) throws SQLException {
        Utilisateur u = new Utilisateur() ; 
        GestionVisiteur sv = new GestionVisiteur();
        String ps = pseudo.getText();
        sv.delete(ps);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));

        Parent root = loader.load();
        btnRetour.getScene().setRoot(root);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Evenement;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.GestionEvenement;
import service.GestionProprietaire;

/**
 * FXML Controller class
 *
 * @author Meyssa
 */
public class EvenementPaneController implements Initializable {

    @FXML
    private TextField eventName;
    @FXML
    private DatePicker debutEvent;
    @FXML
    private DatePicker finEvent;
    @FXML
    private TextField places;
    @FXML
    private TextArea descEvent;
    @FXML
    private Button btnaddEvent;

    /* Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void ajouterEvenement(ActionEvent event) throws IOException, SQLException {
		GestionProprietaire gprop= new GestionProprietaire();
        String nomEv = eventName.getText();
        String descEv = descEvent.getText();
        int nbPlaces = Integer.parseInt(places.getText());
        String typeEv = "publique";
        int idprop = gprop.recupererPropId(Utilisateur.pseudo);
        //Date debutv = new Date(debutEvent.getValue().toEpochDay());
        //Date finEv = new Date(finEvent.getValue().toEpochDay());

        Evenement ev = new Evenement(nomEv, descEv, nbPlaces, typeEv, idprop);
        ev.setDebutEv(java.sql.Date.valueOf(debutEvent.getValue()));
        ev.setFinEv(java.sql.Date.valueOf(finEvent.getValue()));

        GestionEvenement gEvent = new GestionEvenement();

        try {
            gEvent.ajouterEvenement(ev);
            System.out.println("Event ajouté avec succès !! ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Prop_consultationEventFXML.fxml"));
            Parent root = loader.load();
            btnaddEvent.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}

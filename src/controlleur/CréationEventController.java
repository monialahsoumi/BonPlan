/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.DemandeEvent;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.GestionEvenement;
import service.GestionVisiteur;
import service.gestionDemandeEvent;

/**
 *
 * @author Meyssa
 */
public class CréationEventController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button retour;
    @FXML
    private TextArea EventDesc;
    @FXML
    private DatePicker EventDeb;
    @FXML
    private DatePicker EventFin;
    @FXML
    private Button envoyer;
    @FXML
    private TextField nomEvent;
    @FXML
    private TextField nbplace;
    @FXML
    private Label eventAdress;
    @FXML
    private RadioButton prive;
    @FXML
    private RadioButton publique;
    public static int idProp=23;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Consulter.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
    }

    @FXML
    private void envoieDemande(ActionEvent event) throws SQLException {
        GestionVisiteur geV= new GestionVisiteur();
        GestionEvenement geEvent = new GestionEvenement();
        String nom = nomEvent.getText();
        String desc = EventDesc.getText();   //	DateDebut	DateFin	user_iduser	user_iduser1
        String type;
        if (prive.isSelected()) {
             type = "privé";
        } else {
             type = "publique";
        }
        int nbPlaces = Integer.parseInt(nbplace.getText());
        Date deb = java.sql.Date.valueOf(EventDeb.getValue());
        Date fin = java.sql.Date.valueOf(EventFin.getValue());
        int idDemandeur = geV.recupererVisiteurId(Utilisateur.pseudo) ;
        int idprop = this.idProp;

       DemandeEvent demande = new DemandeEvent(nom, desc, deb, fin, nbPlaces, type , idDemandeur, idprop);
        gestionDemandeEvent geDemande = new gestionDemandeEvent();
       try {
           geDemande.ajouterDemande(demande);
            Notifications notificationBuilder = Notifications.create()
                    .title("Création d'une demande ")
                    .text("Votre demande de créer l'évènement '"+nom+"' à été bien créer")
                    .graphic(null)
                    .hideAfter(Duration.seconds(20))
                    .position(Pos.TOP_CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
            notificationBuilder.showError();
            
        
    }
    catch (SQLException ex)
         {
            System.out.println(ex);
    }
}

}

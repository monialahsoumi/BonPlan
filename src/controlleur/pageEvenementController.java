package controlleur;

import entité.Evenement;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.GestionEvenement;
import service.GestionProprietaire;

/**
 *
 * @author Meyssa
 */
public class pageEvenementController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button retour;
    @FXML
    private Label EventName;
    @FXML
    private Label EventDesc;
    @FXML
    private Label EventDeb;
    @FXML
    private Label EventFin;
    @FXML
    private Label EventAdress;
    @FXML
    private Label EventPlaces;
    @FXML
    private Button btnParticiper;
    @FXML
    private Button modif;

    public static Evenement current_event;
    
	String ps;

    public void consulterEvent() throws SQLException {
        if (current_event == null) {
            return;
        }
        System.out.println(current_event.getIdEv());
        EventName.setText(current_event.getNomEv());
        EventDesc.setText(current_event.getDescEv());
        EventDeb.setText(current_event.getDebutEv().toString());
        EventFin.setText(current_event.getFinEv().toString());
        EventAdress.setText("en cours");
        EventPlaces.setText(String.valueOf(current_event.getNbPlace()));

    }

    /*GestionEvenement gEvent = new GestionEvenement();
        try {
            if (listEvenement.size()==0) {
                return;
            }
            this.pseudo = gEvent.recupPseudo_Prop(listEvenement.get(0).getIdEv());//
            
            System.out.println(this.pseudo);
            if (!Utilisateur.pseudo.equals(this.pseudo)) {
                btnAnnuler.setVisible(false);
                btnDelete.setVisible(false);
                System.out.println("not equals pseudo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prop_ConsultationEventController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TEEEEEEEEESTSTT");
		GestionProprietaire gprop = new GestionProprietaire();
        GestionEvenement gEvent = new GestionEvenement();
        try {
            consulterEvent();
			
            this.ps = gEvent.recupPseudo_Prop(current_event.getIdEv());
            if (!Utilisateur.pseudo.equals(this.ps)) {
                modif.setVisible(false);
                
                System.out.println("not equals pseudo");
            }else{
                btnParticiper.setVisible(false);
                System.out.println(" equals pseudo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationPromosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException, SQLException {
		  GestionEvenement gEvent = new GestionEvenement();
		  this.ps = gEvent.recupPseudo_Prop(current_event.getIdEv());
		if ( Utilisateur.pseudo.equals(ps) ){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Prop_consultationEventFXML.fxml"));
			Parent root = loader.load();
			retour.getScene().setRoot(root);
		}else{
		}

    }

    @FXML
    private void participer(ActionEvent event) {
    }

    @FXML
    private void modificationEvent(ActionEvent event) throws IOException {
        ModificationEvenementPaneController.event_a_modifier = current_event;
        int id = current_event.getIdEv();

        String name = current_event.getNomEv();
        String Desc = current_event.getDescEv();
        Date debut = current_event.getDebutEv();
        Date fin = current_event.getFinEv();
        int nb = current_event.getNbPlace();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modificationEvenementPane.fxml"));
        ModificationEvenementPaneController modif_event = loader.getController();
        Parent root = loader.load();
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();

    }
}

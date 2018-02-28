/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.DemandeEvent;
import entité.Evenement;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.GestionEvenement;
import service.GestionVisiteur;
import service.gestionDemandeEvent;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ConsultationDemandesEventController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TableView<DemandeEvent> demandeTab;
    @FXML
    private TableColumn<DemandeEvent, String> event;
    @FXML
    private TableColumn<DemandeEvent, String> descEvent;
    @FXML
    private TableColumn<DemandeEvent, Date> deb;
    @FXML
    private TableColumn<DemandeEvent, Date> fin;
    @FXML
    private TableColumn<DemandeEvent, Integer> nbplaces;
    @FXML
    private TableColumn<DemandeEvent, String> typeEv;
    @FXML
    private TableColumn<DemandeEvent, String> pseudoV;
    @FXML
    private Button btnAccepter;
    @FXML
    private Button btnRejeter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherData();
    }

    private void setCellValue() {
        GestionVisiteur geVisiteur = new GestionVisiteur();

        System.out.println("11111111111111111111111");
        event.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descEvent.setCellValueFactory(new PropertyValueFactory<>("description"));
        deb.setCellValueFactory(new PropertyValueFactory<>("debut"));
        fin.setCellValueFactory(new PropertyValueFactory<>("fin"));
        nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
        typeEv.setCellValueFactory(new PropertyValueFactory<>("typeEvent"));
        pseudoV.setCellValueFactory(new PropertyValueFactory<>("pseudoV"));
    }

    private void AfficherData() {
        try {
            System.out.println("trytrytrytrytry");

            gestionDemandeEvent geDemande = new gestionDemandeEvent();

            ObservableList data = FXCollections.observableArrayList(geDemande.recupererDemande(Utilisateur.pseudo));
            demandeTab.setItems(data);
            setCellValue();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ProfilProprietaire.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }

    @FXML
    private void AccepterDemande(ActionEvent event) throws IOException {
        DemandeEvent selectedDemande = demandeTab.getSelectionModel().getSelectedItem();
        creerEvent(selectedDemande);
        gestionDemandeEvent gDemande = new gestionDemandeEvent();
        gDemande.validerDemande(selectedDemande);
        GestionEvenement gEvent = new GestionEvenement();

        int lastEventint = gEvent.dernierEvent();
        System.out.println("*********" + lastEventint + "********");
        
        /*sendMail*/
    }

    @FXML
    private void rejeterDemande(ActionEvent event) {
        /*sendMail*/
    }

    private void creerEvent(DemandeEvent d) throws IOException {

        String nomEv = d.getNom();
        String descEv = d.getDescription();
        int nbPlaces = d.getNbPlace();
        String typeEv = d.getTypeEvent();
        int idprop = d.getIdProp();
        Evenement ev = new Evenement(nomEv, descEv, nbPlaces, typeEv, idprop);
        ev.setDebutEv(d.getDebut());
        ev.setFinEv(d.getFin());

        GestionEvenement gEvent = new GestionEvenement();

        try {
            gEvent.ajouterEvenement(ev);
            System.out.println("Event ajouté avec succès !! ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Prop_consultationEventFXML.fxml"));
            Parent root = loader.load();
            btnAccepter.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}

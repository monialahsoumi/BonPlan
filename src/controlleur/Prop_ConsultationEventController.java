/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

/**
 *
 * @author Meyssa
 */
import entité.Evenement;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//import javafx.util.Callback;
import service.GestionEvenement;
import service.GestionProprietaire;

public class Prop_ConsultationEventController implements Initializable {
//Alert

    private final static String ALERT_EVENT_SUPP = "aSupprimer n'est pas valid";
    private final static String ALERT_EVENT_SHOW = "aAfficher n'est pas valid";
    private final static String ALERT_HEADER = "Alerte";

    @FXML
    private TableView<Evenement> EventsTab;
    @FXML
    private TableColumn<Evenement, String> event;
    @FXML
    private TableColumn<Evenement, String> descEvent;
    @FXML
    private TableColumn<Evenement, Date> deb;
    @FXML
    private TableColumn<Evenement, Date> fin;
    @FXML
    private TableColumn<Evenement, Integer> nbplaces;
    @FXML
    private TableColumn<Evenement, String> typeEv;
    List<Evenement> listEvenement;

    ObservableList<Evenement> data;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnShow;
    @FXML
    private Button btnAnnuler;
    static String pseudo;
 //  public static int idProp;
	@FXML
	private Button btnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherData();
    
    }

    private void setCellValue() {

        System.out.println("11111111111111111111111");
        event.setCellValueFactory(new PropertyValueFactory<>("nomEv"));
        descEvent.setCellValueFactory(new PropertyValueFactory<>("descEv"));
        deb.setCellValueFactory(new PropertyValueFactory<>("debutEv"));
        fin.setCellValueFactory(new PropertyValueFactory<>("finEv"));
        nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
        typeEv.setCellValueFactory(new PropertyValueFactory<>("typeEv"));

    }

    private void AfficherData() {
        try {
            System.out.println("trytrytrytrytry");

            GestionEvenement geEvent = new GestionEvenement();
			GestionProprietaire gprop =new GestionProprietaire();
            listEvenement = new ArrayList<>();
			int id = gprop.recupererPropId(Utilisateur.pseudo);
			System.out.println(id);
            listEvenement = (List<Evenement>) geEvent.recupererEvenement_P(id);
			data = FXCollections.observableArrayList(listEvenement);
            EventsTab.setItems(data);
            setCellValue();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @FXML
    private void deleteSelected(ActionEvent event) throws SQLException {
        GestionEvenement geEvent = new GestionEvenement();
        Evenement aSuprrimer = EventsTab.getSelectionModel().getSelectedItem();

        if (aSuprrimer == null) {
            Alert finPromoAlert = new Alert(Alert.AlertType.WARNING);
            finPromoAlert.setTitle(ALERT_HEADER);
            finPromoAlert.setContentText(ALERT_EVENT_SUPP);
            finPromoAlert.setHeaderText(null);
            finPromoAlert.show();
        } else {
            System.out.println("valide");
            geEvent.deleteEvent(aSuprrimer.getIdEv());
            EventsTab.getItems().removeAll(EventsTab.getSelectionModel().getSelectedItem());

        }
    }

    @FXML
    private void afficherSelectedItem(ActionEvent event) throws IOException, SQLException {
        Evenement aAfficher = EventsTab.getSelectionModel().getSelectedItem();
        pageEvenementController.current_event = aAfficher;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/pageEvenement.fxml"));
        pageEvenementController pageEVENT = loader.getController();
        Parent root = loader.load();
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();

        // btnShow.getScene().setRoot(root);
    }

    @FXML
    private void AnnulerSelectedItem(ActionEvent event) {
        Evenement aAnnuler = EventsTab.getSelectionModel().getSelectedItem();
        GestionEvenement geEvent = new GestionEvenement();
        geEvent.AnnulerEvent(aAnnuler);
    }

	@FXML
	private void ajoutEvent(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/evenementAjout.fxml"));
        Parent root = loader.load();
        btnAdd.getScene().setRoot(root);
	}

}

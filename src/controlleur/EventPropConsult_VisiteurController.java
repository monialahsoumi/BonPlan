/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entité.Evenement;
import entité.Utilisateur;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.GestionEvenement;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class EventPropConsult_VisiteurController implements Initializable {

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
	
	//List<Evenement> listEvenement;

	@FXML
	private Button btnShow;
	static String pseudo;
	public static int idProp;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			AfficherData();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	private void AfficherData() throws SQLException {
		try {
			System.out.println("trytrytrytrytry");
			GestionEvenement geEvent = new GestionEvenement();
			/*listEvenement = new ArrayList<>();
			listEvenement = (List<Evenement>) geEvent.recupererEvenement_v(idProp);
			System.out.println(listEvenement.get(0).getNbParticipant());*/
			ObservableList<Evenement> data = FXCollections.observableArrayList(geEvent.recupererEvenement_v(idProp));
			System.out.println("11111111111111111111111");
			event.setCellValueFactory(new PropertyValueFactory<>("nomEv"));
			descEvent.setCellValueFactory(new PropertyValueFactory<>("descEv"));
			deb.setCellValueFactory(new PropertyValueFactory<>("debutEv"));
			fin.setCellValueFactory(new PropertyValueFactory<>("finEv"));
			nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
		//	participants.setCellValueFactory(new PropertyValueFactory<>("nbParticipant"));
			EventsTab.setItems(data);
		} catch (SQLException ex) {
			System.out.println(ex);
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
}

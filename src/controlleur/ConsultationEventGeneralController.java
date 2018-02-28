/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.GestionEvenement;

/**
 *
 * @author Meyssa
 */
public class ConsultationEventGeneralController implements Initializable {

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
    private Button btnShow;
	@FXML
	private Button retour;

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
            listEvenement = new ArrayList<Evenement>();
            listEvenement =  geEvent.recupererEvenement();
            data = FXCollections.observableArrayList(listEvenement);
            EventsTab.setItems(data);
            setCellValue();
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

	@FXML
	private void btnRetour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
	}
        
      

    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Proprietaire;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.GestionProprietaire;
import controlleur.CategorieSearchController;
import controlleur.ConsulterController;
import entité.Suggestion;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.GestionSuggestion;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class AcceuilVisiteurController implements Initializable {

    String PropN;
    @FXML
    private TextField recherche;
    @FXML
    private ImageView cafe;
    @FXML
    private ImageView nocturne;
    @FXML
    private ImageView hotel;
    @FXML
    private Button retour;
    @FXML
    private Button btnconsulter;
    @FXML
    private Button btnPromo;
    @FXML
    private Button btnevent;
    @FXML
    private Button decon;
    @FXML
    private TableColumn<Proprietaire, ImageView> photo;
    @FXML
    private TableColumn<Proprietaire, String> nom;
    @FXML
    private TableView<Proprietaire> listRecherche;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView restaurant;
	@FXML
	private ProgressIndicator progress;
	@FXML
	private Button btnrec;
	@FXML
	private Button btnConsulterReservation;
	@FXML
	private TableColumn<Suggestion, String> nomPlan;
	@FXML
	private TableColumn<Suggestion, Integer> rating;
	@FXML
	private TableView<Suggestion> ListSugg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		try {
			suggestion();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

    }

	@FXML
    public void recherchePlan() throws SQLException {

        ObservableList<Proprietaire> ListP;
        GestionProprietaire g = new GestionProprietaire();
        ListP = FXCollections.observableArrayList(g.rechercheProptietaire(recherche.getText()));
        photo.setCellValueFactory(new PropertyValueFactory<>("image"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomPlan"));
        listRecherche.setItems(ListP);
    }
     private void RechercheDynamique(ActionEvent event) {
          ObservableList<Proprietaire> ListP = null;
        FilteredList<Proprietaire> filteredData = new FilteredList<>(ListP, e -> true);
        recherche.setOnKeyReleased(e
                -> {
            recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Proprietaire>) new Predicate<Proprietaire>() {
                    @Override
                    public boolean test(Proprietaire proprietaire) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (proprietaire.getNomPlan().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    }
                });

            });
            SortedList<Proprietaire> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(listRecherche.comparatorProperty());
            listRecherche.setItems(sortedData);
        });}
     
    
    @FXML
    private void Recupere_plan() throws IOException, SQLException {

        Proprietaire p = listRecherche.getSelectionModel().getSelectedItem();
        System.out.println(p.getNomPlan()+p.getPseudo());
        GestionProprietaire g = new GestionProprietaire();
       Proprietaire p2 = g.rechercheProprietaireParnom(p.getNomPlan(),p.getPseudo());
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Consulter.fxml"));
       
    ConsulterController.prop = p2;
        System.out.println(ConsulterController.prop +" jjjjjjjjjj ");
        System.out.println("p2 ===== ");
        System.out.println(p2);
        System.out.println("!!!  "+p2);

        
        Parent root = loader.load();
		progress.getScene().setRoot(root);
				
        

        //  listRecherche.getScene().setRoot(root);
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }

    @FXML
    private void ConsulterProfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ProfilVisiteur.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }

    @FXML
	public void rechercheCat_hotel() throws SQLException, IOException {
		CategorieSearchController c = new CategorieSearchController();
		c.p = "Hôtel";
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieSearch.fxml"));
		Parent root = loader.load();
		hotel.getScene().setRoot(root);
	}

	@FXML
	public void rechercheCat_cafe() throws SQLException, IOException {
		CategorieSearchController c = new CategorieSearchController();
		c.p = "Café";
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieSearch.fxml"));
		Parent root = loader.load();
		cafe.getScene().setRoot(root);
	}

	@FXML
	public void rechercheCat_nocturne() throws SQLException, IOException {
		CategorieSearchController c = new CategorieSearchController();
		c.p = "Bar";
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieSearch.fxml"));
		Parent root = loader.load();
		nocturne.getScene().setRoot(root);
	}

	@FXML
	public void rechercheCat_Restaurant() throws SQLException, IOException {
		CategorieSearchController c = new CategorieSearchController();
		c.p = "Restaurant";
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieSearch.fxml"));
		Parent root = loader.load();
		nocturne.getScene().setRoot(root);
	}

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
        Parent root = loader.load();
        decon.getScene().setRoot(root);

    }

    @FXML
    private void visible(MouseEvent event) {
        listRecherche.setVisible(true);
    }

    @FXML
    private void invisible(MouseEvent event) {
        listRecherche.setVisible(false);
    }

    @FXML
    private void ConsulterPromotion(ActionEvent event) {
    }

    @FXML
    private void ConsulterEvent(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ConsultationEventGeneralPane.fxml"));
        ConsultationEventGeneralController consult_event = loader.getController();
        Parent root = loader.load();
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();
    }
	 @FXML
    private void ConsulterReclam(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ConsulterReclamationV.fxml"));
        Parent root = loader.load();
        btnrec.getScene().setRoot(root);
    }

	@FXML
	private void ConsulterMesReservations(ActionEvent event) throws IOException {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AfficheReservartionVisiteurPane.fxml"));
        Parent root = loader.load();
        btnConsulterReservation.getScene().setRoot(root);
	}

	public void suggestion() throws SQLException{
		ObservableList<Suggestion> listP ;
        GestionSuggestion gs = new GestionSuggestion();
        listP = FXCollections.observableArrayList(gs.afficherSugg());
        nomPlan.setCellValueFactory(new PropertyValueFactory<>("nomPlan"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rate"));
        ListSugg.setItems(listP);
		
	}
	
	

}

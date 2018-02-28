/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Proprietaire;
import entité.Visiteur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import service.GestionAdmin;
import service.GestionProprietaire;
import service.GestionValidite;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class AdminInterfaceController implements Initializable {

	@FXML
	private TableView<Visiteur> tabV;
	@FXML
	private TableColumn<Visiteur, String> pseudoV;
	@FXML
	private TableColumn<Visiteur, String> mailV;
	@FXML
	private TableColumn<Visiteur, String> nomV;
	@FXML
	private TableColumn<Visiteur, String> prenomV;
	@FXML
	private TableView<Proprietaire> tabP;
	@FXML
	private TableColumn<Proprietaire, String> pseudoP;
	@FXML
	private TableColumn<Proprietaire, String> categorie;
	@FXML
	private TableColumn<Proprietaire, String> adresse;
	@FXML
	private TableColumn<Proprietaire, String> tel;
	@FXML
	private Button btnV;
	@FXML
	private Button btnP;
	@FXML
	private Button btnRetour;
	@FXML
	private Button valider;
	@FXML
	private Button btnRec;
	@FXML
	private Button btnCon;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void AfficherV(ActionEvent event) {
		try {
			GestionAdmin sv = new GestionAdmin();
			ObservableList<Visiteur> lista;
			lista = FXCollections.observableArrayList(sv.afficherListeV());
			pseudoV.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
			mailV.setCellValueFactory(new PropertyValueFactory<>("email"));
			nomV.setCellValueFactory(new PropertyValueFactory<>("nom"));
			prenomV.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			tabV.setItems(lista);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void AfficherP(ActionEvent event) throws SQLException {
		GestionAdmin sv = new GestionAdmin();
		GestionProprietaire sp = new GestionProprietaire();
		ArrayList<Proprietaire> list = new ArrayList<>();
		list = sv.afficherListeP();
		pseudoP.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
		categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
		adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		ObservableList<Proprietaire> liste = FXCollections.observableArrayList(list);
		tabP.setItems(liste);
	}

	@FXML
	private void Retour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
		Parent root = loader.load();
		btnRetour.getScene().setRoot(root);
	}

	@FXML
	private void ValiderCompte(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ValidePane.fxml"));
		Parent root = loader.load();
		btnRetour.getScene().setRoot(root);
	}

	@FXML
	private void traiterRecl(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/GestionReclam.fxml"));
		Parent root = loader.load();
		btnRec.getScene().setRoot(root);
	}

	@FXML
	private void ConsulterProfil(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ConsulterAdmin.fxml"));
		Parent root = loader.load();
		btnCon.getScene().setRoot(root);
	}

}

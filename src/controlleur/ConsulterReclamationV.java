/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Reclamer;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.GestionReclamation;

/**
 * FXML Controller class
 *
 * @author gaalo
 */
public class ConsulterReclamationV implements Initializable {

	public ArrayList<Reclamer> ran;

	@FXML
	private AnchorPane a;
	@FXML
	private Button acceuil;
	@FXML
	private TableView<Reclamer> tableView;
	@FXML
	private TableColumn<?, ?> type;
	@FXML
	private TableColumn<?, ?> descp;
	@FXML
	private TableColumn<?, ?> nivRec;
	@FXML
	private TableColumn<?, ?> etat;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			// TODO
			afficher();
		} catch (SQLException ex) {
			Logger.getLogger(ConsulterReclamationV.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void acceuilAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
		Parent root = loader.load();
		acceuil.getScene().setRoot(root);

	}

	@FXML
	private void Cliked(MouseEvent event) {
	}

	public void afficher() throws SQLException {
		GestionReclamation m = new GestionReclamation();
		System.out.println("wsel");
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		descp.setCellValueFactory(new PropertyValueFactory<>("description"));
		nivRec.setCellValueFactory(new PropertyValueFactory<>("nivRec"));
		etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
		ObservableList<Reclamer> ol = FXCollections.observableArrayList(GestionReclamation.DisplayAll1(m.obtenirIdUser(Utilisateur.pseudo)));
		System.out.println(ol);
		System.out.println("skon");
		tableView.setItems(ol);
	}

}

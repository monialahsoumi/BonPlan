/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Reclamer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.GestionAbonnement;
import entité.Utilisateur;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import service.GestionReclamation;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author gaalo
 */
public class FXMLreclameController implements Initializable {

	@FXML
	private TextField type_rec;
	@FXML
	private ComboBox<String> niv_rec;
	@FXML
	private TextArea desc_rec;
	@FXML
	private Button btRec;
	ObservableList<String> option
			= FXCollections.observableArrayList(
					"Elevée",
					"Normal",
					"Faible"
			);

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		niv_rec.setItems(option);
		// TODO
	}

	@FXML
	private void btRecAction(ActionEvent event) throws SQLException, IOException {
		String nP = "";
		GestionReclamation m = new GestionReclamation();
		GestionVisiteur g = new GestionVisiteur();
		String type_recv = type_rec.getText();
		String c = "en cours";
		String f = niv_rec.getValue();
		String desc_recv = desc_rec.getText();
		int currentUser = m.obtenirIdUser(Utilisateur.pseudo);
		//ConsulterController a= new ConsulterController();
		nP = controlleur.ConsulterController.nomPlan;
		int idP = m.obtenirIdPlan(nP);
		//int currenPlan = m.obtenirIdPlan();

		Reclamer r = new Reclamer(currentUser, idP, c, type_recv, f, desc_recv);
		if (type_recv.length() == 0 || f.equals("Priority") || desc_recv.length() == 0) {
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Erreur");
			alert2.setContentText("veuillez remplir les champs indiquer");
			alert2.setHeaderText(null);
			alert2.show();
		} else {
			m.insererReclam(r);
			System.out.println("Reclamtion ajouté avec succès !! ");
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Ajout");
			alert2.setContentText("Reclamtion ajouté avec succès !!");
			alert2.setHeaderText(null);
			alert2.show();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
		Parent root = loader.load();
		btRec.getScene().setRoot(root);
		}
		

	

	}
}

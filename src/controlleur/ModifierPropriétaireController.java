/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.lynden.gmapsfx.GoogleMapView;
import entité.Proprietaire;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.GestionProprietaire;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ModifierPropriétaireController {

	ObservableList<String> tfCategorieList = FXCollections.observableArrayList("Hotêl", "Café", "Bar", "Restaurant");
	public static Utilisateur u;
	@FXML
	private GoogleMapView map;
	@FXML
	private TextField mail;
	@FXML
	private TextArea desc;
	@FXML
	private TextField tel;
	@FXML
	private ComboBox<String> comboCateg;
	@FXML
	private TextField Psuedo;
	@FXML
	private Button rech;
	@FXML
	private TextField nomPlan;
	@FXML
	private PasswordField pwd;
	@FXML
	private PasswordField confirmePwd;
	@FXML
	private Button modifBtn;
	@FXML
	private Button retour;

	/**
	 * Initializes the controller class.
	 */
	public void initialize() {
		comboCateg.setValue("Hotêl");
		comboCateg.setItems(tfCategorieList);
	}

	@FXML
	public void recherche_plan() throws SQLException {
		Psuedo.setDisable(false);
		mail.setDisable(false);
		desc.setDisable(false);
		comboCateg.setDisable(false);
		nomPlan.setDisable(false);
		tel.setDisable(false);
		pwd.setDisable(false);
		ObservableList<Proprietaire> ListP;
		GestionProprietaire gp = new GestionProprietaire();
		ListP = FXCollections.observableArrayList(gp.afficherProp(Utilisateur.pseudo));
		for (Proprietaire p : ListP) {
			System.out.println(ListP);
			Psuedo.setText(p.getPseudo());
			mail.setText(p.getEmail());
			desc.setText(p.getDescription());
			comboCateg.setValue(p.getCategorie());
			nomPlan.setText(p.getNomPlan());
			tel.setText(p.getTelephone());
			pwd.setText(p.getPassword());
		}
	}

	@FXML
	public void Modif_plan() throws SQLException {
		GestionProprietaire gp = new GestionProprietaire();
		String mailc = mail.getText();
		String telephone = tel.getText();
		String pseudo = Utilisateur.pseudo;
		String pass = pwd.getText();
		String nom = nomPlan.getText();
		String descri = desc.getText();
		String confi = confirmePwd.getText();
		String categ = comboCateg.getValue();
		if (mailc.length() != 0 || telephone.length() != 0) {
			Proprietaire p = new Proprietaire();
			p.setEmail(mailc);
			p.setNomPlan(nom);
			p.setPseudo(pseudo);
			p.setTelephone(telephone);
			p.setPassword(pass);
			p.setCategorie(categ);
			p.setDescription(descri);
			gp.modifProp(p);
			Psuedo.setText(null);
			mail.setText(null);
			desc.setText(null);
			comboCateg.setValue(null);
			nomPlan.setText(null);
			tel.setText(null);
			pwd.setText(null);
			Psuedo.setDisable(true);
			mail.setDisable(true);
			desc.setDisable(true);
			comboCateg.setDisable(true);
			nomPlan.setDisable(true);
			tel.setDisable(true);
			pwd.setDisable(true);
		} else {
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Erreur Fatal !!");
			alert2.setContentText("Modification impossible");
			alert2.setHeaderText(null);
			alert2.show();
		}
	}

	@FXML
	private void btnRetour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/accueilFXML.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Proprietaire;
import entité.Utilisateur;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import service.GestionLogin;
import service.GestionProprietaire;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class indexController {

	private Button btnlogin;
	private Button btnaccès;
	@FXML
	private AnchorPane background;
	@FXML
	private Button signUp;
	@FXML
	private Pane Login;
	@FXML
	private TextField login;
	@FXML
	private PasswordField mdp;
	@FXML
	private Button btn;
	@FXML
	private Label label;
	@FXML
	private Button signIn;
	@FXML
	private TextField recherche;
	@FXML
	private ImageView resto;
	@FXML
	private ImageView cafe;
	@FXML
	private ImageView nocturne;
	@FXML
	private ImageView hotel;

	/**
	 * Initializes the controller class.
	 */
	public void initialize() {
		// TODO
	}
	//afficher page pré-inscription :

	@FXML
	public void Loginbtn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/userSignIn.fxml"));
		Parent root = loader.load();
		signIn.getScene().setRoot(root);
	}

	public void show(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/accueilFXML.fxml"));
		Parent root = loader.load();
		btnaccès.getScene().setRoot(root);
	}

	//afficher panneau de login
	@FXML
	public void affichepane() {
		Login.setVisible(true);
	}
	//hide panneau login

	@FXML
	public void invisiblepane() {
		Login.setVisible(false);
	}

	@FXML
	private void btnLoginAction(ActionEvent event) throws SQLException, IOException {
		GestionLogin ser = new GestionLogin();
		accueilController ac = new accueilController();
		Utilisateur p = new Utilisateur();
		p = GestionLogin.recherche(login.getText(), mdp.getText());
		if (p.getStatus_login() == true && p.getRole().equals("Propriétaire")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/accueilFXML.fxml"));
			Parent root = loader.load();
			login.getScene().setRoot(root);
			Utilisateur.pseudo = login.getText();
			TrayNotification tray = new TrayNotification();
			tray.setNotificationType(NotificationType.CUSTOM);
			tray.setTitle("Login Success");
			tray.setMessage("Bonjour Cher Propriétaire, on vous souhaite une bonne visite");
			tray.setAnimationType(AnimationType.FADE);
			tray.showAndDismiss(Duration.millis(2500));

			tray.setRectangleFill(Color.valueOf("#f78c37"));
		} else if (p.getStatus_login() == true && p.getRole().equals("Visiteur")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
			Parent root = loader.load();
			login.getScene().setRoot(root);
			Utilisateur.pseudo = login.getText();
			TrayNotification tray = new TrayNotification();
			tray.setNotificationType(NotificationType.CUSTOM);
			tray.setTitle("Login Success");
			tray.setMessage("Bonjour Cher Visiteur, on vous souhaite une bonne visite");
			tray.setAnimationType(AnimationType.FADE);
			tray.showAndDismiss(Duration.millis(2500));

			tray.setRectangleFill(Color.valueOf("#f78c37"));
		} else if (p.getStatus_login() == true && p.getRole().equals("Administrateur")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminInterface.fxml"));
			Parent root = loader.load();
			login.getScene().setRoot(root);
			Utilisateur.pseudo = login.getText();
			TrayNotification tray = new TrayNotification();
			tray.setNotificationType(NotificationType.CUSTOM);
			tray.setTitle("Login Success");
			tray.setMessage("Bonjour Cher Administrateur, on vous souhaite une bonne visite");
			tray.setAnimationType(AnimationType.FADE);
			tray.showAndDismiss(Duration.millis(2500));

			tray.setRectangleFill(Color.valueOf("#f78c37"));
		} else {
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Erreur");
			alert2.setContentText("Veillez vérifier votre pseudo ou mot de passe");
			alert2.setHeaderText(null);
			alert2.show();
		}
	}

	@FXML
	public void recherche() throws SQLException {
		ObservableList<Proprietaire> ListP;
		GestionProprietaire g = new GestionProprietaire();
		ListP = FXCollections.observableArrayList(g.rechercheP());
		for (Proprietaire p : ListP) {
			TextFields.bindAutoCompletion(recherche, p.getNomPlan());
		}
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
	public void rechercheIn() throws SQLException, IOException {
		GestionProprietaire gp = new GestionProprietaire();
		gp.rechercheIndex(recherche.getText());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieSearch.fxml"));
		Parent root = loader.load();
		nocturne.getScene().setRoot(root);
	}
}

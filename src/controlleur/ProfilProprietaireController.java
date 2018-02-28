/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Proprietaire;
import entité.Suggestion;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.GestionAbonnement;
import service.GestionProprietaire;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ProfilProprietaireController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Button retour;
	@FXML
	private ImageView pdp;
	@FXML
	private Button event;
	@FXML
	private Button promo;
	@FXML
	private Button reservation;
	@FXML
	private Button modif;
	@FXML
	private Label login;
	@FXML
	private Label pseudo;
	@FXML
	private Label mail;
	@FXML
	private Label adresse;
	@FXML
	private Label desc;
	@FXML
	private Label categorie;
	@FXML
	private Label tel;

	public static Suggestion p;
	@FXML
	private Button btnConsulD;
	static Proprietaire prop;
	@FXML
	private PieChart pieChart;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			consulter_profil();
		} catch (SQLException ex) {
			Logger.getLogger(ProfilProprietaireController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void btnRetour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/accueilFXML.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
	}

	public void consulter_profil() throws SQLException {
		accueilController ac = new accueilController();
		ObservableList<Proprietaire> ListP;
		GestionProprietaire gp = new GestionProprietaire();
		ListP = FXCollections.observableArrayList(gp.afficherProp(Utilisateur.pseudo));
		for (Proprietaire prop : ListP) {
			System.out.println(ListP);
			login.setText(prop.getNomPlan());
			pseudo.setText(prop.getPseudo());
			mail.setText(prop.getEmail());
			adresse.setText(prop.getAdresse());
			desc.setText(prop.getDescription());
			categorie.setText(prop.getCategorie());
			tel.setText(prop.getTelephone());
			String imagepath = "file:///" + prop.getPhotoProfil();
			Image image = new Image(imagepath);
			pdp.setImage(image);
			pdp.setFitHeight(200);
			pdp.setFitWidth(150);
		}
		GestionAbonnement c = new GestionAbonnement();

		int x = 0;
		int y=0;
		try {
			int idP = c.obtenirIdPlan(login.getText());
			x = c.returnAbonner(idP);
			y = c.returnNbVisit();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		ObservableList<PieChart.Data> pieChartDatas = FXCollections.observableArrayList(
				new PieChart.Data("Nombre de visiteur abonner " + "(" + x + ")", x),
				new PieChart.Data("Nombre de tous les Visiteurs " + "(" + y + ")", y)
		);
		pieChart.setData(pieChartDatas);
	}

	@FXML
	public void modification() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifierPropriétaire.fxml"));
		Parent root = loader.load();
		modif.getScene().setRoot(root);
	}

	@FXML
	private void ConsulterMesEvents(ActionEvent event) throws IOException {
		//Prop_ConsultationEventController.idProp= this.prop.getId();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Prop_consultationEventFXML.fxml"));
        Prop_ConsultationEventController propEvents= loader.getController();
        Parent root = loader.load();
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();
	}

	@FXML
	private void ConsulterMesPromos(ActionEvent event) {
	}

	@FXML
	private void ConsulterLesDemandes(ActionEvent event) {
	}

	@FXML
	private void afficherListeReservation(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AffiicherReservationPane.fxml"));
		Parent root = loader.load();
		reservation.getScene().setRoot(root);
		
	}

}

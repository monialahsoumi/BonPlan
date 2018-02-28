/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import entité.Reclamer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.GestionReclamation;

/**
 * FXML Controller class
 *
 * @author gaalo
 */
public class GestionReclamController implements Initializable {
		public ArrayList<Reclamer> ran;

	@FXML
	private TableView<Reclamer> tableView;
	@FXML
	private TableColumn<?, ?> idRec;
	@FXML
	private TableColumn<?, ?> idUser;
	@FXML
	private TableColumn<?, ?> idPlan;
	@FXML
	private TableColumn<?, ?> type;
	@FXML
	private TableColumn<?, ?> descp;
	@FXML
	private TableColumn<?, ?> nivRec;
	@FXML
	private TableColumn<?, ?> etat;
	
	@FXML
	private Button btSup;
	@FXML
	private AnchorPane a;
	@FXML
	private TextField rec;
	@FXML
	private Button modif;
	@FXML
	private ComboBox<String> niv_etat;
	
	ObservableList<String> niveau
			= FXCollections.observableArrayList(
					"non Validée",
					"en cours",
					"Valider"
			);
	@FXML
	private Button acceuil;
	@FXML
	private Button stat;
	@FXML
	private PieChart pieChart;
	@FXML
	private Button statist;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				niv_etat.setItems(niveau);

			try {
				afficher();
			} catch (SQLException ex) {
				Logger.getLogger(GestionReclamController.class.getName()).log(Level.SEVERE, null, ex);
			}

	}	
	public void afficher() throws SQLException {
		System.out.println("wsel");
		idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
		idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
		idPlan.setCellValueFactory(new PropertyValueFactory<>("idPlan"));
		etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		nivRec.setCellValueFactory(new PropertyValueFactory<>("nivRec"));
		descp.setCellValueFactory(new PropertyValueFactory<>("description"));



		ObservableList<Reclamer> ol = FXCollections.observableArrayList(GestionReclamation.DisplayAll());
		System.out.println(ol);
		tableView.setItems(ol);
	}


	@FXML
	private void ClickSup(ActionEvent event) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation suppression");
		alert.setHeaderText("voulez vous effacer cette image?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String e = rec.getText();
			int r = Integer.parseInt(e);
			GestionReclamation s = new GestionReclamation();
			s.DeleteRec(r);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Delete succes!");
			alert.show();
			afficher();
		}
	}

	@FXML
	private void Cliked(MouseEvent event) {
			 GestionReclamation g1 = new GestionReclamation();
		//List<String> Liste = g1.DisplayImageFromDB();
		
		Reclamer g = (Reclamer) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
		String ide = Integer.toString(g.getIdRec());
		rec.setText(ide);
		
		niv_etat.setValue(g.getEtat());
		String s= g.getEtat();
		
		
		
      
	}

	@FXML
	private void modifActionEtat(ActionEvent event) throws SQLException {
		
		
        GestionReclamation g = new GestionReclamation();
		
		String A = rec.getText();
		int b = Integer.parseInt(A);
		
		String e = niv_etat.getValue();
        Reclamer p = new Reclamer(e);
		
        g.Modifer(b,p);
        afficher();
	 }

	@FXML
	private void modifAction(MouseEvent event) {
	}

	@FXML
	private void acceuilAction(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminInterface.fxml"));
        Parent root = loader.load();
        acceuil.getScene().setRoot(root);
	}

	@FXML
	private void statAction(ActionEvent event) {
		GestionReclamation m = new GestionReclamation();
		int x = 0;
		int y = 0;
		int z = 0;

		try {
			x = m.returnEtat();
			y = m.returnEtat1();
			z = m.returnEtat2();
		} catch (SQLException ex) {
			System.out.println(ex);		}
		ObservableList<PieChart.Data> pieChartDatas = FXCollections.observableArrayList(
				new PieChart.Data("Nombre de reclamation en cours " + "(" + x + ")", x),
				new PieChart.Data("Nombre de reclamation non Validée " + "(" + y + ")", y),
				new PieChart.Data("Nombre de reclamation Validée " + "(" + z + ")", z)
		);
		pieChart.setData(pieChartDatas);
	}
	
	@FXML
	private void statistAction(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Statistique.fxml"));
        Parent root = loader.load();
		Stage s = new Stage();
		Scene scene = new Scene(root);
		s.setScene(scene);
		s.show();
	}
	}

	
	


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import GUI.Upload;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import entité.Proprietaire;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.GestionProprietaire;

/**
 *
 * @author Ali
 */
public class inscritProprietaireController implements MapComponentInitializedListener {

	ObservableList<String> tfCategorieList = FXCollections.observableArrayList("Hôtel", "Café", "Bar", "Restaurant");

	private Label label;
	@FXML
	private TextField tfPseudo;
	@FXML
	private TextField tfMail;
	@FXML
	private PasswordField tfPwd;
	@FXML
	private TextField tfNomPlan;
	@FXML
	private TextField tfAdresse;
	@FXML
	private TextField tfTel;
	@FXML
	private TextField tfDesc;
	@FXML
	private ChoiceBox tfCategorie;

	@FXML
	private Button btnSinscrire;
	@FXML
	private Button retourbtn;
	@FXML
	private ImageView pdp;
	@FXML
	private Button btnU;
	@FXML
	private TextField txtpath;

	@FXML
	private GoogleMapView map;

	private GeocodingService geocodingService;
	private MapOptions mapOptions;
	private GoogleMap map2;

	@FXML
	private Label lon;
	@FXML
	private Label lat;
	@FXML
	private TextField tfVille;
	@FXML
	private TextField tfCodeP;
	@FXML
	private PasswordField tfPwdConfirme;


	/*private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
	public void initialize() {
		// TODO
		tfCategorie.setValue("Hôtel");
		tfCategorie.setItems(tfCategorieList);
		map.addMapInializedListener(this);
	}

	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(36.798335, 10.211310))
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.zoom(13);
		map2 = map.createMap(mapOptions);
		MarkerOptions markerOptions = new MarkerOptions();
		map2.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
			map2.clearMarkers();
			LatLong latLong = event.getLatLong();
			lat.setText(String.valueOf(latLong.getLatitude()));
			lon.setText(String.valueOf(latLong.getLongitude()));
			markerOptions.position(new LatLong(latLong.getLatitude(), latLong.getLongitude()))
					.visible(Boolean.TRUE)
					.title("Ma Position");
			Marker marker = new Marker(markerOptions);
			map2.addMarker(marker);
		});

	}

	@FXML
	private void addMethod() throws SQLException{
		int codeP = 0;
		double latitude =0;
		double longitude=0;
		String telp=null;
		String pseudop = tfPseudo.getText();
		String mailp = tfMail.getText();
		String passwordp = tfPwd.getText();
		String nomp = tfNomPlan.getText();
		String adressep = tfAdresse.getText();
		String ville = tfVille.getText();
		if (tfCodeP.getText().length() != 0) {
			codeP = Integer.valueOf(tfCodeP.getText());
			
		} else {
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Erreur");
			alert2.setContentText("saisir code postale");
			alert2.setHeaderText(null);
			alert2.show();
		}
		for (int i = 0; i < tfTel.getText().length(); ++i) {
				if ((int) tfTel.getText().charAt(i) < (int) '0' || (int) tfTel.getText().charAt(i) > (int) '9' && tfTel.getText().length() != 8) {
					Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Erreur");
			alert2.setContentText("saisir Telephone");
			alert2.setHeaderText(null);
			alert2.show();
				}else{
					telp = "+216"+tfTel.getText();
				}
		}
		String descp = tfDesc.getText();
		String photoP = txtpath.getText();
		String categp = (String) tfCategorie.getValue();
		if (lat.getText().equals("Label") || lon.getText().equals("Label"))
		{
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Erreur");
			alert2.setContentText("Pointez votre position de Plan !");
			alert2.setHeaderText(null);
			alert2.show();
		}else{
			 latitude = Double.parseDouble(lat.getText());
		longitude = Double.parseDouble(lon.getText());
		}
		
		Proprietaire p = new Proprietaire(pseudop, mailp, passwordp, photoP, nomp, telp, descp, categp, "Propriétaire", latitude, longitude, adressep, ville, codeP, false);
		GestionProprietaire sp = new GestionProprietaire();
		if (tfPwd.getText().length() == 0 || txtpath.getText().length() == 0  ) {
			
					Alert alert2 = new Alert(Alert.AlertType.WARNING);
					alert2.setTitle("Erreur");
					alert2.setContentText("saisir un mot de passe");
					alert2.setHeaderText(null);
					alert2.show();
				
			
		} else {
			try {
				sp.ajouterProprietaire(p);
				System.out.println("Propriétaire ajouté avec succès !! ");
			} catch (SQLException ex) {
				Alert alert2 = new Alert(Alert.AlertType.WARNING);
				alert2.setTitle("Erreur");
				alert2.setContentText("Inscription impossible");
				alert2.setHeaderText(null);
				alert2.show();
			}
		}
		/*MarkerOptions markerOptions = new MarkerOptions();
		map2.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
			map2.clearMarkers();
			LatLong latLong = event.getLatLong();
			
			//a.setLatitude(latLong.getLatitude());
			// a.setLongiturde(latLong.getLongitude());
			markerOptions.position(new LatLong(latLong.getLatitude(), latLong.getLongitude()))
					.visible(Boolean.TRUE)
					.title("Ma Position");
			Marker marker = new Marker(markerOptions);
			map2.addMarker(marker);
			System.out.println("LAT"+a.getLatitude());
		});*/
	}

	@FXML
	private void btnRetour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
		Parent root = loader.load();
		retourbtn.getScene().setRoot(root);
	}

	@FXML
	private void btnUpload(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("file", "*.jpg", "*.png"));
		File selectedfile = fc.showOpenDialog(null);
		if (selectedfile != null) {
			Upload u = new Upload();
			u.upload(selectedfile);
			txtpath.setText(u.upload(selectedfile));
			Image image = new Image(new FileInputStream(selectedfile));
			pdp.setImage(image);
		}

	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import entité.Proprietaire;
import entité.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import service.GestionProprietaire;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class CategorieSearchController implements MapComponentInitializedListener, Initializable {

	@FXML
	private GoogleMapView map;

	private GeocodingService geocodingService;
	private MapOptions mapOptions;
	private GoogleMap map2;
	@FXML
	private Button retour;
	@FXML
	private TableView<Proprietaire> tableCateg;
	@FXML
	private TableColumn<Proprietaire, ImageView> photo;
	@FXML
	private TableColumn<Proprietaire, String> nom;

	private String categorie;
	@FXML
	private Label label;

	public static String p;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		map.addMapInializedListener(this);
		try {
			recherchePlan();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(36.798335, 10.211310))
				.mapType(MapTypeIdEnum.ROADMAP)
				.mapTypeControl(false)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.minZoom(8)
				.zoom(13);
		map2 = map.createMap(mapOptions);

	}

	@FXML
	public void recherchePlan() throws SQLException {
		GestionProprietaire g = new GestionProprietaire();
		ObservableList<Proprietaire> ListP = FXCollections.observableList(g.rechercheCategorie(p));
		photo.setCellValueFactory(new PropertyValueFactory<>("image"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nomPlan"));
		tableCateg.setItems(ListP);

		label.setText(p + "s");
	}

	@FXML
	private void retourIndex() throws IOException {
		if(Utilisateur.pseudo==null){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
		p = null;
		}else{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
		p = null;
		}
	}

	public void selectLieu() {
		Proprietaire prop = tableCateg.getSelectionModel().getSelectedItem();
		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(prop.getLatitude(), prop.getLongiturde()))
				.mapType(MapTypeIdEnum.ROADMAP)
				.mapTypeControl(false)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.minZoom(8)
				.zoom(18);
		map2 = map.createMap(mapOptions);
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLong(prop.getLatitude(), prop.getLongiturde()))
				.visible(Boolean.TRUE)
				.title(prop.getNomPlan());
		Marker marker = new Marker(markerOptions);
		map2.addMarker(marker);
	}
}

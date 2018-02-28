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
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.GestionProprietaire;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class accueilController implements MapComponentInitializedListener{
    @FXML
    private Label login;
    @FXML
    private TextField tfRecherche;
    @FXML
    private SplitPane paneRech;
    @FXML
    private TableView<Proprietaire> listRecherche;
    @FXML
    private AnchorPane déconnectlien;
    @FXML
    private TableColumn<Proprietaire, ImageView> photo;
    @FXML
    private TableColumn<Proprietaire, String> nom;
    
    @FXML
    private GoogleMapView map;
    private GeocodingService geocodingService ;
    private MapOptions mapOptions;
    private GoogleMap map2;
	@FXML
	private Button btnVsiter;
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
       map.addMapInializedListener(this);
		login.setText(Utilisateur.pseudo);
		//afficherPlanMap();
    }    
    
    
    @FXML
    public void affichepane()
    {
         paneRech.setVisible(true);
    }
    @FXML
    public void invisiblepane()
    {
         paneRech.setVisible(false);
    }
    @FXML
    public void recherchePlan() throws SQLException
    {
        ObservableList<Proprietaire> ListP ;
        GestionProprietaire g = new GestionProprietaire();
        ListP = FXCollections.observableArrayList(g.rechercheProptietaire(tfRecherche.getText())) ;
        photo.setCellValueFactory(new PropertyValueFactory<>("image"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomPlan"));
        listRecherche.setItems(ListP);
    }
    
    @FXML
    public void deconnection () throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
        Parent root = loader.load();
        déconnectlien.getScene().setRoot(root);
    }
    
    @FXML
    public void profil() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ProfilProprietaire.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }

    @Override
    public void mapInitialized() 
    { GestionProprietaire gp = new GestionProprietaire();
		ObservableList<Proprietaire> ListP ;
		
		try {
			ListP = FXCollections.observableArrayList(gp.afficherProp(Utilisateur.pseudo));
			for (Proprietaire p : ListP) {
			geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(p.getLatitude(),p.getLongiturde()))
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(true)
				.zoom(17);
		map2 = map.createMap(mapOptions);
		
	
	MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.position(new LatLong(p.getLatitude(),p.getLongiturde()))
                .visible(Boolean.TRUE)
                .title(p.getNomPlan());
    Marker marker = new Marker( markerOptions );
    map2.addMarker(marker);
		 
		}
		} catch (SQLException ex) {
			System.out.println(ex);
		}     
    }
	
	/*public void afficherPlanMap(){
		if(listRecherche.getSelectionModel().getSelectedItem().equals(true)){
			double lat = listRecherche.getSelectionModel().getSelectedItem().getLat();
			double lon = listRecherche.getSelectionModel().getSelectedItem().getLon();
			geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(lat,lon));
		map2 = map.createMap(mapOptions);
		}
	}*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Abonner;
import entité.Commentaire;
import entité.Proprietaire;
import entité.Rate;
import entité.Utilisateur;
import entité.Visiteur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import service.GestionAbonnement;
import service.GestionCommentaire;
import service.GestionRate;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ConsulterController implements Initializable {

	@FXML
	private Label mail;

	@FXML
	private Button retour;
	@FXML
	private ImageView pdp;
	@FXML
	private Label nomP;
	@FXML
	private Label adresse;
	@FXML
	private Label desc;
	@FXML
	private Label categorie;
	@FXML
	private Label tel;
	@FXML
	private Button reserver;
	@FXML
	private Button btnAbn;
	private Label etatAbon;
	@FXML
	private Button btnevent;
	@FXML
	private Button btnpromo;
	@FXML
	private Button btnDemande;
	@FXML
	private Button rec;
	
	public static String nomPlan;
	public static Proprietaire prop;
	@FXML
	private TextArea textcommentaire;
	@FXML
	private Button commentez;
	@FXML
    private TableView<Commentaire> tablecommentaire;
    @FXML
    private TableColumn<Commentaire, String> commentaire;
    @FXML
    private TableColumn<Commentaire, String> dateCommentaire;
	@FXML
	private Button supprimer;
	ObservableList<Commentaire> data;
    List<Commentaire> listCommentaire;
	@FXML
	private Rating rating;
	/**
	 * Initializes the controller class.
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.InitialiserProfil();
			AfficherData();
		} catch (SQLException ex) {
			Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void InitialiserProfil() throws SQLException {
		if (prop == null) {
			System.out.println("prooooop null");
		} else {
			System.out.println("prop =");
			System.out.println(prop);
			//     Proprietaire pr = new Proprietaire (prop.getEmail(),prop.getPhotoProfil(),prop.getNomPlan(),prop.getAdresse(),prop.getTelephone(),prop.getDescription(),prop.getCategorie());
			//     public Proprietaire(String email, String photoProfil, String nomPlan, String adresse, String telephone, String description, String categorie) {
			nomP.setText(prop.getNomPlan());
			nomPlan = nomP.getText();
			System.out.println("");
			mail.setText(prop.getEmail());
			System.out.println(" nom no, " + prop.getPseudo());
			categorie.setText(prop.getCategorie());
			desc.setText(prop.getDescription());
			
			adresse.setText(prop.getAdresse());
			tel.setText(prop.getTelephone());
			String pathimage = "file:///" + prop.getPhotoProfil();
			Image image = new Image(pathimage);
			pdp.setImage(image);
			pdp.setFitHeight(150);
			pdp.setFitWidth(200);
			
			GestionAbonnement c = new GestionAbonnement();
			int vefP =c.VerifIdPlan(c.obtenirIdPlan(nomPlan));
			int vefU = c.VerifIdUser(c.obtenirIdUser(Utilisateur.pseudo));
			if (vefP!=0 && vefU!=0) {
				if (vefP==vefU) {
					btnAbn.setText("Abonnée");
				}
			}
		}

		//Proprietaire p =this.recupererP(prop.getNomPlan());
		/* nomP.setText(prop.getNomPlan());
            mail.setText(prop.getEmail());
            categorie.setText(prop.getCategorie());
            desc.setText(prop.getDescription());          
           etatAbon.setText("ETAT ABONENEMENT ");
            tel.setText(prop.getTelephone());
            String pathimage = "file:///" + prop.getPhotoProfil();
            Image image = new Image(pathimage);
            pdp.setImage(image);
            pdp.setFitHeight(300);
            pdp.setFitWidth(300);*/
	}

	/*  public Proprietaire recupererP (String nom) throws SQLException {
        GestionProprietaire g = new GestionProprietaire();
    Proprietaire prop = new Proprietaire () ; 
    prop= g.rechercheProptietaireParnom(nom);
    return prop ;
    }*/
	@FXML
	private void btnRetour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
	}

	@FXML
	private void reserverPlan(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Reserver.fxml"));
		Parent root = loader.load();
		retour.getScene().setRoot(root);
		ReserverController.p = prop;
	}

	

	@FXML
	private void consulterEventsProp(ActionEvent event) throws IOException {
		System.out.println(prop.getId());
		EventPropConsult_VisiteurController.idProp = ConsulterController.prop.getId();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/EventPropConsult_Visiteur.fxml"));
		Parent root = loader.load();
		btnevent.getScene().setRoot(root);
		/*Stage s = new Stage();
		Scene scene = new Scene(root);
		s.setScene(scene);
		s.show();*/
	}

	@FXML
	private void Abonner(ActionEvent event) throws SQLException {
		GestionAbonnement m = new GestionAbonnement();
		
		String current_user = Utilisateur.pseudo;
		int currentUser = m.obtenirIdUser(current_user);
		int currentPlan = m.obtenirIdPlan(nomPlan);
		Abonner r = new Abonner(currentUser, currentPlan);
		if (btnAbn.getText().equals("S'abonner")) {
			
			btnAbn.setText("Abonnée");
			
			m.insererAb(r);
		} else {
			btnAbn.setText("S'abonner");
			//String s=nomP.getText();
			m.delete(currentUser,currentPlan);
		}
	}
	
	@FXML
	private void recAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLreclamer.fxml"));
		Parent root = loader.load();
		rec.getScene().setRoot(root);
		
	}
	
    @FXML
    private void ajoutCommentaire(ActionEvent event) throws SQLException {
        GestionVisiteur gv = new GestionVisiteur();
        ArrayList<Visiteur> listv;
        listv = gv.afficherVisiteur(Utilisateur.pseudo);

        System.out.println("aa");
        ConsulterController cc = new ConsulterController();
        Commentaire c = null;

        GestionCommentaire sp = new GestionCommentaire();

        //    Calendar calendar = Calendar.getInstance();
        // java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
        try {
            for (Visiteur visiteur : listv) {
                int idV = visiteur.getId();
                int idP = prop.getId();
                Commentaire r = new Commentaire(textcommentaire.getText(), Date.valueOf(LocalDate.now()), idV, idP);

                System.out.println("houni houni rani houni");

                sp.ajouterCommentaire(r);

                System.out.println("commentaire ajouté avec succès !! ");
            }

        } catch (SQLException ex) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("commentaire impossible!!!!!!");
            alert2.setHeaderText(null);
            alert2.show();
        }

    }

    private void setCellValue() {

        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        dateCommentaire.setCellValueFactory(new PropertyValueFactory<>("dateComment"));
        tablecommentaire.setEditable(true);
        commentaire.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void AfficherData() {
        try {

            GestionCommentaire avis = new GestionCommentaire();
            listCommentaire = new ArrayList<>();
            listCommentaire = (List<Commentaire>) avis.affichercommentaire();
            data = FXCollections.observableArrayList(listCommentaire);
            tablecommentaire.setItems(data);
            setCellValue();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @FXML
    private void changerCommentaire(TableColumn.CellEditEvent edditedCell) {
        GestionCommentaire ct = new GestionCommentaire();
        String a = null;
        Commentaire RecSelect = tablecommentaire.getSelectionModel().getSelectedItem();
        RecSelect.setCommentaire(edditedCell.getNewValue().toString());
        Commentaire rs = new Commentaire(edditedCell.getNewValue().toString(), RecSelect.getIdAvis());
        ct.updateContenu(rs);
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) {
        GestionCommentaire ct = new GestionCommentaire();

        Commentaire c = tablecommentaire.getSelectionModel().getSelectedItem();
        int cmd = c.getIdAvis();
        ct.supprimerCommentaire(cmd);
        tablecommentaire.getItems().remove(c);
        System.out.println(" supprimer");
        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Erreur");
        alert2.setContentText("La réservations a ete supprimé  avec succès !!");
        alert2.setHeaderText(null);
        alert2.show();
    }

    private void ajoutrating(MouseEvent event) throws SQLException {
        GestionVisiteur gv = new GestionVisiteur();
        ArrayList<Visiteur> listv;
        listv = gv.afficherVisiteur(Utilisateur.pseudo);

        System.out.println("aa");
        ConsulterController cc = new ConsulterController();
        Rate c = null;

        GestionRate sp = new GestionRate();

        //    Calendar calendar = Calendar.getInstance();
        // java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
        try {
            for (Visiteur visiteur : listv) {
                int idV = visiteur.getId();
                int idP = prop.getId();
                Rate r = new Rate(idV, idP, rating.getRating());

                System.out.println("houni houni rani houni");

                sp.ajouterRate(r);

                System.out.println("Rate ajouté avec succès !! ");
            }

        } catch (SQLException ex) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Rate impossible!!!!!!");
            alert2.setHeaderText(null);
            alert2.show();
        }
    }
	@FXML
	private void demanderEvent(ActionEvent event) throws IOException {
            System.out.println(prop.getId());
		CréationEventController.idProp = ConsulterController.prop.getId();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CréationEventPane.fxml"));
		Parent root = loader.load();
		btnevent.getScene().setRoot(root);
	}

	@FXML
	private void consulterPromoProp(ActionEvent event) {
	}
}

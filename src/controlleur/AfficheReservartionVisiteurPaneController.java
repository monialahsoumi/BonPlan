/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.GestionReservation;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AfficheReservartionVisiteurPaneController implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nbrPlace;
    @FXML
    private TableColumn<?, ?> dateReservation;
    @FXML
    private TableColumn<?, ?> telephone;
    @FXML
    private TableColumn<?, ?> heure;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private TableColumn<?, ?> nomPlan;
  List<Reservation> listReservationVisiteur;

    ObservableList<Reservation> data;
    @FXML
    private TableView<Reservation> reservationTabVisiteur;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private Button supprimer;
    @FXML
    private Button ModifierReservation;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                AfficherData();

    }    
 private void setCellValue() {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace"));
        dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        nomPlan.setCellValueFactory(new PropertyValueFactory<>("nomPlan"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
      
    }
    @FXML
    private void selectIdReservation(MouseEvent event) {
    }
       private void AfficherData() {
        try {
            System.out.println("trytrytrytrytry");

            GestionReservation geRes = new GestionReservation();
            listReservationVisiteur = new ArrayList<>();
            listReservationVisiteur = (List<Reservation>) geRes.afficherReservationVisiteur();
            data = FXCollections.observableArrayList(listReservationVisiteur);
            reservationTabVisiteur.setItems(data);
            setCellValue();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
              GestionReservation ct = new GestionReservation();

        Reservation c = reservationTabVisiteur.getSelectionModel().getSelectedItem();
        int cmd  = c.getId();
        ct.supprimerReservationParVisiteur(cmd);
        reservationTabVisiteur.getItems().remove(c);
        System.out.println(" supprimer");
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("La réservations a ete supprimé  avec succès !!");
            alert2.setHeaderText(null);
            alert2.show();
    }

    @FXML
    private void ModifierMaReservation(ActionEvent event) {
                     try
        {
       Reservation g = reservationTabVisiteur.getSelectionModel().getSelectedItem();
      int id = g.getId();
      //  String etat = g.getEtat();
         FXMLLoader loader = new FXMLLoader (getClass().getResource("/GUI/ModifiermaReservation.fxml"));
        Parent root =loader.load();
            ModifiermaReservationController ms = loader.getController();
            ms.RecupId(id);
        Scene scene = new Scene(root);
 Stage  stage = new Stage();
        stage.setScene(scene);
        stage.show();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void retourProfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }
}

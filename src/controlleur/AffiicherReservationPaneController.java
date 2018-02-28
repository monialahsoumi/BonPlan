/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Proprietaire;
import entité.Reservation;
import entité.Utilisateur;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.GestionProprietaire;
import service.GestionReservation;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AffiicherReservationPaneController implements Initializable {

    @FXML
    private TableColumn<Reservation, Integer> id;
    @FXML
    private TableColumn<Reservation, String> nbrPlace;
    @FXML
    private TableColumn<Reservation, String> dateReservation;
    @FXML
    private TableColumn<Reservation, String> telephone;
    @FXML
    private TableColumn<Reservation, String> heure;
    @FXML
    private TableColumn<Reservation, String> etat;

    List<Reservation> listReservation;

    ObservableList<Reservation> data;
    private Button modif;

    @FXML
    private TableView<Reservation> reservationTab;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    private void AfficherData() {
        try {
            GestionProprietaire gp = new GestionProprietaire();
            int i = gp.afficherIdProp(Utilisateur.pseudo);
            //  Proprietaire p = new Proprietaire(i);

            System.out.println("trytrytrytrytry");

            GestionReservation geEvent = new GestionReservation();
            listReservation = new ArrayList<>();
            listReservation = (List<Reservation>) geEvent.afficherReservation(i);
            data = FXCollections.observableArrayList(listReservation);
            reservationTab.setItems(data);
            setCellValue();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    public void Acceder(Reservation g) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifReservationPane.fxml"));
        Parent root = loader.load();
        modif.getScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherData();
    }

    private void setCellValue() throws SQLException {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace"));
        dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    @FXML
    private void selectIdReservation(MouseEvent event) throws IOException {
        try {
            Reservation g = reservationTab.getSelectionModel().getSelectedItem();
            int id = g.getId();
			System.out.println(id);
            String etat = g.getEtat();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifReservationPane.fxml"));
            Parent root = loader.load();
            ModifReservationPaneController.id=id;
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void retourProfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ProfilProprietaire.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }
}

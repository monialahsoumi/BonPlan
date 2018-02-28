/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Reservation;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.GestionReservation;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class ModifiermaReservationController implements Initializable {

    @FXML
    private TextField Nbrplace;
    @FXML
    private TextField telephone;
    @FXML
    private DatePicker dateReservation;
    @FXML
    private Button btnModReservtion;
    @FXML
    private Button btnAnnuler;
    @FXML
    private ComboBox<String> comboHeure;
    @FXML
    private TextField idText;
    ObservableList<String> comboHeureList = FXCollections.observableArrayList("1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboHeure.setValue("1:00");
        comboHeure.setItems(comboHeureList);
    }

    @FXML
    private void ModifierReservation(ActionEvent event) throws IOException {
        System.out.println("aa");
        System.out.println("houni houni rani houni");
        GestionReservation sp = new GestionReservation();

        Reservation r = new Reservation(Integer.parseInt(Nbrplace.getText()), Integer.parseInt(telephone.getText()), (String) comboHeure.getValue());

        r.setDateReservation(java.sql.Date.valueOf(dateReservation.getValue()));
        r.setId(Integer.parseInt(idText.getText()));
        //       Reservation r = reservationTabVisiteur.getSelectionModel().getSelectedItem();

        sp.ModifierMaReservation(r);
        System.out.println("Réservation Modifier avec succès !! ");

        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Erreur");
        alert2.setContentText("Réservation Modifier avec succès !!");
        alert2.setHeaderText(null);
        alert2.show();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AfficheReservartionVisiteurPane.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize(ActionEvent event) {
    }

    public void RecupId(int id) {
        idText.setText(Integer.toString(id));
    }
}

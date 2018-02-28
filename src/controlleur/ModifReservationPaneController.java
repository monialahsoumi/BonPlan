/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Reservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.GestionReservation;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class ModifReservationPaneController implements Initializable {

    ObservableList<String> comboEtatList = FXCollections.observableArrayList("en cours", "annulé", "accepté");
    @FXML
    private Button btnValider;
    @FXML
    private ComboBox etat;
    static Reservation res;
    @FXML
    private TextArea Commentaire;
    private TextField idReservation;
	@FXML
	private TextField idText;
	
	public static int id;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        etat.setValue("en cours");
        etat.setItems(comboEtatList);
    }

    void getReservation(Reservation g) {
        System.out.println(g);
        GestionReservation R = new GestionReservation();
        g = R.RechercheReservation(g.getId());

        //  adresseDestinationTxt.setText(g.getLieu_dest());
    }

    @FXML
    private void ValiderReservation(ActionEvent event) throws SQLException {

        System.out.println("aa");
        System.out.println("houni houni rani houni");
        System.out.println(etat.getItems().get(0).toString());
        GestionReservation gr = new GestionReservation();
        Reservation rs = new Reservation(etat.getSelectionModel().getSelectedItem().toString(), Commentaire.getText(), id/*Integer.parseInt(idReservation.getText())*/);
      

            gr.TraiterReservation(rs);
            System.out.println("Réservation traitée avec succès !! ");
           
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Réservation traitée avec succès !!");
            alert2.setHeaderText(null);
            alert2.show();
        }
    
    public void recupId(int id){
       idReservation.setText(Integer.toString(id));
    }
    }


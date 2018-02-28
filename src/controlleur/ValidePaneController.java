/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import GUI.Sms;
import entité.Proprietaire;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import service.GestionValidite;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ValidePaneController implements Initializable {

    @FXML
    private TableView<Proprietaire> tab;
    @FXML
    private TableColumn< Proprietaire, String> telephone;
    @FXML
    private TableColumn<Proprietaire, String> pseudo;
    @FXML
    private TableColumn<Proprietaire, String> nomPlan;
    @FXML
    private TableColumn<Proprietaire, String> adresse;

    @FXML
    private TableColumn<Proprietaire, String> mail;
    @FXML
    private Button afficher;

    @FXML
    private TableColumn<Proprietaire, String> categorie;
    @FXML
    private Button valider;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AfficherListe(ActionEvent event) throws SQLException {
        try {
            GestionValidite sv = new GestionValidite();
            ArrayList<Proprietaire> lista = new ArrayList<>();
            lista = sv.afficherListeP();
            pseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
            nomPlan.setCellValueFactory(new PropertyValueFactory<>("nomPlan"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            mail.setCellValueFactory(new PropertyValueFactory<>("email"));
            categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            ObservableList<Proprietaire> lista2 = FXCollections.observableArrayList(lista);

            tab.setItems(lista2);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        /**
         *
         */
    }

    @FXML
    private void Valider(ActionEvent event) throws SQLException {
        GestionValidite gp = new GestionValidite();
        Proprietaire p = tab.getSelectionModel().getSelectedItem();
		String tel="+21655741403";
        String ps = p.getPseudo();
        p.setValidité(true);
        gp.valider(ps, p);
		Sms s=new Sms();
		s.sendSms(tel);
         JOptionPane.showMessageDialog(null, "valider successfully");
         this.AfficherListe(event);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminInterface.fxml"));
            Parent root = loader.load();
            btnRetour.getScene().setRoot(root);
    }

}

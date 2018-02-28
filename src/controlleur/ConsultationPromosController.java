/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import service.GestionPromo;
import entité.Promotion;
import controlleur.PagePromoController;
import entité.Utilisateur;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Meyssa
 */
public class ConsultationPromosController implements Initializable {

    private final static String ALERT_PROMO_SUPP = "aSupprimer n'est pas valid";
    private final static String ALERT_HEADER = "Alerte";

    @FXML
    private TableView<Promotion> PromosTab;
    @FXML
    private TableColumn<Promotion, ImageView> imagePromo;
    @FXML
    private TableColumn<Promotion, String> descPromo;
    @FXML
    private TableColumn<Promotion, Date> deb;
    @FXML
    private TableColumn<Promotion, Date> fin;

    List<Promotion> listPromos;

    ObservableList<Promotion> data;
    @FXML
    private Button btnAffichePromo;
    static String pseudo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AfficherPromo();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationPromosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setCellValue() {

        System.out.println("11111111111111111111111");
        // imagePromo.setCellValueFactory(new PropertyValueFactory<>("image"));
        descPromo.setCellValueFactory(new PropertyValueFactory<>("description"));
        deb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        fin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

    }

    private void AfficherPromo() throws SQLException {

        System.out.println("trytrytrytrytry");

        GestionPromo gePromo = new GestionPromo();
        listPromos = new ArrayList<>();
        listPromos = (List<Promotion>) gePromo.AfficherPromotion();
        data = FXCollections.observableArrayList(listPromos);
        PromosTab.setItems(data);
        setCellValue();

    }

    private void deleteSelected(ActionEvent event) throws SQLException {
        GestionPromo gePromo = new GestionPromo();
        Promotion aSuprrimer = PromosTab.getSelectionModel().getSelectedItem();

        if (aSuprrimer == null) {
            Alert finPromoAlert = new Alert(Alert.AlertType.WARNING);
            finPromoAlert.setTitle(ALERT_HEADER);
            finPromoAlert.setContentText(ALERT_PROMO_SUPP);
            finPromoAlert.setHeaderText(null);
            finPromoAlert.show();
        } else {
            System.out.println("Valid");
            gePromo.supprimerPromotion(aSuprrimer.getIdPromo());
            PromosTab.getItems().removeAll(PromosTab.getSelectionModel().getSelectedItem());

        }
    }

    @FXML
    private void afficherSelectedItem(ActionEvent event) throws IOException {

        Promotion aAfficher = PromosTab.getSelectionModel().getSelectedItem();

        PagePromoController.selectedPromo = aAfficher;

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PagePromoPane.fxml"));
        Parent root = loader.load();
        btnAffichePromo.getScene().setRoot(root);
    }
}

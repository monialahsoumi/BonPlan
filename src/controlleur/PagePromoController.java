/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import entité.Promotion;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Meyssa
 */
public class PagePromoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button retour;
    @FXML
    private Button modif;
    @FXML
    private Label promoDesc;
    @FXML
    private Label promoDeb;
    @FXML
    private Label promoFin;
    @FXML
    private ImageView promoImage;

    static Promotion selectedPromo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        promoDesc.setText(selectedPromo.getDescription());
        promoDeb.setText(selectedPromo.getDateDebut().toString());
        promoFin.setText(selectedPromo.getDateFin().toString());

        Image image = new Image(selectedPromo.getUrlPromo());
        promoImage.setImage(image);
        promoImage.setFitHeight(172);
        promoImage.setFitWidth(150);

    }

    @FXML
    private void btnRetour(ActionEvent event) {
    }

    @FXML
    private void modificationEvent(ActionEvent event) throws IOException {
        
        ModificationPromoContoller.aModifier= selectedPromo;
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModificationPromoPane.fxml"));
       PagePromoController pc = loader.getController();
           Parent root = loader.load();
            Stage s =  new Stage();
            Scene scene = new Scene(root);
            s.setScene(scene);
            s.show();  
    }
}

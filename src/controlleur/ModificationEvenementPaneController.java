/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.GestionEvenement;

/**
 *
 * @author Meyssa
 */
public class ModificationEvenementPaneController implements Initializable{

    @FXML
    private TextArea descEV;
    @FXML
    private TextField nomEv;
    @FXML
    private Button retour;
    @FXML
    private TextField places;
    @FXML
    private DatePicker deb;
    @FXML
    private DatePicker fin;
    public static Evenement event_a_modifier;
    @FXML
    private Button btnmodifEvent;

     @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (event_a_modifier==null){return;}
      nomEv.setText(event_a_modifier.getNomEv());
      descEV.setText(event_a_modifier.getDescEv());
      places.setText(String.valueOf(event_a_modifier.getNbPlace()));
      deb.setValue(event_a_modifier.getDebutEv().toLocalDate());
      fin.setValue(event_a_modifier.getFinEv().toLocalDate());
    }

    @FXML
    private void modifierEvenement(ActionEvent event) throws IOException {
        	GestionEvenement geEvent =new GestionEvenement(); 
		event_a_modifier.setNomEv(nomEv.getText()); 
		event_a_modifier.setDescEv(descEV.getText());  
		event_a_modifier.setNbPlace(Integer.parseInt(places.getText()));
		event_a_modifier.setDebutEv(java.sql.Date.valueOf(deb.getValue())); 
		event_a_modifier.setDebutEv(java.sql.Date.valueOf(fin.getValue())); 
		      try {
             geEvent.modifierEvent(event_a_modifier);
        } catch (Exception e) {
                          System.out.println(event_a_modifier);
        }
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/pageEvenement.fxml"));
            pageEvenementController pageEVENT = loader.getController();
            Parent root = loader.load();
            Stage s =  new Stage();
            Scene scene = new Scene(root);
            s.setScene(scene);
            s.show(); 
             
	 }
    }


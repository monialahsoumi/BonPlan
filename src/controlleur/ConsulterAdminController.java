/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entité.Utilisateur;
import entité.Visiteur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import service.GestionAdmin;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ConsulterAdminController implements Initializable {

    @FXML
    private Label pseudo;
    @FXML
    private Label pass;
    @FXML
    private Button modif;
    @FXML
    private PasswordField tfpass;
    @FXML
    private TextField tfpseudo;
    @FXML
    private Label nvpseudo;
    @FXML
    private Label nvpass;
    @FXML
    private Button btnenr;

    /**
     * Initializes the controller class.
     */
    Utilisateur u= new Utilisateur ();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nvpass.setVisible(false);
        nvpseudo.setVisible(false);
        tfpass.setVisible(false);
        tfpseudo.setVisible(false);
        btnenr.setVisible(false);
        
         
        GestionAdmin gp = new GestionAdmin();
        try {
            u=gp.recupererAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
         
        
         pseudo.setText(u.getPseudo());
         pass.setText(u.getPassword());
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException, IOException {
        
        btnenr.setVisible(true);
        nvpass.setVisible(true);
        nvpseudo.setVisible(true);
        tfpass.setVisible(true);
        tfpseudo.setVisible(true);
         GestionAdmin gp = new GestionAdmin();
         
         u=gp.recupererAdmin();
         System.out.println("password = " +u.getPassword());
          tfpass.setText(u.getPassword());
        tfpseudo.setText(u.getPseudo());
         
        
        }
        
    


    @FXML
    private void Enregistrer(ActionEvent event) throws SQLException, IOException {
        GestionAdmin gp = new GestionAdmin();
          
        
        String ps = tfpseudo.getText();
        String pwd = tfpass.getText();
         Utilisateur um = new Utilisateur(ps, pwd);
         
         
            u.setPassword(pwd);
            u.setPseudo(ps);
        gp.modifAdmin(um);
        System.out.println(u.getPassword());
             
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Succes");
            alert2.setContentText("Administrateur Modifié avec Succes ");
            alert2.setHeaderText(null);
            alert2.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminInterface.fxml"));
            Parent root = loader.load();
           btnenr.getScene().setRoot(root);
           
        
        
        
            
    }
    
}

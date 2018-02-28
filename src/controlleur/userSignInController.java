/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class userSignInController{

    
    @FXML
    private Button retourbtn;
    @FXML
    private ImageView visit;
    @FXML
    private ImageView plan;

    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
        
    }

   
     @FXML
    private void propInscrit() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/inscritProprietaire.fxml"));
        Parent root = loader.load();
        plan.getScene().setRoot(root);
    }

    @FXML
    private void visiteurInscrit() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/userSignUp.fxml"));
        Parent root = loader.load();
        visit.getScene().setRoot(root);
        
    }
    @FXML
    private void retourIndex() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/index.fxml"));
        Parent root = loader.load();
        retourbtn.getScene().setRoot(root);
        
    }
    
        
          

}

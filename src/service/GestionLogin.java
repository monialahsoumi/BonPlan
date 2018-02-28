/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Proprietaire;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import entité.Utilisateur;

/**
 *
 * @author Ali
 */
public class GestionLogin {

    static DataSource ds = DataSource.getInstance();

    public static Utilisateur recherche(String login, String mdp) {
		 Utilisateur p = new Utilisateur();
		Statement ste;
        try {
            int row = 0;
			ste = ds.getConnection().createStatement();
            String req = "select * from user where pseudo='" + login + "' and password='" + mdp + "'";
            ResultSet res = null;
            try {
                System.out.println(req);
                res = ste.executeQuery(req);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            while (res.next()) {
                row = res.getRow();
                p.setPseudo(res.getString("pseudo"));
                p.setPassword(res.getString("password"));
                p.setRole(res.getString("role"));
            }
            if (row == 1) {
                p.setStatus_login(true);
            } else {
                p.setStatus_login(false);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
		
		/*Statement ste1;
        try {
            ste1 = ds.getConnection().createStatement();
            String req = "SELECT pseudo,password,role FROM USER WHERE PSEUDO='" + login + "'AND PASSWORD ='" + mdp + "'";
            ResultSet rs = ste1.executeQuery(req);
			
            while (rs.next()) {
				p.setPseudo(rs.getString("pseudo"));
				p.setPassword(rs.getString("password"));
				p.setRole(rs.getString("role"));
				
                System.out.println("authentification effectuer avec succès !! ");
               
            }

        } catch (SQLException ex) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre nom d'utilisateur et mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
        }*/
		return p;
    }
    
    public Utilisateur recupererUtilisateur (String pseudo) throws SQLException{
        Utilisateur u  = null ;
        Statement ste = ds.getConnection().createStatement();
        String req="SELECT * FROM user Where pseudo LIKE '"+pseudo;
        ResultSet rs = ste.executeQuery(req);
		
        while(rs.next()){
             u = new Utilisateur("password", "pseudo" ); 
        }
        return u ;
    }
	
    
    
    
    
}

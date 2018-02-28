/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Proprietaire;
import entité.Visiteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Monia
 */
public class GestionProfil {
    
   
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
    public GestionProfil() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public  Visiteur  recupererVisiteur()
    {
        Visiteur v1 = null;
        try
    {
        String query  = "Select * From  `user` where ('username =pseudo )" ;
        Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(query);
              while (rs.next())
      {
        String nomm = rs.getString ("nomVisiteur");
        String  prenomm = rs.getString("prenomVisiteur");
        String  passwordm  = rs.getString("password");
        String  emailm  = rs.getString("emailm");
        
        v1 = new Visiteur(emailm, passwordm, nomm, prenomm);
      }
              rs.close () ; 
    }
        catch (SQLException e) 
            
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
     
    }
       return v1;
    }
    
    public void ModifierVisiteur(Visiteur v ) throws SQLException {
        
		try {
            String req = "UPDATE user "
                    + "SET nomPlan=?, email=?,telephone=?,description=?, categorie=? , password=?  WHERE pseudo=?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, v .getPseudo());
            st.setString(2, v.getEmail());
            st.setString(3, v.getNom());
            st.setString(4, v.getPassword());
			st.setString(5, v.getPrenom());
			
            st.executeUpdate();
            System.out.println("modifié");

        } catch (SQLException ex) {
                System.err.println("ERREUR ");} 
       
    }
    
         public ArrayList<Visiteur> afficherVisiteur(String pseudo) throws SQLException
    {   
        ArrayList<Visiteur> listv = new ArrayList<>();
       Visiteur v ;
         String query = "SELECT * FROM `user` WHERE role LIKE 'visiteur' AND pseudo LIKE '"+pseudo+"'";
      // create the java statement
      ste = con.createStatement();
      // execute the query, and get a java resultset
      ResultSet rs = ste.executeQuery(query);
      // iterate through the java resultset
      while (rs.next())
      {
          v = new Visiteur (rs.getString("pseudo"), rs.getString("email"), rs.getString("nomVisiteur"),rs.getString("prenomVisiteur"));
		  //p = new Proprietaire(rs.getString("nomPlan"));
		  listv.add(v);
      }
         return listv ;
	}

         public void delete(String pseudo) throws SQLException{
      String req = "DELETE FROM `user` WHERE pseudo="+pseudo;
      ste=con.createStatement();
      ste.executeUpdate(req); 
      JOptionPane.showConfirmDialog(null, "voulez-vous supprimer ce compte");
  
  }
    
    
}



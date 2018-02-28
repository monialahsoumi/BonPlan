/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Proprietaire;
import entité.Utilisateur;
import entité.Visiteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Monia
 */
public class GestionAdmin {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    ResultSet rs;
    PreparedStatement pre;

public  GestionAdmin () {
try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}
public Utilisateur recupererAdmin () throws SQLException {
    Utilisateur u = null;
         
        String query = "Select * FROM `user` where pseudo like 'admin' And role like 'Administrateur'";

        ste = con.createStatement();

        ResultSet rs = ste.executeQuery(query);

        while (rs.next()) {
            //p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("password"),rs.getString("photoProfil"), rs.getString("nomPlan"),rs.getString("adresse"),rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"),rs.getString("role"));
            u = new Utilisateur (rs.getString("pseudo"),rs.getString("password"));
            System.out.println(u);

        }
        return u;
}
   public void modifAdmin(Utilisateur u) {
        try {
            String req = "UPDATE user "
                    + "SET pseudo=?, password=?  WHERE role like 'Administrateur' ";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, u.getPseudo());
            st.setString(2, u.getPassword());
            
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
}
 public ArrayList<Proprietaire> afficherListeP() throws SQLException {
        ArrayList<Proprietaire> listp = new ArrayList<>();
        Proprietaire p;
        String query = "SELECT * FROM `user` WHERE role LIKE 'Propriétaire' And validite like false";
        // create the java statement
        ste = con.createStatement();
        // execute the query, and get a java resultset
        ResultSet rs = ste.executeQuery(query);
     
        while (rs.next()) {
            p = new Proprietaire(rs.getString("pseudo"), rs.getString("categorie"), rs.getString("adresse"), rs.getString("telephone"));
            
            listp.add(p);
        }
        return listp;
    }
 public ArrayList<Visiteur> afficherListeV() throws SQLException {
        ArrayList<Visiteur> listv = new ArrayList<>();
        Visiteur v;
        String query = "SELECT * FROM `user` WHERE role LIKE 'Visiteur'";
        // create the java statement
        ste = con.createStatement();
        // execute the query, and get a java resultset
        ResultSet rs = ste.executeQuery(query);
     
        while (rs.next()) {
            v = new Visiteur(rs.getString("pseudo"), rs.getString("email"), rs.getString("nomVisiteur"), rs.getString("prenomVisiteur"));
            
            listv.add(v);
        }
        return listv;
    }
 
}
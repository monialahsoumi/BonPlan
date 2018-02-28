/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Proprietaire;
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
public class GestionValidite {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    ResultSet rs;
    PreparedStatement pre;

    public GestionValidite() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void valider(String pseudo, Proprietaire p) throws SQLException {

        String req = " Update `user` set validite=true where pseudo=?" ;
        PreparedStatement st = con.prepareStatement(req);
        st.setString(1, pseudo);
        
        st.executeUpdate();

    }

    public ArrayList<Proprietaire> afficherListeP() throws SQLException {
        ArrayList<Proprietaire> listp = new ArrayList<>();
        Proprietaire p;
        String query = "SELECT * FROM `user` WHERE role LIKE 'Propriétaire' And validite like false";
        // create the java statement
        ste = con.createStatement();
        // execute the query, and get a java resultset
        ResultSet rs = ste.executeQuery(query);
        // iterate through the java resultset
        while (rs.next()) {
            p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("nomPlan"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("categorie"));
            //p = new Proprietaire(rs.getString("nomPlan"));
            listp.add(p);
        }
        return listp;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Commentaire;
import entité.Rate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author firas
 */
public class GestionRate {
    
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public GestionRate() {
                try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        public void ajouterRate(Rate c) throws SQLException {
        String req = "INSERT INTO `rate`( `rating`,`user_iduser`, `user_iduser1`) VALUES(?,?,?)";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setDouble(1, c.getRate());
        pre.setInt(2, c.getIduserV());
        pre.setInt(3, c.getIduserP());
        

        pre.executeUpdate();
        System.out.println("Rate ajoutée!");
    }
}

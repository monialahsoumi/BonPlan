/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Promotion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Meyssa
 */
public class GestionPromo {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public GestionPromo() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterPromotion(Promotion promo) throws SQLException {

        String req0 = "INSERT INTO `Promotion` ( `description`, `urlPromo`,`dateDebutP`,`dateFinP`,`user_iduser`) VALUES (?,?,?,?,?)";

        PreparedStatement pst = con.prepareStatement(req0);

        pst.setString(1, promo.getDescription());
        pst.setString(2, promo.getUrlPromo());
        pst.setDate(3, (Date) promo.getDateDebut());
        pst.setDate(4, (Date) promo.getDateFin());
        pst.setInt(5, promo.getProprietaire_id());
        pst.executeUpdate();
        System.out.println("Promo crée");

    }

    public List<Promotion> AfficherPromotion_prop(int id) throws SQLException {

        List<Promotion> listPromotions = new ArrayList<>();
        String req1 = "Select *   from `promotion` where user_iduser="+id;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        while (rs.next()) {
            listPromotions.add(new Promotion(
                    rs.getInt("idPromotion"),
                    rs.getString("description"),
                    rs.getString("urlPromo"),
                    rs.getDate("dateDebutP"),
                    rs.getDate("dateFinP")
                  
            ));
        }
        System.out.println("PROMO récupéré");
        return listPromotions;
    }
    
     public List<Promotion> AfficherPromotion() throws SQLException {

        List<Promotion> listPromotions = new ArrayList<>();
        String req1 = "Select *   from `promotion`";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        while (rs.next()) {
            listPromotions.add(new Promotion(
                    rs.getInt("idPromotion"),
                    rs.getString("description"),
                    rs.getString("urlPromo"),
                    rs.getDate("dateDebutP"),
                    rs.getDate("dateFinP")
                  
            ));
        }
        System.out.println("PROMO récupéré");
        return listPromotions;
    }
    
    
    
    
     public void modifierPromo(Promotion promo) {
        try {
            String req = "UPDATE `promotion` "
                    + "SET Description=?,`urlPromo`=?,dateDebutP=?,dateFinP=?  WHERE idPromotion =" + promo.getIdPromo();
            PreparedStatement pst = con.prepareStatement(req);
            pst.setString(1, promo.getDescription());
            pst.setString(2, promo.getUrlPromo());
            pst.setDate(3,  promo.getDateDebut());
            pst.setDate(4,  promo.getDateFin());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


     public void supprimerPromotion(int idpromo) throws SQLException {
           try {
         String req2 = "Delete from `promotion` where `idPromotion`="+idpromo;
        PreparedStatement pst = con.prepareStatement(req2);
        pst.executeUpdate();
        System.out.println("PROMO suppriméé!!!");
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
      }
     //récupération pseudo prop
      public String recupPseudo_Prop(int id) throws SQLException {
        try {
       String req1 = "Select `pseudo`   from `user` u join `promotion` p on p.user_iduser = u.iduser"
                        + " where idPromotion="+id;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
           String p = rs.getString("pseudo");
           return p;
        } catch (SQLException ex) {
            System.out.println(ex);
            return "erreeeeeur pseudo";
        }
      }  
}

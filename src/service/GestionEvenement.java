/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Evenement;
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
public class GestionEvenement {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public GestionEvenement() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterEvenement(Evenement ev) throws SQLException {

        String req = "INSERT INTO `events` ( `description`, `nbrPlace`,`type`,`user_iduser`,`dateDebutEv`,`dateFinEv`,`nomEv`) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement pst = con.prepareStatement(req);

        pst.setString(1, ev.getDescEv());
        pst.setInt(2, ev.getNbPlace());
        pst.setString(3, ev.getTypeEv());
        pst.setInt(4, ev.getProp());
        pst.setDate(5, (Date) ev.getDebutEv());
        pst.setDate(6, (Date) ev.getFinEv());
        pst.setString(7, ev.getNomEv());
        pst.executeUpdate();
        System.out.println("evenement crééééééééééééééééééééééé!!!!");

    }

    public List<Evenement> recupererEvenement() throws SQLException {

        List<Evenement> listEvenement = new ArrayList<>();
        String req1 = "Select *   from `events` where `etatEv` LIKE 'En cours' and `type` LIKE 'publique'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(req1);

        while (rs.next()) {
            listEvenement.add(new Evenement(
                    rs.getInt("idEvents"),
                    rs.getString("nomEv"),
                    rs.getString("Description"),
                    rs.getDate("dateDebutEv"),
                    rs.getDate("dateFinEv"),
                    rs.getInt("nbrPlace"),
                    rs.getString("type")
            ));
        }
        System.out.println("touts les évènements publiques en cours sont récupérés");
        return listEvenement;

    }

    public ArrayList<Evenement> recupererEvenement_P(int id) throws SQLException {
			
        ArrayList<Evenement> listEvenement = new ArrayList<>();
        Evenement e;
        String req1 = "Select * from `events` where `user_iduser`=" + id;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);

        while (rs.next()) {
            System.out.println("kbal remplissage liste");
            e =new Evenement(
                    rs.getInt("idEvents"),
                    rs.getString("nomEv"),
                    rs.getString("Description"),
                    rs.getDate("dateDebutEv"),
                    rs.getDate("dateFinEv"),
                    rs.getInt("nbrPlace"),
                    rs.getString("type"),
					rs.getInt("nbParticipant")
            );
            System.out.println(e);
            listEvenement.add(e);
        }
        System.out.println(listEvenement);
        return listEvenement;

    }
	public ArrayList<Evenement> recupererEvenement_v(int id) throws SQLException {
			System.out.println(id);
        ArrayList<Evenement> listEvenement = new ArrayList<>();
        Evenement e;
        String req1 = "Select * from `events` where `etatEv` LIKE 'En cours' and `type` LIKE 'publique' and `user_iduser`="+id;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);

        while (rs.next()) {
            System.out.println("kbal remplissage liste");
            e =new Evenement(rs.getString("nomEv"),rs.getString("Description"),
                    rs.getDate("dateDebutEv"),
                    rs.getDate("dateFinEv"),
                    rs.getInt("nbrPlace"),
                    rs.getInt("nbParticipant"));
            System.out.println(e);
            listEvenement.add(e);
			System.out.println(listEvenement);
        }
        return listEvenement;
    }

    public ArrayList<Evenement> afficherEvenement(int idEvent) throws SQLException {

        ArrayList<Evenement> listEvent = new ArrayList<>();

        String req1 = "Select *   from `events` where `idEvents`= " + idEvent;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        Evenement event = new Evenement(
                rs.getInt("idEvents"),
                rs.getString("nomEv"),
                rs.getString("Description"),
                rs.getDate("dateDebutEv"),
                rs.getDate("dateFinEv"),
                rs.getInt("nbrPlace"),
                rs.getString("type"));
        listEvent.add(event);
        return listEvent;
    }

    public void modifierEvent(Evenement ev) {
        try {
            String req = "UPDATE `events` "
                    + "SET nomEv=?, Description=?,dateDebutEv=?,dateFinEv=?, nbrPlace=?  WHERE idEvents =" + ev.getIdEv();
            PreparedStatement pst = con.prepareStatement(req);
            pst.setString(1, ev.getNomEv());
            pst.setString(2, ev.getDescEv());
            pst.setDate(3, ev.getDebutEv());
            pst.setDate(4, ev.getFinEv());
            pst.setInt(5, ev.getNbPlace());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void AnnulerEvent(Evenement ev) {
        try {
            String req = "UPDATE `events` "
                    + "SET `etatEv`=? WHERE idEvents =" + ev.getIdEv();
            PreparedStatement pst = con.prepareStatement(req);
            pst.setString(1, "Annulé");
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteEvent(int idEvent) throws SQLException {

        try {
            String req2 = "Delete from `events` where `idEvents`=" + idEvent;
            PreparedStatement pst = con.prepareStatement(req2);
            pst.executeUpdate();
            System.out.println("Event suppriméé!!!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //récupération pseudo prop
    public String recupPseudo_Prop(int id) throws SQLException {
        String p = null;
        try {
            String req1 = "Select `pseudo`   from `user` u join `events` e on e.user_iduser = u.iduser"
                    + " where idEvents=" + id;

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(req1);
            while (rs.next()) {
                p = rs.getString("pseudo");
                System.out.println(p);
            }

            return p;
        } catch (SQLException ex) {
            System.out.println(ex);
            return "erreeeeeur pseudo";
        }
    }
    
    public int dernierEvent(){
        int id=0;
           try {
            String req1 = "Select max(idEvents) from `events` ";
                  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(req1);
            while (rs.next()) {
                id = rs.getInt("idEvents");
                System.out.println(id);
            }

            return id;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }

}

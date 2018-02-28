/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.DemandeEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListDataEvent;

/**
 *
 * @author Meyssa
 */
public class gestionDemandeEvent {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public gestionDemandeEvent() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterDemande(DemandeEvent demande) throws SQLException {
        String req = "INSERT INTO `demandeevent` ( `nomevent`, `typeEvent`,`descriptionEvent`,`nbPlaceEvent`,`DateDebut`,`DateFin`,`user_iduser`,`user_iduser1`) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement pst = con.prepareStatement(req);

        pst.setString(1, demande.getNom());
        pst.setString(2, demande.getTypeEvent());
        pst.setString(3, demande.getDescription());
        pst.setInt(4, demande.getNbPlace());
        pst.setDate(5, (Date) demande.getDebut());
        pst.setDate(6, (Date) demande.getFin());
        pst.setInt(7, demande.getIdDemandeur());
        pst.setInt(8, demande.getIdProp());
        pst.executeUpdate();
        System.out.println("demande crééééééééééééééééééééééé!!!!");

    }

    public ArrayList<DemandeEvent> recupererDemande(String pseudo) throws SQLException {
            System.out.println(pseudo+" kaed yrécupéri");
        ArrayList<DemandeEvent> listDemandeEvents = new ArrayList<>();
        String req1 = "Select d.*,u.pseudo from `demandeevent` d join `user` u on d.user_iduser= u.iduser";// where u.pseudo="+pseudo;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        while (rs.next()) {

            listDemandeEvents.add(new DemandeEvent(
                    rs.getInt("idDemande"),
                    rs.getString("nomEvent"),
                    rs.getString("descriptionEvent"),
                    rs.getDate("DateDebut"),
                    rs.getDate("DateFin"),
                    rs.getInt("nbPlaceEvent"),
                    rs.getString("typeEvent"),
                    rs.getInt("user_iduser"),
                    rs.getInt("user_iduser1"),
                    rs.getString("u.pseudo"),
                    rs.getBoolean("validité")
            ));
        }
        System.out.println("demande récupérés");
        return listDemandeEvents;

    }

 public void validerDemande(DemandeEvent d) {
        try {
            String req = "UPDATE `demandeevent` "
                    + "SET `Validité`=? WHERE idDemande =" + d.getIdDemande();
            PreparedStatement pst = con.prepareStatement(req);
            pst.setInt(1, 1);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteDemande(int iddemande) throws SQLException {

        try {
            String req2 = "Delete from `events` where `idDemande=" + iddemande;
            PreparedStatement pst = con.prepareStatement(req2);
            pst.executeUpdate();
            System.out.println("Event suppriméé!!!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
}

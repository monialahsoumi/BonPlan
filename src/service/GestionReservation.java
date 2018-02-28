/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author firas
 */
public class GestionReservation {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public GestionReservation() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
  
    public void ajouterReservation(Reservation v) throws SQLException {
        String req = "INSERT INTO `reservation`(`nbrPlace`, `date`, `etat`, `téléphone`, `heure`, `user_iduser`, `user_iduser1`) VALUES (?,?,?,?,?,?,?)";
        
        
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, v.getNbrPlace());
        pre.setDate(2, v.getDateReservation());
        pre.setString(3, "en cours");
        pre.setInt(4, v.getTelephone());
        pre.setString(5, v.getHeure());
        pre.setInt(6, v.getIduserV());
        pre.setInt(7, v.getIduserP());
        pre.executeUpdate();
        System.out.println("Réservation ajoutée!");
    }

    public List<Reservation> afficherReservation(int id) throws SQLException {

        List<Reservation> listReservation = new ArrayList<>();
        String req1 = "SELECT r.idReservation,r.nbrPlace,r.date,r.téléphone,r.heure,r.etat,u.email from reservation r JOIN user u ON u.iduser = r.user_iduser where r.user_iduser1 = "+id;
//
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        while (rs.next()) {
            listReservation.add(new Reservation(
                    rs.getInt("r.idReservation"),
                    rs.getInt("r.nbrPlace"),
                    rs.getDate("r.date"),
                    rs.getInt("r.téléphone"),
                    rs.getString("r.heure"),
                    rs.getString("r.etat"),
                    rs.getString("u.email")
            ));
        }
        return listReservation;
    }

    public void TraiterReservation(Reservation rs) {
        try {

            String req2 = "UPDATE `reservation` SET `etat`=?,description=? WHERE idReservation=?";
            PreparedStatement st = con.prepareStatement(req2);

            st.setString(1, rs.getEtat());
            st.setString(2, rs.getDescription());
            st.setInt(3, rs.getId());

            st.executeUpdate();
            System.out.println("reservation a ete mis à jour avec succès !");

        } catch (SQLException ex) {

            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(GestionReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reservation RechercheReservation(int id) {
        Reservation C = null;

        try {
            String req5 = "SELECT * FROM `reservation` WHERE idReservation= ? ";
            PreparedStatement st = con.prepareStatement(req5);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                C = new Reservation();
                C.setId(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;

    }

    public List<Reservation> afficherReservationVisiteur() throws SQLException {

        List<Reservation> listReservationVisiteur = new ArrayList<>();
        String req1 = "SELECT r.description, r.idReservation,nbrPlace,date,etat,nomPlan,r.téléphone,heure from reservation r LEFT JOIN user u ON u.iduser = r.user_iduser";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        while (rs.next()) {
            listReservationVisiteur.add(new Reservation(
                    rs.getString("r.description"),
                    rs.getInt("r.idReservation"),
                    rs.getInt("nbrPlace"),
                    rs.getDate("date"),
                    rs.getInt("r.téléphone"),
                    rs.getString("heure"),
                    rs.getString("etat"),
                    rs.getString("nomPlan")
            ));
        }
        return listReservationVisiteur;
    }

    public void supprimerReservationParVisiteur(int id) {
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM `reservation` WHERE `idReservation`=?");
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            ex.getLocalizedMessage();
        }
    }

    public void ModifierMaReservation(Reservation rs) {
        try {

            String req2 = "UPDATE `reservation` SET `nbrPlace`=?,`date`=?,`téléphone`=?,`heure`=? WHERE idReservation=?";
            PreparedStatement st = con.prepareStatement(req2);

            st.setInt(1, rs.getNbrPlace());
            st.setDate(2, rs.getDateReservation());
            st.setInt(3, rs.getTelephone());
            st.setString(4, rs.getHeure());
            st.setInt(5, rs.getId());

            st.executeUpdate();
            System.out.println("reservation a ete mis à jour avec succès !");

        } catch (SQLException ex) {

            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(GestionReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author firas
 */
public class GestionCommentaire {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public GestionCommentaire() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterCommentaire(Commentaire c) throws SQLException {
        String req = "INSERT INTO `avis` (`user_iduser`, `user_iduser1`,`commentaire`, `dateComment`) VALUES (?,?,?,?)";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, c.getIduserV());
        pre.setInt(2, c.getIduserP());
        pre.setString(3, c.getCommentaire());
        pre.setDate(4, java.sql.Date.valueOf(LocalDate.now()));

        pre.executeUpdate();
        System.out.println("commentaire ajoutée!");
    }

    public List<Commentaire> affichercommentaire() throws SQLException {

        List<Commentaire> listCommentaire = new ArrayList<>();
        String req1 = "SELECT * from avis a JOIN user u ON u.iduser = a.user_iduser ORDER BY a.dateComment DESC";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(req1);
        while (rs.next()) {
            listCommentaire.add(new Commentaire(
                    rs.getInt("a.idAvis"),
                    rs.getString("a.commentaire"),
                    rs.getDate("a.dateComment")
            ));
        }
        return listCommentaire;
    }

    public void supprimerCommentaire(int id) {
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM `avis` WHERE `idAvis`=?");
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            ex.getLocalizedMessage();
        }
    }

    public void updateContenu(Commentaire rs) {
        try {
            System.out.println("aaaaa");
            Statement ste = con.createStatement();
            String req = "UPDATE avis SET commentaire = ? WHERE idAvis = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, rs.getCommentaire());
            st.setInt(2, rs.getIdAvis());
            st.executeUpdate();
            System.out.println("reservation a ete mis à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}

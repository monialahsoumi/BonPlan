/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Utilisateur;
import entité.Visiteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Monia
 */
public class GestionVisiteur {

	Connection con = DataSource.getInstance().getConnection();
	private Statement ste;

	public GestionVisiteur() {
		try {
			ste = con.createStatement();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void ModifierVisiteur(Visiteur v) throws SQLException {
		Utilisateur u = new Utilisateur();
		try {
			String req = "UPDATE user "
					+ "SET nomVisiteur=?, email=?, prenomVisiteur=?,password=?, photoProfil=?  WHERE pseudo=?";
			PreparedStatement st = con.prepareStatement(req);
			st.setString(1, v.getNom());
			st.setString(2, v.getEmail());
			st.setString(3, v.getPrenom());
			st.setString(4, v.getPassword());
			st.setString(5, v.getPdp());
			st.setString(6, u.getPseudo());
			st.executeUpdate();
			System.out.println("modifié");
		} catch (SQLException ex) {
			System.err.println(ex);
		}

	}

	public ArrayList<Visiteur> afficherVisiteur(String pseudo) throws SQLException {
		ArrayList<Visiteur> listv = new ArrayList<>();
		Visiteur v;
		String query = "SELECT * FROM `user` WHERE role LIKE 'visiteur' AND pseudo LIKE '" + pseudo + "'";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			v = new Visiteur(rs.getInt("iduser"),
					rs.getString("pseudo"), rs.getString("email"), rs.getString("password"),
					rs.getString("nomVisiteur"), rs.getString("prenomVisiteur"), rs.getString("photoProfil"));
			//p = new Proprietaire(rs.getString("nomPlan"));
			listv.add(v);
		}
		return listv;
	}

	public void delete(String pseudo) throws SQLException {
		Statement stmt = con.createStatement();
		String SQL = "DELETE FROM `user` WHERE pseudo='" + pseudo + "'";
		stmt.executeUpdate(SQL);
		JOptionPane.showMessageDialog(null, "Deleted successfully");
	}

	public int recupererVisiteurId(String pseudo) throws SQLException {
		int id = 0;
		String query = "Select * From  `user` where pseudo= '" + pseudo + "' And role like 'Visiteur'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			id = rs.getInt("iduser");
		}
		return id;
	}

	public Visiteur recupererVisiteur(String pseudo) {
		Visiteur v1 = null;
		try {
			String query = "Select * From  `user` where pseudo= '" + pseudo + "' And role like 'Visiteur'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				String nomm = rs.getString("nomVisiteur");
				String prenomm = rs.getString("prenomVisiteur");
				String passwordm = rs.getString("password");
				String emailm = rs.getString("emailm");

				v1 = new Visiteur(emailm, passwordm, nomm, prenomm);
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

		}
		return v1;
	}

	public void ajouterVisiteur(Visiteur v) throws SQLException {
		String req = "INSERT INTO `user` ( `pseudo`, `email`,`password`,`nomVisiteur`,`prenomVisiteur`,`role`,`photoProfil`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setString(1, v.getPseudo());
		pre.setString(2, v.getEmail());
		pre.setString(3, v.getPassword());
		pre.setString(4, v.getNom());
		pre.setString(5, v.getPrenom());
		pre.setString(6, v.getRole());
		pre.setString(7, v.getPdp());
		pre.executeUpdate();
		System.out.println("Visiteur ajoutée!");
	}

	public String ExisteOuNN(String pseudo) throws SQLException {

		String ps = "";
		String query = "Select * From  `user` where pseudo= '" + pseudo + "' And role like 'Visiteur'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			return ps = rs.getString("pseudo");
		}

		return ps;
	}
}

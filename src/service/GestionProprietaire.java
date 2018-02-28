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
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ali
 */
public class GestionProprietaire {

	Connection con = DataSource.getInstance().getConnection();
	private Statement ste;

	public GestionProprietaire() {
		try {
			ste = con.createStatement();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void ajouterProprietaire(Proprietaire p) throws SQLException {

		try {
			String req = "INSERT INTO `user` ( `pseudo`, `email`,`password`,`photoProfil`,`nomPlan`,`telephone`,`description`,`categorie`,`role`,`latitude`,`longitude`,`adresse`,`ville`,`codePostal`,`validite`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setString(1, p.getPseudo());
			pre.setString(2, p.getEmail());
			pre.setString(3, p.getPassword());
			pre.setString(4, p.getPhotoProfil());
			pre.setString(5, p.getNomPlan());
			pre.setString(6, p.getTelephone());
			pre.setString(7, p.getDescription());
			pre.setString(8, p.getCategorie());
			pre.setString(9, p.getRole());
			pre.setDouble(10, p.getLatitude());
			pre.setDouble(11, p.getLongiturde());
			pre.setString(12, p.getAdresse());
			pre.setString(13, p.getVille());
			pre.setInt(14, p.getCodepostal());
			pre.setBoolean(15, p.getValidité());
			pre.executeUpdate();
			System.out.println("Personne ajoutée!");
		} catch (Exception e) {
			System.out.println("Erreur");
		}
	}

	public Proprietaire rechercheProptietaire(int id) throws SQLException {
		Proprietaire p = null;
		String query = "SELECT * FROM `user` WHERE iduser =" + id;
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("password"), rs.getString("photoProfil"), rs.getString("nomPlan"), rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"), rs.getString("role"));

		}
		return p;
	}

	public ArrayList<Proprietaire> rechercheProptietaire(String nomPlan) throws SQLException {
		ArrayList<Proprietaire> listp = new ArrayList<>();
		Proprietaire p;
		String query = "SELECT photoProfil,nomPlan,pseudo FROM `user` WHERE role LIKE 'Propriétaire' AND nomPlan Like '" + nomPlan + "'";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			p = new Proprietaire(rs.getString("photoProfil"), rs.getString("nomPlan"), rs.getString("pseudo"));
			listp.add(p);
		}
		return listp;
	}

	public ArrayList<Proprietaire> rechercheP() throws SQLException {
		ArrayList<Proprietaire> listp = new ArrayList<>();
		Proprietaire p;
		String query = "SELECT photoProfil,nomPlan FROM `user` WHERE role LIKE 'Propriétaire' ";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			//p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("password"),rs.getString("photoProfil"), rs.getString("nomPlan"),rs.getString("adresse"),rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"),rs.getString("role"));
			p = new Proprietaire(rs.getString("nomPlan"));
			listp.add(p);
		}
		return listp;
	}

	public ArrayList<Proprietaire> rechercheCategorie(String categorie) throws SQLException {
		ArrayList<Proprietaire> listp = new ArrayList<>();
		Proprietaire p;
		String query = "SELECT * FROM user WHERE role LIKE 'Propriétaire' AND categorie LIKE '" + categorie + "'";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("password"), rs.getString("photoProfil"), rs.getString("nomPlan"), rs.getString("telephone"), rs.getString("Description"), rs.getString("categorie"), rs.getString("role"), rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getString("adresse"), rs.getString("ville"), rs.getInt("codePostal"), rs.getBoolean("validite"));

			//p = new Proprietaire(rs.getString("nomPlan"));
			listp.add(p);
		}
		return listp;
	}

	public ArrayList<Proprietaire> afficherProp(String pseudo) throws SQLException {
		ArrayList<Proprietaire> listp = new ArrayList<>();
		Proprietaire p;
		String query = "SELECT * FROM `user` WHERE role LIKE 'Propriétaire' AND pseudo LIKE '" + pseudo + "'";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"),
					rs.getString("password"), rs.getString("photoProfil"), rs.getString("nomPlan"),
					rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"),
					rs.getString("role"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("adresse"),
					rs.getString("ville"), rs.getInt("CodePostal"), rs.getBoolean("validite"));
			//p = new Proprietaire(rs.getString("nomPlan"));
			listp.add(p);
		}
		return listp;
	}

	public void modifProp(Proprietaire p) {
		try {
			String req = "UPDATE user "
					+ "SET nomPlan=?, email=?,telephone=?,description=?, categorie=? , password=?  WHERE pseudo=?";
			PreparedStatement st = con.prepareStatement(req);
			st.setString(1, p.getNomPlan());
			st.setString(2, p.getEmail());
			st.setString(3, p.getTelephone());
			st.setString(4, p.getDescription());
			st.setString(5, p.getCategorie());
			st.setString(6, p.getPassword());
			st.setString(7, p.getPseudo());
			st.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public Proprietaire rechercheProprietaireParnom(String nomPlan, String pseudo) throws SQLException {

		Proprietaire p = null;
		System.out.println("pseudo = " + pseudo + " nomplan = " + nomPlan);
		String query = "SELECT * FROM `user` WHERE role LIKE 'Propriétaire' AND nomPlan Like '" + nomPlan + "' AND `pseudo` LIKE '" + pseudo + "'";

		ste = con.createStatement();

		ResultSet rs = ste.executeQuery(query);

		while (rs.next()) {
			//p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("password"),rs.getString("photoProfil"), rs.getString("nomPlan"),rs.getString("adresse"),rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"),rs.getString("role"));
			p = new Proprietaire(rs.getInt("iduser"), rs.getString("email"), rs.getString("photoProfil"),
					rs.getString("nomPlan"), rs.getString("adresse"), rs.getString("telephone"),
					rs.getString("description"), rs.getString("categorie"));

		}
		return p;
	}

	public int recupererPropId(String pseudo) throws SQLException {
		int id = 0;
		String query = "Select iduser From  `user` where pseudo= '" + pseudo + "'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			id = rs.getInt("iduser");
		}
		return id;
	}

	public Proprietaire rechercheIndex(String rech) throws SQLException {
		Proprietaire p = new Proprietaire();
		String query = "Select iduser From  `user` where nomPlan= '" + rech + "' or ville='" + rech + "'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			return p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"),
					rs.getString("password"), rs.getString("photoProfil"), rs.getString("nomPlan"),
					rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"),
					rs.getString("role"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("adresse"),
					rs.getString("ville"), rs.getInt("CodePostal"), rs.getBoolean("validite"));
		}
		return p;
	}
        public int afficherIdProp(String pseudo) throws SQLException {
		int p=0;
		String query = "SELECT iduser FROM `user` WHERE role LIKE 'Propriétaire' AND pseudo LIKE '" + pseudo + "'";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			return p = (rs.getInt("iduser"));
			//p = new Proprietaire(rs.getString("nomPlan"));
		
		}
		return p;
	}
}

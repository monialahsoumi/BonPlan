/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import entité.Proprietaire;
import entité.Suggestion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ali
 */
public class GestionSuggestion {
	//SELECT * FROM avis a,user u WHERE a.user_iduser=u.iduser ORDER BY `rating` DESC

	Connection con = DataSource.getInstance().getConnection();
	private Statement ste;

	public GestionSuggestion() {
		try {
			ste = con.createStatement();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public ArrayList<Suggestion> afficherSugg() throws SQLException {
		
		ArrayList<Suggestion> listp = new ArrayList<>();

		String query = "SELECT * FROM rate r,user u WHERE r.user_iduser=u.iduser ORDER BY `rate` DESC";
		// create the java statement
		ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		// iterate through the java resultset
		while (rs.next()) {
			
			//p = new Proprietaire(rs.getString("pseudo"), rs.getString("email"), rs.getString("password"),rs.getString("photoProfil"), rs.getString("nomPlan"),rs.getString("adresse"),rs.getString("telephone"), rs.getString("description"), rs.getString("categorie"),rs.getString("role"));
			/*Avis a = new Avis(rs.getInt("idAvis"), rs.getString("commentaire"), rs.getInt("rating"), rs.getString("urlPhoto"));
			Proprietaire p = new Proprietaire(rs.getString("nomPlan"));*/
			Suggestion s = new Suggestion(rs.getInt("idRate"), rs.getDouble("rate"),rs.getString("pseudo"), rs.getString("email"), rs.getString("password"),rs.getString("photoProfil"),rs.getString("nomPlan"), rs.getString("telephone"), rs.getString("Description"), rs.getString("categorie"), rs.getString("role"),rs.getDouble("longitude"),rs.getDouble("latitude"), rs.getString("adresse"), rs.getString("ville"), rs.getInt("codePostal"), rs.getBoolean("validite"));
			listp.add(s);
		}
		return listp;
	}
}

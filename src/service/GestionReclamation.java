/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author gaalo
 */
import DataSource.DataSource;
import entité.Reclamer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sof
 */
public class GestionReclamation {

	static DataSource ds = DataSource.getInstance();

	public void insererReclam(Reclamer p) {
		try {

			String req = "insert into reclamation(user_iduser,user_idPlan , etat, type, niv_rec, description)  values (?,?,?,?,?,?)";
			PreparedStatement ste1 = ds.getInstance().getConnection().prepareStatement(req);
			ste1.setInt(1, p.getIdUser());
			ste1.setInt(2, p.getIdPlan());
			ste1.setString(3, p.getEtat());
			ste1.setString(4, p.getType());
			ste1.setString(5, p.getNivRec());
			ste1.setString(6, p.getDescription());
			ste1.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public void Modifer(int id, Reclamer g) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		String req = "UPDATE `reclamation` SET `etat`=? WHERE idReclamation=? ";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setString(1, g.getEtat());
		pre.setInt(2, id);
		pre.executeUpdate();
	}

	public static List<Reclamer> DisplayAll() throws SQLException {
		List<Reclamer> list = new ArrayList<>();
		String req = "SELECT * FROM `reclamation`";
		Statement ste = ds.getConnection().createStatement();
		ResultSet rs = ste.executeQuery(req);
		while (rs.next()) {
			Reclamer g = new Reclamer(rs.getInt("idReclamation"), rs.getInt("user_iduser"), rs.getInt("user_idPlan"), rs.getString("etat"),
			rs.getString("type"), rs.getString("niv_rec"), rs.getString("description"));
			list.add(g);
			System.out.println("soo");
		}
		return list;
	}

	public static List<Reclamer> DisplayAll1(int idUs) throws SQLException {
		List<Reclamer> list = new ArrayList<>();

		String req = "SELECT type,niv_rec,description,etat FROM `reclamation` where user_iduser = '" + idUs + "'";
		Statement ste = ds.getConnection().createStatement();
		ResultSet rs = ste.executeQuery(req);
		while (rs.next()) {
			Reclamer g = new Reclamer(rs.getString("type"), rs.getString("description"), rs.getString("niv_rec"), rs.getString("etat"));
			list.add(g);
			System.out.println("soo");
		}
		return list;
	}

	public void DeleteRec(int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		String req = "DELETE from  reclamation  WHERE idReclamation =?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setInt(1, id);
		pre.executeUpdate();
	}

	public int returnEtat() throws SQLException {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `reclamation` WHERE `etat`='en cours' ");
		while (rs3.next()) {
			count = rs3.getInt(1);
		};
		return count;
	}

	public int returnEtat1() throws SQLException {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `reclamation` WHERE `etat`='non validée' ");
		while (rs3.next()) {
			count = rs3.getInt(1);
		};
		return count;
	}

	public int returnEtat2() throws SQLException {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `reclamation` WHERE `etat`='Valider' ");
		while (rs3.next()) {
			count = rs3.getInt(1);
		};
		return count;
	}

	public int obtenirIdUser(String nomU) throws SQLException {
		int a = 0;
		String query = "SELECT iduser FROM `user` WHERE pseudo LIKE '" + nomU + "'";
		Connection con = DataSource.getInstance().getConnection();
		Statement ste = con.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = ste.executeQuery(query);
		while (rs.next()) {
			a = rs.getInt("iduser");
		}
		return a;
	}

	public int obtenirIdPlan(String nomP) throws SQLException {
		int a = 0;
		String query = "SELECT iduser FROM `user` WHERE nomPlan LIKE '" + nomP + "'";
		Connection con = DataSource.getInstance().getConnection();
		Statement ste = con.createStatement();
		ResultSet rs = ste.executeQuery(query);
		while (rs.next()) {
			a = rs.getInt("iduser");
		}
		return a;
	}
}

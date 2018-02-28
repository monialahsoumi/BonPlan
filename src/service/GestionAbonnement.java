/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataSource.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import entité.Abonner;
import java.util.Scanner;

/**
 *
 * @author gaalo
 */
public class GestionAbonnement {
    Connection con = DataSource.getInstance().getConnection();
	public void insererAb(Abonner p) {
		try {
            //  try {
			// Statement ste = ds.getConnection().createStatement();
			//String req = "insert into personne (`nom`,`prenom`) values ('"+p.getNom()+"','"+p.getPrenom()+"')";
			//ste.executeUpdate(req);

			String req = "insert into `abonner`(user_iduser,user_iduser1)  values (?,?)";
            PreparedStatement ste1 = con.prepareStatement(req);
			ste1.setInt(1, p.getIdUser());
			ste1.setInt(2, p.getIdPlan());
		

			ste1.executeUpdate(); //exécution
			//  } catch (Exception e) {
			//      System.out.println("probleme");
			// }
		} catch (SQLException ex) {
			System.out.println(ex);		}

	}
	 public void delete(int idu ,int idp) throws SQLException {
        Statement stmt = con.createStatement();
        String SQL = "DELETE FROM `abonner` WHERE user_iduser='" +idu+ "'"+"AND user_iduser1='"+idp+"'";
        stmt.executeUpdate(SQL);

        

    }
	public int obtenirIdPlan (String nomP) throws SQLException{
		int a=0;
		    String query = "SELECT iduser FROM `user` WHERE nomPlan LIKE '"+nomP+"'";
				Statement ste = con.createStatement();
      // execute the query, and get a java resultset
      ResultSet rs = ste.executeQuery(query);


			while (rs.next()) {
				a = rs.getInt("iduser");
			
		}
        // create the java statement
        // execute the query, and get a java resultset
    
		
	
		return a;

	
	}
	
	public int obtenirIdUser (String nomU) throws SQLException{
		int a=0;
		    String query = "SELECT iduser FROM `user` WHERE pseudo LIKE '"+nomU+"'";
		Connection con = DataSource.getInstance().getConnection();
		Statement ste = con.createStatement();
      // execute the query, and get a java resultset
      ResultSet rs = ste.executeQuery(query);


			while (rs.next()) {
				a = rs.getInt("iduser");
			
		}
	return a;

}
	
		public int returnAbonner(int idPl) throws SQLException {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `abonner` WHERE `user_iduser1`='"+idPl+"'");
		while (rs3.next()) {
			count = rs3.getInt(1);

		};
		return count;
	}
		
		public int VerifIdUser (int idAb) throws SQLException{
		int a=0;
		    String query = "SELECT idabonner FROM `abonner` WHERE user_iduser LIKE '"+idAb+"'";
		Connection con = DataSource.getInstance().getConnection();
		Statement ste = con.createStatement();
      // execute the query, and get a java resultset
      ResultSet rs = ste.executeQuery(query);


			while (rs.next()) {
				a = rs.getInt("idabonner");
			
		}
	return a;

}
	public int VerifIdPlan (int idPl) throws SQLException{
		int a=0;
		    String query = "SELECT idabonner FROM `abonner` WHERE user_iduser1='"+idPl+"'";
		Connection con = DataSource.getInstance().getConnection();
		Statement ste = con.createStatement();
		

      // execute the query, and get a java resultset
      ResultSet rs = ste.executeQuery(query);


			while (rs.next()) {
				a = rs.getInt("idabonner");
			
		}
	return a;

}
public int returnNbVisit() throws SQLException {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getConnection();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `user` WHERE `role`='Visiteur' ");
		while (rs3.next()) {
			count = rs3.getInt(1);

		};
		return count;
	}
}

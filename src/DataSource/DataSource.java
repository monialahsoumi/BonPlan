/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Ali
 */
public class DataSource {
    private static DataSource data;
    private Connection con;
    
String username = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/angrynerds";
private DataSource(){
    try {
        con = DriverManager.getConnection(url, username, password);
        System.out.println("connexion");
        } 
    catch ( SQLException ex )
        {
        Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Connection Impossible");
            alert2.setHeaderText(null);
            alert2.show();
        }
                    }
 public Connection getConnection(){
 return con;
 }
 
 public static  DataSource getInstance(){
 if (data== null) {
    data = new DataSource();
    }
 return (DataSource) data;
 }
}

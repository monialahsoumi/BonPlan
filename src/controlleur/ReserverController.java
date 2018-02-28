/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entité.Proprietaire;
import entité.Reservation;
import entité.Utilisateur;
import entité.Visiteur;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.GestionReservation;
import service.GestionVisiteur;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class ReserverController implements Initializable {

    @FXML
    private Button mail;
    ObservableList<String> comboHeureList = FXCollections.observableArrayList("1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
    @FXML
    private TextField Nbrplace;
    @FXML
    private TextField telephone;
    @FXML
    private DatePicker dateReservation;
    private TextField heure;
    @FXML
    private Button btnReserver;
    @FXML
    private Button btnAnnuler;
    @FXML
    private ComboBox comboHeure;
    @FXML
    private Button retour;

    public static Proprietaire p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboHeure.setValue("1:00");
        comboHeure.setItems(comboHeureList);
    }

  

    @FXML
    private void AjouterReservation(ActionEvent event) throws SQLException, DocumentException {
        GestionVisiteur gv = new GestionVisiteur();
        ArrayList<Visiteur> listv;
        listv = gv.afficherVisiteur(Utilisateur.pseudo);
        int nbPlace = Integer.parseInt(Nbrplace.getText());
        int tel = Integer.parseInt(telephone.getText());
        String heure = (String) comboHeure.getValue();
        System.out.println("aa");
        Document documment = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(documment, new FileOutputStream("E:/Reservation1.pdf"));
            documment.open();

        } catch (Exception e) {
            System.out.println(e);
        }

        GestionReservation sp = new GestionReservation();
        ConsulterController cc = new ConsulterController();
        Reservation r = null;
        try {
            for (Visiteur visiteur : listv) {
                int idV = visiteur.getId();
                int idP = p.getId();
                Date date = java.sql.Date.valueOf(dateReservation.getValue());
                System.out.println("id vis" + visiteur.getId());
                System.out.println("id prop" + idP);
                r = new Reservation(nbPlace, tel, heure, idV, idP, date);
                documment.add(new Paragraph("Récapitulatif de votre réservation"));
                documment.add(new Paragraph("votre réservation"));
                documment.add(new Paragraph("Statut en cours du traitement"));
                documment.add(new Paragraph("Votre sélécetion:"));
                documment.add(new Paragraph("Date du réservation "+dateReservation.getValue()));
                documment.add(new Paragraph("Nombre du place " + Nbrplace.getText()));
                documment.add(new Paragraph("Heure de réservation " + heure));
                documment.add(new Paragraph("Téléphone " + telephone.getText()));
                documment.close();
                System.out.println(r);
                sp.ajouterReservation(r);
                System.out.println("Réservation ajouté avec succès !! ");
            }
        } catch (SQLException ex) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("réservation impossible!!!!!!");
            alert2.setHeaderText(null);
            alert2.show();
        }

    }

    @FXML
    private void initialize(ActionEvent event) {
    }

    @FXML
    private void retourProfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceuilVisiteur.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }
@FXML
    private void EnvoyerMail(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MailPane.fxml"));
        Parent root = loader.load();
        mail.getScene().setRoot(root);
        
    }
}

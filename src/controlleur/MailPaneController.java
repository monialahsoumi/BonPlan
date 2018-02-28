/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import javax.mail.MessagingException;
import GUI.SendMail;

/**
 * FXML Controller class
 *
 * @author Monia
 */
public class MailPaneController implements Initializable {

    @FXML
    private Button send;
    @FXML
    private TextArea message;
    @FXML
    private TextField mail;
    @FXML
    private TextField pass;
    @FXML
    private TextField dest;
    @FXML
    private TextField Title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Send(ActionEvent event) throws MessagingException {

        String USER_NAME = mail.getText();
        String PASSWORD = pass.getText();
        String RECIPIENT = dest.getText();

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = Title.getText();
        String body = message.getText();
        SendMail send = new SendMail();
        send.sendFromGMail(from, pass, to, subject, body);
    }

}

/**
 * @FXML private void sendMailClicked(MouseEvent event) { String USER_NAME = "";
 * String PASSWORD = ""; String RECIPIENT = tfRecipient.getText();
 *
 * String from = USER_NAME; String pass = PASSWORD; String[] to = {RECIPIENT};
 * // list of recipient email addresses String subject =
 * tfArticleTitle.getText(); String body = contenu.getHtmlText();
 * sendFromGMail(from, pass, to, subject, body); 
    }/
 */

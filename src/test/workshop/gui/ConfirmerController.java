/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ConfirmerController {
    // Votre compte Twilio SID et Auth Token

    public static final String ACCOUNT_SID = "AC0643a8413f76b0e5bdd8ea93378281d0";
    public static final String AUTH_TOKEN = "0d4ffaa40c2b31200a2a3411815af60d";

    
    @FXML
    private Button confirmationButton;

    @FXML
    private void handleConfirmation() {
        // Initialisation de la bibliothèque Twilio avec votre SID et Auth Token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Envoi du SMS
        Message message = Message.creator(new PhoneNumber("+21625363115"),
                new PhoneNumber("++12766630621"),
                "Bonjour, Votre colis est livré :) merci d'avoir utiliser AMENA !").create();

        // Affichage du SID du message dans une fenêtre de dialogue
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation de livraison");
        alert.setHeaderText(null);
        alert.setContentText("Le message a été envoyé avec succès ! SID : " + message.getSid());
        alert.showAndWait();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import static test.workshop.gui.ConfirmerController.ACCOUNT_SID;
import static test.workshop.gui.ConfirmerController.AUTH_TOKEN;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordTController implements Initializable {
    public static final String ACCOUNT_SID = "AC0643a8413f76b0e5bdd8ea93378281d0";
    public static final String AUTH_TOKEN = "679e1403ee3132355b58cc0c2ad828ba";
    @FXML
    private AnchorPane paneA2;
    @FXML
    private ListView<Colis> lsview;
    @FXML
    private WebView webview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           WebEngine engine = webview.getEngine();
        engine.load("https://calendar.google.com/calendar/embed?src=26c1fb8a6a4cc738fb7a1acd1425fb081b0296ba5dbe96e26c98713e751ce4fc%40group.calendar.google.com&ctz=Africa%2FTunis");
    }  
       


    @FXML
    private void afficher(ActionEvent event) {
         ColisCRUD c = new ColisCRUD();
        
        List<Colis> list=c.afficher4();
        ObservableList<Colis> observableList = FXCollections.observableArrayList(list);
        lsview.setItems(observableList);
        lsview.setCellFactory(param -> new ListCell<Colis>() {
        @Override
        protected void updateItem(Colis item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText("Nom expéditeur: " + item.getNomExpediteur() + 
                    "\nAdresse expéditeur: " + item.getAdresseExpediteur() +
                    "\nNom destinataire: " + item.getNomDestinataire() +
                    "\nAdresse destinataire: " + item.getAdresseDestinataire() +
                    "\nStatut: " + item.getStatut()+
                    "\nDate d'expedition: " + item.getDateExpedition()+
                    "\nPoids: " + item.getPoids() + "kg");
        }
    }
});
    }
    

    @FXML
    private void Traquer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de partager votre position ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("Suivre.fxml"));
            paneA2.getChildren().removeAll();
            paneA2.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    @FXML
    private void Confirmer(ActionEvent event) {
        
            Colis colisSelectionne = lsview.getSelectionModel().getSelectedItem();
    if (colisSelectionne != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de livraison");
        alert.setHeaderText(null);
        alert.setContentText("Vous êtes sûr de confirmer la livraison de ce colis ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            colisSelectionne.setStatut("Livré");
            ColisCRUD c = new ColisCRUD();
            c.modifier(colisSelectionne); // mettre à jour le statut du colis dans la base de données
            // Envoi du SMS et affichage d'une alerte pour confirmer l'envoi
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+21625363115"),
                    new PhoneNumber("+12766630621"),
                    "Bonjour, Votre colis est livré :) merci d'avoir utiliser AMENA !").create();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Confirmation de livraison");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Le message a été envoyé avec succès ! SID : " + message.getSid());
            successAlert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun colis sélectionné");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un colis dans la liste.");
        alert.showAndWait();
    }
}
    }
    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import test.workshop.gui.Accueil;
import static test.workshop.gui.ConfirmerController.ACCOUNT_SID;
import static test.workshop.gui.ConfirmerController.AUTH_TOKEN;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordTransporteurController implements Initializable {

    @FXML
    private AnchorPane pane4;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane3;
    @FXML
    private ListView<Colis> lsview;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Circle circle2;
    @FXML
    private ImageView menu;
    @FXML
    private ImageView exit;
    @FXML
    private Button partager;
    @FXML
    private WebView webview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    WebEngine engine = webview.getEngine();
        engine.load("https://calendar.google.com/calendar/embed?height=600&wkst=1&bgcolor=%23ffffff&ctz=Africa%2FTunis&showTz=0&showCalendars=0&showTabs=0&src=bWVsZWt6YWlkaTQwN0BnbWFpbC5jb20&src=MjZjMWZiOGE2YTRjYzczOGZiN2ExYWNkMTQyNWZiMDgxYjAyOTZiYTVkYmU5NmUyNmM5ODcxM2U3NTFjZTRmY0Bncm91cC5jYWxlbmRhci5nb29nbGUuY29t&src=YWRkcmVzc2Jvb2sjY29udGFjdHNAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&src=ZW4udG4jaG9saWRheUBncm91cC52LmNhbGVuZGFyLmdvb2dsZS5jb20&color=%23039BE5&color=%238E24AA&color=%2333B679&color=%230B8043\" style=\"border:solid 1px #777\" width=\"800\" height=\"600\" frameborder=\"0\" scrolling=\"no\"");
    }    

    @FXML
   private void confirmer(ActionEvent event) {
    Colis colisSelectionne = lsview.getSelectionModel().getSelectedItem();
    if (colisSelectionne != null) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    

    @FXML
private void partager(ActionEvent event) throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de partager votre position ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("Suivre.fxml"));
            pane3.getChildren().removeAll();
            pane3.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    @FXML
    private void afficher(ActionEvent event) {
                  
        ColisCRUD c = new ColisCRUD();
        
        List<Colis> list=c.afficher3();
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
    private void HandleButtonClick1(ActionEvent event) {
    }

    @FXML
    private void HandleButtonClick(ActionEvent event) {
    }




    


    
}

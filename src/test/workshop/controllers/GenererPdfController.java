/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import amena.utils.MyConnectionn;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import test.workshop.model.Colis;
import test.workshop.model.DocumentExpedition;
import test.workshop.services.ColisCRUD;
import test.workshop.services.DocumentExpeditionCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class GenererPdfController implements Initializable {

    @FXML
    private ListView<DocumentExpedition> lvColis;
    private static  DocumentExpedition  a ;
    
    @FXML
    private AnchorPane paneA2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DocumentExpeditionCRUD cc = new DocumentExpeditionCRUD();
        
        lvColis.getItems().addAll(cc.afficher());
        lvColis.setCellFactory(param -> new ListCell<DocumentExpedition>() {
    @Override
    protected void updateItem(DocumentExpedition item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText(/*"Nom expéditeur: " + item.getId_colis().getNomExpediteur()+
                    "\nAdresse expéditeur: " + item.getId_colis().getAdresseExpediteur() +
                    "\nNom destinataire: " + item.getId_colis().getNomDestinataire() +
                    "\nAdresse destinataire: " + item.getId_colis().getAdresseDestinataire() +
                    "\nDate d'expedition: " + item.getId_colis().getDateExpedition()+
                    "\nPoids: " + item.getId_colis().getPoids() + "kg"+*/
                    "\nDescription:"+item.getDescription());
        }
    }
});
    }
    // Récupération de la connexion à la base de données
    private Connection conn = MyConnectionn.getInstance().getConn();
   
    @FXML
    private void Imprimer(ActionEvent event) throws SQLException {
    PrintService printService = new PrintService(59); // Replace 1 with the ID of the document to be printed
            printService.setOnSucceeded(e -> {
                boolean success = printService.getValue();
                if (success) {
  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Document imprimé avec succès !");
        alert.showAndWait();                
               } else {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur !");
        alert.setHeaderText(null);
        alert.setContentText("ERREUR! !");
        alert.showAndWait();
                }
            });
            printService.start();
        System.out.println(a.getId());
             
  
        
        
       /*DocumentExpeditionCRUD.modifier(c);*/
        
        // Affichage du message de succès
      
  
       
    }

    @FXML
    private void Imprimer(MouseEvent event) {
        PrintService printService = new PrintService(59); // Replace 1 with the ID of the document to be printed
            printService.setOnSucceeded(e -> {
                boolean success = printService.getValue();
                if (success) {
  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Document imprimé avec succès !");
        alert.showAndWait();                
               } else {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur !");
        alert.setHeaderText(null);
        alert.setContentText("ERREUR! !");
        alert.showAndWait();
                }
            });
            printService.start();
    }

    
}

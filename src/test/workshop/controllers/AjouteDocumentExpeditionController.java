/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import test.workshop.model.Colis;
import test.workshop.model.DocumentExpedition;
import test.workshop.services.ColisCRUD;
import test.workshop.services.DocumentExpeditionCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouteDocumentExpeditionController {
    
    @FXML private ListView<Colis> lsview;
    @FXML private TextField descriptionField;
    
    private Colis selectedColis;
    
    @FXML
    private void initialize() {
        ColisCRUD colisCRUD = new ColisCRUD();
        List<Colis> colisList = colisCRUD.afficher4();
        ObservableList<Colis> observableList = FXCollections.observableArrayList(colisList);
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
                            "\nStatut: " + item.getStatut() +
                            "\nDate d'expedition: " + item.getDateExpedition() +
                            "\nPoids: " + item.getPoids() + "kg");
                }
            }
        });
        
        // Ajouter un listener pour le clic sur un élément de la liste
        lsview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedColis = newValue;
        });
    }
    
    @FXML
    private void ajouterDocumentExpedition(ActionEvent event) {
        // Vérifier que l'utilisateur a sélectionné un colis
        if (selectedColis == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Colis non sélectionné");
            alert.setContentText("Veuillez sélectionner un colis avant de continuer.");
            alert.showAndWait();
            return;
        }
        
        // Récupérer les valeurs des champs
        String description = descriptionField.getText();
        if (description == null) {
            description = "";
        }
        
        // Créer le document d'expédition
        DocumentExpedition documentExpedition = new DocumentExpedition();
        documentExpedition.setId_colis(selectedColis);
        documentExpedition.setStatut("En attente");
        documentExpedition.setDescription(description);
        documentExpedition.setDateSignature(Date.valueOf(LocalDate.now()));
        
        // Insérer le document d'expédition dans la base de données
        DocumentExpeditionCRUD documentExpeditionCRUD = new DocumentExpeditionCRUD();
        documentExpeditionCRUD.ajouter(documentExpedition);
        
        // Afficher un message de succès ou d'erreur
   
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Document d'expédition ajouté");
            alert.setContentText("Le document d'expédition a été ajouté avec succès.");
            alert.showAndWait();
            
            // Réinitialiser les champs du formulaire
            descriptionField.setText("");
            
            // Réinitialiser la sélection du colis
            selectedColis = null;
        

        
    }
}

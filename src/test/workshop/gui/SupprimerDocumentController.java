/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import test.workshop.model.DocumentExpedition;
import test.workshop.services.DocumentExpeditionCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SupprimerDocumentController {

    @FXML
    private ListView<DocumentExpedition> docListView;

    @FXML
    private Button btnSupp;

    private DocumentExpeditionCRUD docDAO;

    @FXML
    private void initialize() {
        docDAO = new DocumentExpeditionCRUD();
        docListView.getItems().setAll(docDAO.afficher());
        
    }

    @FXML
    private void handleSupprimerBtn() {
        DocumentExpedition selectedDoc = docListView.getSelectionModel().getSelectedItem();
        if (selectedDoc != null) {
            docDAO.supprimer(selectedDoc.getId());
            docListView.getItems().remove(selectedDoc);
            showAlert("Document d'expédition supprimé");
        } else {
            showAlert("Veuillez sélectionner un document d'expédition à supprimer");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}

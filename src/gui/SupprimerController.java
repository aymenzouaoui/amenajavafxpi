/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Annonce;
import services.AnnonceCRUD;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class SupprimerController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private ListView<Annonce> docListView;


    private AnnonceCRUD AN;
    @FXML
    private Button sup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AN = new AnnonceCRUD();
        docListView.getItems().setAll(AN.afficher());
    }  
   
    @FXML
    private void suppBtn(ActionEvent event) {
         Annonce selectedDoc = docListView.getSelectionModel().getSelectedItem();
        if (selectedDoc != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous certain de vouloir supprimer définitivement cette annonce ? Cette action est irréversible et toutes les données associées à cette annonce seront perdues.");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        AN.supprimer(selectedDoc.getId_annonce());
        docListView.getItems().remove(selectedDoc);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information");
        alert1.setHeaderText(null);
        alert1.setContentText("Annonce supprimé");
        alert1.showAndWait();
       // showAlert("Annonce supprimé");
        } else {
            //showAlert("Veuillez sélectionner une Annonce à supprimer");
            //alert1.setContentText("Veuillez sélectionner une Annonce à supprimer");
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information");
        alert1.setHeaderText(null);
        alert1.setContentText("Veuillez sélectionner une Annonce à supprimer");
        alert1.showAndWait();
        }
    }

     
    
}}

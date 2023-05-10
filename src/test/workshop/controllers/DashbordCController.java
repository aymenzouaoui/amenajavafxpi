/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import amena.gui.DashboardClient.DashboardController;
import amena.gui.LocationInterface.GestionLocationController;
import static amena.gui.LocationInterface.GestionLocationController.ids;
import static amena.gui.LocationInterface.GestionLocationController.v;
import static amena.gui.ReservationInterface.DetResController.idures;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import test.workshop.model.Colis;
import test.workshop.model.ColisReserver;
import test.workshop.services.ColisCRUD;
import test.workshop.services.ColisReserverCrud;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordCController implements Initializable {

    @FXML
    private AnchorPane paneA2;
    @FXML
    private WebView webview;
    @FXML
    private ListView<Colis> lsview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
                }

    @FXML
    private void ajouterColis(ActionEvent event) {
          try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("Ajout.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @FXML
    private void Traquer(ActionEvent event) {
          try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("Suivre.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void modifier(ActionEvent event) {
          try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/test/workshop/gui/Modifier.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        Colis selectedDoc = lsview.getSelectionModel().getSelectedItem();
        if (selectedDoc != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

            Optional<ButtonType> result = alert.showAndWait();
            
            ColisCRUD c = new ColisCRUD() ; 
        /*    
                    
            if (result.get() == ButtonType.OK){
                c.supprimer(selectedDoc.getId());
                lsview.getItems().remove(selectedDoc);
            showAlert("Colis supprimé");}
        } else {
            showAlert("Veuillez sélectionner un colis à supprimer");
        }*/
    }
    }
    }
    


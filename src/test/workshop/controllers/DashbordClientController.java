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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import test.workshop.gui.Accueil;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordClientController implements Initializable {

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
    private Button Ajouter;
    @FXML
    private Button Suivre;
    @FXML
    private Button modifier;
    @FXML
    private WebView webView;

    /**
     * 26c1fb8a6a4cc738fb7a1acd1425fb081b0296ba5dbe96e26c98713e751ce4fc@group.calendar.google.com
     * 991874853792-2c0pfe7nfk3cbt4ia12a0nm0qtcnjk09.apps.googleusercontent.com
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         WebEngine engine = webView.getEngine();
        engine.load("https://calendar.google.com/calendar/embed?src=26c1fb8a6a4cc738fb7a1acd1425fb081b0296ba5dbe96e26c98713e751ce4fc@group.calendar.google.com");
    }    


    @FXML
    private void afficher(ActionEvent event) {
                         
        ColisCRUD c = new ColisCRUD();
        
        List<Colis> list=c.afficher2();
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

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        /* Accueil accueil = new Accueil();
        Parent AjParent = FXMLLoader.load(getClass().getResource("/test/workshop/gui/FXML.fxml"));
        Scene profilScene = new Scene(AjParent);
        
        // Get the stage information
        Stage window = (Stage) Ajouter.getScene().getWindow();
        
        // Set the new scene
        window.setScene(profilScene);
        window.show();}*/
        /*                                 try {
        Parent sv;
        sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/test/workshop/gui/FXML.fxml"));
        pane4.getChildren().removeAll();
        pane4.getChildren().setAll(sv);
        } catch (IOException ex) {
        Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }*/
         try {
            Parent sv;
            sv = (VBox) FXMLLoader.load(getClass().getResource("../gui/FXML.fxml"));
            pane3.getChildren().removeAll();
            pane3.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    

    @FXML
    private void Suivre(ActionEvent event) throws IOException {
        /*                  Accueil accueil = new Accueil();
        Parent AjParent = FXMLLoader.load(getClass().getResource("Suivre.fxml"));
        Scene profilScene = new Scene(AjParent);
        
        // Get the stage information
        Stage window = (Stage) Ajouter.getScene().getWindow();
        
        // Set the new scene
        window.setScene(profilScene);
        window.show();*/
                         try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("Suivre.fxml"));
            pane3.getChildren().removeAll();
            pane3.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        /*                     Accueil accueil = new Accueil();
        Parent AjParent = FXMLLoader.load(getClass().getResource("/test/workshop/gui/Modifier.fxml"));
        Scene profilScene = new Scene(AjParent);
        
        // Get the stage information
        Stage window = (Stage) modifier.getScene().getWindow();
        
        // Set the new scene
        window.setScene(profilScene);
        window.show();*/
                    try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../gui/Modifier.fxml"));
            pane3.getChildren().removeAll();
            pane3.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
        
    
    


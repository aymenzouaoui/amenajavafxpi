/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.AController.test;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Annonce;
import model.Reaction;
import services.AnnonceCRUD;
import services.ReactionCRUD;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class TransporteurController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
private VBox vbox;
  @FXML
private ScrollPane scrollPane;
    @Override  
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
     
    AnnonceCRUD AC = new AnnonceCRUD();
    Annonce annonce = AC.getByID(AfficherTransporteurAnnonceController.test);
        System.out.println(annonce);
        System.out.println( AfficherTransporteurAnnonceController.test);
    if (annonce != null) {
       // Create a new AnchorPane to display the announcement details
         AnchorPane annoncePane = new AnchorPane();
            annoncePane.setPrefSize(600, 200); // taille de chaque annoncePane

            Label labelType = new Label("Type : " + annonce.getType());
            AnchorPane.setTopAnchor(labelType, 10.0);
            AnchorPane.setLeftAnchor(labelType, 10.0);

            Label labelVilleDep = new Label("Ville de départ : " + annonce.getVille_dep());
            AnchorPane.setTopAnchor(labelVilleDep, 30.0);
            AnchorPane.setLeftAnchor(labelVilleDep, 10.0);

            Label labelVilleArr = new Label("Ville d'arrivée : " + annonce.getVille_arr());
            AnchorPane.setTopAnchor(labelVilleArr, 50.0);
            AnchorPane.setLeftAnchor(labelVilleArr, 10.0);
            
            Label labelDateDep = new Label("Date de depart : " + annonce.getDate_dep());
            AnchorPane.setTopAnchor(labelDateDep, 70.0);
            AnchorPane.setLeftAnchor(labelDateDep, 10.0);
            
            Label labelDateArr = new Label("Date d'arrivée : " + annonce.getDate_arr());
            AnchorPane.setTopAnchor(labelDateArr, 90.0);
            AnchorPane.setLeftAnchor(labelDateArr, 10.0);
            
            Label labelPrix = new Label("Prix : " + annonce.getPrix());
            AnchorPane.setTopAnchor(labelPrix, 110.0);
            AnchorPane.setLeftAnchor(labelPrix, 10.0);
            
            Button btnInteresse = new Button("Plus de détails..");
            
             btnInteresse.setOnAction (event -> {
          
                try {
                    // Ouvrir l'interface d'ajout
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("offre.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(TransporteurController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                }
         );
        AnchorPane.setTopAnchor(btnInteresse, 10.0);
        AnchorPane.setRightAnchor(btnInteresse, 10.0);
       annoncePane.getChildren().addAll(labelType, labelVilleDep, labelVilleArr,labelDateArr,labelDateDep,labelPrix,btnInteresse);

        // Add the AnchorPane to the VBox
        vbox.getChildren().clear(); // Clear any previously displayed announcements
        vbox.getChildren().add(annoncePane);// Display the announcement details
        // ...
    } else {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Cette Annonce n'existe pas!");
        alert.showAndWait();
    }
        
        
     
    }  
}
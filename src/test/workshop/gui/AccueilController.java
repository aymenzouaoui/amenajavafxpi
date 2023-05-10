package test.workshop.gui;

import amena.gui.LocationInterface.GestionLocationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    private Button ajoutColisBtn;
    @FXML
    private Button supprimerColisBtn;
    @FXML
    private Button modifierColisBtn;
    @FXML
    private Button afficherColisBtn;
    @FXML
    private AnchorPane pane1;


    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Ajouter un colis"
    @FXML
    void onAjoutColis(ActionEvent event) throws IOException {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/test/workshop/controllers/Ajout.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        Accueil accueil = new Accueil();
        Parent AjParent = FXMLLoader.load(getClass().getResource("/test/workshop/controllers/Ajout.fxml"));
            Scene profilScene = new Scene(AjParent);

            // Get the stage information
            Stage window = (Stage) modifierColisBtn.getScene().getWindow();

            // Set the new scene
            window.setScene(profilScene);
            window.show();*/
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Supprimer un colis"
    @FXML
    void onSupprimerColis(ActionEvent event) throws IOException {
         try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("Supprimer.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Modifier un colis"
    @FXML
    void onModifierColis(ActionEvent event) throws IOException {
        
         try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("Modifier2.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Afficher les colis"
    @FXML
    void onAfficherColis(ActionEvent event) throws IOException {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("AfficherColis.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    private void openFXML(String fxml, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setController(controller);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(fxml);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
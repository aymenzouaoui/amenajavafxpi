/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;

import amena.gui.dashboard.First3Controller;
import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;
import amena.utils.MyConnectionn;
import digideramers.amena.models.Competition;
import static digidreamers.amena.gui.GestionGamificationController.c;
import digidreamers.amena.services.CompetitionCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Modifier2Controller implements Initializable {

    private TextField idfield;
    @FXML
    private TextField nomExField;
    @FXML
    private TextField adressExField;
    @FXML
    private TextField nomDesField;
    @FXML
    private TextField adressDesField;
    @FXML
    private TextField poidsField;
    @FXML
    private Button btnMod;
    @FXML
    private ListView<Colis> lvColis;
    private ColisCRUD ColisCRUD;
    private static  Colis  a ;
    @FXML
    private AnchorPane paneA2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ColisCRUD cc = new ColisCRUD();
        
        lvColis.getItems().addAll(cc.afficher());
        lvColis.setCellFactory(param -> new ListCell<Colis>() {
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

        lvColis.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Colis>() {
            public void changed(ObservableValue<? extends Colis> observable, Colis oldValue, Colis newValue) {
                a = lvColis.getSelectionModel().getSelectedItem();
                nomExField.setText(a.getNomExpediteur());

                adressExField.setText(a.getAdresseExpediteur());
                nomDesField.setText(a.getNomDestinataire());
                adressDesField.setText(a.getAdresseDestinataire());
                poidsField.setText(String.valueOf(a.getPoids()));

            }

        });

    }
    // Récupération de la connexion à la base de données
    private Connection conn = MyConnectionn.getInstance().getConn();
   
    @FXML
    private void ModifierColis(ActionEvent event) throws SQLException {
   
        ColisCRUD colisCRUD = new ColisCRUD();
        System.out.println(a.getId());
       
        String poids = poidsField.getText();
        if (!poids.matches("\\d+(\\.\\d+)?") || Float.parseFloat(poids) <= 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le poids doit être un nombre positif !");
        alert.showAndWait();
        return;
                }
        
        Colis c = new Colis(a.getId(), nomExField.getText(), adressExField.getText(), nomDesField.getText(), adressDesField.getText(), Float.parseFloat(poidsField.getText()));
        
        System.out.println(c);
        
        
        colisCRUD.modifier(c);
        
        // Affichage du message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Colis modifié avec succès !");
        alert.showAndWait();
        Stage stage = (Stage) btnMod.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void retournerPagePrecedente(ActionEvent event) throws IOException {
               try {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/test/workshop/gui/Accueil.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(First3Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}

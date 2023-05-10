package test.workshop.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;

public class AfficherColisController implements Initializable {

    @FXML
    private ListView<Colis> docListView;
    @FXML
    private GridPane colisGrid;
    @FXML
    private Label nomExpediteurLabel;
    @FXML
    private Label adresseExpediteurLabel;
    @FXML
    private Label nomDestinataireLabel;
    @FXML
    private Label adresseDestinataireLabel;
    @FXML
    private Label statutLabel;
    @FXML
    private Label dateExpeditionLabel;
    @FXML
    private Label poidsLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAfficherBtn() {
        ColisCRUD c = new ColisCRUD();
        List<Colis> list = c.afficher2();
        docListView.getItems().setAll(list);
        
        // Effacer les données précédentes du GridPane
        colisGrid.getChildren().removeAll(nomExpediteurLabel, adresseExpediteurLabel, 
                nomDestinataireLabel, adresseDestinataireLabel, statutLabel, dateExpeditionLabel, poidsLabel);
        
        // Afficher les informations du colis sélectionné dans le GridPane
        docListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nomExpediteurLabel.setText(newValue.getNomExpediteur());
                adresseExpediteurLabel.setText(newValue.getAdresseExpediteur());
                nomDestinataireLabel.setText(newValue.getNomDestinataire());
                adresseDestinataireLabel.setText(newValue.getAdresseDestinataire());
                statutLabel.setText(newValue.getStatut());
                dateExpeditionLabel.setText(newValue.getDateExpedition().toString());
                poidsLabel.setText(newValue.getPoids() + " kg");
                colisGrid.getChildren().addAll(nomExpediteurLabel, adresseExpediteurLabel, 
                        nomDestinataireLabel, adresseDestinataireLabel, statutLabel, dateExpeditionLabel, poidsLabel);
            }
        });
    }

    @FXML
    private void handleQuitterBtn() {
        Stage stage = (Stage) docListView.getScene().getWindow();
        stage.close();
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import test.workshop.model.Colis;
import org.json.JSONObject;
import test.workshop.services.ColisCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherColisController implements Initializable {

    @FXML
    private ListView<Colis> docListView;
    @FXML
    private Button afficherBtn;
    @FXML
    private Button quitterBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAfficherBtn(ActionEvent event) {
      
        ColisCRUD c = new ColisCRUD();
        
        List<Colis> list=c.afficher();
        ObservableList<Colis> observableList = FXCollections.observableArrayList(list);
        docListView.setItems(observableList);
        docListView.setCellFactory(param -> new ListCell<Colis>() {
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
    private void handleQuitterBtn(ActionEvent event) {
                 Stage stage = (Stage) quitterBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void retournerPagePrecedente(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    // fermer la fenêtre actuelle
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    currentStage.close();
    }
    @FXML
    private void handleGenerateStats(ActionEvent event) {

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Choix de statistiques");
    alert.setHeaderText("Veuillez choisir le type de statistiques à afficher");
    alert.setContentText("Choisissez une option :");

    ButtonType statutBtn = new ButtonType("Statuts des colis");
    ButtonType chartBtn = new ButtonType("Nombre de colis expédiés par date d'expédition");

    alert.getButtonTypes().setAll(statutBtn, chartBtn);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == statutBtn){
        // Afficher les statistiques sur les statuts des colis
    ColisCRUD c = new ColisCRUD();

    // Obtenir la liste des colis
    java.util.List<Colis> list = c.afficher2();

    // Créer une carte pour stocker le nombre de colis par statut
    Map<String, Integer> stats = new HashMap<>();
    for (Colis colis : list) {
        String statut = colis.getStatut();
        if (stats.containsKey(statut)) {
            stats.put(statut, stats.get(statut) + 1);
        } else {
            stats.put(statut, 1);
        }
    }

    // Créer un graphique de barres pour afficher les statistiques
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Statut des colis");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Nombre de colis");

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Statistiques des colis par statut");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Nombre de colis");

    for (Map.Entry<String, Integer> entry : stats.entrySet()) {
        String statut = entry.getKey();
        Integer count = entry.getValue();
        series.getData().add(new XYChart.Data<>(statut, count));
    }

    barChart.getData().add(series);

    // Afficher le graphique dans une nouvelle fenêtre
    Stage stage = new Stage();
    stage.setScene(new Scene(barChart, 800, 600));
    stage.show();
    } else if (result.get() == chartBtn) {ColisCRUD c = new ColisCRUD();

    // Obtenir la liste des colis
    java.util.List<Colis> list = c.afficher2();

    // Créer une carte pour stocker le nombre de colis par date d'expédition
    Map<Date, Integer> stats = new HashMap<>();
    for (Colis colis : list) {
        Date date = colis.getDateExpedition();
        if (stats.containsKey(date)) {
            stats.put(date, stats.get(date) + 1);
        } else {
            stats.put(date, 1);
        }
    }

    // Créer un graphique de barres pour afficher les statistiques
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Date d'expédition");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Nombre de colis");

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Statistiques des colis par date d'expédition");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Nombre de colis");

    for (Map.Entry<Date, Integer> entry : stats.entrySet()) {
        Date date = entry.getKey();
        Integer count = entry.getValue();
        series.getData().add(new XYChart.Data<>(date.toString(), count));
    }

    barChart.getData().add(series);

    // Afficher le graphique dans une nouvelle fenêtre
    Stage stage = new Stage();
    stage.setScene(new Scene(barChart, 800, 600));
    stage.show();
    
}



}
}

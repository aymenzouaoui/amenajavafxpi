/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import test.workshop.gui.ModifierController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SuivreController  {

    @FXML
    private ImageView mapView;
    private final String API_KEY = "pk.eyJ1IjoibWFsZWt6YWlkaSIsImEiOiJjbGVvamRjOGYwMnhwM3ZvMXNnamc0MHVzIn0.eLHD_XEu1Wev8OUfe4eD-Q";
    @FXML
    private AnchorPane paneA2;

    public void initialize() {
        try {
            // Demande la position actuelle du transporteur (remplacez les valeurs par défaut avec les valeurs réelles)
            double lat = 36.898799;
            double lon = 10.1812702;

            // Récupère l'image de la carte statique centrée sur la position du transporteur
            String imageUrl = "https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/" +
                    "pin-s+9ed4bd(" + lon + "," + lat + ")/" + lon + "," + lat + ",14/600x400" +
                    "?access_token=" + API_KEY;

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    String response = scanner.useDelimiter("\\A").next();
                }
                Image image = new Image(imageUrl);
                mapView.setImage(image);
            } else {
                System.out.println("Erreur lors de la récupération de l'image de la carte.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backbtn(ActionEvent event) {
       try{
        Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/test/workshop/controllers/DashbordC.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}                
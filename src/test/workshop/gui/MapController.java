/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author hp
 */
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapController {
    @FXML
    private AnchorPane mapPane;

    public void initialize() {
        String token = "pk.eyJ1IjoibWFsZWt6YWlkaSIsImEiOiJjbGVsZXdwdWIwdXM2M3luNGNqZXJybGJwIn0.pT6KgpnGNkMUdaZ-8vq08w";
        String url = "https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/10.1928,36.5241,10,0,0/800x600?access_token=" + token;

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Image image = new Image(con.getInputStream());
                ImageView imageView = new ImageView(image);
                mapPane.getChildren().add(imageView);
            }
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
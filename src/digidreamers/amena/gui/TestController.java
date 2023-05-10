/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import org.json.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TestController {
    @FXML
    private Label temperatureLabel;
    @FXML
    private Button btnRet;
    @FXML
    private AnchorPane pane26;

    public void initialize() throws MalformedURLException, IOException {
        String city = "Tunis";
        String countryCode = "tn";
        String apiKey = "b5f368112adba08ef3e1342643035eed"; // Replace with your actual API key

        // Create the API endpoint URL
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s", city, countryCode, apiKey);

        // Create an HTTP connection to the API endpoint
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the API response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the API response
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject main = jsonObject.getJSONObject("main");
        Double temperatureKelvin = main.getDouble("temp");
        Double temperatureCelsius = temperatureKelvin - 273.15;

        // Update the label with the temperature in Celsius
        temperatureLabel.setText(String.format("%.1f °C", temperatureCelsius));
    }

    @FXML
    private void retournerverTT(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfaceTransporteur.fxml"));
            pane26.getChildren().removeAll();
            pane26.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DetailsGiftsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
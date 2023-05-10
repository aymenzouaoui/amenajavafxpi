package test.workshop.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PositionController {

    @FXML
    private ImageView mapView;

    private final String API_KEY = "pk.eyJ1IjoibWFsZWt6YWlkaSIsImEiOiJjbGVvamRjOGYwMnhwM3ZvMXNnamc0MHVzIn0.eLHD_XEu1Wev8OUfe4eD-Q";

    @FXML
    public void onSharePositionClicked() {
        try {
            // Demande la position actuelle du transporteur (remplacez les valeurs par défaut avec les valeurs réelles)
            double lat = 36.883965;
            double lon = 10.301462;

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
                Scanner scanner = new Scanner(connection.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();
                Image image = new Image(imageUrl);
                mapView.setImage(image);
            } else {
                System.out.println("Erreur lors de la récupération de l'image de la carte.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}                      


















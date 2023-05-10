package test.workshop.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Accueil extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Charger la vue de l'interface d'accueil
        Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        primaryStage.setTitle("Accueil");

        // Créer une scène avec la vue de l'interface d'accueil
        Scene scene = new Scene(root, 600, 400);

        // Afficher la scène
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
public void openFXML(String fxml, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setController(controller);

            // Charger la vue correspondante à l'interface demandée
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(fxml);

            // Créer une scène avec la vue chargée
            Scene scene = new Scene(root, 600, 400);

            // Afficher la scène
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
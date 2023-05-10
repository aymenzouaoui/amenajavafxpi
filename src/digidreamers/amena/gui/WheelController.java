/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class WheelController implements Initializable {

    @FXML
    private ImageView wheel;
    @FXML
    private Button spinButton;
    @FXML
    private AnchorPane AnchorPane;
    private final String[] gifts = {"Score : 12", "Score : 0 ", "Score : 50", "Score: 0", "Score : 0", "Score : 2", "Score : 100", "Score : 0", "Score : 0"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinButton.setOnAction(event -> spinWheel());
    }

    @FXML
    private void spinWheel() {
        spinButton.setDisable(true);

        double angle = Math.random() * 360;
        double duration = 2.0;
        double offset = Math.random() * 360 / gifts.length;

        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), wheel);
        rotate.setByAngle(360 * 3 + angle + offset);
        rotate.setOnFinished(event -> {
            // Enable the spin button after animation
            spinButton.setDisable(false);

            // Select a random gift and display it
            String gift = gifts[new Random().nextInt(gifts.length)];
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setHeaderText("You've won a score!");
                alert.setContentText("You won: " + gift);
                alert.showAndWait();
            });
        });
        rotate.play();
    }
}

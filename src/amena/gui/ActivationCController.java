/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.Identifier_votre_compteController.emailS;
import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ActivationCController implements Initializable {

    @FXML
    private TextField fxToken;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnenvoi;
    @FXML
    private Label fxtimer;

    private String email;
    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleActivationButtonClick(ActionEvent event) throws SQLException, IOException {
        String token = fxToken.getText();
        UserService userService = new UserService();
        User user = userService.getUserByEmai(emailS);

        if (token.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le champ.");
            alert.showAndWait();
            return;
        }

        if (token.equals(user.getToken())) {
            user.setStatus(true);
            userService.modifier(user);

            Stage stage = (Stage) btnenvoi.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = loader.load();

            ResetPasswordController resetPasswordController = loader.getController();
            resetPasswordController.setCurrentUser(user);

            Stage resetPasswordStage = new Stage();
            resetPasswordStage.setScene(new Scene(root));
            resetPasswordStage.show();
            updateTimer(user);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La clé d'activation est incorrecte.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleQuitterBtn(ActionEvent event) {
        Stage stage = (Stage) btnenvoi.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void updateTimer(User user) {
        long remainingTime = user.getTokenExpirationDate().getTime() - System.currentTimeMillis();
        if (remainingTime < 0) {
            fxtimer.setText("Le token a expiré");
        } else {
            long seconds = remainingTime / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            String remainingTimeString = String.format("%02d:%02d:%02d:%02d", days, hours % 24, minutes % 60, seconds % 60);
            fxtimer.setText("Temps restant : " + remainingTimeString);
        }
    }
    
    
}

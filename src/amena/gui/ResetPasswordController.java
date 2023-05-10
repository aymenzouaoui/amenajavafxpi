package amena.gui;

import amena.model.User;
import amena.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ResetPasswordController {

    @FXML
    private PasswordField fxmotpasse;

    @FXML
    private PasswordField fxCmotpasse;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @FXML
    void Comfirmer(ActionEvent event) throws SQLException, IOException {
        String motdepasse = fxmotpasse.getText();
        String confirmer = fxCmotpasse.getText();

        if (motdepasse.isEmpty() || confirmer.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else if (!motdepasse.equals(confirmer)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Les deux mots de passe ne correspondent pas.");
            alert.showAndWait();
        } else {
            UserService u = new UserService();
            currentUser.setMot_pass(motdepasse);

            u.modifierPassword(currentUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mot de passe réinitialisé");
            alert.setHeaderText(null);
            alert.setContentText("Votre mot de passe a été réinitialisé avec succès.");
            alert.showAndWait();

            Stage stage = (Stage) fxmotpasse.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginAccount.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.show();
        }
    }

}

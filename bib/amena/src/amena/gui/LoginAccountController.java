package amena.gui;

import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginAccountController implements Initializable {

    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxmotpass;
    @FXML
    private Button fxconnetc;

    private User user;
    private UserService userService;
    @FXML
    private Button fxCreate;
    @FXML
    private Text fxoubier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Validate user input and show error messages if necessary.
     */
    private boolean validateInput() throws SQLException, NoSuchAlgorithmException {
        boolean isValid = true;
        String email = fxemail.getText();
        String password = fxmotpass.getText();
        password = hashPassword(password);

        UserService user = new UserService();

        // Validate email address
        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
            isValid = false;
            fxemail.setStyle("-fx-border-color: red;");
        } else {
            fxemail.setStyle("");
        }

        // Validate password
        if (password.isEmpty()) {
            isValid = false;
            fxmotpass.setStyle("-fx-border-color: red;");
        } else {
            fxmotpass.setStyle("");
        }
        if (!user.FoundUser(email, password)) {
            isValid = false;

        }

        return isValid;
    }

    @FXML
    private void handleConnectButtonAction() throws IOException, SQLException, NoSuchAlgorithmException {
        if (validateInput()) {

            //User u = userService.getUserByEmai(email); // Récupérer l'utilisateur connecté
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));

            Parent root = loader.load();
            ProfilController dc = loader.getController();
            String email = fxemail.getText();
          
            dc.setUserInformation(email);

            // Create a new scene with the loaded FXML file and show it
            Scene scene = new Scene(root);
            Stage stage = (Stage) fxconnetc.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedhash);
    }

    @FXML
    private void CretateAccount(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    private void ForgetPassword(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPasword.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
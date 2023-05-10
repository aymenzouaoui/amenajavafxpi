/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.model.User;
import amena.services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class HiController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField cinField;
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private PasswordField motDePasseField;
    @FXML
    private PasswordField confirmationField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField numTelField;
    @FXML
    private Button choisirImageButton;
    @FXML
    private ImageView imageView;
    private String imagePath;
    @FXML
    private Button ajouterButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(new FileInputStream(selectedFile));
            imageView.setImage(image);
            imagePath = selectedFile.getPath();
        }
    }

    @FXML
    private void ajouteruser(ActionEvent event) throws IOException {

        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || adresseField.getText().isEmpty()
                || dateNaissancePicker.getValue() == null || cinField.getText().isEmpty() || emailField.getText().isEmpty()
                || motDePasseField.getText().isEmpty() || confirmationField.getText().isEmpty() || numTelField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        if (!motDePasseField.getText().equals(confirmationField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Les mots de passe ne correspondent pas.");
            alert.showAndWait();
            return;
        }
         
        try {

            // Generate a unique token
            // String token = generateToken();
            // Create a new User object with the form data
            User user = new User();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            user.setAdress(adresseField.getText());
            Date Date_naissance =(Date.valueOf(dateNaissancePicker.getValue()));
            user.setCin(cinField.getText());
            String email = emailField.getText();
            String motpass = motDePasseField.getText();
            //user.setToken(token);
            user.setNum(numTelField.getText());
           String Image=(imagePath);

            // Add the user to the database
            UserService userDao = new UserService();
            User k = new User(nom, motpass, nom, prenom, Date_naissance, Date_naissance, true, motpass, email,email, email, email, email, email);
            userDao.ajouter(k);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText(null);
            alert.setContentText("L'utilisateur a été ajouté avec succès.");
            alert.showAndWait();

            // Clear the form
            clearForm();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ajouter l'utilisateur. Veuillez réessayer.");
            alert.showAndWait();
        }

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Create a new scene with the root and set it on the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void clearForm() {
        nomField.setText("ddddddd");
        prenomField.setText("cccccccc");
        adresseField.setText("");
        dateNaissancePicker.setValue(null);
        cinField.setText("ccccccc");
        emailField.setText("");
        motDePasseField.setText("");
        confirmationField.setText("");

    }

    @FXML
    private void redirectToLogin(ActionEvent event) throws IOException {
        // Load the login.fxml file

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.model.User;
import amena.services.UserService;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    // private TextField fxcin;
    @FXML
    private TextField fxemail;

    @FXML
    private TextField fxmotpass;
    @FXML
    private Button fxcreate;
    @FXML
    private DatePicker fxdateNaissance;

    @FXML
    private CheckBox btnTransporteur;
    @FXML
    private CheckBox btnClient;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnumtel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void addperson(ActionEvent event) throws SQLException, IOException {

        String role = "";
        if (btnTransporteur.isSelected()) {
            role = "[\"ROLE_TRANSPORTEUR\"]";

        } else if (btnClient.isSelected()) {
            role = "[\"ROLE_USER\"]";
        }

        if (btnTransporteur.isSelected() && btnClient.isSelected()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("SVP selectionne seulment un role");
            alert.showAndWait();
            return;
        }
        // get the current date and time
        Date date = new java.sql.Date(new java.util.Date().getTime());

        // get the user's input from the text fields
        String nom = fxnom.getText();
        String prenom = fxprenom.getText();
        String cin = fxcin.getText();

        String email = fxemail.getText();
        String num = fxnumtel.getText();
        String motpass = fxmotpass.getText();

        LocalDate localDate = fxdateNaissance.getValue();
        Date dateNaissance = Date.valueOf(localDate);

        String token = generateToken();

        UserService pc = new UserService();
        User existingUser = pc.getUserByEmai(fxemail.getText());
        System.out.println(existingUser);
        User k = new User(cin, motpass, nom, prenom,  dateNaissance , date, true, motpass, email, role, "0","http://localhost/img/useravatar.jpg", date, token, date, num);
        // validate the input

        if (nom.isEmpty() || prenom.isEmpty() || /*adress.isEmpty() ||cin.isEmpty() || */ email.isEmpty() || motpass.isEmpty()) {
            // display an error message if any of the fields are empty
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            // display an error message if the email address is not valid
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address");
            alert.showAndWait();
            return;
        } // check if the email already exists in the database
        else if (existingUser != null) {
            // display an error message if the email already exists
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This email address already exists");
            alert.showAndWait();
            return;

        }

        // create a new user object
        // add the user to the database
        /*   
    if (cin.length() != 8 && !cin.matches("[0-9]+")) {
        // display an error message if the CIN is not valid
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid CIN (8 digits)");
        alert.showAndWait();
        return;
    }
         */
        // add the user to the database using a UserService object
        pc.ajouter(k);

        // Load profil.fxml file
        Parent profilParent = FXMLLoader.load(getClass().getResource("LoginAccount.fxml"));
        Scene profilScene = new Scene(profilParent);

        // Get the stage information
        Stage window = (Stage) fxcreate.getScene().getWindow();

        // Set the new scene
        window.setScene(profilScene);
        window.show();
    }

    public String generateToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        return token;
    }

}

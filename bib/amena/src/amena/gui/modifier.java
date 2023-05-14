/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.Role;
import amena.model.User;
import amena.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class modifier implements Initializable {

    Role b = new Role(51, "client");

    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxxadress;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxemail;
    @FXML
    private Button chat;
    @FXML
    private Button quitterBtn;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnmodifier;
    @FXML
    private PasswordField fxpassword;
    @FXML
    private DatePicker fxdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifierUtilisateur(ActionEvent event) throws SQLException {
        UserService u = new UserService();

        User user = u.getUserByEmai(amena.gui.ProfilController.semail);

        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir modifier cet utilisateur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                String nom = fxnom.getText();
                String prenom = fxprenom.getText();
                String adresse = fxxadress.getText();
                String cin = fxcin.getText();
                String email = fxemail.getText();
                String password = fxpassword.getText();

                LocalDate dateNaissance = fxdate.getValue();
                /*
                if (!dateNaissanceTextField.getEditor().getText().trim().isEmpty()) {
                    try {
                        dateNaissance = dateNaissanceTextField.getValue();
                    } catch (DateTimeException ex) {
                        Alert dateAlert = new Alert(Alert.AlertType.ERROR);
                        dateAlert.setTitle("Erreur");
                        dateAlert.setHeaderText(null);
                        dateAlert.setContentText("Veuillez saisir une date de naissance valide !");
                        dateAlert.showAndWait();
                        return;
                    }
                }*/

                if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || cin.isEmpty() || email.isEmpty()) {
                    Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
                    emptyAlert.setTitle("Erreur");
                    emptyAlert.setHeaderText(null);
                    emptyAlert.setContentText("Tous les champs sont obligatoires !");
                    emptyAlert.showAndWait();
                    return;
                }

                if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                    Alert emailAlert = new Alert(Alert.AlertType.ERROR);
                    emailAlert.setTitle("Erreur");
                    emailAlert.setHeaderText(null);
                    emailAlert.setContentText("Veuillez saisir une adresse email valide !");
                    emailAlert.showAndWait();
                    return;
                }

                if (cin.length() != 8) {
                    Alert cinAlert = new Alert(Alert.AlertType.ERROR);
                    cinAlert.setTitle("Erreur");
                    cinAlert.setHeaderText(null);
                    cinAlert.setContentText("Veuillez saisir un numéro de cin valide (8 chiffres) !");
                    cinAlert.showAndWait();
                    return;
                }
                UserService userService = new UserService();
                if (!userService.FoundUser(semail, fxpassword.getText())) {

                    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
                    User u1 = new User(user.getId(), cin, adresse, nom, prenom, date, true, user.getMot_pass(), email, b);
                    user.setId(user.getId());
                    user.setEmail(email);
                    user.setNom(nom);
                    user.setPrenom(prenom);
                    user.setAdress(adresse);
                    user.setCin(cin);
                    //user.setMot_pass(user.getMot_pass());
                    // user.setDate_naissance(dateNaissance);
                    //user.setRole(user.getRole());
                    //user.setDate_creation_c(date_creation_c);
                    //  UserService userService = new UserService();

                    userService.modifier(u1);
                } else {
                    Alert selectAlert = new Alert(Alert.AlertType.ERROR);
                    selectAlert.setTitle("Erreur");
                    selectAlert.setHeaderText(null);
                    selectAlert.setContentText("Veuillez saisir le mot passe correct!");
                    selectAlert.showAndWait();

                }
            }
        } else {
            Alert selectAlert = new Alert(Alert.AlertType.ERROR);
            selectAlert.setTitle("Erreur");
            selectAlert.setHeaderText(null);
            selectAlert.setContentText("Veuillez remplir tous champ !");
            selectAlert.showAndWait();
        }
    }

    @FXML
    private void chat(ActionEvent event) {
    }

    @FXML
    private void handleQuitterBtn(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

}

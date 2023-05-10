/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.ProfilController.semail;

import amena.model.User;
import amena.services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser.ExtensionFilter;
import test.workshop.gui.ModifierController;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class modifier implements Initializable {

 
    private UserService userService;
    @FXML
    private AnchorPane PuserM;
    @FXML
    private JFXTextField fxnom;
    @FXML
    private JFXTextField fxprenom;
    @FXML
    private JFXTextField fxxadress;
    @FXML
    private JFXTextField fxcin;
    @FXML
    private JFXTextField fxemail;
    @FXML
    private JFXCheckBox fxtransorteur;
    @FXML
    private JFXCheckBox fxclient;
    @FXML
    private JFXTextField fxnum;
    @FXML
    private JFXPasswordField fxpassword;
    @FXML
    private JFXDatePicker fxdaten;
    @FXML
    private Button chat;
    @FXML
    private Button quitterBtn;
    @FXML
    private Button btnsuprimer;
    @FXML
    private ImageView img;
    @FXML
    private Label date;
    @FXML
    private JFXButton btnimg;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            userService = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(modifier.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user = userService.getUserByEmai(semail);
        // récupérer l'utilisateur sélectionné
        int a = user.getId();
        fxemail.setText(user.getEmail());
        fxnom.setText(user.getNom());
        fxprenom.setText(user.getPrenom());
        fxxadress.setText(user.getAdress());
        fxcin.setText(user.getCin());
        fxnum.setText(user.getNum());
        // convert java.sql.Date to LocalDate and set it in DatePicker
            java.sql.Date sqlDate = user.getDate_naissance();
            LocalDate localDate = sqlDate.toLocalDate();
            fxdaten.setValue(localDate);

        String role = user.getRole();
        if (role.equals("transporteur")) {
            fxtransorteur.setSelected(true);
            fxclient.setSelected(false);
        } else if (role.equals("client")) {
            fxtransorteur.setSelected(false);
            fxclient.setSelected(true);
        }

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
                String numtel = fxnum.getText();

                String role = "";

                if (fxtransorteur.isSelected() && !fxclient.isSelected()) {
                    role = "transporteur";
                } else if (fxclient.isSelected() && !fxtransorteur.isSelected()) {
                    role = "client";
                } else if (fxtransorteur.isSelected() && fxclient.isSelected()) {
                    // Display an alert to ask the user to choose only one role
                    Alert roleAlert = new Alert(Alert.AlertType.ERROR);
                    roleAlert.setTitle("Erreur");
                    roleAlert.setHeaderText(null);
                    roleAlert.setContentText("Veuillez choisir seulement un rôle !");
                    roleAlert.showAndWait();
                    return; // exit the method without modifying the user
                }

                LocalDate dateNaissance = fxdaten.getValue();

                if (!fxdaten.getEditor().getText().trim().isEmpty()) {
                    try {
                        dateNaissance = fxdaten.getValue();
                    } catch (DateTimeException ex) {
                        Alert dateAlert = new Alert(Alert.AlertType.ERROR);
                        dateAlert.setTitle("Erreur");
                        dateAlert.setHeaderText(null);
                        dateAlert.setContentText("Veuillez saisir une date de naissance valide !");
                        dateAlert.showAndWait();
                        return;
                    }
                }
                if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() /*|| cin.isEmpty()*/ || email.isEmpty()) {
                    Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
                    emptyAlert.setTitle("Erreur");
                    emptyAlert.setHeaderText(null);
                    emptyAlert.setContentText("Tous les champs sont obligatoires !");
                    emptyAlert.showAndWait();

                }

                if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                    Alert emailAlert = new Alert(Alert.AlertType.ERROR);
                    emailAlert.setTitle("Erreur");
                    emailAlert.setHeaderText(null);
                    emailAlert.setContentText("Veuillez saisir une adresse email valide !");
                    emailAlert.showAndWait();

                }

                if (cin.length() != 8) {
                    Alert cinAlert = new Alert(Alert.AlertType.ERROR);
                    cinAlert.setTitle("Erreur");
                    cinAlert.setHeaderText(null);
                    cinAlert.setContentText("Veuillez saisir un numéro de cin valide (8 chiffres) !");
                    cinAlert.showAndWait();

                }

                if (!userService.FoundUser(semail, fxpassword.getText())) {

                    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
                    // User u1 = new User(user.getId(), cin, adresse, nom, prenom, date, role, email, role);
                    User u1 = userService.getUserByEmai(semail);
                    System.out.println(u1);
                    u1.setEmail(email);
                    u1.setNom(nom);
                    u1.setPrenom(prenom);
                    u1.setAdress(adresse);
                    u1.setCin(cin);
                    u1.setNum(numtel);

                    // user.setMot_pass(password);
                    //   user.setDate_naissance(dateNaissance);
                    u1.setRole(user.getRole());

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
    private void chat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleQuitterBtn(ActionEvent event) {
        Stage stage = (Stage) quitterBtn.getScene().getWindow();
        stage.close();

        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/test/workshop/controllers/DashbordC.fxml"));
            PuserM.getChildren().removeAll();
            PuserM.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        UserService p = new UserService();
        User u1 = p.getUserByEmai(semail);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            p.supprimer(u1.getId());
        }
    }

   @FXML
void handleUploadButtonAction() throws IOException {
    FileChooser fileChooser = new FileChooser();
    User u1 = userService.getUserByEmai(semail);
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(btnimg.getScene().getWindow());
    if (selectedFile != null) {
        try {
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);

            String fileName = selectedFile.getName();
            File destinationFile = new File("C:/xampp/htdocs/img/" + fileName.trim());

            // Copier le fichier sélectionné vers le dossier de destination
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Mettre à jour l'URL de l'image dans l'objet utilisateur
            u1.setImage("http://localhost/img/" + fileName.trim());
            userService.modifier(u1);

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la lecture de l'image.");
            alert.showAndWait();
        }
    }


    }
}

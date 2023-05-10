/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.LoginAccountController.hashPassword;
import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ModifiermotpasseController implements Initializable {

    @FXML
    private PasswordField fxmdp;
    @FXML
    private TextField fxnmdp;
    @FXML
    private TextField fxcnmdp;
    @FXML
    private Button btnmodifer;
    @FXML
    private Button btnprof;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void handleModifierBtn(ActionEvent event) throws SQLException, NoSuchAlgorithmException {

        UserService p = new UserService();

        User u = new UserService().getUserByEmai(semail);
        System.out.println(u);
        String motpaas = u.getMot_pass();
        System.out.println(motpaas);
        String ancienMdp = hashPassword(fxmdp.getText()); // ici le mot de passe actuel de l'utilisateur
        String nouveauMdp = hashPassword(fxnmdp.getText());
        System.out.println(nouveauMdp);
        String confirmerMdp = hashPassword(fxcnmdp.getText());
        System.out.println(confirmerMdp);
        // Vérifier que l'utilisateur a entré correctement l'ancien mot de passe
        if (!ancienMdp.equals(motpaas)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Mot de passe incorrect");
            alert.setContentText("Le mot de passe que vous avez entré ne correspond pas à votre mot de passe actuel.");
            alert.showAndWait();
            return;
        }

        // Vérifier que le nouveau mot de passe correspond à la confirmation
        if (!nouveauMdp.equals(confirmerMdp)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Nouveaux mots de passe ne correspondent pas");
            alert.setContentText("Les nouveaux mots de passe que vous avez entrés ne correspondent pas.");
            alert.showAndWait();
            return;
        }

        // Si tout est correct, modifier le mot de passe
        ancienMdp = nouveauMdp;
        u.setMot_pass(nouveauMdp);
        p.modifierPassword(u);

        // Afficher une alerte de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Mot de passe modifié");
        alert.setContentText("Votre mot de passe a été modifié avec succès !");
        alert.showAndWait();
    }

    void handleProfilBtn(ActionEvent event) {
        // ici le code pour ouvrir la fenêtre de profil de l'utilisateur
    }

}

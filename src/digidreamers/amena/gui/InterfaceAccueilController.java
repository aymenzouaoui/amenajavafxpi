/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class InterfaceAccueilController implements Initializable {

    @FXML
    private Button btnDemande;
    @FXML
    private Button btnOffre;
    @FXML
    private Button contact;
    @FXML
    private Button cnx;
    @FXML
    private Button sinscrire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void switchDemand(ActionEvent event) {
        btnDemande.setStyle("-fx-background-color: #55d5e0 ; -fx-background-radius: 30px;");
        btnOffre.setStyle("-fx-background-color:  #0b303c; -fx-background-radius: 30px;");
    }

    @FXML
    private void switchOffre(ActionEvent event) {
        btnDemande.setStyle("-fx-background-color: #0b303c; -fx-background-radius: 30px;");
        btnOffre.setStyle("-fx-background-color: #55d5e0; -fx-background-radius: 30px;");
    }

    @FXML
    private void contacter(ActionEvent event) {
    }

    @FXML
    private void connexion(ActionEvent event) {
    }

    @FXML
    private void inscription(ActionEvent event) {
    }

}

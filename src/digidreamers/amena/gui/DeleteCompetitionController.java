/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import static digidreamers.amena.gui.GestionGamificationController.c;
import digidreamers.amena.services.CompetitionCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class DeleteCompetitionController implements Initializable {

    @FXML
    private Button buttonA;
    @FXML
    private Button btnS;
    @FXML
    private AnchorPane pane9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retournerGest(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("GestionGamification.fxml"));
            pane9.getChildren().removeAll();
            pane9.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteComp(ActionEvent event) {

        CompetitionCRUD cc = new CompetitionCRUD();
        cc.supprimer(c.getId());

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class QuizController implements Initializable {

    @FXML
    private RadioButton q1a1;
    @FXML
    private RadioButton q1a2;
    @FXML
    private RadioButton q1a3;
    @FXML
    private RadioButton q1a4;
    @FXML
    private RadioButton q2a1;
    @FXML
    private RadioButton q2a2;
    @FXML
    private RadioButton q2a3;
    @FXML
    private RadioButton q2a4;
    @FXML
    private RadioButton q3a1;
    @FXML
    private RadioButton q3a2;
    @FXML
    private RadioButton q3a3;
    @FXML
    private RadioButton q3a4;
    @FXML
    private RadioButton q4a1;
    @FXML
    private RadioButton q4a2;
    @FXML
    private RadioButton q4a3;
    @FXML
    private RadioButton q4a4;
    @FXML
    private RadioButton q5a1;
    @FXML
    private RadioButton q5a2;
    @FXML
    private RadioButton q5a3;
    @FXML
    private RadioButton q5a4;
    @FXML
    private RadioButton q6a1;
    @FXML
    private RadioButton q6a2;
    @FXML
    private RadioButton q6a3;
    @FXML
    private RadioButton q6a4;
    @FXML
    private RadioButton q7a1;
    @FXML
    private RadioButton q7a2;
    @FXML
    private RadioButton q7a3;
    @FXML
    private RadioButton q7a4;
    @FXML
    private RadioButton q8a1;
    @FXML
    private RadioButton q8a2;
    @FXML
    private RadioButton q8a3;
    @FXML
    private RadioButton q8a4;
    @FXML
    private RadioButton q9a1;
    @FXML
    private RadioButton q9a2;
    @FXML
    private RadioButton q9a3;
    @FXML
    private RadioButton q9a4;
    @FXML
    private RadioButton q10a1;
    @FXML
    private RadioButton q10a2;
    @FXML
    private RadioButton q10a3;
    @FXML
    private RadioButton q10a4;
    @FXML
    private Button btnSub;
    @FXML
    private Label resultLabel;
    @FXML
    private ScrollPane scrollPane1;
public static int sc;
    @FXML
    private AnchorPane pane21;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitQu(ActionEvent event) throws SQLException {
               int score = 0;
        if (q1a1.isSelected()) {
            score++;
        }
        if (q2a3.isSelected()) {
            score++;
        }
        if (q3a2.isSelected()) {
            score++;
        }
        if (q4a4.isSelected()) {
            score++;
        }
        if (q5a3.isSelected()) {
            score++;
        }
        if (q6a2.isSelected()) {
            score++;
        }
        if (q7a4.isSelected()) {
            score++;
        }
        if (q8a2.isSelected()) {
            score++;
        }
        if (q9a2.isSelected()) {
            score++;
        }
        if (q10a2.isSelected()) {
            score++;
        }
                  sc = score;

        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("FinalResult.fxml"));
            pane21.getChildren().removeAll();
            pane21.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DetailsGiftsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserService u = new UserService();
        User p = u.getUserByEmai(semail);
        u.modifierscore(score+Integer.parseInt(p.getScore()));
        
         
       // resultLabel.setText("Your score is " + score + " out of 10");
    
    }
    
    
}

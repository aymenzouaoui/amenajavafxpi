/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import digideramers.amena.models.Competition;
import static digidreamers.amena.gui.GestionGamificationController.c;
import digidreamers.amena.services.CompetitionCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class InterfaceTransporteurController implements Initializable {

    @FXML
    private TextField idScore;
    @FXML
    private TextField idDiam;
    @FXML
    private ListView<Competition> lvC;
    @FXML
    private Button btnParticiper;
    @FXML
    private AnchorPane pane17;
    @FXML
    private Button btnAffGifts;
    @FXML
    private Button btnConvert;
    @FXML
    private Button btnM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserService usr = null; 
        try {
            usr = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceTransporteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User u = usr.getUserByEmai(semail);
        
        idScore.setText(u.getScore());
        
        
        
        btnParticiper.setDisable(true);
        CompetitionCRUD cc = new CompetitionCRUD();
        lvC.getItems().addAll(cc.afficher());

        lvC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Competition>() {
            @Override
            public void changed(ObservableValue<? extends Competition> observable, Competition oldValue, Competition newValue) {
                c = lvC.getSelectionModel().getSelectedItem();
                btnParticiper.setDisable(false);

            }
        });
    }

    @FXML
    private void compParticipation(ActionEvent event) {
        c.setNbp(c.getNbp() + 1);
        CompetitionCRUD cc = new CompetitionCRUD();
        cc.modifier(c);
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource(c.getTitle() + ".fxml"));
            pane17.getChildren().removeAll();
            pane17.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void affichierGiftss(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfaceGiftsTransporteurs.fxml"));
            pane17.getChildren().removeAll();
            pane17.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ConvertScore(ActionEvent event) throws SQLException {
       UserService u = new UserService();
        User p =u.getUserByEmai(semail);
       // String scoreStr = idScore.getText();
       String scoreStr =p.getScore();
       idScore.setText(scoreStr);
        
                
        if (!scoreStr.isEmpty()) {
            int score = Integer.parseInt(scoreStr);
            double diamonds = score / 15.0 * 3.5;
            idDiam.setText(String.format("%.2f", diamonds));
        }
    }

    @FXML
    private void meteoApi(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("Test.fxml"));
            pane17.getChildren().removeAll();
            pane17.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

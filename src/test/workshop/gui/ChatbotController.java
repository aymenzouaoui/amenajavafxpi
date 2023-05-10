/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author hp
 */
public class ChatbotController  {


    @FXML
    private Label answerLabel;

    @FXML
    public void onQuestion1Clicked() {
        answerLabel.setText("Oui, vos données sont proteger .");
    }

    @FXML
    public void onQuestion2Clicked() {
        answerLabel.setText("Oui, vos données sont proteger.");
    }

    @FXML
    public void onQuestion3Clicked() {
        answerLabel.setText("Oui, vos données sont proteger.");
    }

    @FXML
    public void onQuestion4Clicked() {
        answerLabel.setText("Oui, vos données sont proteger.");
    }

    @FXML
    public void onQuestion5Clicked() {
        answerLabel.setText("Oui, vos données sont proteger.");
    }

}

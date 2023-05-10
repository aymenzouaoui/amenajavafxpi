/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class TestNavigateur extends Application {
    
    @Override
    public void start(Stage stage) {
        // Créer un WebView
        WebView webView = new WebView();
        
        // Charger la page HTML5Test.com
        webView.getEngine().load("https://html5test.com");
        
        // Créer une scène et y ajouter le WebView
        Scene scene = new Scene(webView);
        stage.setScene(scene);
        
        // Afficher la fenêtre
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
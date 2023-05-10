/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class AcceilleController implements Initializable {

           
   @FXML
    private TabPane tabPane;

    @FXML
    private AjoutDController ajoutController;

    @FXML
    private AController afficherController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {


   
        // Récupération des contrôleurs des onglets
        ajoutController = (AjoutDController) tabPane.getTabs().get(0).getContent().getUserData();
        afficherController = (AController) tabPane.getTabs().get(1).getContent().getUserData();
    

    }    
    
}

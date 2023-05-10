/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Annonce;
import services.AnnonceCRUD;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField tfPrixM;
    @FXML
    private TextField tfVille_DM;
    @FXML
    private TextField tfVille_AM;
    @FXML
    private TextField tfDate_DM;
    @FXML
    private TextField tfDate_AM;
    @FXML
    private TextArea tfDescM;
    @FXML
    private TextField tfIDM;
    @FXML
    private Button btnMod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modif(ActionEvent event) {
    String idA = tfIDM.getText();
    String VD = tfVille_DM.getText();
    String VA = tfVille_AM.getText();
    String DD = tfDate_DM.getText();
    String DA = tfDate_AM.getText();
    String Des = tfDescM.getText();
  //  String P = tfDescM.getText();
    
     int idANN = Integer.parseInt(idA);
    // int PP = Integer.parseInt(P);
     

    
    Annonce a = new Annonce(idANN,VD,VA,DD,DA,Des);

    
    AnnonceCRUD an = new AnnonceCRUD();
    an.modifier(a);
    

    }
    }
    


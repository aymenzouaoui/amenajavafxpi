/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Annonce;
import services.AnnonceCRUD;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class AfficherController implements Initializable {

    @FXML
    private ListView<Annonce> tfList;
    @FXML
    private Button btnAff;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aff(ActionEvent event) {
            
    
         AnnonceCRUD AC = new AnnonceCRUD();
        
        List<Annonce> list=AC.afficher();
        ObservableList<Annonce> observableList = FXCollections.observableArrayList(list);
        tfList.setItems(observableList);
    }
    
    
}

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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class TestController implements Initializable {

    @FXML
    private ListView<Colis> lv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ColisCRUD c =new ColisCRUD();
            Colis co =new Colis();
            lv.getItems().setAll(c.afficher()); 
            
           
        
    
    }    

    @FXML
    private void testc(MouseEvent event) {
         Colis selectedDoc = lv.getSelectionModel().getSelectedItem();
        if (selectedDoc != null) {
          int a =selectedDoc.getId();
            System.out.println(a);
            //lv.getItems().remove(selectedDoc);
           // showAlert("Annonce supprimé");
        } else {
           // showAlert("Veuillez sélectionner une Annonce à supprimer");
        }
    }
    
}

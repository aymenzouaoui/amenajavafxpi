/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.AjoutDController.annoncefinal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Annonce;
import services.AnnonceCRUD;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ListColisController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private ListView<Colis> lsview;
    @FXML
    private Button tfValide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ColisCRUD   docDAO = new ColisCRUD();
        lsview.getItems().setAll(docDAO.afficher3());
        lsview.setCellFactory(param -> new ListCell<Colis>() {
    @Override
    protected void updateItem(Colis item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText("Nom expéditeur: " + item.getNomExpediteur() + 
                    "\nAdresse expéditeur: " + item.getAdresseExpediteur() +
                    "\nNom destinataire: " + item.getNomDestinataire() +
                    "\nAdresse destinataire: " + item.getAdresseDestinataire() +
                    "\nStatut: " + item.getStatut()+
                    "\nDate d'expedition: " + item.getDateExpedition()+
                    "\nPoids: " + item.getPoids() + "kg");
        }
    }
});
        // TODO
    }   

    @FXML
    private void btnValider(ActionEvent event) {
        AnnonceCRUD ac = new AnnonceCRUD() ; 
            Annonce annonce=annoncefinal;
             Colis c1 = lsview.getSelectionModel().getSelectedItem();
            annonce.setColis(c1);
            ac.ajouter(annonce);
        
    }
    
}

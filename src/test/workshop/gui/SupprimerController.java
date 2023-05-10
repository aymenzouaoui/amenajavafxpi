package test.workshop.gui;

import amena.gui.dashboard.First3Controller;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import test.workshop.model.Colis;
import test.workshop.model.DocumentExpedition;
import test.workshop.services.ColisCRUD;
import test.workshop.services.DocumentExpeditionCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SupprimerController  implements Initializable{

    @FXML
    private ListView<Colis> docListView;

    @FXML
    private Button btnSupp;

    private ColisCRUD docDAO;
    @FXML
    private AnchorPane paneA2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        docDAO = new ColisCRUD();
        docListView.getItems().setAll(docDAO.afficher());
        docListView.setCellFactory(param -> new ListCell<Colis>() {
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
    }

    @FXML
    private void handleSupprimerBtn() {
        Colis selectedDoc = docListView.getSelectionModel().getSelectedItem();
        if (selectedDoc != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                docDAO.supprimer(selectedDoc.getId());
                docListView.getItems().remove(selectedDoc);
            showAlert("Colis supprimé");}
        } else {
            showAlert("Veuillez sélectionner un colis à supprimer");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void backbtn(ActionEvent event) {
                 try {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/test/workshop/gui/Accueil.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(First3Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
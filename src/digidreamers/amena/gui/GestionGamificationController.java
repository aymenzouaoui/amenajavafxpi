/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import digideramers.amena.models.Competition;
import digidreamers.amena.services.CompetitionCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class GestionGamificationController implements Initializable {

    @FXML
    private ListView<Competition> listView;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Label titrelabel;
    @FXML
    private Label date_deblabel;
    @FXML
    private Label date_finlabel;
    @FXML
    private Label typelabel;
    @FXML
    private Label nbplabel;

    public static Competition c;
    @FXML
    private Button button3;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Label valuelabel;
    @FXML
    private Label idclabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CompetitionCRUD cc = new CompetitionCRUD();
        listView.getItems().addAll(cc.afficher());

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Competition>() {
            @Override
            public void changed(ObservableValue<? extends Competition> observable, Competition oldValue, Competition newValue) {
                c = listView.getSelectionModel().getSelectedItem();
                titrelabel.setText(c.getTitle());

                titrelabel.setText(c.getTitle());
                date_deblabel.setText(c.getDate_deb().toString());
                date_finlabel.setText(c.getDate_fin().toString());
                typelabel.setText(Integer.toString(c.getType()));
                nbplabel.setText(Integer.toString(c.getNbp()));

            }

        });

    }

    @FXML
    private void handlebc1(ActionEvent event) {

        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("UpdateCompetition.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handlebc2(ActionEvent event) {
        /*   try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("DeleteCompetition.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Competition c = listView.getSelectionModel().getSelectedItem();
        if (c != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette compétition ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CompetitionCRUD cc = new CompetitionCRUD();
                cc.supprimer(c.getId());
                /*    ObservableList<Competition> comp = FXCollections.observableArrayList(cc.afficher());
                listView.setItems(comp);*/
   listView.getItems().clear();
                listView.getItems().addAll(cc.afficher());
               
                
            }
        }
    }

    @FXML
    private void giftsAction(MouseEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("DetailsGifts.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlebc3(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("AddCompetition.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

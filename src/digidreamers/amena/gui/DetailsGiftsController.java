/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import digideramers.amena.models.Gifts;
import digidreamers.amena.services.GiftsCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class DetailsGiftsController implements Initializable {

    @FXML
    private AnchorPane pane5;
    @FXML
    private Button button4;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Label valuelabel;
    @FXML
    private Label idclabel;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private ListView<Gifts> listtView;
    public static Gifts g;
    @FXML
    private ImageView imagev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GiftsCRUD gc = new GiftsCRUD();
        listtView.getItems().addAll(gc.afficher());

        listtView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Gifts>() {
            @Override
            public void changed(ObservableValue<? extends Gifts> observable, Gifts oldValue, Gifts newValue) {
                g = listtView.getSelectionModel().getSelectedItem();
                System.out.println("");
                nomlabel.setText(g.getName());
                descriptionlabel.setText(g.getDescription());
                valuelabel.setText(g.getValue());
                idclabel.setText(Integer.toString(g.getIdC()));
                System.out.println(g.getPhoto());
                imagev.setImage(new Image(g.getPhoto()));
            }
        });
    }

    @FXML
    private void handlebc4(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("AddGift.fxml"));
            pane5.getChildren().removeAll();
            pane5.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlebc5(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("UpdateGift.fxml"));
            pane5.getChildren().removeAll();
            pane5.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlebc6(ActionEvent event) {

        Gifts g = listtView.getSelectionModel().getSelectedItem();
        if (g != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce cadeau ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                GiftsCRUD gc = new GiftsCRUD();
                gc.supprimer(g.getId());
                /*    ObservableList<Competition> comp = FXCollections.observableArrayList(cc.afficher());
                listView.setItems(comp);*/
                listtView.getItems().clear();
                listtView.getItems().addAll(gc.afficher());
            }
        }
    }
}

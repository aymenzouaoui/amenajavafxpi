/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import digideramers.amena.models.Gifts;
import static digidreamers.amena.gui.DetailsGiftsController.g;
import digidreamers.amena.services.GiftsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class InterfaceGiftsTransporteursController implements Initializable {

    @FXML
    private ListView<Gifts> listViewGifts;
    @FXML
    private Button btnConv;
    @FXML
    private AnchorPane pane20;
    @FXML
    private Button btnRe;
    @FXML
    private TextField idScoreT;
    @FXML
    private TextField idDiamT;

    /**
     * Initializes the controller class.
     */
    
    User p ; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        UserService usr = null ; 
        try {
            usr = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceGiftsTransporteursController.class.getName()).log(Level.SEVERE, null, ex);
        }
         p = usr.getUserByEmai(semail) ; 
        idScoreT.setText(p.getScore());
        
        GiftsCRUD gc = new GiftsCRUD();
        listViewGifts.getItems().addAll(gc.afficher());
        btnConv.setDisable(true);
        listViewGifts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Gifts>() {
            @Override
            public void changed(ObservableValue<? extends Gifts> observable, Gifts oldValue, Gifts newValue) {
                g = listViewGifts.getSelectionModel().getSelectedItem();
                btnConv.setDisable(false);

            }
        });

    }

    @FXML
    private void convertDiam(ActionEvent event) throws SQLException {
     if(Integer.parseInt(g.getValue())>Integer.parseInt(p.getScore()))
     {
          Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Impossible de convertir vos points !");
                    alert.showAndWait();
     }
     else
     {
         p.setScore(Integer.toString(Integer.parseInt(p.getScore())-Integer.parseInt(g.getValue())));
          UserService  usr = new UserService();
            usr.modifier(p);
                    idScoreT.setText(p.getScore());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Felicitation !");
                    alert.showAndWait();
     }
         
    }

    @FXML
    private void retourVersL(ActionEvent event) {
         try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfaceTransporteur.fxml"));
            pane20.getChildren().removeAll();
            pane20.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

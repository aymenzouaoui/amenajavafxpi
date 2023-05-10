/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.detaille;

import amena.gui.LocationInterface.GestionLocationController;
import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class DetailleController implements Initializable {

    @FXML
    private Button modbtn;
    @FXML
    private Button back;
    @FXML
    private Button supbtn;
    @FXML
    private Label labtp;
    @FXML
    private Label labmat;
    @FXML
    private Label labmar;
    @FXML
    private Label labmod;
    @FXML
    private Label labkilo;
    @FXML
    private Label labe;
    @FXML
    private Label labprx;
    @FXML
    private Pane pancol;
    @FXML
    private ImageView imgv1;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Label infosup;

    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

Vehicule v ; 
VehiculeCRUD vc = new VehiculeCRUD() ; 
v = vc.getByID(GestionLocationController.ids) ; 
labmat.setText(v.getImmat());
labkilo.setText(v.getKilometrage());
labmar.setText(v.getMarque()); 

labmod.setText(v.getModele());
labprx.setText(Float.toString(v.getPrix()));
if(v.isEtat())
{labe.setText("Reservé");
infosup.setText("Vous ne pouvez pas supprimer cet vehicule car elle est reservé ");
supbtn.setDisable(true);
}
else
    labe.setText("Disponible");
labtp.setText(v.getType());
imgv1.setImage(new Image(v.getImg()));
pancol.setStyle("-fx-background-color: "+ v.getCouleur() +";" );
 }    

    @FXML
    private void modifier(ActionEvent event) {
                    try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../modifierp/ModifierV.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retourner(ActionEvent event) {
         try {
                Parent sv;
                sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../LocationInterface/GestionLocation.fxml"));
                pane1.getChildren().removeAll();
                pane1.getChildren().setAll(sv);
            } catch (IOException ex) {
                Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void supprimer(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");
VehiculeCRUD vc = new  VehiculeCRUD() ; 
            Optional<ButtonType> result = alert.showAndWait();
      
            if (result.get() == ButtonType.YES){
                Vehicule v = vc.getByID(GestionLocationController.ids) ; 
               vc.supprimer(v.getId());
            }
    }
    
}

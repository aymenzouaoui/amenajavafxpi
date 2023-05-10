/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.detaille;
import amena.gui.LocationInterface.GestionLocationController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import amena.gui.LocationInterface.* ; 
import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author klair
 */
public class DisplayController implements Initializable {

    @FXML
    private ImageView imgv;
    @FXML
    private Label labtp;
    @FXML
    private Label labmat;
    @FXML
    private Label labkilo;
    @FXML
    private Label labmar;
    @FXML
    private Label labmod;
    @FXML
    private Label labe;
    @FXML
    private Label labprx;
    @FXML
    private Pane pancol;

    /**
     * Initializes the controller class.
     */
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
Vehicule v ; 
VehiculeCRUD vc = new VehiculeCRUD() ; 
v = vc.getByID(GestionLocationController.ids) ; 
        System.out.println(v.getImmat());
labmat.setText(v.getImmat());
labkilo.setText(v.getKilometrage());
labmar.setText(v.getMarque());
labmod.setText(v.getModele());
labprx.setText(Float.toString(v.getPrix()));
if(v.isEtat())
    labe.setText("Reservé");
else
    labe.setText("Disponible");
labtp.setText(v.getType());

//imgv.setImage(new Image(v.getImg()));
pancol.setStyle("-fx-background-color: "+ v.getCouleur() +";" );

// TODO
    }    

    @FXML
    private void modif(ActionEvent event) {
    }

    @FXML
    private void sup(ActionEvent event) {
    }
    
}

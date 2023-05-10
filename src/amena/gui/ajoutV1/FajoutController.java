/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.ajoutV1;

import amena.gui.ajoutV2.Fajout2Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class FajoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
  // paneA1 : parentContainer
  
// paneA2 : container
    
    @FXML
    private AnchorPane paneA1;
    /**
     * Initializes the controller class.
     */
   
  // paneA1 : parentContainer
  
// paneA2 : container
    
    private AnchorPane paneA2,paneA3,paneA5,paneA30;
    @FXML 
    private Pane button1,button2 ; 
    @FXML
    private Pane button12;
    @FXML
    private Pane button31;
    @FXML
    private Button ret;
    @FXML
    private AnchorPane panea1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
      /*  ObservableList<String> list = FXCollections.observableArrayList("Voiture","Camion","Moto","Velo");
        combo1.setItems(list) ; */
    }    
    @FXML 
        private void loadFVoiture(MouseEvent event) throws IOException
        {
        
            Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("../ajoutV2/Fajout2.fxml"));
            Scene scene = button1.getScene() ; 
            
            root.translateXProperty().set(scene.getWidth());

            AnchorPane parentContainer = (AnchorPane) button1.getScene().getRoot();

            paneA1.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            paneA1.getChildren().remove(paneA2);
        });
        timeline.play();
        }

    @FXML
    private void loadMB(MouseEvent event) throws IOException {
          Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("../ajoutMB/ajoutMB.fxml"));
            Scene scene = button2.getScene() ; 
            
            root.translateXProperty().set(scene.getWidth());

            AnchorPane parentContainer = (AnchorPane) button2.getScene().getRoot();

            paneA1.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            paneA1.getChildren().remove(paneA3);
        });
        timeline.play();
    }

    @FXML
    private void loadCam(MouseEvent event) throws IOException {
             Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("../camionInterface/camInterface.fxml"));
            Scene scene = button12.getScene() ; 
            
            root.translateXProperty().set(scene.getWidth());

            AnchorPane parentContainer = (AnchorPane) button12.getScene().getRoot();

            paneA1.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            paneA1.getChildren().remove(paneA5);
        });
        timeline.play();
    }

    @FXML
    private void loadVelo(MouseEvent event) throws IOException {
    
    Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaceVelo/ajoutVelo.fxml"));
            Scene scene = button31.getScene() ; 
            
            root.translateXProperty().set(scene.getWidth());

            AnchorPane parentContainer = (AnchorPane) button31.getScene().getRoot();

            paneA1.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            paneA1.getChildren().remove(paneA30);
        });
        timeline.play();
    }

    @FXML
    public  void btback(ActionEvent event) {
          try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../LocationInterface/GestionLocation.fxml"));
          panea1.getChildren().removeAll() ; 
          panea1.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(FajoutController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        
        
   
        
}

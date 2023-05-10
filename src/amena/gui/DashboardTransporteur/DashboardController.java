package amena.gui.DashboardTransporteur;



import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
     @FXML
    private Circle circle2 ; 

    @FXML
    private AnchorPane pane1,pane2,pane3,pane4 ;


    @FXML
    private ImageView menu ,exit;
    @FXML
    private Label nomper;
    @FXML
    private Circle c2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try {
             UserService u = new UserService();
             User p = u.getUserByEmai(semail);
                         System.out.println("aa" +p.getId());

             if(u.userVer(p.getId())){
                 Image imgcer = new Image("http://localhost/img/ver.png",false) ;
                 c2.setFill(new ImagePattern(imgcer));
             }
             else
             {
                 c2.setFill(Color.TRANSPARENT);
                 System.out.println("nnnnnnn");
             }
             
             User a =u.getUserByEmai(semail);
             Image img = new Image(a.getImage(),false) ;
             circle2.setFill(new ImagePattern(img));
             exit.setOnMouseClicked(event -> { System.exit(0);});
             pane1.setVisible(false);
             nomper.setText(a.getNom() + " " + a.getPrenom());
             FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),pane1);
             fadeTransition.setFromValue(1);
             fadeTransition.setToValue(0);
             fadeTransition.play();
             TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),pane2);
             translateTransition.setByX(-600);
             translateTransition.play();
             menu.setOnMouseClicked(event -> {
                 
                 
                 pane1.setVisible(true);
                 
                 FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
                 fadeTransition1.setFromValue(0);
                 fadeTransition1.setToValue(0.15);
                 fadeTransition1.play();
                 
                 TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
                 translateTransition1.setByX(+600);
                 translateTransition1.play();
             });
             pane1.setOnMouseClicked(event -> {
                 
                 
                 
                 FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
                 fadeTransition1.setFromValue(0.15);
                 fadeTransition1.setToValue(0);
                 fadeTransition1.play();
                 
                 fadeTransition1.setOnFinished(event1 -> {
                     pane1.setVisible(false);
                 });
                 
                 
                 TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
                 translateTransition1.setByX(-600);
                 translateTransition1.play();
             });
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        
    }
    
    @FXML 
    public void HandleButtonClick1(ActionEvent event)
    {
    /*    try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("Accueil.fxml"));
          pane4.getChildren().removeAll() ; 
          pane4.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(First3Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        */
    }
    
    @FXML 
    public void HandleButtonClick(ActionEvent event)
    {
       try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../client/vitrine.fxml"));
          pane4.getChildren().removeAll() ; 
          pane4.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }


    @FXML
    private void Gestion_des_colis(ActionEvent event) {
         try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/test/workshop/controllers/DashbordT.fxml"));
          pane4.getChildren().removeAll() ; 
          pane4.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @FXML
    private void Gammification(ActionEvent event) {
         try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/digidreamers/amena/gui/InterfaceTransporteur.fxml"));
          pane4.getChildren().removeAll() ; 
          pane4.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void profilbtn(MouseEvent event) {
         try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../Profil.fxml"));
          pane4.getChildren().removeAll() ; 
          pane4.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(amena.gui.DashboardClient.DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Gestion_des_annonce(ActionEvent event) {
         try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/gui/AfficherTransporteurAnnonce.fxml"));
          pane4.getChildren().removeAll() ; 
          pane4.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(amena.gui.DashboardClient.DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}


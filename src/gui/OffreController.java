/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import static gui.AjoutDController.annoncefinal;
import java.awt.Image;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Annonce;
import org.controlsfx.control.Notifications;
import services.AnnonceCRUD;
import test.workshop.model.Colis;
import test.workshop.model.ColisReserver;
import test.workshop.services.ColisCRUD;
import test.workshop.services.ColisReserverCrud;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class OffreController implements Initializable {

    @FXML
    private Button btnAcc;
    private VBox vbox;
    @FXML
    private Label nelab;
    @FXML
    private Label adselab;
    @FXML
    private Label ndlab;
    @FXML
    private Label adslab;
    @FXML
    private Label pdlab;
    @FXML
    private Label datelab;

     Colis c ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceCRUD ac = new AnnonceCRUD();
        System.out.println(AfficherTransporteurAnnonceController.test);
        Annonce a = ac.getByID( AfficherTransporteurAnnonceController.test) ;
        
        c  = a.getColis() ;
               System.out.println(c);
        nelab.setText(c.getNomExpediteur());
        adselab.setText(c.getAdresseExpediteur());
        datelab.setText(c.getDateExpedition().toString());
        pdlab.setText(Float.toString((c.getPoids())));
        adslab.setText(c.getAdresseDestinataire());
               

        
        
            }
// TODO
        
    
 @FXML
    private void btnNotificationOnAction(ActionEvent event) throws SQLException {
        UserService u =new UserService();
        User p = u.getUserByEmai(semail) ;
        System.out.println(semail);
        // Image image=new Image("amanalogo.png");

        Notifications notifications = Notifications.create();
        notifications.graphic(new ImageView(image));
        notifications.text("hey im nour");
       notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(4));
//offre 
      //  notifications.show();
      
      ColisReserverCrud u2 = new ColisReserverCrud();
      ColisReserver r= new ColisReserver();
      r.setColis(c);
      UserService us = new UserService();
      r.setUser(p);
      
     u2.ajouter(r);
        System.out.println(r);
     
    
}
        // Image image=new Image("amanalogo.png");

      //  Notifications notifications = Notifications.create();
        // notifications.graphic(new ImageView(image));
      //  notifications.text("hey im nour");
     //   notifications.title("Success Message");
        //notifications.hideAfter(Duration.seconds(4));

    //    notifications.show();
    
   
}

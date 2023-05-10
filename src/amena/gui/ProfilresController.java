/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.Identifier_votre_compteController.emailS;
import amena.gui.ReservationInterface.DetResController;
import amena.model.User;

import amena.services.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import digidreamers.amena.gui.GestionGamificationController;
import javafx.scene.paint.ImagePattern;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ProfilresController implements Initializable {

    private ListView<User> userListView;
 
    public static String semail;
    @FXML
    private AnchorPane PuserP;
    @FXML
    private Button chat;
    @FXML
    private Label fxxadress;
    @FXML
    private Label fxdate;
    @FXML
    private Label fxemail;
    @FXML
    private Label fxnom;
    @FXML
    private Circle img;
    @FXML
    private Button quitterBtn;
    @FXML
    private Circle c2;
    

    public void setUserInformation(String email) throws SQLException, FileNotFoundException {
        semail = email;
        UserService u = new UserService();
        User p = u.getUserByEmai(email);
        fxemail.setText(p.getEmail());

        fxnom.setText(p.getNom() + "" + p.getPrenom());  // Récupérer l'utilisateur connecté
        //fxprenom.setText(p.getPrenom());
        fxxadress.setText(p.getAdress());
        fxdate.setText(p.getDate_naissance().toString());
        //fxcin.setText(p.getCin());

        // File file = new File(p.getImage());
        // FileInputStream fileInputStream = new FileInputStream(file);
        // Image image = new Image(p.getImage());
        // img.setImage(image);
        //fxdate.setText(user.getDate_naissance().toString());
        // fxemail.setText(p.getEmail());
    }

    public ProfilresController() throws SQLException {

    }

    public void handleAfficherBtn(ActionEvent event) throws SQLException {

        UserService u = new UserService();

        List<User> list = u.afficher();
        u.afficher2(semail);
        ObservableList<User> observableList = FXCollections.observableArrayList(list);
        userListView.setItems(observableList);
    }

    @FXML
    private void handleQuitterBtn(ActionEvent event) {
        Stage stage = (Stage) quitterBtn.getScene().getWindow();
        stage.close();
    }

    private void handleModifierBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           
            
            UserService u = new UserService();
           
             User  p = u.getByID(DetResController.idures) ;
            fxemail.setText(p.getEmail());
            fxnom.setText(p.getNom() + "" + p.getPrenom());  // Récupérer l'utilisateur connecté
            //fxprenom.setText(p.getPrenom());
            fxxadress.setText(p.getAdress());
            fxdate.setText(p.getDate_naissance().toString());
            System.out.println("ds");
            Image img1 = new Image(p.getImage(),false) ;
            img.setFill(new ImagePattern(img1));
            
            if(u.userVer(p.getId())){
           Image imgcer = new Image("http://localhost/img/ver.png",false) ;
            c2.setFill(new ImagePattern(imgcer));
            }
            
            
            
            System.out.println("ds");
        } catch (SQLException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @FXML
    private void chat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    private void supprimer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerProfil.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void modifierUtilisateur(ActionEvent event) throws IOException {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("UpdateProfil.fxml"));
            PuserP.getChildren().removeAll();
            PuserP.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void renitialiser(ActionEvent event) throws SQLException, IOException {
        emailS = semail;
        UserService userService = new UserService();
        User user = userService.getUserByEmai(emailS);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        Parent root = loader.load();
        ResetPasswordController resetPasswordController = loader.getController();
        resetPasswordController.setCurrentUser(user);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
/*
    private void ajoutc(ActionEvent event) throws IOException {
        emailS = semail;

        try {
            Parent sv;
            sv = (VBox) FXMLLoader.load(getClass().getResource("/test/workshop/gui/FXML.fxml"));
            PuserP.getChildren().removeAll();
            PuserP.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void ajoutcomp(ActionEvent event) throws IOException {
        emailS = semail;

        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/digidreamers/amena/gui/InterfaceTransporteur.fxml"));
            PuserP.getChildren().removeAll();
            PuserP.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mouve(ActionEvent event) {
        emailS = semail;

        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/gui/A.fxml"));
            PuserP.getChildren().removeAll();
            PuserP.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class TransporteurController implements Initializable {

    @FXML
    private Label fxnom;
    @FXML
    private Label fxprenom;
    @FXML
    private Label fxcin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    void setUserInformation(String email) throws SQLException {
      
       // fxemail.setText(email);
        semail = email;
        UserService u = new UserService();
        User p = u.getUserByEmai(email);
        fxnom.setText(p.getNom());  // Récupérer l'utilisateur connecté
        fxprenom.setText(p.getPrenom());
       // fxxadress.setText(p.getAdress());
        fxcin.setText(p.getCin());
    
    
    }
   
}

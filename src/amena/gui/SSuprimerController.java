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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class SSuprimerController implements Initializable {

    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxxadress;
    @FXML
    private TextField fxxemail;
    @FXML
    private TextField fxnom;
    
    @FXML
  private UserService userService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         try {
            userService = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(modifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         User user = userService.getUserByEmai(semail);
                    // récupérer l'utilisateur sélectionné
                    int a = user.getId();
                    fxxemail.setText(user.getEmail());
                    fxnom.setText(user.getNom());
                    fxprenom.setText(user.getPrenom());
                   fxxadress.setText(user.getAdress());
                    fxcin.setText(user.getCin());
                  //  fxnum.setText(user.getNum());
                    // convert java.sql.Date to LocalDate and set it in DatePicker
                    java.sql.Date sqlDate = user.getDate_naissance();
                    LocalDate localDate = sqlDate.toLocalDate();
                    //dateNaissanceTextField.setValue(localDate);
                    

       
        // TODO
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
         UserService p = new UserService();
    User u1 = p.getUserByEmai(semail);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        u1.setStatus(false);
        p.modifier(u1);
    }
    }
    
}

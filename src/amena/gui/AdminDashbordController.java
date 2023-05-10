/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.model.User;
import amena.services.UserService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AdminDashbordController implements Initializable {

    @FXML
    private TextField fxrecherche;
    @FXML
    private DatePicker fxdate;
    @FXML
    private Button tbnban;
    @FXML
    private Button btnrecherche;
    @FXML
    private Button btnsuspendu;
    @FXML
    private ListView<User> lvuser;
    private UserService userService;

   private String emails ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            userService = new UserService();
            lvuser.getItems().addAll(userService.afficher());

            lvuser.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
                public ListCell<User> call(ListView<User> param) {
                    return new ListCell<User>() {
                        @Override
                        protected void updateItem(User item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item.getNom() + " " + item.getPrenom() + " (" + item.getCin() + ")");
                            }
                        }
                    };
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }

    void setUserInformation(String email) {
       this.emails=email;
    }
    
  

    @FXML
    private void timeout(ActionEvent event) {
        User u = lvuser.getSelectionModel().getSelectedItem();
        LocalDate localDate = fxdate.getValue();
        u.setCompteExpirationDate(Date.valueOf(localDate));
        u.setStatus(false);
        userService.modifier(u);

    }

    Date date = new java.sql.Date(new java.util.Date().getTime());

    @FXML
    private void clearban(ActionEvent event) {

        User u = lvuser.getSelectionModel().getSelectedItem();

        u.setCompteExpirationDate(date);
        u.setStatus(true);
        userService.modifier(u);

    }

    @FXML
    private void deactiverAccount(ActionEvent event) {
        User u = lvuser.getSelectionModel().getSelectedItem();
         LocalDate localDate = fxdate.getValue();
        Date a = Date.valueOf(localDate);
        u.setCompteExpirationDate(a);
        u.setStatus(false);
        userService.modifier(u);

    }

}

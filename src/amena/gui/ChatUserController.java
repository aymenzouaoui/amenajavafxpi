/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.Message;
import amena.model.User;
import amena.services.ChatService;
import amena.services.UserService;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ChatUserController implements Initializable {

    @FXML
    private AnchorPane chatPane;
    @FXML
    private Text nomlab;
    @FXML
    private Button sendButton;
    @FXML
    private ListView<Message> messageList;
    @FXML
    private Button btnback;
    @FXML
    private JFXTextArea msgf;
    private UserService p1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void sendMessage(ActionEvent event) throws SQLException {
        User p2 = p1.getUserByEmai(semail);
        java.sql.Date timestamp = new java.sql.Date(new java.util.Date().getTime());
        String message = msgf.getText();
        // Vérification de saisie
        if (message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        UserService u = new UserService();
        User p = u.getUserByEmai(semail);
        int senderId = p.getId();
        ChatService chat = new ChatService();
        int recerveid = p2.getId();
        Message m = new Message(senderId, recerveid, message, timestamp);
        chat.ajouter(m);

        // Effacer le champ de saisie de message
        msgf.clear();
    }

   

    @FXML
    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
    /*
    @FXML
private void userFind(ActionEvent event) throws SQLException {
    String email = fxrecherche.getText(); // userEmail est l'objet TextField qui contient l'email entré par l'utilisateur
    User user = userService.getUserByEmai(email);
    if (user != null) {
        ObservableList<User> userList = FXCollections.observableArrayList();
        userList.add(user);
        fxlisteusert.setItems(userList);
        fxlisteusert.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom()); // Afficher seulement le nom de l'utilisateur dans la liste
                }
            }
        });
    } else {
        // Si aucun utilisateur n'est trouvé, effacer la liste des utilisateurs
        fxlisteusert.setItems(null);
    }
}
}
     */

}

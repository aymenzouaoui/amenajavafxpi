package amena.gui;

import amena.model.User;
import amena.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.Optional;

public class SupprimerProfilController {

    @FXML
    private ListView<User> userListView;

    private UserService userService;

    public SupprimerProfilController() {
        try {
            userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        ObservableList<User> users = FXCollections.observableArrayList(userService.afficher());
        userListView.setItems(users);
    }

    @FXML
    public void deleteUser() {
        User user = userListView.getSelectionModel().getSelectedItem();
        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir deactiver votre compte ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                user.setStatus(false);
                userService.modifier(user);
                ObservableList<User> users = FXCollections.observableArrayList(userService.afficher());
                userListView.setItems(users);
            }
        }
    }
}

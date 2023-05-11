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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ChatController implements Initializable {

    @FXML
    private AnchorPane chatPane;

    @FXML
    private Text chatTitle;
    @FXML
    private TextArea messageField;
    @FXML
    private Button sendButton;
    @FXML
    private ListView<Message> messageList;
    @FXML
    private JFXListView<User> fxlisteusert;

    /**
     * Initializes the controller class.
     */
    private UserService userService;
    private ChatService ChatService;

    @FXML
    private Button bafficher;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXTextField fxrecherche;
    @FXML
    private JFXButton btnrecherche;

    public ChatController() {
        try {
            userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws SQLException {

    }

    private void sendMessage(ActionEvent event) throws SQLException {
        User user = fxlisteusert.getSelectionModel().getSelectedItem();
        java.sql.Date timestamp = new java.sql.Date(new java.util.Date().getTime());
        String message = messageField.getText();
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
        int recerveid = user.getId();
        Message m = new Message(senderId, recerveid, message, timestamp);
        chat.ajouter(m);
        User selectedUser = fxlisteusert.getSelectionModel().getSelectedItem();

        List<Message> messages = chat.getChatsBySenderReceiverIds(p.getId(), selectedUser.getId());
        ObservableList<Message> observableMessages = FXCollections.observableArrayList(messages);
        messageList.setItems(observableMessages);
        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> listView) {
                return new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            try {
                                UserService userService = new UserService();
                                User sender = userService.getByID(item.getSenderId());
                                String senderName = sender.getNom();
                                String messageContent = item.getContent();
                                String text = senderName + ": " + messageContent;
                                setText(text);
                                setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                            } catch (SQLException ex) {
                                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                };
            }
        });
        // Effacer le champ de saisie de message
        messageField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userService = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendButton.setOnAction((event) -> {
            try {
                sendMessage(event);
            } catch (SQLException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            userService = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // récupérer la liste des utilisateurs à partir du service
        List<User> users = userService.afficher();

        // convertir la liste en observable list pour pouvoir l'afficher dans la liste view
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);

        // ajouter les utilisateurs à la liste view
        fxlisteusert.setItems(observableUsers);

        fxlisteusert.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<User>() {
                    @Override
                    protected void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);

                        if (user != null) {
                            // afficher le nom de l'utilisateur dans la cellule de la liste
                            setText(user.getNom());
                            if (user.isStatus()) {
                                setTextFill(Color.GREEN); // mettre la couleur de texte en vert
                            } else {
                                setTextFill(Color.RED); // mettre la couleur de texte en rouge
                            }
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        fxlisteusert.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                try {
                    // récupérer l'utilisateur sélectionné
                    User user = fxlisteusert.getSelectionModel().getSelectedItem();

                    // afficher le nom de l'utilisateur sélectionné dans le titre de la fenêtre de chat
                    chatTitle.setText("Chat avec " + user.getNom());

                    // mettre à jour la liste des messages avec les messages de l'utilisateur sélectionné
                    messageList.setItems(FXCollections.observableArrayList());
                    User selectedUser = fxlisteusert.getSelectionModel().getSelectedItem();
                    ChatService chat = new ChatService();
                    UserService u = new UserService();
                    User p = u.getUserByEmai(semail);
                    List<Message> messages = chat.getChatsBySenderReceiverIds(p.getId(), selectedUser.getId());
                    ObservableList<Message> observableMessages = FXCollections.observableArrayList(messages);
                    messageList.setItems(observableMessages);
                    messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
                        @Override
                        public ListCell<Message> call(ListView<Message> listView) {
                            return new ListCell<Message>() {
                                @Override
                                protected void updateItem(Message item, boolean empty) {
                                    super.updateItem(item, empty);

                                    if (item == null || empty) {
                                        setText(null);
                                    } else {
                                        try {
                                            UserService userService = new UserService();
                                            User sender = userService.getByID(item.getSenderId());
                                            String senderName = sender.getNom();
                                            String messageContent = item.getContent();
                                            String text = senderName + ": " + messageContent;
                                            setText(text);
                                            setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                                        } catch (SQLException ex) {
                                            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }

                            };
                        }
                    });
                } catch (SQLException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
private void userFind(ActionEvent event) throws SQLException {
    String email = fxrecherche.getText(); // userEmail est l'objet TextField qui contient l'email entré par l'utilisateur
    List<User> userList = userService.getUsersByNom(email);
    if (!userList.isEmpty()) {
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        fxlisteusert.setItems(observableUserList);
        fxlisteusert.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom()); // Afficher seulement le nom de l'utilisateur dans la liste
                    if (user.isStatus()) {
                        setTextFill(Color.GREEN); // Si le status est vrai, afficher en vert
                    } else {
                        setTextFill(Color.RED); // Si le status est faux, afficher en rouge
                    }
                }
            }
        });
    } else {
        // Si aucun utilisateur n'est trouvé, effacer la liste des utilisateurs
        fxlisteusert.setItems(null);
    }
}

    @FXML
    private void rech(KeyEvent event) {
        
    }

}

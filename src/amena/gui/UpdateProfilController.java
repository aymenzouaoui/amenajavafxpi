package amena.gui;

import amena.model.User;
import amena.services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class UpdateProfilController implements Initializable {

    @FXML
    private ListView<User> selectedListView;
    @FXML
    private JFXTextField nomTextField;
    @FXML
    private JFXTextField prenomTextField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private JFXTextField cinTextField;
    @FXML
    private JFXDatePicker dateNaissanceTextField;
    @FXML
    private JFXTextField emailTextField;

    
    @FXML
    private Button modifierButton;

    private UserService userService;
    @FXML
    private Button btnback;
    Date date = new java.sql.Date(new java.util.Date().getTime());

    @FXML
    private JFXCheckBox fxclient;
    @FXML
    private JFXCheckBox fxtransoorteur;
    @FXML
    private Circle img;
    @FXML
    private JFXCheckBox ac;
    @FXML
    private JFXCheckBox dea;
    @FXML
    private JFXPasswordField motDePasseTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            userService = new UserService();
            selectedListView.getItems().addAll(userService.afficher());

            selectedListView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
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
        selectedListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends User> observable, User oldValue, User newValue) -> {
            // code qui s'exécute lorsque l'utilisateur sélectionne un élément dans la liste
            User user = selectedListView.getSelectionModel().getSelectedItem();
            // récupérer l'utilisateur sélectionné
            int a = user.getId();
            System.out.println(a);
            emailTextField.setText(user.getEmail());
            nomTextField.setText(user.getNom());
            prenomTextField.setText(user.getPrenom());
            adresseTextField.setText(user.getAdress());
            cinTextField.setText(user.getCin());
            // convert java.sql.Date to LocalDate and set it in DatePicker
            java.sql.Date sqlDate = user.getDate_naissance();
            LocalDate localDate = sqlDate.toLocalDate();
            dateNaissanceTextField.setValue(localDate);

            Image img1 = new Image(user.getImage(), false);
            img.setFill(new ImagePattern(img1));

            String role = user.getRole();
            if (role.equals("transporteur")) {
                fxtransoorteur.setSelected(true);
                fxclient.setSelected(false);
            } else if (role.equals("client")) {
                fxtransoorteur.setSelected(false);
                fxclient.setSelected(true);
            }
            boolean etat = user.isStatus();
            System.out.println(etat);
            if (etat) {
                ac.setSelected(true);
                dea.setSelected(false);
            } else {
                ac.setSelected(false);
                dea.setSelected(true);
            }

        });
    }

    @FXML
    private void modifierUtilisateur(ActionEvent event) throws SQLException {
        User user = selectedListView.getSelectionModel().getSelectedItem();

        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir modifier cet utilisateur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                String nom = nomTextField.getText().trim();
                String prenom = prenomTextField.getText().trim();
                String adresse = adresseTextField.getText().trim();
                String cin = cinTextField.getText().trim();
                String email = emailTextField.getText().trim();
                //String motDePasse = motDePasseTextField.getText().trim();
                LocalDate dateNaissance = dateNaissanceTextField.getValue();

                if (!dateNaissanceTextField.getEditor().getText().trim().isEmpty()) {
                    try {
                        dateNaissance = dateNaissanceTextField.getValue();
                    } catch (DateTimeException ex) {
                        Alert dateAlert = new Alert(Alert.AlertType.ERROR);
                        dateAlert.setTitle("Erreur");
                        dateAlert.setHeaderText(null);
                        dateAlert.setContentText("Veuillez saisir une date de naissance valide !");
                        dateAlert.showAndWait();
                        return;
                    }
                }

                if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || cin.isEmpty() || email.isEmpty()/* || motDePasse.isEmpty() */|| dateNaissance == null) {
                    Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
                    emptyAlert.setTitle("Erreur");
                    emptyAlert.setHeaderText(null);
                    emptyAlert.setContentText("Tous les champs sont obligatoires !");
                    emptyAlert.showAndWait();
                    return;
                }

                if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                    Alert emailAlert = new Alert(Alert.AlertType.ERROR);
                    emailAlert.setTitle("Erreur");
                    emailAlert.setHeaderText(null);
                    emailAlert.setContentText("Veuillez saisir une adresse email valide !");
                    emailAlert.showAndWait();
                    return;
                }

                if (cin.length() != 8) {
                    Alert cinAlert = new Alert(Alert.AlertType.ERROR);
                    cinAlert.setTitle("Erreur");
                    cinAlert.setHeaderText(null);
                    cinAlert.setContentText("Veuillez saisir un numéro de cin valide (8 chiffres) !");
                    cinAlert.showAndWait();
                    return;
                }
                String role = "";

                if (fxtransoorteur.isSelected() && !fxclient.isSelected()) {
                    role = "transporteur";
                } else if (fxclient.isSelected() && !fxtransoorteur.isSelected()) {
                    role = "client";
                } else if (fxtransoorteur.isSelected() && fxclient.isSelected()) {
                    // Display an alert to ask the user to choose only one role
                    Alert roleAlert = new Alert(Alert.AlertType.ERROR);
                    roleAlert.setTitle("Erreur");
                    roleAlert.setHeaderText(null);
                    roleAlert.setContentText("Veuillez choisir seulement un rôle !");
                    roleAlert.showAndWait();
                    return; // exit the method without modifying the user
                }
                boolean etat = user.isStatus();
                if (ac.isSelected() && dea.isSelected()) {
                    // Display an alert if both checkboxes are selected
                    JOptionPane.showMessageDialog(null, "Error: Only one checkbox can be selected.");
                } else if (ac.isSelected()) {
                    etat = true;
                } else if (dea.isSelected()) {
                    etat = false;
                }

                User u1 = userService.getUserByEmai(user.getEmail());
                u1.setId(user.getId());
                u1.setEmail(email);
                u1.setNom(nom);
                u1.setPrenom(prenom);
                u1.setAdress(adresse);
                u1.setCin(cin);
                //u1.setMot_pass(motDePasse);
                u1.setStatus(etat);
                u1.setRole(role);
                //user.setDate_creation_c(date_creation_c);

                userService.modifier(u1);

                ObservableList<User> users = FXCollections.observableArrayList(userService.afficher());
                selectedListView.setItems(users);

            }
        } else {
            Alert selectAlert = new Alert(Alert.AlertType.ERROR);
            selectAlert.setTitle("Erreur");
            selectAlert.setHeaderText(null);
            selectAlert.setContentText("Veuillez sélectionner un utilisateur !");
            selectAlert.showAndWait();
        }
    }
    private Button btnProfil;

    public void initialize() {
        btnProfil.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnProfil.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
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

}

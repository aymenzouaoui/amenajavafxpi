import amena.model.User;
import amena.services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UpdateProfilController implements Initializable {

    @FXML
    private ListView<User> listView;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField cinField;
    @FXML
    private TextField dateNaissanceField;
    @FXML
    private TextField roleField;
    @FXML
    private TextField motPassField;
    @FXML
    private TextField emailField;
    @FXML
    private Button modifierButton;

    private UserService userService;
    private List<User> users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            userService = new UserService();
            users = userService.afficher();

            listView.getItems().addAll(users);

            listView.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> afficherUser(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        User user = listView.getSelectionModel().getSelectedItem();

        user.setNom(nomField.getText());
        user.setPrenom(prenomField.getText());
        user.setAdress(adresseField.getText());
        user.setCin(cinField.getText());
        user.setDate_naissance(java.sql.Date.valueOf(dateNaissanceField.getText()));
       // user.setRole(Integer.parseInt(roleField.getText()));
        user.setMot_pass(motPassField.getText());
        user.setEmail(emailField.getText());

        userService.modifier(user);
    }

    private void afficherUser(User user) {
        nomField.setText(user.getNom());
        prenomField.setText(user.getPrenom());
        adresseField.setText(user.getAdress());
        cinField.setText(user.getCin());
        dateNaissanceField.setText(user.getDate_naissance().toString());
       // roleField.setText(Integer.toString(user.getRole()));
        motPassField.setText(user.getMot_pass());
        emailField.setText(user.getEmail());
    }
}

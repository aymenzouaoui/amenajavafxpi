/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import amena.gui.DashboardClient.DashboardController;
import amena.gui.ProfilController;
import static amena.gui.ProfilController.semail;
import amena.gui.ResetPasswordController;
import amena.model.User;
import amena.services.UserService;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Annonce;
import services.AnnonceCRUD;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class AjoutDController implements Initializable {

    @FXML
    private Button tfValide;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfDate_D;
    @FXML
    private TextField tfDate_A;
    @FXML
    private TextArea tfDesc;
    @FXML
    private ChoiceBox<String> cbVilleDep;
    @FXML
    private ChoiceBox<String> cbVilleArr;
    @FXML
    private ChoiceBox<String> cbType;

    private UserService userservice;

    public static Annonce annoncefinal;
    @FXML
    private AnchorPane paneA2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> villes = FXCollections.observableArrayList(
                "Tunis",
                "Ariana",
                "Ben Arous",
                "Bizerte",
                "Gabès",
                "Gafsa",
                "Jendouba",
                "Kairouan",
                "Kasserine",
                "Kébili",
                "Le Kef",
                "Mahdia",
                "La Manouba",
                "Médenine",
                "Monastir",
                "Nabeul",
                "Sfax",
                "Sidi Bouzid",
                "Siliana",
                "Sousse",
                "Tataouine",
                "Tozeur",
                "Zaghouan"
        );
        cbVilleDep.setItems(villes);
        cbVilleArr.setItems(villes);
        ObservableList<String> types = FXCollections.observableArrayList(
                "Demande",
                "Offre"
        );
        cbType.setItems(types);

    }

    @FXML
    private void btnValider(ActionEvent event) throws SQLException, IOException {

        String datePattern = "\\d{2}-\\d{2}-\\d{4}";
        if (!tfDate_D.getText().matches(datePattern) || !tfDate_A.getText().matches(datePattern)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le format de la date doit être jj-mm-aaaa!");
            alert.showAndWait();
        } else {
            if (tfPrix.getText().isEmpty() || cbVilleDep.getValue() == null || cbVilleArr.getValue() == null || cbType.getValue() == null
                    || tfDate_D.getText().isEmpty() || tfDate_A.getText().isEmpty() || tfDesc.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs!");
                alert.showAndWait();

            } else {

                // User u = userservice.getUserByEmai(semail);
                UserService u = new UserService();
                User b = u.getUserByEmai(semail);

                Annonce A = new Annonce();
                A.setPrix(Integer.parseInt(tfPrix.getText()));
                A.setVille_dep(cbVilleDep.getValue());
                A.setVille_arr(cbVilleArr.getValue());
                A.setDate_dep(tfDate_D.getText());
                A.setDate_arr(tfDate_A.getText());
                A.setDescription(tfDesc.getText());
                A.setType(cbType.getValue());
                A.setUser(b);
                System.out.println(b);
                AnnonceCRUD AK = new AnnonceCRUD();
                if (b.getRole().equals("Transporteur")) {
                    AK.ajouter2(A);
                    Parent sv;
                    sv = (AnchorPane) FXMLLoader.load(getClass().getResource("AfficherTransporteurAnnonce.fxml"));
                    paneA2.getChildren().removeAll();
                    paneA2.getChildren().setAll(sv);
                    return;
                }
                annoncefinal = A;
                try {
                    Parent sv;
                    sv = (AnchorPane) FXMLLoader.load(getClass().getResource("listColis.fxml"));
                    paneA2.getChildren().removeAll();
                    paneA2.getChildren().setAll(sv);
                } catch (IOException ex) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void ajoutC(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/workshop/controllers/Ajout.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
}

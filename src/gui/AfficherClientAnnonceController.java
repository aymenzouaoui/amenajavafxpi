/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Annonce;
import model.Reaction;
import services.AnnonceCRUD;
import services.ReactionCRUD;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;

/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class AfficherClientAnnonceController implements Initializable {

    
    
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnFiltre;
    @FXML
    private TextField txtId;
    @FXML
    private ChoiceBox<String> choixType;
    
    private UserService userservice;
    
    public static int test;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            AnnonceCRUD AC = new AnnonceCRUD();
            Annonce A = new Annonce();

            UserService u = new UserService();
            A.setUser(u.getUserByEmai(semail));
            System.out.println(u.getUserByEmai(semail));
           // String Ty="Demande";
            List<Annonce> list = AC.AfficherO();

            for (Annonce annonce : list) {
                AnchorPane annoncePane = new AnchorPane();
                annoncePane.setPrefSize(600, 200); // taille de chaque annoncePane

                Label labelType = new Label("Type : " + annonce.getType());
                AnchorPane.setTopAnchor(labelType, 10.0);
                AnchorPane.setLeftAnchor(labelType, 10.0);

                Label labelVilleDep = new Label("Ville de départ : " + annonce.getVille_dep());
                AnchorPane.setTopAnchor(labelVilleDep, 30.0);
                AnchorPane.setLeftAnchor(labelVilleDep, 10.0);

                Label labelVilleArr = new Label("Ville d'arrivée : " + annonce.getVille_arr());
                AnchorPane.setTopAnchor(labelVilleArr, 50.0);
                AnchorPane.setLeftAnchor(labelVilleArr, 10.0);

                Label labelDateDep = new Label("Date de depart : " + annonce.getDate_dep());
                AnchorPane.setTopAnchor(labelDateDep, 70.0);
                AnchorPane.setLeftAnchor(labelDateDep, 10.0);

                Label labelDateArr = new Label("Date d'arrivée : " + annonce.getDate_arr());
                AnchorPane.setTopAnchor(labelDateArr, 90.0);
                AnchorPane.setLeftAnchor(labelDateArr, 10.0);

                Label labelPrix = new Label("Prix : " + annonce.getPrix());
                AnchorPane.setTopAnchor(labelPrix, 110.0);
                AnchorPane.setLeftAnchor(labelPrix, 10.0);

                Button btnInteresse = new Button("Je suis intéressé(e)");

                // Définition de l'EventHandler pour le clic sur le bouton
                btnInteresse.setOnAction(event -> {
                    if (annonce.getType().equals("Demande")) {

                        try {

                            test = annonce.getId_annonce();

                            AnnonceCRUD ab = new AnnonceCRUD();
                            Annonce an = ab.getByID(test);

                            UserService userService = new UserService();
                            User aa = userService.getUserByEmai(semail);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Transporteur.fxml"));

                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                            ColisCRUD b = new ColisCRUD();
                            Colis c = b.getByIDu(aa.getId());
                            System.out.println(c);
                            Reaction r = new Reaction(aa, c, an);
                            ReactionCRUD R = new ReactionCRUD();
                            R.ajouter(r);

                            System.out.println("Vous êtes intéressé(e) par l'annonce " + annonce.getId_annonce());
                            test = annonce.getId_annonce();
                            System.out.println(test);
                        } catch (IOException ex) {
                            Logger.getLogger(AController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(AController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        try {
                            

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));
                            test = annonce.getId_annonce();
                            System.out.println("gui.AController.initializaaaaaaae()");
                            System.out.println(test);
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();

                            System.out.println("Vous êtes intéressé(e) par l'annonce " + annonce.getId_annonce());

                        } catch (IOException ex) {
                            Logger.getLogger(AController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                AnchorPane.setTopAnchor(btnInteresse, 10.0);
                AnchorPane.setRightAnchor(btnInteresse, 10.0);
                annoncePane.getChildren().addAll(labelType, labelVilleDep, labelVilleArr, labelDateArr, labelDateDep, labelPrix, btnInteresse);

                vbox.getChildren().add(annoncePane);
            }
            scrollPane.setContent(vbox);
        } catch (SQLException ex) {
            Logger.getLogger(AController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleAddButton(ActionEvent event) throws IOException {
        // Ouvrir l'interface d'ajout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutD.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void searchById(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        AnnonceCRUD AC = new AnnonceCRUD();
        Annonce annonce = AC.getByID(id);
        if (annonce != null) {
            // Create a new AnchorPane to display the announcement details
            AnchorPane annoncePane = new AnchorPane();
            annoncePane.setPrefSize(600, 200); // taille de chaque annoncePane

            Label labelType = new Label("Type : " + annonce.getType());
            AnchorPane.setTopAnchor(labelType, 10.0);
            AnchorPane.setLeftAnchor(labelType, 10.0);

            Label labelVilleDep = new Label("Ville de départ : " + annonce.getVille_dep());
            AnchorPane.setTopAnchor(labelVilleDep, 30.0);
            AnchorPane.setLeftAnchor(labelVilleDep, 10.0);

            Label labelVilleArr = new Label("Ville d'arrivée : " + annonce.getVille_arr());
            AnchorPane.setTopAnchor(labelVilleArr, 50.0);
            AnchorPane.setLeftAnchor(labelVilleArr, 10.0);

            Label labelDateDep = new Label("Date de depart : " + annonce.getDate_dep());
            AnchorPane.setTopAnchor(labelDateDep, 70.0);
            AnchorPane.setLeftAnchor(labelDateDep, 10.0);

            Label labelDateArr = new Label("Date d'arrivée : " + annonce.getDate_arr());
            AnchorPane.setTopAnchor(labelDateArr, 90.0);
            AnchorPane.setLeftAnchor(labelDateArr, 10.0);

            Label labelPrix = new Label("Prix : " + annonce.getPrix());
            AnchorPane.setTopAnchor(labelPrix, 110.0);
            AnchorPane.setLeftAnchor(labelPrix, 10.0);

            annoncePane.getChildren().addAll(labelType, labelVilleDep, labelVilleArr, labelDateArr, labelDateDep, labelPrix);

            // Add the AnchorPane to the VBox
            vbox.getChildren().clear(); // Clear any previously displayed announcements
            vbox.getChildren().add(annoncePane);// Display the announcement details
            // ...
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cette Annonce n'existe pas!");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnFiltreClicked(ActionEvent event) {
    }

    @FXML
    private void listeTransporteurBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListTransporteur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

   
    
}

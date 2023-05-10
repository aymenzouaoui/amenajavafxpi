/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherController implements Initializable {

    @FXML
    private TableView<Colis> tableColis;
    @FXML
    private TableColumn<Colis, String> colNomExpediteur;
    @FXML
    private TableColumn<Colis, String> colAdresseExpediteur;
    @FXML
    private TableColumn<Colis, String> colNomDestinataire;
    @FXML
    private TableColumn<Colis, String> colAdresseDestinataire;
    @FXML
    private TableColumn<Colis,Float> colPoids;
    @FXML
    private TableColumn<Colis, String> colStatut;
    @FXML
    private TableColumn<?, ?> colDateExpedition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    colNomExpediteur.setCellValueFactory(new PropertyValueFactory<>("nomExpediteur"));
    colAdresseExpediteur.setCellValueFactory(new PropertyValueFactory<>("adresseExpediteur"));
    colNomDestinataire.setCellValueFactory(new PropertyValueFactory<>("nomDestinataire"));
    colAdresseDestinataire.setCellValueFactory(new PropertyValueFactory<>("adresseDestinataire"));
    colPoids.setCellValueFactory(new PropertyValueFactory<>("poids"));
    colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
    colDateExpedition.setCellValueFactory(new PropertyValueFactory<>("dateExpedition"));

    // Récupération des données de la base de données
    ColisCRUD a = new ColisCRUD();
     List<Colis> colisList;
     colisList = a.afficher();
    // Ajout des données dans la table view
    ObservableList<Colis> observableList = FXCollections.observableArrayList(colisList);
    tableColis.setItems(observableList);
}    
    
}

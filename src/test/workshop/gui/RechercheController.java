/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;
/**
 * FXML Controller class
 *
 * @author hp
 */
public class RechercheController {

   @FXML
    private TextField poidsTextField;

    @FXML
    private DatePicker dateExpPicker;

    @FXML
    private ComboBox<String> statutComboBox;

    @FXML
    private TableView<Colis> colisTableView;

    @FXML
    private TableColumn<Colis, Integer> idCol;

    @FXML
    private TableColumn<Colis, String> nomExpCol;

    @FXML
    private TableColumn<Colis, String> adresseExpCol;

    @FXML
    private TableColumn<Colis, String> nomDestCol;

    @FXML
    private TableColumn<Colis, String> adresseDestCol;

    @FXML
    private TableColumn<Colis, Double> poidsCol;

    @FXML
    private TableColumn<Colis, String> statutCol;

    @FXML
    private TableColumn<Colis, LocalDate> dateExpCol;
    ColisCRUD col= new ColisCRUD();

    /**
     * Initializes the controller class.
     */
    @FXML
    private void rechercherColis(ActionEvent event) {// Récupération des critères de recherche
float poids = 0.0f;
LocalDate dateExp = null;
String statut = null;

if (!poidsTextField.getText().isEmpty()) {
    poids = Float.parseFloat(poidsTextField.getText());
}

if (dateExpPicker.getValue() != null) {
    dateExp = dateExpPicker.getValue();
}

if (statutComboBox.getValue() != null) {
    statut = statutComboBox.getValue();
}

List<Colis> result = col.rechercher(poids,dateExp,statut);
colisTableView.getItems().setAll(result);
}  
    
 public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomExpCol.setCellValueFactory(new PropertyValueFactory<>("nomExp"));
        adresseExpCol.setCellValueFactory(new PropertyValueFactory<>("adresseExp"));
        nomDestCol.setCellValueFactory(new PropertyValueFactory<>("nomDest"));
        adresseDestCol.setCellValueFactory(new PropertyValueFactory<>("adresseDest"));
        poidsCol.setCellValueFactory(new PropertyValueFactory<>("poids"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
        dateExpCol.setCellValueFactory(new PropertyValueFactory<>("dateExp"));
    }  
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import static gui.AfficherClientAnnonceController.test;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Annonce;
import model.Evaluation;
import org.controlsfx.control.Rating;
import services.AnnonceCRUD;
import services.EvaluationCRUD;

//onRatingChanged="#onRatingChanged" rating="0"
/**
 * FXML Controller class
 *
 * @author Nour Saidi
 */
public class ClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vbox;
    @FXML
    private Rating rating;
    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Rating rating = new Rating();
        rating.setPartialRating(true);

        // VBox vbox = new VBox(rating);
        // Scene scene = new Scene(vbox);
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws SQLException {

        UserService userService = new UserService();
        User user = userService.getUserByEmai(semail);
        ////////////////////////////////////////////

        AnnonceCRUD AC = new AnnonceCRUD();
    Annonce annonce = AC.getByIdbb(test);
        System.out.println(test);
    
        System.out.println(annonce);
        double note = rating.getRating();
        // Enregistrer l'évaluation dans la table d'évaluation des clients
        EvaluationCRUD EV = new EvaluationCRUD();
        Evaluation E = new Evaluation();
        E.setIdClient(user.getId());
        E.setIdTransporteur(annonce.getUser().getId());
        EV.ajouterE(note, E);
        
        // Mettre à jour le score du transporteur dans la table utilisateur
        //calculerScoreTransporteur(idTransporteur);
        // Enregistrer l'évaluation dans la base de données
        // ...
    }

}

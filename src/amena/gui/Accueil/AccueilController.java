/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.Accueil;

import amena.gui.DashboardClient.DashboardController;
import amena.gui.ProfilController;
import amena.gui.ResetPasswordController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class AccueilController implements Initializable {

    @FXML
    private Button contact;
    @FXML
    private Button btnDemande;
    @FXML
    private Button btnOffre;
    @FXML
    private Button cnx;
    @FXML
    private Button sinscrire;
        private Parent fxml;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane aa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        // TODO
    }    

      @FXML
    private void switchDemand(ActionEvent event) {
        btnDemande.setStyle("-fx-background-color: #55d5e0 ; -fx-background-radius: 30px;");
        btnOffre.setStyle("-fx-background-color:  #0b303c; -fx-background-radius: 30px;");
    }

    @FXML
    private void switchOffre(ActionEvent event) {
        btnDemande.setStyle("-fx-background-color: #0b303c; -fx-background-radius: 30px;");
        btnOffre.setStyle("-fx-background-color: #55d5e0; -fx-background-radius: 30px;");
    }
    
    @FXML
    private void contacter(ActionEvent event) {
    }
DashboardController dc ; 
    @FXML
    private void connexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../LoginAccount.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CreateAccount.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
    }/*
     @FXML
    private void connexion(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
            t.setToX(17);
                       

        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("../LoginAccount.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {

            }
        });
    }
/*
    @FXML
    private void inscription(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
                                

        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("../CreateAccount.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {

            }
        });
*/
}

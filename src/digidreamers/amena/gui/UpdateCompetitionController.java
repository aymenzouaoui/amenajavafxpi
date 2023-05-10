/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import digideramers.amena.models.Competition;
import static digidreamers.amena.gui.AddCompetitionController.estDateValide;
import digidreamers.amena.services.CompetitionCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class UpdateCompetitionController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDate_deb;
    @FXML
    private TextField tfDate_fin;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfNbp;
    @FXML
    private Button btnModifier;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Button btnRe;

    /**
     * Initializes the controller class.
     */
    // méthode pour vérifier si la chaine de caractère ne contient pas de num
    public boolean verif_Num(String num) {
        int i = 0;
        for (i = 0; i < num.length(); i++) {
            if (Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean verif_Num2(String num) {
        int i = 0;
        for (i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }
// pour vérifier si le titre est non vide aussi que ne contient pas de num

    public boolean testTitle(String tit) {

        if (tit.length() == 0) {
            return false;
        }
        if (!verif_Num(tit)) {
            return false;
        }

        return true;

    }

    public static boolean estDateValide(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean testType(String type) {
        if (type.length() == 0) {
            return false;
        }
        if (!verif_Num2(type)) {
            return false;
        }
        return true;
    }

    public boolean testNbp(String nbp) {
        if (nbp.length() == 0) {
            return false;
        }
        if (!verif_Num2(nbp)) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfTitle.setText(GestionGamificationController.c.getTitle());
        tfDate_deb.setText(GestionGamificationController.c.getDate_deb().toString());
        tfDate_fin.setText(GestionGamificationController.c.getDate_fin().toString());
        tfType.setText(Integer.toString(GestionGamificationController.c.getType()));
        tfNbp.setText(Integer.toString(GestionGamificationController.c.getNbp()));
    }

    @FXML
    private void updateCompetition(ActionEvent event) {
        try {
            String title = tfTitle.getText();

            if (!testTitle(title)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le Titre doit être une chaine de caractères");
                alert.showAndWait();
            } else {
                if (!estDateValide(tfDate_deb.getText()) || !estDateValide(tfDate_fin.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez saisir des dates valides sous forme yyyy-mm-dd !");
                    alert.showAndWait();
                } else {
                    Date date_deb = Date.valueOf(tfDate_deb.getText());
                    Date date_fin = Date.valueOf(tfDate_fin.getText());
                    if (!verif_Num2(tfType.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Le Titre doit être un nombre");
                        alert.showAndWait();
                    } else {
                        int type = Integer.parseInt(tfType.getText());
                        if (!verif_Num2(tfNbp.getText())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Le nombre de participants doit être un nombre");
                            alert.showAndWait();
                        } else {
                            int nbp = Integer.parseInt(tfNbp.getText());
                            Competition c = new Competition(GestionGamificationController.c.getId(), title, date_deb, date_fin, type, nbp);
                            CompetitionCRUD cr = new CompetitionCRUD();
                            cr.modifier(c);

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionGamification.fxml"));
                            Parent root = loader.load();
                            GestionGamificationController dcc = loader.getController();

                            tfTitle.getScene().setRoot(root);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;

        }
    }

    @FXML
    private void retournerver(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("GestionGamification.fxml"));
            pane2.getChildren().removeAll();
            pane2.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionGamificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

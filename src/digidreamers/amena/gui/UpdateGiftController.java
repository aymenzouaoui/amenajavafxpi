/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import digideramers.amena.models.Gifts;
import digidreamers.amena.services.CompetitionCRUD;
import digidreamers.amena.services.GiftsCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
public class UpdateGiftController implements Initializable {

    @FXML
    private Button btnRet;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tdDesc;
    @FXML
    private TextField tfValu;
    @FXML
    private TextField tfIdC;
    @FXML
    private Button btnModify;
    @FXML
    private AnchorPane pane11;

    @FXML
    private Button btnnPhoto;
    @FXML
    private ImageView imgvv;
    private String urlImg;
    @FXML
    private ComboBox<String> listComps;

    /**
     * Initializes the controller class.
     */
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

    public boolean testTitle(String tit) {

        if (tit.length() == 0) {
            return false;
        }
        if (!verif_Num(tit)) {
            return false;
        }

        return true;

    }

    private boolean isImageSelected() {
        return urlImg != null && !urlImg.isEmpty();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfName.setText(DetailsGiftsController.g.getName());
        tdDesc.setText(DetailsGiftsController.g.getDescription());
        tfValu.setText(DetailsGiftsController.g.getValue());
        tfIdC.setText(Integer.toString(DetailsGiftsController.g.getIdC()));
        List<String> listCompTitle = new ArrayList<>();
        CompetitionCRUD cr = new CompetitionCRUD();
        listCompTitle = cr.titreComp();
        ObservableList<String> observable2 = FXCollections.observableArrayList(listCompTitle);
        listComps.setItems(observable2);
    }

    @FXML
    private void retournerverG(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("DetailsGifts.fxml"));
            pane11.getChildren().removeAll();
            pane11.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DetailsGiftsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateGift(ActionEvent event) {
        CompetitionCRUD cr = new CompetitionCRUD();
        try {
            String name = tfName.getText();
            if (!testTitle(name)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le nom doit être une chaîne de caractères !");
                alert.showAndWait();
            } else {
                String description = tdDesc.getText();
                if (description.length() > 100) {
                    description = description.substring(0, 100);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention");
                    alert.setHeaderText(null);
                    alert.setContentText("La description a été tronquée à 100 caractères.");
                    alert.showAndWait();
                } else {
                    String value = tfValu.getText();
                    int idC = cr.convertTitleToId(listComps.getValue());
                    Gifts g = new Gifts(DetailsGiftsController.g.getId(), name, description, value, idC, urlImg);
                    if (!isImageSelected()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Ajoutez une photo !");
                        alert.showAndWait();
                    } else {
                        GiftsCRUD gc = new GiftsCRUD();

                        gc.modifier(g);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsGifts.fxml"));
                        Parent root = loader.load();
                        DetailsGiftsController dgc = loader.getController();
                        tfName.getScene().setRoot(root);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @FXML
    private void ajouterrPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedfile = fileChooser.showOpenDialog(null);
        if (selectedfile != null) {
            urlImg = selectedfile.toURI().toString();
            Image image = new Image(urlImg);
            imgvv.setImage(image);
        }
    }

}

package amena.gui;

import amena.model.ValidationT;
import amena.services.UserService;
import amena.services.ValidationService;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class ValidationTransporteurController implements Initializable {

    @FXML
    private Button btna1;
    @FXML
    private ImageView imgv1;
    @FXML
    private ImageView imgv2;
    @FXML
    private Button btna2;
    @FXML
    private Button btnUplod;

    private File selectedFile1;
    private File selectedFile2;
    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            userService = new UserService();
        } catch (SQLException ex) {
            Logger.getLogger(ValidationTransporteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Uplodimmage(ActionEvent event) throws SQLException {
        ValidationService validationService = new ValidationService();

        if (selectedFile1 != null && selectedFile2 != null) {
            ValidationT validationT = new ValidationT(selectedFile1.getName(), selectedFile2.getName(), userService.getUserByEmai(ProfilController.semail), false);
            validationService.ajouter(validationT);

            // Upload images
            File destinationFile1 = new File("C:/xampp/htdocs/" + selectedFile1.getName());
            try {
                Files.copy(selectedFile1.toPath(), destinationFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            File destinationFile2 = new File("C:/xampp/htdocs/" + selectedFile2.getName());
            try {
                Files.copy(selectedFile2.toPath(), destinationFile2.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void Uplodimmage1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        /*
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
*/
        selectedFile1 = fileChooser.showOpenDialog(null);
        if (selectedFile1 != null) {
            Image image1 = new Image(selectedFile1.toURI().toString());
            imgv1.setImage(image1);
        }
    }

    @FXML
    private void Uplodimmage2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        /*
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
*/
        selectedFile2 = fileChooser.showOpenDialog(null);
        if (selectedFile2 != null) {
            Image image1 = new Image(selectedFile2.toURI().toString());
            imgv2.setImage(image1);
        }
    
}
}

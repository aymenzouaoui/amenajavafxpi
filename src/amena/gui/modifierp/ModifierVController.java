/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.modifierp;

import amena.gui.LocationInterface.GestionLocationController;
import static amena.gui.LocationInterface.GestionLocationController.v;
import amena.gui.ajoutV2.Fajout2Controller;
import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class ModifierVController implements Initializable {

    @FXML
    private TextField kmf;

    @FXML
    private TextField matf;
    private TextField marf;
    @FXML
    private TextField chvf;
    @FXML
    private TextField prxf;
    @FXML
    private Button picf;
    @FXML
    private ImageView imgv;
    private String urlImg;
    private TextField mod;
    @FXML
    private ComboBox<String> cmbmar;
    @FXML
    private ComboBox<String> cmbmod;
    @FXML
    private AnchorPane pane1;
    @FXML
    private RadioButton ebr;
    @FXML
    private RadioButton rbd;
    @FXML
    private TextField tfmar;
    @FXML
    private TextField tfmod;
    @FXML
    private TextField lpec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup toggleGroup = new ToggleGroup();

        ebr.setToggleGroup(toggleGroup);
        rbd.setToggleGroup(toggleGroup);

        Vehicule v;
        VehiculeCRUD vc = new VehiculeCRUD();
        v = vc.getByID(GestionLocationController.ids);
        if (!"Voiture".equals(v.getType())) {
            tfmar.setVisible(true);
            tfmod.setVisible(true);
            tfmar.setText(v.getMarque());
            tfmod.setText(v.getModele());
        }
        else
        {
            tfmar.setVisible(false);
            tfmod.setVisible(false);
        }
        matf.setText(v.getImmat());
        chvf.setText(Integer.toString(v.getChevaux()));
        prxf.setText(Float.toString(v.getPrix()));
        kmf.setText(v.getKilometrage());
        urlImg = v.getImg();
        imgv.setImage(new Image(urlImg));
        lpec.setText(v.getLpec());
        if (!v.isEtat()) {
            rbd.setSelected(true);
        } else {
            ebr.setSelected(true);
        }

        List<String> list = new ArrayList<>();
        try {
            list = vc.remplirmarque();
        } catch (ProtocolException ex) {
            Logger.getLogger(Fajout2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fajout2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<String> observable = FXCollections.observableArrayList(list);
        cmbmar.setItems(observable);
        cmbmar.setValue(v.getMarque());

        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        list2 = vc.remplirmod(cmbmar.getValue());
        for (int i = 0; i < list2.size(); i = i + 2) {
            list3.add(list2.get(i));
        }

        ObservableList<String> observable2 = FXCollections.observableArrayList(list3);
        cmbmod.setItems(observable2);
        
        cmbmar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> list2 = new ArrayList<>();
                List<String> list3 = new ArrayList<>();

                list2 = vc.remplirmod(cmbmar.getValue());
                for (int i = 0; i < list2.size(); i = i + 2) {
                    list3.add(list2.get(i));
                }

                ObservableList<String> observable2 = FXCollections.observableArrayList(list3);
                cmbmod.setItems(observable2);
            }
        });
        cmbmod.setValue(v.getModele());

    }

    public boolean verif_Num(String num) {
        int i = 0;
        while (i < num.length() && Character.isDigit(num.charAt(i))) {
            i++;
        }
        if (i < num.length()) {
            return false;
        }
        return true;
    }

    public boolean verif_Num2(String num) {
        int i = 0;
        for (i = 0; i < num.length(); i++) {
            if (Character.isAlphabetic(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean testMat(String mat) {
        String ch1 = "";
        String ch2 = "";
        String ch3 = "";

        int i = 0, ns = 0;

        if (mat.length() == 0) {
            return false;
        }

        for (i = 0; i < mat.length(); i++) {
            if (mat.charAt(i) == ' ') {
                ns++;
            }
        }

        if (ns != 2) {
            return false;
        }

        i = 0;
        while (mat.charAt(i) != ' ') {
            ch1 += mat.charAt(i);
            i++;
        }

        i++;
        while (mat.charAt(i) != ' ') {

            ch2 += mat.charAt(i);
            i++;
        }
        i++;
        for (int k = i; k < mat.length(); k++) {
            System.out.println(k);
            ch3 += mat.charAt(k);
        }

        if ((!verif_Num(ch1)) || (!verif_Num(ch3)) || (ch2 == "tun")) {
            return false;
        }
        return true;

    }

    public boolean testMar(String mar) {
        if (mar.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean testChv(String chv) {
        if (chv.length() == 0 || !verif_Num(chv)) {
            return false;
        }
        return true;

    }

    public boolean test_prx(String prx) {
        if (prx.length() == 0 || !verif_Num2(prx)) {
            return false;
        }
        return true;
    }

    public boolean test(String type,String mat, String chvx, String prx, String km, String mar, String modf) {

        if(!"Voiture".equals(type))         
            if(!testMar(mar) || !testMar(modf))
                return false ;
              
        if("Voiture".equals(type))             
            if(!testMat(mat))
                return false ; 
                   
        if (!testChv(chvx) || !test_prx(prx) || !test_prx(kmf.getText())) 
            return false;
        
        return true;

    }

    @FXML
    private void btnmod(ActionEvent event) {

        String mar,mod ; 
        VehiculeCRUD vc = new VehiculeCRUD();
        Vehicule v = vc.getByID(GestionLocationController.ids);
        if (lpec.getText().length()!=0 && test(v.getType(), matf.getText(), chvf.getText(), prxf.getText(), kmf.getText(), tfmar.getText(), tfmod.getText())) {
            Vehicule v2;
            
            if("Voiture".equals(v.getType()))
            {
                mar = cmbmar.getValue() ; 
                mod =  cmbmod.getValue() ;          
            }
            else
            {
                mar = tfmar.getText();
                mod = tfmod.getText();                                  
            }
            
            if (ebr.isSelected()) {
                v2 = new Vehicule(v.getId(), v.getType(), matf.getText(), true, kmf.getText(), Integer.parseInt(chvf.getText()), mar,mod, lpec.getText(), Float.parseFloat(prxf.getText()), urlImg);
            } else {
                v2 = new Vehicule(v.getId(), v.getType(), matf.getText(), false, kmf.getText(), Integer.parseInt(chvf.getText()), mar,mod, lpec.getText(), Float.parseFloat(prxf.getText()), urlImg);
            }

            vc.modifier(v2);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Modification réussi !");
            alert.showAndWait();
            try {
                Parent sv;
                sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../LocationInterface/GestionLocation.fxml"));
                pane1.getChildren().removeAll();
                pane1.getChildren().setAll(sv);
            } catch (IOException ex) {
                Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vérifiez vos champs !");
            alert.showAndWait();
        }

    }

      @FXML
    private void ajoutpic(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(picf.getScene().getWindow());
    if (selectedFile != null) {
        try {
            Image image = new Image(selectedFile.toURI().toString());
            imgv.setImage(image);

            String fileName = selectedFile.getName();
            File destinationFile = new File("C:/xampp/htdocs/img/" + fileName.trim());

            // Copier le fichier sélectionné vers le dossier de destination
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Mettre à jour l'URL de l'image dans l'objet utilisateur
            urlImg = "http://localhost/img/" + fileName.trim();
            //userService.modifier(u1);

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la lecture de l'image.");
            alert.showAndWait();
        }
    }
    }


    @FXML
    private void retbtn(ActionEvent event) {
         try {
                Parent sv;
                sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../LocationInterface/GestionLocation.fxml"));
                pane1.getChildren().removeAll();
                pane1.getChildren().setAll(sv);
            } catch (IOException ex) {
                Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.interfaceVelo;

import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
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
public class AjoutVeloController implements Initializable {

        private String urlImg ;            

    @FXML
    private AnchorPane paneA30;
    @FXML
    private TextField tfmat;
    @FXML
    private TextField tfmar;
    @FXML
    private TextField tfprx;
    @FXML
    private Button btnpic;
    @FXML
    private ImageView imgv;
    @FXML
    private TextField tfmod;
    @FXML
    private TextField lpec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

      @FXML
    private void ajoutpic(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(btnpic.getScene().getWindow());
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

  public boolean verif_Num(String num)
    {
        int i=0; 
        while(i<num.length() && Character.isDigit(num.charAt(i)))
            i++ ;
        if(i<num.length())
            return false ; 
        return true ;
    }
    
    public boolean testMat(String mat)
    {
    
      
      if(mat.length()==0)
          return false ;
      
  
       return true ;

    }
    
    
    public boolean testMar(String mar)
    { 
        if (mar.length() == 0 )
            return false ; 
       return true ; 
   }
    
     public boolean testChv(String chv)
    { 
        if (chv.length() == 0 || !verif_Num(chv) )
            return false ;
        return true ; 
        
   }
    
    public boolean test_prx(String prx)
    {
        if(prx.length() == 0 || !verif_Num(prx) )
            return false ;
        return true ;
    }
    
    
    public boolean test(String mat,String mar,String prx)
    {
        if (testMat(mat) && testMar(mar)  && test_prx(prx) )
            return true ;
        return false ;
                   
    }
        @FXML
    private void ajouterBc(ActionEvent event) {
         if(lpec.getText().length()!=0 && test(tfmat.getText(),tfmar.getText(),tfprx.getText()))
        {
     Vehicule v = new Vehicule("Velo",tfmat.getText(),false,"0",0,tfmar.getText(),tfmod.getText(),lpec.getText(),Float.parseFloat(tfprx.getText()),urlImg) ;         
        VehiculeCRUD vc = new VehiculeCRUD() ;
        vc.ajouter(v);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Ajout réussi !");
        alert.showAndWait();
   /* try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../ajoutV1/Fajout.fxml"));
          paneA2.getChildren().removeAll() ; 
          paneA2.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(Fajout2Controller.class.getName()).log(Level.SEVERE, null, ex);
         }        
        */
        }
        else 
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Vérifiez vos champs !");
        alert.showAndWait();
        }
        
    
    }
    



    
}

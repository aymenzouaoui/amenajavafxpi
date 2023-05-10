/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.interfaceVelo;

import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import java.io.File;
import java.net.URL;
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
    private Button buttonback;
    @FXML
    private TextField tfmat;
    @FXML
    private TextField tfmar;
    @FXML
    private TextField tfprx;
    @FXML
    private ColorPicker tfclr;
    @FXML
    private Button btnpic;
    @FXML
    private ImageView imgv;
    @FXML
    private TextField tfmod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

     @FXML
    private void ajoutpic(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        File selectedfile = fileChooser.showOpenDialog(null);
        if (selectedfile != null) {
           urlImg = selectedfile.toURI().toString() ; 
            Image image = new Image(urlImg) ; 
            imgv.setImage(image);
    }}
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
         if(test(tfmat.getText(),tfmar.getText(),tfprx.getText()))
        {
             Color color = tfclr.getValue();
            String colorCode = String.format("#%02X%02X%02X", (int) (color.getRed() * 255),
                    (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
     Vehicule v = new Vehicule("Velo",tfmat.getText(),false,"0",0,tfmar.getText(),tfmod.getText(),colorCode,Float.parseFloat(tfprx.getText()),urlImg) ;         
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

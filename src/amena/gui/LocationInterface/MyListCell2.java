/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.LocationInterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class MyListCell2 extends ListCell<MyData> implements Initializable {

    @FXML
    private ImageView imgv;
    @FXML
    private Label lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public MyListCell2() {
            super();
        }
        
        @Override
        protected void updateItem(MyData item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                //imgv.setImage(item.getImage());
                lab.setText(item.getTitle());              
            }
        }
    
}

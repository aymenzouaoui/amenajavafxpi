/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.gui.client.VitrineController;
import static amena.gui.client.VitrineController.vs;
import amena.model.User;
import amena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class TransController implements Initializable {
    int comp;
    int k = 0;
 public static int profiltrans ; 
    List<User> lvc = new ArrayList<>();
    private ScrollPane anchscroll;
    @FXML
    private AnchorPane pane;
    private int PAGE_SIZE;
    private int NUM_ITEMS;
   

    public static User vs ; 
    @FXML
    private AnchorPane allp;

    public Pane mypane(int k) {
        System.out.println(lvc.get(k));
        User v = lvc.get(k);
        Pane pane = new Pane();
        pane.setMaxHeight(Double.NEGATIVE_INFINITY);
        pane.setMaxWidth(Double.NEGATIVE_INFINITY);
        pane.setMinHeight(Double.NEGATIVE_INFINITY);
        pane.setMinWidth(Double.NEGATIVE_INFINITY);
        pane.setPrefHeight(124.0);
        pane.setPrefWidth(186.0);
        pane.setStyle("-fx-background-color: #e6e3e3; -fx-border-radius: 30;");
//pane.getStyleClass().add("pv");
//pane.getStylesheets().add("file:/C:/Users/klair/OneDrive/Documents/NetBeansProjects/amena/src/amena/gui/style.css");

        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(Color.DODGERBLUE);
        rectangle.setHeight(126.0);
        rectangle.setLayoutY(-1.0);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeMiterLimit(0.0);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeWidth(0.0);
        rectangle.setWidth(187.0);

        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        Image image = new Image(v.getImage());
        ImagePattern pattern = new ImagePattern(image);
        rectangle.setFill(pattern);
        Pane innerPane = new Pane();
        innerPane.setLayoutY(96.0);
        innerPane.setPrefHeight(29.0);
        innerPane.setPrefWidth(187.0);
//innerPane.setStyle("-fx-background-color: #2f4558; -fx-border-radius: 30;");

        innerPane.getStyleClass().add("in");

        innerPane.getStylesheets().add("http://localhost/style.css");

        Label label = new Label(v.getNom() + " " + v.getPrenom());
        label.setLayoutX(3.0);
        label.setLayoutY(7.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(110.0);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("System Bold", 12.0));
        label.setStyle("-fx-font-weight: bold;");

        Pane innerPane2 = new Pane();
        innerPane2.setLayoutX(108.0);
        innerPane2.setLayoutY(3.0);
        innerPane2.setPrefHeight(23.0);
        innerPane2.setPrefWidth(77.0);
        innerPane2.setStyle("-fx-background-color: #FFD700; -fx-border-radius: 20;");
        innerPane2.getStyleClass().add("prx");
        innerPane2.getStylesheets().add("http://localhost/style.css");

        Label label2 = new Label(v.getScore() + "dimond");
        label2.setStyle("-fx-font-weight: bold;");

        label2.setLayoutX(20.0);
        label2.setLayoutY(3.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(69.0);
        label2.setFont(Font.font("System Bold", 12.0));

        innerPane2.getChildren().add(label2);
        innerPane.getChildren().addAll(label, innerPane2);
        pane.getChildren().addAll(rectangle, innerPane);
        
        
         pane.setOnMouseClicked(e -> {
 profiltrans = v.getId() ; 
 try {
     Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("ProfilTransporteur.fxml"));
            allp.getChildren().removeAll();
            allp.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(VitrineController.class.getName()).log(Level.SEVERE, null, ex);
        }
            });
        
        return pane;
     
    }

    private Node createPage(int pageIndex) {

        VBox page = new VBox(10);
        int startIndex = pageIndex * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, NUM_ITEMS);

        comp = NUM_ITEMS - (pageIndex * 6);
        k = pageIndex * 6;
        VBox vb = new VBox(30);
        for (int i = startIndex; i < endIndex; i++) {

            if (comp >= 3) {

                HBox h1 = new HBox(60);
                h1.getChildren().addAll(mypane(k++), mypane(k++), mypane(k++));
                vb.getChildren().add(h1);
                comp -= 3;
            } else if (comp == 2) {
                HBox h1 = new HBox(60);
                h1.getChildren().addAll(mypane(k++), mypane(k++));
                vb.getChildren().add(h1);
                comp -= 2;
            } else if (comp == 1) {
                HBox h1 = new HBox(60);
                h1.getChildren().addAll(mypane(k++));
                vb.getChildren().add(h1);
                comp -= 1;
            }

        }
        Pane pk = new Pane();
        pk.setPrefSize(1, 1);
        vb.getChildren().add(pk);
        page.getChildren().add(vb);
        return page;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
           System.out.println("dd");
 UserService vc = new UserService() ;
            lvc = vc.afficher();
           
            PAGE_SIZE = 2;
            NUM_ITEMS = lvc.size();
            int nbpage = NUM_ITEMS / 6;
            int q = 6;
            if (NUM_ITEMS / 6 > 0) {
                nbpage = NUM_ITEMS / 6 + 1;
            }
            
            Pagination pagination = new Pagination(nbpage, 0);
            pagination.setPageFactory(pageIndex -> createPage(pageIndex));
            
            VBox root = new VBox(10);
            pane.getChildren().addAll(pagination);
            
            List<String> ltype = new ArrayList<>();
            ltype.add("Voiture");
            ltype.add("Camion");
            ltype.add("Moto");
            ltype.add("Velo");
            ObservableList<String> observable = FXCollections.observableArrayList(ltype);
           // cmbtype.setItems(observable);
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TransController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

  
        
       
    

}

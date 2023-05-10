/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.client;

import amena.gui.LocationInterface.GestionLocationController;
import amena.model.Reservation;
import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class VitrineController implements Initializable {
    int comp;
    int k = 0;
    VehiculeCRUD vc = new VehiculeCRUD();
    List<Vehicule> lvc = new ArrayList<>();
    private ScrollPane anchscroll;
    @FXML
    private AnchorPane pane;
    private int PAGE_SIZE;
    private int NUM_ITEMS;
    @FXML
    private ComboBox<String> cmbtype;
    @FXML
    private ComboBox<String> cmbmar;
    @FXML
    private ComboBox<String> cmbmod;

    public static Vehicule vs ; 
    @FXML
    private AnchorPane allp;

    public Pane mypane(int k) {
        System.out.println(lvc.get(k));
        Vehicule v = lvc.get(k);
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
        Image image = new Image(v.getImg());
        ImagePattern pattern = new ImagePattern(image);
        rectangle.setFill(pattern);
        Pane innerPane = new Pane();
        innerPane.setLayoutY(96.0);
        innerPane.setPrefHeight(29.0);
        innerPane.setPrefWidth(187.0);
//innerPane.setStyle("-fx-background-color: #2f4558; -fx-border-radius: 30;");

        innerPane.getStyleClass().add("in");

        innerPane.getStylesheets().add("http://localhost/style.css");

        Label label = new Label(v.getMarque() + " " + v.getModele());
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

        Label label2 = new Label(v.getPrix() + "DT");
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
vs= v ; 
 try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("detRes.fxml"));
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
        // TODO
        lvc = vc.afficher_nonReserve();

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
        cmbtype.setItems(observable);

        VehiculeCRUD vc = new VehiculeCRUD();

        cmbtype.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
lvc = vc.afficher_nonReserve();

        PAGE_SIZE = 2;
        NUM_ITEMS = lvc.size();
        int nbpage = NUM_ITEMS / 6;
        int q = 6;
        if (NUM_ITEMS / 6 > 0) {
            nbpage = NUM_ITEMS / 6 + 1;
        }

        Pagination pagination = new Pagination(nbpage, 0);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex));
                
                List<String> list2 = new ArrayList<>();
                list2 = vc.affMar(cmbtype.getValue());
                ObservableList<String> observable2 = FXCollections.observableArrayList(list2);
                cmbmar.setItems(observable2);

            }
        });

        cmbmar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                List<String> list2 = new ArrayList<>();
                list2 = vc.affMod(cmbmar.getValue());
                ObservableList<String> observable2 = FXCollections.observableArrayList(list2);
                cmbmod.setItems(observable2);

            }
        });

    }

    @FXML
    private void buttonrech(ActionEvent event) {
        System.out.println("s");
        List<Vehicule> res = new ArrayList<>();
        List<Vehicule> lmar = new ArrayList<>();
        List<Vehicule> lmod = new ArrayList<>();
        List<Vehicule> ltype = new ArrayList<>();
        List<Vehicule> let = new ArrayList<>();
        VehiculeCRUD vc = new VehiculeCRUD();

        if (cmbtype.getValue() != null) {
            ltype = vc.filterByType(cmbtype.getValue());
        }
        if (cmbmar.getValue() != null) {
            lmar = vc.filterByMar(cmbmar.getValue());
        }
        if (cmbmod.getValue() != null) {
            lmod = vc.filterByMod(cmbmod.getValue());
        }
             let = vc.filterByEtat(0);
         

        if (cmbtype.getValue() != null && cmbmar.getValue() == null) {          
                for (int i = 0; i < ltype.size(); i++) {
                    Vehicule element = ltype.get(i);
                    if (let.contains(ltype.get(i))) {
                        res.add(element);
                    }
                }
            }
           else if (cmbtype.getValue() != null && cmbmar.getValue() != null && cmbmod.getValue() == null) {
                for (int i = 0; i < lmar.size(); i++) {
                    Vehicule element = lmar.get(i);
                    if (let.contains(element)) {
                        res.add(element);
                    }
                }
        } else if (cmbtype.getValue() != null && cmbmar.getValue() != null && cmbmod.getValue() != null) {
                for (int i = 0; i < lmod.size(); i++) {
                    Vehicule element = lmod.get(i);
                    if (let.contains(element)) {
                        res.add(element);
                    }
                }
            }
        lvc.removeAll(lvc) ; 
        lvc.addAll(res);
        System.out.println(lvc);
        PAGE_SIZE = 2;
        NUM_ITEMS = lvc.size();
        int nbpage = NUM_ITEMS / 6;
        int q = 6;
        if (NUM_ITEMS / 6 > 0) {
            nbpage = NUM_ITEMS / 6 + 1;
        }
        System.out.println(nbpage);
        if(nbpage==0)
            nbpage++ ; 
        Pagination pagination = new Pagination(nbpage, 0);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex));
       
        pane.getChildren().clear();
        pane.getChildren().addAll(pagination);
    
    }

    @FXML
    private void resetbut(MouseEvent event) {
        cmbtype.setValue(null);
        cmbmod.setValue(null);
        cmbmar.setValue(null);
        lvc = vc.afficher_nonReserve();

        PAGE_SIZE = 2;
        NUM_ITEMS = lvc.size();
        int nbpage = NUM_ITEMS / 6;
        int q = 6;
        if (NUM_ITEMS / 6 > 0) {
            nbpage = NUM_ITEMS / 6 + 1;
        }

        Pagination pagination = new Pagination(nbpage, 0);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex));
         pane.getChildren().clear();
        pane.getChildren().addAll(pagination);
        
        
    }

    @FXML
    private void mesresbtn(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("listres.fxml"));
            allp.getChildren().removeAll();
            allp.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(VitrineController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
        
       
    

}

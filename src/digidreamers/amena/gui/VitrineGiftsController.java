/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.gui;

import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import digideramers.amena.models.Competition;
import digideramers.amena.models.Gifts;
import digidreamers.amena.services.CompetitionCRUD;
import digidreamers.amena.services.GiftsCRUD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class VitrineGiftsController implements Initializable {

    int comp;
    int k = 0;
    GiftsCRUD gc = new GiftsCRUD();
    List<Gifts> lgc = new ArrayList<>();
    private ScrollPane anchscroll;
    @FXML
    private AnchorPane pane;
    private int PAGE_SIZE;
    private int NUM_ITEMS;
    @FXML
    private ComboBox<String> cmbtype;
    private ComboBox<String> cmbmar;
    private ComboBox<String> cmbmod;

    public static Gifts gs;
    @FXML
    private AnchorPane allp;
    @FXML
    private Button btnPhoto;
    @FXML
    private TextField idScoreT;
    @FXML
    private TextField idDiamT;

    public Pane mypane(int k) {
        Gifts g = lgc.get(k);

        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setHeight(126.0);
        rectangle.setLayoutY(-1.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeMiterLimit(0.0);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setStrokeWidth(0.0);
        rectangle.setWidth(126.0);

        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        Image image = new Image(g.getPhoto());
        ImagePattern pattern = new ImagePattern(image);
        rectangle.setFill(pattern);

        Pane pane = new Pane();
        pane.setLayoutY(91.0);
        pane.setPrefHeight(34.0);
        pane.setPrefWidth(126.0);
        pane.setStyle("-fx-background-color: #023c6b;");

        pane.getStyleClass().add("in");

        pane.getStylesheets().add("file:/C:/Users/Ahlem/Desktop/DigiDreamsAmena%20-%20Cope%202/src/digidreamers/amena/gui/style.css");

        Label label = new Label(g.getName());
        label.setLayoutX(3.0);
        label.setLayoutY(7.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(110.0);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new javafx.scene.text.Font("System Bold", 12.0));

        Button button = new Button("+");
        button.setLayoutX(90.0);
        button.setLayoutY(4.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(0.0);
        button.setPrefWidth(30.0);
        button.setStyle("-fx-background-color: #81baff;-fx-background-radius : 30;");

        pane.getChildren().addAll(label, button);

        Pane pf = new Pane();
        pf.getChildren().addAll(rectangle, pane);
        //pf.setStyle("-fx-background-color: #e6e3e3; -fx-border-radius: 30;");

        return pf;

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
            UserService u = new UserService();
            User p = u.getUserByEmai(semail);
            
            idScoreT.setText(p.getScore());
            idDiamT.setText(p.getScore());
    
                    lgc = gc.afficher();
            
            
            
            PAGE_SIZE = 2;
            NUM_ITEMS = lgc.size();
            
            int nbpage = NUM_ITEMS / 6;
            System.out.println(nbpage);
            int q = 6;
            if (NUM_ITEMS / 6 > 1) {
                nbpage = NUM_ITEMS / 6 + 1;
            }
            if (NUM_ITEMS / 6 <= 1) {
                nbpage = 1;
            }
            Pagination pagination = new Pagination(nbpage, 0);
            pagination.setPageFactory(pageIndex -> createPage(pageIndex));
            
            VBox root = new VBox(10);
            pane.getChildren().addAll(pagination);
            
            List<String> lcomp = new ArrayList<>();
            
            CompetitionCRUD cr = new CompetitionCRUD();
            Competition c;
            for (int i = 0; i < lgc.size(); i++) {
                lcomp.add(cr.getByID(lgc.get(i).getIdC()).getTitle());
            }
            
            ObservableList<String> observable = FXCollections.observableArrayList(lcomp);
            cmbtype.setItems(observable);
        } catch (SQLException ex) {
            Logger.getLogger(VitrineGiftsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void buttonrech(ActionEvent event) {
        System.out.println("s");
        List<Gifts> res = new ArrayList<>();
        List<Gifts> lmar = new ArrayList<>();
        List<Gifts> lmod = new ArrayList<>();
        List<Gifts> ltype = new ArrayList<>();
        List<Gifts> let = new ArrayList<>();
        GiftsCRUD vc = new GiftsCRUD();

        if (cmbtype.getValue() != null) {
            //ltype = gc.filterByComp();
        } else if (cmbtype.getValue() != null && cmbmar.getValue() != null && cmbmod.getValue() == null) {
            for (int i = 0; i < lmar.size(); i++) {
                Gifts element = lmar.get(i);
                if (let.contains(element)) {
                    res.add(element);
                }
            }
        } else if (cmbtype.getValue() != null && cmbmar.getValue() != null && cmbmod.getValue() != null) {
            for (int i = 0; i < lmod.size(); i++) {
                Gifts element = lmod.get(i);
                if (let.contains(element)) {
                    res.add(element);
                }
            }
        }
        lgc.removeAll(lgc);
        lgc.addAll(res);
        System.out.println(lgc);
        PAGE_SIZE = 2;
        NUM_ITEMS = lgc.size();
        int nbpage = NUM_ITEMS / 6;
        int q = 6;
        if (NUM_ITEMS / 6 > 0) {
            nbpage = NUM_ITEMS / 6 + 1;
        }
        System.out.println(nbpage);
        if (nbpage == 0) {
            nbpage++;
        }
        Pagination pagination = new Pagination(nbpage, 0);
        pagination.setPageFactory(pageIndex -> createPage(pageIndex));

        pane.getChildren().clear();
        pane.getChildren().addAll(pagination);

    }

    private void resetbut(MouseEvent event) {
        cmbtype.setValue(null);
        cmbmod.setValue(null);
        cmbmar.setValue(null);
        lgc = gc.afficher();

        PAGE_SIZE = 2;
        NUM_ITEMS = lgc.size();
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
    private void ajouterPhoto(ActionEvent event) {
    }

}

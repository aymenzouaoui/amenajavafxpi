/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.LocationInterface;

import amena.gui.dashboard.First3Controller;
import amena.gui.detaille.DisplayController;
import amena.model.Vehicule;
import amena.services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class GestionLocationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static MyData v;
public static int ids ;
    @FXML
    private AnchorPane pane10;

    private Label matLab;
    private Label marLab;
    private Label coulLab;
    private Label prxLab;
    private Label kmlab;
    private Label chvLab;
    private ImageView imgv;
    @FXML
    private Label nbrvtr;
    @FXML
    private Label nbrv;
    @FXML
    private Label nbrcm;
    @FXML
    private Label nbrmto;
    int nbv = 0, nbc = 0, nbm = 0, nbvel = 0;
    @FXML
    private ListView<MyData> lv;
    @FXML
    private TextField rechtf;
    @FXML
    private ComboBox<String> cmbmar;
    @FXML
    private RadioButton rbr;
    @FXML
    private RadioButton rbdis;

    @FXML
    private ComboBox<String> cmbmod;
    @FXML
    private ComboBox<String> cmbtype;
    @FXML
    private Button btnveh;
    @FXML
    private AnchorPane paneprinc;
    @FXML
    private Button btnres;
    
    public void stats() {
        List<Vehicule> list = new ArrayList<>();
        VehiculeCRUD vc = new VehiculeCRUD();
        list = vc.afficher();
        int nbv = 0, nbc = 0, nbm = 0, nbvel = 0;

        for (int i = 0; i < list.size(); i++) {

            if ("Voiture".equals(list.get(i).getType())) {
                nbv++;
            }
            if ("Camion".equals(list.get(i).getType())) {
                nbc++;
            }
            if ("Moto".equals(list.get(i).getType())) {
                nbm++;
            }
            if ("Velo".equals(list.get(i).getType())) {
                nbvel++;
            }
        }
        nbrvtr.setText(Integer.toString(nbv));
        nbrv.setText(Integer.toString(nbvel));
        nbrcm.setText(Integer.toString(nbc));
        nbrmto.setText(Integer.toString(nbm));

    }

    private AnchorPane createAnchorPane(String text, Color color) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-border-color: black;");
        anchorPane.setPrefHeight(50);

        javafx.scene.control.Label label = new javafx.scene.control.Label(text);
        label.setTextFill(color);
        anchorPane.getChildren().add(label);

        return anchorPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        ToggleGroup toggleGroup = new ToggleGroup();
        rbdis.setToggleGroup(toggleGroup);
        rbr.setToggleGroup(toggleGroup);

        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyData>() {
            @Override
            public void changed(ObservableValue<? extends MyData> observable, MyData oldValue, MyData newValue) {
                v = lv.getSelectionModel().getSelectedItem();
               ids= v.getId();
                if (v != null) {
               try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../detaille/detaille.fxml"));
            pane10.getChildren().removeAll();
            pane10.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
            }

        });
        /**
         * *******************************************************************
         */
        List<Vehicule> lv2 = new ArrayList<>();
        lv2.addAll(vc.afficher());
        stats();

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) {
            if (lv2.get(i).isEtat() != true) {
                lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getMarque(), lv2.get(i).getImmat(), new Image(lv2.get(i).getImg()), new Image("http://localhost/imageAmena/location/checked.png")));
            } else {
                lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getMarque(), lv2.get(i).getImmat(), new Image(lv2.get(i).getImg()), new Image("http://localhost/imageAmena/location/nondis.png")));
            }
        }

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });

        /**
         * *********************************************************************************
         */
    }

    @FXML
    public void ajouterBC(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../ajoutV1/Fajout.fxml"));
            pane10.getChildren().removeAll();
            pane10.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    @FXML
    public void modifierButton(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../modifier/modifierV.fxml"));
            pane10.getChildren().removeAll();
            pane10.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnsup2(ActionEvent event) {
        VehiculeCRUD vc = new VehiculeCRUD();

        matLab.setText("");
        kmlab.setText("");
        marLab.setText("");
        coulLab.setText("");
        prxLab.setText("");
        chvLab.setText("");
        imgv.setImage(null);

        vc.supprimer(v.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Suppression réussie !");
        alert.showAndWait();
        lv.getItems().clear();
        lv.getItems().addAll(vc.afficher());
        stats();

    }
     */
    @FXML
    private void buttonrech(ActionEvent event) {
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
        if (rbdis.isSelected()) {
            let = vc.filterByEtat(0);
        } else {
            let = vc.filterByEtat(1);
        }

        System.out.println(ltype);
        System.out.println(let);

        if (cmbtype.getValue() != null && cmbmar.getValue() == null) {
            if (!rbdis.isSelected() && !rbr.isSelected()) {
                res = ltype;
            } else {
                for (int i = 0; i < ltype.size(); i++) {
                    Vehicule element = ltype.get(i);
                    if (let.contains(ltype.get(i))) {
                        res.add(element);
                    }
                }
            }
            System.out.println(res);
        } else if (cmbtype.getValue() != null && cmbmar.getValue() != null && cmbmod.getValue() == null) {
            if (!rbdis.isSelected() && !rbr.isSelected()) {
                res = lmar;
            } else {
                for (int i = 0; i < lmar.size(); i++) {
                    Vehicule element = lmar.get(i);
                    if (let.contains(element)) {
                        res.add(element);
                    }
                }
            }

        } else if (cmbtype.getValue() != null && cmbmar.getValue() != null && cmbmod.getValue() != null) {
            if (!rbdis.isSelected() && !rbr.isSelected()) {
                res = lmod;
            } else {
                for (int i = 0; i < ltype.size(); i++) {
                    Vehicule element = ltype.get(i);
                    if (let.contains(element)) {
                        res.add(element);
                    }
                }
            }
        } else if (cmbtype.getValue() == null && cmbmar.getValue() == null && cmbmod.getValue() == null && (rbdis.isSelected() || rbr.isSelected())) {
            res = let;
        }

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).getImg());
            if (!res.get(i).isEtat()) {
                lmd.add(new MyData(res.get(i).getId(), res.get(i).getMarque(), res.get(i).getImmat(), new Image(res.get(i).getImg()), new Image("http://localhost/imageAmena/location/checked.png")));
            } else {
                lmd.add(new MyData(res.get(i).getId(), res.get(i).getMarque(), res.get(i).getImmat(), new Image(res.get(i).getImg()), new Image("http://localhost/imageAmena/location/nondis.png")));
            }

        }
        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
    }

    @FXML
    private void retabbtn(ActionEvent event) {
        cmbtype.setValue(null);
        cmbmod.setValue(null);
        cmbmar.setValue(null);
        rbdis.setSelected(false);
        rbr.setSelected(false);
        
        VehiculeCRUD vc = new VehiculeCRUD();
          List<Vehicule> lv2 = new ArrayList<>();
        lv2.addAll(vc.afficher());
        stats();

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) {
            if (lv2.get(i).isEtat() != true) {
                lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getMarque(), lv2.get(i).getImmat(), new Image(lv2.get(i).getImg()), new Image("http://localhost/imageAmena/location/checked.png")));
            } else {
                lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getMarque(), lv2.get(i).getImmat(), new Image(lv2.get(i).getImg()), new Image("http://localhost/imageAmena/location/nondis.png")));
            }
        }

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });

    }

    @FXML
    private void btnRes(ActionEvent event) throws IOException {
         try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../ReservationInterface/ResInter.fxml"));
            paneprinc.getChildren().removeAll();
            paneprinc.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnveh(ActionEvent event) {
            try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../LocationInterface/GestionLocation.fxml"));
            pane10.getChildren().removeAll();
            pane10.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

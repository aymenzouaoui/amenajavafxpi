/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.ReservationInterface;

import amena.gui.LocationInterface.GestionLocationController;
import amena.gui.ReservationInterface.MyDataRes;
import amena.gui.ReservationInterface.reslistcell;
import amena.model.Reservation;
import amena.model.Vehicule;
import amena.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class ResInterController implements Initializable {

    public static int ids;

    @FXML
    private ListView<MyDataRes> lv;
    @FXML
    private Label nbrvtr11;
    MyDataRes v;
    @FXML
    private AnchorPane paneres;
    @FXML
    private Label nbrvtr111;
    @FXML
    private Label nbrvtr112;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationCRUD rc = new ReservationCRUD();
        List<Reservation> lv2 = new ArrayList<>();
        lv2.addAll(rc.afficher());
        List<MyDataRes> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) {
            Date df;
            df = lv2.get(i).getDate_fin();
            LocalDate date = LocalDate.parse(lv2.get(i).getDate_fin().toString());
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            CountdownDays cd = new CountdownDays(year, month, day);
            if (lv2.get(i).getEtat() == "En cours") {
                lmd.add(new MyDataRes(lv2.get(i).getIdRes(), lv2.get(i).getDate_deb().toString(), lv2.get(i).getDate_fin().toString(), new Image("http://localhost/imageAmena/location/time.png"), lv2.get(i).getIdTrans(), lv2.get(i).getIdVeh()));
            } else {
                lmd.add(new MyDataRes(lv2.get(i).getIdRes(), lv2.get(i).getDate_deb().toString(), lv2.get(i).getDate_fin().toString(), new Image("http://localhost/imageAmena/location/checked.png"), lv2.get(i).getIdTrans(), lv2.get(i).getIdVeh()));
            }
        }

        ObservableList<MyDataRes> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyDataRes>, ListCell<MyDataRes>>() {
            @Override
            public ListCell<MyDataRes> call(ListView<MyDataRes> listView) {
                return new reslistcell();
            }
        });
        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyDataRes>() {
            @Override
            public void changed(ObservableValue<? extends MyDataRes> observable, MyDataRes oldValue, MyDataRes newValue) {
                v = lv.getSelectionModel().getSelectedItem();
                ids = v.getId();
                if (v != null) {
                    try {
                        Parent sv;
                        sv = (AnchorPane) FXMLLoader.load(getClass().getResource("detRes.fxml"));
                        paneres.getChildren().removeAll();
                        paneres.getChildren().setAll(sv);
                    } catch (IOException ex) {
                        Logger.getLogger(ResInterController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
    }

}

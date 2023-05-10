/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.client;

import amena.gui.LocationInterface.GestionLocationController;
import amena.gui.ReservationInterface.CountdownDays;
import amena.gui.ReservationInterface.MyDataRes;
import amena.gui.ReservationInterface.ResInterController;
import static amena.gui.ReservationInterface.ResInterController.ids;
import amena.gui.ReservationInterface.reslistcell;
import amena.model.Reservation;
import amena.model.Vehicule;
import amena.services.ReservationCRUD;
import amena.services.VehiculeCRUD;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class ListresController implements Initializable {

    public static int page;

    @FXML
    private Label deblab;
    @FXML
    private Label finlab;
    @FXML
    private Label montlab;
    @FXML
    private Label etatlab;
    @FXML
    private Label templab;
    @FXML
    private ListView<MyDataRes> lv;
    @FXML
    private Pane pf;
    @FXML
    private AnchorPane allpane;

    Reservation rf;
    @FXML
    private Button anulbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        page = 0;
        ReservationCRUD rc = new ReservationCRUD();
        List<Reservation> lv2 = new ArrayList<>();
       try{
        lv2.addAll(rc.afficher_cl());
       }catch(Exception e){System.out.println(e.getMessage());}
        List<MyDataRes> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) {
            if ("En cours".equals(lv2.get(i).getEtat())) {
                lmd.add(new MyDataRes(lv2.get(i).getIdRes(), lv2.get(i).getDate_deb().toString(), lv2.get(i).getDate_fin().toString(), new Image("http://localhost/img/time.png"), lv2.get(i).getIdTrans(), lv2.get(i).getIdVeh()));
            } else {
                lmd.add(new MyDataRes(lv2.get(i).getIdRes(), lv2.get(i).getDate_deb().toString(), lv2.get(i).getDate_fin().toString(), new Image("http://localhost/img/checked.png"), lv2.get(i).getIdTrans(), lv2.get(i).getIdVeh()));
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
                MyDataRes rn;
                rn = lv.getSelectionModel().getSelectedItem();

                ReservationCRUD rc = new ReservationCRUD();

                DetResController.rstat = rc.getByID(rn.getId());
                if (DetResController.rstat != null) {
                    deblab.setText(DetResController.rstat.getDate_deb().toString());
                    finlab.setText(DetResController.rstat.getDate_fin().toString());
                    montlab.setText(Float.toString(DetResController.rstat.getSomme()));
                    etatlab.setText(DetResController.rstat.getEtat());
                    Date df;
                    df = DetResController.rstat.getDate_fin();
                    LocalDate date = LocalDate.parse(DetResController.rstat.getDate_fin().toString());
                    int year = date.getYear();
                    int month = date.getMonthValue();
                    int day = date.getDayOfMonth();
                    CountdownDays cd = new CountdownDays(year, month, day);
                    templab.setText(cd.updateCountdown());

                   
                    
                    
                    Date df2;
                    df2 = DetResController.rstat.getDate_fin();
                    LocalDate date2 = LocalDate.parse(DetResController.rstat.getDate_fin().toString());
                    int year2 = date2.getYear();
                    int month2 = date2.getMonthValue();
                    int day2 = date2.getDayOfMonth();
                    CountdownDays cd2 = new CountdownDays(year, month, day);

                   

                    if (Integer.parseInt(cd2.updateCountdown()) < 3) {
                        anulbtn.setDisable(true);
                    } else {
                        anulbtn.setDisable(false);
                    }

                }

            }

        });

    }

    @FXML
    private void retbtn(ActionEvent event) {
        if (page == 0) {
            try {
                Parent sv;
                sv = (AnchorPane) FXMLLoader.load(getClass().getResource("vitrine.fxml"));
                allpane.getChildren().removeAll();
                allpane.getChildren().setAll(sv);
            } catch (IOException ex) {
                Logger.getLogger(ListresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Parent sv;
                sv = (AnchorPane) FXMLLoader.load(getClass().getResource("listres.fxml"));
                allpane.getChildren().removeAll();
                allpane.getChildren().setAll(sv);
            } catch (IOException ex) {
                Logger.getLogger(ListresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void anulresbtn(ActionEvent event) {
        Date df;
        df = DetResController.rstat.getDate_deb();
        LocalDate date = LocalDate.parse(DetResController.rstat.getDate_deb().toString());
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        CountdownDays cd = new CountdownDays(year, month, day);
        if (Integer.parseInt(cd.updateCountdown()) > 3) {
            VehiculeCRUD vc = new VehiculeCRUD();
            ReservationCRUD rc = new ReservationCRUD();
            rc.modifier_etat(DetResController.rstat,"Terminé");
            vc.modifier_etat(DetResController.rstat.getIdVeh());
            vc.modifier_etat(DetResController.rstat.getIdVeh());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Reservation annulé avec succes");
            alert.showAndWait();
        }

    }

    @FXML
    private void vehbtn(ActionEvent event) {
        page = 1;
        GestionLocationController.ids = DetResController.rstat.getIdVeh();
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../detaille/detaille.fxml"));
            pf.getChildren().removeAll();
            pf.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ListresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

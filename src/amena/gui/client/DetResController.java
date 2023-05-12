/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.client;

import amena.gui.LocationInterface.GestionLocationController;
import static amena.gui.ProfilController.semail;
import amena.gui.ReservationInterface.CountdownDays;
import amena.model.Reservation;
import amena.model.User;
import amena.model.Vehicule;
import amena.services.ReservationCRUD;
import amena.services.UserService;
import amena.services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/**
 * FXML Controller class
 *
 * @author klair
 */
public class DetResController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private Button back;
    @FXML
    private Label labmat;
    @FXML
    private Label labmar;
    @FXML
    private Label labmod;
    @FXML
    private Label labkilo;
    @FXML
    private Label labprx;
    @FXML
    private ImageView imgv1;
    @FXML
    private DatePicker inpDate_deb;
    @FXML
    private DatePicker inpDate_fin;
    Vehicule v;
    @FXML
    private Label sommelab;

    public static Reservation rstat;
    @FXML
    private Label labPrise;
    
    public static List<java.util.Date> getDatesBetween(java.util.Date startDate, java.util.Date endDate) 
    {
    List<java.util.Date> datesInRange = new ArrayList<>();
    long interval = 24 * 1000 * 60 * 60; // 1 day in milliseconds

    long endTime = endDate.getTime();
    long curTime = startDate.getTime();
    while (curTime <= endTime) {
        datesInRange.add(new java.util.Date(curTime));
        curTime += interval;
    }
    return datesInRange;
    }
    
    
ArrayList<LocalDate> selectedDates = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v = VitrineController.vs;
        VehiculeCRUD vc = new VehiculeCRUD();

        labmat.setText(v.getImmat());
        labkilo.setText(v.getKilometrage());
        labmar.setText(v.getMarque());
        labmod.setText(v.getModele());
        labprx.setText(Float.toString(v.getPrix()));
        imgv1.setImage(new Image(v.getImg()));

        
        List<Reservation> resV = new ArrayList<>() ;
        ReservationCRUD rc = new ReservationCRUD() ; 
        try {
            resV = rc.afficher_ByVeh(v.getId()) ;
        } catch (SQLException ex) {
            Logger.getLogger(DetResController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        for (int i = 0; i < resV.size(); i++) {
  Date datedeb = resV.get(i).getDate_deb();
        Date datefin = resV.get(i).getDate_fin();
       java.util.Date utilDatedeb = new java.util.Date(datedeb.getTime());
        java.util.Date utilDatefin = new java.util.Date(datefin.getTime());

        LocalDate startLocalDate = utilDatedeb.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = utilDatefin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate currentDate = startLocalDate;


        while (!currentDate.isAfter(endLocalDate)) {
            selectedDates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
            System.out.println(selectedDates);
        }     
       
        
        
        
inpDate_deb.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (selectedDates.contains(date)) {
                    // Marquer les dates sélectionnées
setStyle("-fx-background-color: #ff0000;");
                 //   setSelected(true);
                } else {
                    setStyle(""); // Réinitialiser le style pour les autres jours
                   // setSelected(false);
                }
            }
        });

inpDate_fin.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (selectedDates.contains(date)) {
                    // Marquer les dates sélectionnées
setStyle("-fx-background-color: #ff0000;");
                 //   setSelected(true);
                } else {
                    setStyle(""); // Réinitialiser le style pour les autres jours
                   // setSelected(false);
                }
            }
        });
        
        labPrise.setText(v.getLpec());
        inpDate_deb.valueProperty().addListener((obs, oldValue, newValue) -> {
            inpDate_fin.valueProperty().addListener((obs2, oldValue2, newValue2) -> {
                Duration diff = Duration.between(inpDate_deb.getValue().atStartOfDay(), inpDate_fin.getValue().atStartOfDay());
                Period period = Period.between(inpDate_deb.getValue(), inpDate_fin.getValue());
                long diffDays = diff.toDays();
                if (diffDays > 0) {
                    sommelab.setText("TOTAL : " + diffDays * v.getPrix() + "dt");
                } else if (diffDays == 0) {
                    sommelab.setText("TOTAL : " + v.getPrix() + "dt");
                } else {
                    sommelab.setText("Date invalide");
                }

            });

        });
        
        
    }

    @FXML
    private void retourner(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("vitrine.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DetResController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reserverbtn(ActionEvent event) throws SQLException {
        Date date_deb = Date.valueOf(inpDate_deb.getValue().toString());
        Date date_fin = Date.valueOf(inpDate_fin.getValue().toString());
        
         java.util.Date utilDatedeb = new java.util.Date(date_deb.getTime());
        java.util.Date utilDatefin = new java.util.Date(date_fin.getTime());

        
          LocalDate startLocalDate = utilDatedeb.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate =utilDatefin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (date_fin.compareTo(date_deb) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vérifiez vos champs !");
            alert.showAndWait();
        } else if (selectedDates.contains(startLocalDate) || selectedDates.contains(endLocalDate)) 
{
      Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vehicule reservé dans cette periode");
            alert.showAndWait();
}
else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir reserver cet vehicule ?");
            VehiculeCRUD vc = new VehiculeCRUD();
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Duration diff = Duration.between(inpDate_deb.getValue().atStartOfDay(), inpDate_fin.getValue().atStartOfDay());
                Period period = Period.between(inpDate_deb.getValue(), inpDate_fin.getValue());
                long diffDays = diff.toDays();
                ReservationCRUD rc = new ReservationCRUD();

                UserService m = new UserService();
                User u=m.getUserByEmai(semail);
                System.out.println(v);
                Reservation r = new Reservation(v.getId(),u.getId(), date_deb, date_fin, v.getPrix() * diffDays,"En Attente");
                rc.ajouter(r);
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("reservation");
                alert2.setHeaderText(null);
                alert2.setContentText("Reservation reussite");
                alert2.showAndWait();
                rstat = r;
                try {
                    Parent sv;
                    sv = (AnchorPane) FXMLLoader.load(getClass().getResource("listres.fxml"));
                    pane1.getChildren().removeAll();
                    pane1.getChildren().setAll(sv);
                } catch (IOException ex) {
                    Logger.getLogger(DetResController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

}

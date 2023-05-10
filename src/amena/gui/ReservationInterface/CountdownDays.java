package amena.gui.ReservationInterface;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CountdownDays {

    private LocalDate END_DATE;  

  //  public CountdownDays(String )

    public CountdownDays(int a,int m,int j) {
             END_DATE = LocalDate.of(a, m, j);

    }
   
    
    
    

    public String updateCountdown() {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), END_DATE);
        return (String.format("%d", days));
    }
}
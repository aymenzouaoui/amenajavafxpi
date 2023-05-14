package test.workshop.controllers;
import static amena.gui.ProfilController.semail;
import amena.model.User;
import amena.services.UserService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TEST extends Application {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 300;

    private static final int GROUND_Y = CANVAS_HEIGHT - 50;
    private static final int DINO_SIZE = 50;
    private static final int CACTUS_SIZE = 30;

    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer timer;

    private int dinoY = GROUND_Y - DINO_SIZE;
    private int dinoSpeedY = 0;
    private boolean jumping = false;

    private int cactusX = CANVAS_WIDTH;
    private int cactusSpeedX = -5;

    private int score = 0;
    private boolean gameover = false;

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            // Reset game variables
            dinoY = GROUND_Y - DINO_SIZE;
            dinoSpeedY = 0;
            jumping = false;
            cactusX = CANVAS_WIDTH;
            cactusSpeedX = -5;
            score = 0;
            gameover = false;

            // Restart timer
            timer.start();
        });

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE && !jumping) {
                jumping = true;
                dinoSpeedY = -15;
            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    update();
                    draw();
                } catch (SQLException ex) {
                    Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dino Game");
        primaryStage.show();
    }

    private void update() throws SQLException {
        if (gameover) {
            return;
        }

        // Update dino position
        dinoY += dinoSpeedY;
        dinoSpeedY += 1;
        if (dinoY >= GROUND_Y - DINO_SIZE) {
            dinoY = GROUND_Y - DINO_SIZE;
            dinoSpeedY = 0;
            jumping = false;
        }

        // Update cactus position
        cactusX += cactusSpeedX;
        if (cactusX < -CACTUS_SIZE) {
            cactusX = CANVAS_WIDTH;
            score++;
            
  
        }

        // Check for collision
if (cactusX < 100 && cactusX + CACTUS_SIZE > 100 &&
        dinoY + DINO_SIZE > GROUND_Y - CACTUS_SIZE) {
    gameover = true;
    
    
}
    }

    private void draw() {
        try {
            // Clear canvas
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
            
            // Draw ground
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(0, GROUND_Y, CANVAS_WIDTH, CANVAS_HEIGHT - GROUND_Y);
            
            // Draw dino
            gc.setFill(Color.RED);
            gc.fillRect(100, dinoY, DINO_SIZE, DINO_SIZE);
            
            // Draw cactus
            gc.setFill(Color.BLACK);
            gc.fillRect(cactusX, GROUND_Y - CACTUS_SIZE, CACTUS_SIZE, CACTUS_SIZE);
            
            // Draw score
            gc.setFont(Font.getDefault());
            gc.setFill(Color.BLACK);
            gc.fillText("Score: " + score, 10, 20);
            UserService u = new UserService();
            User p =u.getUserByEmai(semail);
            String ss=p.getScore();
            int j=Integer.parseInt(ss);
            j=j+score;
            p.setScore(Integer.toString(j));
            u.modifier(p);
            
            // Draw game over message
            if (gameover) {
                gc.setFill(Color.RED);
                gc.setFont(new Font("Arial", 30));
                gc.fillText("GAME OVER", CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2);
                timer.stop();
            }   } catch (SQLException ex) {
            Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public static void main(String[] args) {
    launch(args);
}}
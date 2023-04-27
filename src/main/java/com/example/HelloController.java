package com.example;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    AnimationTimer gameLoop; // Declare an instance of AnimationTimer called gameLoop
    @FXML
    private AnchorPane plane; // Reference to the main pane in our FXML file

    Player player; // Declare an instance of Player called player

    /* Load in our background image from resources folder and store it as an instance of Image.
       We use getClass() method with getResourceAsStream() method because this is a JavaFX application,
       not a standard Java application */
    Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background.png"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load(); // Call load() method when program starts up

        /* Set the ImageView's image property using setImage() method and pass it backgroundImage variable */
        ImageView background = new ImageView(backgroundImage);
        plane.getChildren().add(background);

        Rectangle playerRect = new Rectangle(50, 50); // Create a rectangle for the player
        plane.getChildren().add(playerRect); // Add the rectangle to the pane

        player = new Player(playerRect, background); // Initialize our Player object with its attributes

        gameLoop = new AnimationTimer() {
            /* Create an anonymous inner class that extends AnimationTimer and override its handle()
               method which will be called every frame */
            @Override
            public void handle(long l) {
                update(); // Call update() method every frame
            }
        };

        gameLoop.start(); // Start the game loop
    }

    @FXML
    public void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            player.jump(); // Call jump() method when SPACE key is pressed
        } else if (event.getCode() == KeyCode.W) {
            player.jump(); // Call jump() method when W key is pressed
        } else if (event.getCode() == KeyCode.D) {
            player.moveRight(); // Call moveRight() method when D key is pressed
        } else if (event.getCode() == KeyCode.A) {
            player.moveLeft(); // Call moveLeft() method when A key is pressed
        }
    }

    private void update(){
          /* This method will be called every frame by our game loop.
          We can use it to check for collisions, update scores, etc. */
        if(player.isPlayerDead()){
            System.out.println("Game over!");
            gameLoop.stop();
            player.resetPlayer();
            load();
        }
    }

    private void load(){
        System.out.println("Game starting"); // Print a message when program starts up
    }
}
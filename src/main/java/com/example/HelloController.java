package com.example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    AnimationTimer gameLoop; // Declare an instance of AnimationTimer called gameLoop
    @FXML
    private AnchorPane plane; // Reference to the main pane in our FXML file
    @FXML
    private Rectangle bird; // Reference to the background image in our FXML file
    @FXML
    private ImageView background; // Reference to the background image in our FXML file
    double time = 0;




    /* Load in our background image from resources folder and store it as an instance of Image.
      We use getClass() method with getResourceAsStream() method because this is a JavaFX application,
      not a standard Java application */
    Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background.png"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        load(); // Call load() method when program starts up
        /* Set the ImageView's image property using setImage() method and pass it backgroundImage variable */
        background.setImage(backgroundImage);
        gameLoop = new AnimationTimer() {
            /* Create an anonymous inner class that extends AnimationTimer and override its handle()
               method which will be called every frame */
            @Override
            public void handle(long l) {
                //update(); // Call update() method every frame
            }
        };

        gameLoop.start(); // Start the game loop
    }

    @FXML
    public void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            jump(); // Call jump() method when SPACE key is pressed
        } else if (event.getCode() == KeyCode.W) {
            jump(); // Call jump() method when W key is pressed
        } else if (event.getCode() == KeyCode.D) {
            moveRight(); // Call moveRight() method when D key is pressed
        } else if (event.getCode() == KeyCode.A) {
            moveLeft(); // Call moveLeft() method when A key is pressed
        }

    }

    private void moveRight() {
        /* Move bird to the right by 20 pixels. This will be called in our keyPressed()
          method whenever user presses 'D' */
        moveBird(20, 0);
    }

    private void moveLeft() {
        /* Move bird to the left by 20 pixels. This will be called in our keyPressed()
         method whenever user presses 'A' */
        moveBird(-20, 0);
    }

    private void jump() {
        /* Make the bird "jump" up by changing its y-coordinate.
          We also schedule a task to make it fall back down after a certain amount of time */
        if (bird.getLayoutY() < 0) {
            return; // If bird has already jumped and hasn't fallen yet, don't allow another jump.
        }

        // Move the bird up by 10x ,30y pixels
        moveBird(10, -30);

        /* Create an instance of Timeline with a duration of 0.3 seconds and add a KeyFrame that calls this lambda expression,
              which moves the bird back down after jumping for some time */
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
            moveBird(0, 30);
            time = 0;
        }));

        timeline.play(); // Start the Timeline
    }

//    private void update() {
//        /* This method is called every frame by our game loop. It updates the bird's position and checks if it has collided with anything */
//        time++;
//        moveBird(0, yDelta * time);
//
//        if (isBirdDead()) {
//            resetBird(); // If bird has hit the ground, reset its position to top of screen.
//        }
//    }

    private void load() {
        System.out.println("Game starting"); // Print a message when program starts up

    }


    private void moveBird(double xChange, double yChange) {
        double oldX = bird.getX();
        double newX = oldX + xChange;

        /* Calculate new X-coordinate for the bird after moving it left or right */
        bird.setX(newX);

        double oldY = bird.getY();
        double newY = oldY + yChange;

        /* Calculate new Y-coordinate for the bird after moving it up or down */
        bird.setY(newY);


        double screenWidth = plane.getWidth();
        /* Check if we need to move our background too (if bird is near edge of screen) */
        if (bird.getBoundsInParent().getMaxX() >= 0.8 * screenWidth) {
            background.setX(background.getX() - 0.5 * xChange);
            /* If the bird is on the right side of the screen, move the background image to left by half
               as much as we moved the bird */
        } else if (bird.getBoundsInParent().getMinX() <= 0.2 * screenWidth) {
            background.setX(background.getX() - 0.5 * xChange);
            /* If the bird is on the left side of the screen, move the background image to right by half
                as much as we moved the bird */
        }
    }

    private boolean isBirdDead() {
        /* This method checks whether or not our bird has collided with anything.
          In this case, it just checks whether its y-coordinate exceeds that of our game window's height */
        double birdY = bird.getLayoutY() + bird.getY();
        return birdY >= plane.getHeight();
    }

    private void resetBird() {
        // Reset position and time when called
        bird.setY(0);
        time = 0;
    }



}


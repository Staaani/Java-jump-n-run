package com.example;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class Player {
    private Rectangle playerRect; // Reference to the background image in our FXML file
    private ImageView backgroundImg; // Reference to the background image in our FXML file
    private double time = 0;

    public Player(Rectangle playerRect, ImageView backgroundImg) {
        this.playerRect = playerRect;
        this.backgroundImg = backgroundImg;
    }

    public void moveRight() {
        /* Move bird to the right by 20 pixels. This will be called in our keyPressed()
        method whenever user presses 'D' */
        movePlayer(20, 0);
    }

    public void moveLeft() {
         /* Move bird to the left by 20 pixels. This will be called in our keyPressed()
         method whenever user presses 'A' */
        movePlayer(-20, 0);
    }

    public void jump() {
          /* Make the bird "jump" up by changing its y-coordinate.
          We also schedule a task to make it fall back down after a certain amount of time */
        if (playerRect.getLayoutY() < 0) {
            return; // If bird has already jumped and hasn't fallen yet, don't allow another jump.
        }

        // Move the bird up by 10x ,30y pixels
        movePlayer(10, -30);

        /* Create an instance of Timeline with a duration of 0.3 seconds and add a KeyFrame that calls this lambda expression,
        which moves the bird back down after jumping for some time */
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
            movePlayer(0, 30);
            time = 0;
        }));

        timeline.play(); // Start the Timeline
    }

    private void movePlayer(double xChange, double yChange) {
        double oldX = playerRect.getX();
        double newX = oldX + xChange;

        /* Calculate new X-coordinate for the bird after moving it left or right */
        playerRect.setX(newX);

        double oldY = playerRect.getY();
        double newY = oldY + yChange;

        /* Calculate new Y-coordinate for the bird after moving it up or down */
        playerRect.setY(newY);


        double screenWidth = backgroundImg.getParent().getBoundsInLocal().getWidth();
        /* Check if we need to move our background too (if bird is near edge of screen) */
        if (playerRect.getBoundsInParent().getMaxX() >= 0.8 * screenWidth) {
            backgroundImg.setX(backgroundImg.getX() - 0.5 * xChange);
           /* If the bird is on the right side of the screen, move the background image to left by half
           as much as we moved the bird */
        } else if (playerRect.getBoundsInParent().getMinX() <= 0.2 * screenWidth) {
            backgroundImg.setX(backgroundImg.getX() - 0.5 * xChange);
           /* If the bird is on the left side of the screen, move the background image to right by half
           as much as we moved the bird */
        }
    }

    public boolean isPlayerDead() {
        /* This method checks whether or not our bird has collided with anything.
        In this case, it just checks whether its y-coordinate exceeds that of our game window's height */
        double playerY = playerRect.getLayoutY() + playerRect.getY();
        return playerY >= backgroundImg.getParent().getBoundsInLocal().getHeight();
    }

    public void resetPlayer() {
        // Reset position and time when called
        playerRect.setY(0);
        time = 0;
    }
}
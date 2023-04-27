package com.example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;
    @FXML
    private Rectangle bird;

    @FXML
    private Rectangle platform1;

    @FXML
    private ImageView background;

    double yDelta = 0.02;
    double time = 0;
    int jumpHeight = 100;

    int jumpWidth = 50;

    // Load in our background image from resources folder
    Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background.png"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
        background.setImage(backgroundImage);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //update();
            }
        };
        gameLoop.start();
    }

    @FXML
    public void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
//            fly();
            jump();
        } else if (event.getCode() == KeyCode.D) {
            moveRight();
        }else if (event.getCode() == KeyCode.A) {
            moveLeft();
        }
//        else if (event.getCode() == KeyCode.S) {
//            moveDown();
//        }
    }

    private void moveRight() {
        moveBird(20,0);
    }
    private void moveLeft() {
        moveBird(-20,0);
    }
//    private void moveDown() {
//        moveBirdY(20);
//    }

    private void jump() {
        // If the bird is already in the air, don't allow another jump
        if (bird.getLayoutY() < 0) {
            return;
        }

        // Move the bird up by 30 pixels
        moveBird(10,-30);

        // Schedule a task to move the bird back down after 0.3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
            moveBird(0,30);
            time = 0;
        }));

        timeline.play();
    }

//    private void fly() {
//        if (bird.getLayoutY() + bird.getY() <= jumpHeight) {
//            moveBirdY(-(bird.getLayoutY() + bird.getY()));
//            time = 0;
//            return;
//        }
//        moveBirdY(-jumpHeight);
//        time = 0;
//    }

    private void update() {
        time++;
        moveBirdY(yDelta * time);

        if (isBirdDead()) {
            resetBird();
        }
    }

    private void load() {
        System.out.println("Game starting");

    }

//    private void moveBirdY(double positionChange) {
//        bird.setY(bird.getY() + positionChange);
//    }

    private void moveBirdY(double positionChange){
        double oldY = bird.getY();
        double newY = oldY + positionChange;

        // Move bird up/down by given amount
        bird.setY(newY);

        // Check if we need to move our background too (if bird is near edge of screen)
        double screenWidth = plane.getWidth();
        if (bird.getBoundsInParent().getMaxX() >= 0.8 * screenWidth) {
            background.setX(background.getX() - 0.5 * positionChange);
        } else if (bird.getBoundsInParent().getMinX() <= 0.2 * screenWidth) {
            background.setX(background.getX() - 0.5 * positionChange);
        }
    }

    private void moveBirdX(double positionChange) {
        bird.setX(bird.getX() + positionChange);
    }

    private void moveBird(double xChange, double yChange) {
        double oldX = bird.getX();
        double newX = oldX + xChange;

        // Move bird left/right by given amount
        bird.setX(newX);

        double oldY = bird.getY();
        double newY = oldY + yChange;

        // Move bird up/down by given amount
        bird.setY(newY);

        // Check if we need to move our background too (if bird is near edge of screen)
        double screenWidth = plane.getWidth();
        if (bird.getBoundsInParent().getMaxX() >= 0.8 * screenWidth) {
            background.setX(background.getX() - 0.5 * xChange);
        } else if (bird.getBoundsInParent().getMinX() <= 0.2 * screenWidth) {
            background.setX(background.getX() - 0.5 * xChange);
        }
    }

    private boolean isBirdDead() {
        double birdY = bird.getLayoutY() + bird.getY();
        return birdY >= plane.getHeight();
    }

    private void resetBird() {
        bird.setY(0);
        time = 0;
    }

}


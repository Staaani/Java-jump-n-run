package com.example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    double yDelta = 0.02;
    double time = 0;
    int jumpHeight = 100;

    int jumpWidth = 50;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();

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
        moveBirdX(20);
    }
    private void moveLeft() {
        moveBirdX(-20);
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
        moveBirdY(-30);

        // Schedule a task to move the bird back down after 0.3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            moveBirdY(30);
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

    private void moveBirdY(double positionChange) {
        bird.setY(bird.getY() + positionChange);
    }

    private void moveBirdX(double positionChange) {
        bird.setX(bird.getX() + positionChange);
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


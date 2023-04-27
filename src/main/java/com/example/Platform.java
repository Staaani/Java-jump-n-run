package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Platform {
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Rectangle view;

    public Platform(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.view = new Rectangle(xPos, yPos, width, height);
        this.view.setFill(Color.GREEN);
    }

    public Rectangle getView() {
        return view;
    }

    public static void generatePlatforms(Pane root) {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            double xPos = rand.nextDouble() * 700;
            double yPos = rand.nextDouble() * 500;
            double width = rand.nextDouble() * 50 + 50;
            double height = 10;

            Platform platform = new Platform(xPos, yPos, width, height);
            root.getChildren().add(platform.getView());
        }
    }
}


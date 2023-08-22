package com.example.programacion4proyectofinal.Utils;

import javafx.stage.Stage;

public class ChangePropertiesStage {

    public void changeSizeStage(int width, int height, Stage stage) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
    }

    public void changeMinSizeStage(int minWidth, int minHeight, double width, double height, Stage stage) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
    }

    public void changePositionStage(int positionX, int positionY, Stage stage) {
        stage.setX(positionX);
        stage.setY(positionY);
    }

    public void changeToMaximizeSizeStage(int width, int height, Stage stage) {
        stage.setMaximized(true);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
    }

}

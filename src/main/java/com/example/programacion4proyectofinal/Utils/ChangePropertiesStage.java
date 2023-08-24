package com.example.programacion4proyectofinal.Utils;

import javafx.stage.Stage;

/**
 * This utility class provides methods to change various properties of a JavaFX Stage.
 */
public class ChangePropertiesStage {

    /**
     * Changes the size of the stage and sets minimum width and height.
     *
     * @param width  The new width of the stage.
     * @param height The new height of the stage.
     * @param stage  The JavaFX Stage to modify.
     */
    public void changeSizeStage(int width, int height, Stage stage) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
    }

    /**
     * Changes the minimum size, size, and position of the stage.
     *
     * @param minWidth  The new minimum width of the stage.
     * @param minHeight The new minimum height of the stage.
     * @param width     The new width of the stage.
     * @param height    The new height of the stage.
     * @param stage     The JavaFX Stage to modify.
     */
    public void changeMinSizeStage(int minWidth, int minHeight, double width, double height, Stage stage) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
    }

    /**
     * Changes the position of the stage.
     *
     * @param positionX The new X position of the stage.
     * @param positionY The new Y position of the stage.
     * @param stage     The JavaFX Stage to modify.
     */
    public void changePositionStage(int positionX, int positionY, Stage stage) {
        stage.setX(positionX);
        stage.setY(positionY);
    }

    /**
     * Maximizes the stage and sets minimum width and height.
     *
     * @param width  The new width of the stage.
     * @param height The new height of the stage.
     * @param stage  The JavaFX Stage to modify.
     */
    public void changeToMaximizeSizeStage(int width, int height, Stage stage) {
        stage.setMaximized(true);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
    }
}
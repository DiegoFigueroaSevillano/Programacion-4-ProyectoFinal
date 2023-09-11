package com.example.programacion4proyectofinal.Utils.ViewUtils;

import javafx.stage.Screen;
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
    }

    /**
     * Maximizes the stage and sets minimum width and height.
     *
     * @param stage  The JavaFX Stage to modify.
     */
    public void changeToMaximizeSizeStage(Stage stage) {
        Screen screen = Screen.getPrimary();
        stage.setMaximized(true);
        stage.setMinWidth(screen.getBounds().getWidth());
        stage.setMinHeight(screen.getBounds().getHeight());
    }
}
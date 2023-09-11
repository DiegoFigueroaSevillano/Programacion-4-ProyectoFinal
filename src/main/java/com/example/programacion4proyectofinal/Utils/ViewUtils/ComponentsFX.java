package com.example.programacion4proyectofinal.Utils.ViewUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * This utility class provides methods for generating JavaFX components.
 */
public class ComponentsFX {
    private static ComponentsFX instance;

    /**
     * Creates a singleton instance of this class.
     *
     * @return A singleton instance of this class.
     */
    public static ComponentsFX getInstance() {
        if (instance == null) instance = new ComponentsFX();
        return instance;
    }

    /**
     * Creates a label with the specified parameters.
     * @param labelText The text to be displayed in the label.
     * @param labelWidth The width of the label.
     * @param labelHeight The height of the label.
     * @param alignment The alignment of the label.
     * @param positionX The X position of the label.
     * @param positionY The Y position of the label.
     * @param colorBack The background color of the label.
     * @param fontSize The font size of the label.
     * @param padding The padding of the label.
     * @param fontFamily The font family of the label.
     * @param fontWeight The font weight of the label.
     * @return A JavaFX Label object with the specified parameters.
     */
    public Label createLabel(String labelText, int labelWidth, int labelHeight, Pos alignment, int positionX,
                             int positionY, String colorBack, int fontSize, int padding, String fontFamily, String fontWeight) {
        Label label = new Label(labelText);
        label.setPrefWidth(labelWidth);
        label.setAlignment(alignment);
        label.setPrefHeight(labelHeight);
        label.setPadding(new Insets(padding));
        label.setMaxWidth(labelWidth);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setTextFill(Paint.valueOf(colorBack));
        label.setStyle("-fx-font-size: " + fontSize + ";" +
                "-fx-font-weight: " + fontWeight + ";" +
                "-fx-font-family: " + fontFamily + ";");
        label.setLayoutX(positionX);
        label.setLayoutY(positionY);
        return label;
    }
}

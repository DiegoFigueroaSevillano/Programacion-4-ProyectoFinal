package com.example.programacion4proyectofinal.Utils.ViewUtils;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This utility class provides methods for generating JavaFX Background objects.
 */
public class BackgroundGenerator {

    /**
     * Creates a solid color background with the specified color.
     *
     * @param color The color value for the background.
     * @return A JavaFX Background object with the specified color.
     */
    public Background createBackground(String color) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY);
        return new Background(backgroundFill);
    }

    /**
     * Creates a background with the specified image URL as the background image.
     *
     * @param imageURL The URL of the image to be used as the background.
     * @return A JavaFX Background object with the specified image as the background.
     */
    public Background createBackgroundImage(String imageURL) {
        Image backgroundImage = new Image(imageURL);
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(background);
    }

}
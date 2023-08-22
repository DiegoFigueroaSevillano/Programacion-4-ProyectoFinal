package com.example.programacion4proyectofinal.Utils;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.SKY_BLUE_75;

public class BackgroundGenerator {

    public Background createBackground(String color) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY);
        return new Background(backgroundFill);
    }

    public Background createBackgroundImage(String imageURL) {
        Image homeCover = new Image(imageURL);
        BackgroundImage homeBackground = new BackgroundImage(
                homeCover,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(homeBackground);
    }

}

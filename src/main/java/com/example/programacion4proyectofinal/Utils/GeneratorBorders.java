package com.example.programacion4proyectofinal.Utils;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Utility class for generating custom Borders with different styles and radii.
 */
public class GeneratorBorders {

    /**
     * Creates a solid Border with rounded corners of a specified radius, width, and color.
     *
     * @param radius    The radius of the rounded corners.
     * @param widthSize The width of the border.
     * @param color     The color of the border.
     * @return A Border object with the specified properties.
     */
    public Border createBorderRadiusSolid(int radius, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(radius);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.SOLID, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }
}

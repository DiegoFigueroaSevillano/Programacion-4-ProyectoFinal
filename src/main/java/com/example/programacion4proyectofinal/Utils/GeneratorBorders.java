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

    /**
     * Creates a solid Border with individual rounded corners, width, and color for each corner.
     *
     * @param leftTop     The radius for the top-left corner.
     * @param rightTop    The radius for the top-right corner.
     * @param rightBottom The radius for the bottom-right corner.
     * @param leftBottom  The radius for the bottom-left corner.
     * @param widthSize   The width of the border.
     * @param color       The color of the border.
     * @return A Border object with the specified properties for each corner.
     */
    public Border createBorderRadiusSolid(int leftTop, int rightTop, int rightBottom, int leftBottom, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(leftTop, rightTop, rightBottom, leftBottom, false);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.SOLID, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }

    /**
     * Creates a dashed Border with rounded corners of a specified radius, width, and color.
     *
     * @param radius    The radius of the rounded corners.
     * @param widthSize The width of the border.
     * @param color     The color of the border.
     * @return A Border object with dashed style and the specified properties.
     */
    public Border createBorderRadiusDashed(int radius, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(radius);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.DASHED, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }

    /**
     * Creates a dashed Border with individual rounded corners, width, and color for each corner.
     *
     * @param leftTop     The radius for the top-left corner.
     * @param rightTop    The radius for the top-right corner.
     * @param rightBottom The radius for the bottom-right corner.
     * @param leftBottom  The radius for the bottom-left corner.
     * @param widthSize   The width of the border.
     * @param color       The color of the border.
     * @return A Border object with dashed style and the specified properties for each corner.
     */
    public Border createBorderRadiusDashed(int leftTop, int rightTop, int rightBottom, int leftBottom, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(leftTop, rightTop, rightBottom, leftBottom, false);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.DASHED, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }
}

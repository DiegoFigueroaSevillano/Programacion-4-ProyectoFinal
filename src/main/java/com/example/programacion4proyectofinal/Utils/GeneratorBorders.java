package com.example.programacion4proyectofinal.Utils;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GeneratorBorders {

    public Border createBorderRadiusSolid(int radius, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(radius);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.SOLID, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }

    public Border createBorderRadiusSolid(int leftTop, int rightTop, int rightBottom, int leftBottom, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(leftTop, rightTop, rightBottom, leftBottom, false);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.SOLID, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }

    public Border createBorderRadiusDashed(int radius, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(radius);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.DASHED, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }

    public Border createBorderRadiusDashed(int leftTop, int rightTop, int rightBottom, int leftBottom, int widthSize, String color) {
        CornerRadii cornerRadii = new CornerRadii(leftTop, rightTop, rightBottom, leftBottom, false);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(color), BorderStrokeStyle.DASHED, cornerRadii, new BorderWidths(widthSize));
        return new Border(borderStroke);
    }

}

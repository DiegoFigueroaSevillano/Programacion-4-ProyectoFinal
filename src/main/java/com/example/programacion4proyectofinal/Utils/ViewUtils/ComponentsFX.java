package com.example.programacion4proyectofinal.Utils.ViewUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ComponentsFX {
    private static ComponentsFX instance;

    public static ComponentsFX getInstance() {
        if (instance == null) instance = new ComponentsFX();
        return instance;
    }

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

    public HBox createHBox(int width, int height, int spacing, Pos alignment, int positionX, int positionY) {
        HBox hBox = new HBox();
        hBox.setPrefWidth(width);
        hBox.setPrefHeight(height);
        hBox.setMinHeight(height);
        hBox.setMinWidth(width);
        hBox.setSpacing(spacing);
        hBox.setAlignment(alignment);
        hBox.setLayoutX(positionX);
        hBox.setLayoutY(positionY);
        return hBox;
    }

    public VBox createVBox(int width, int height, int spacing, Pos alignment, int positionX, int positionY) {
        VBox vBox = new VBox();
        vBox.setPrefWidth(width);
        vBox.setPrefHeight(height);
        vBox.setMinHeight(height);
        vBox.setMinWidth(width);
        vBox.setSpacing(spacing);
        vBox.setAlignment(alignment);
        vBox.setLayoutX(positionX);
        vBox.setLayoutY(positionY);
        return vBox;
    }

    public ScrollPane createScrollPane(int width, int height, int positionX, int positionY) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(width);
        scrollPane.setPrefHeight(height);
        scrollPane.setMinHeight(height);
        scrollPane.setMinWidth(width);
        scrollPane.setLayoutX(positionX);
        scrollPane.setLayoutY(positionY);
        return scrollPane;
    }

    public void followSizeFlowPane(ScrollPane scrollPane) {
        FlowPane flowPane = (FlowPane) scrollPane.getContent();
        scrollPane.viewportBoundsProperty().addListener((ov, oldBounds, bounds) -> {
            Stage window = (Stage) (scrollPane.getContent().getScene().getWindow());
            flowPane.setPrefWidth(bounds.getWidth());
            flowPane.setPrefHeight(bounds.getHeight());
            scrollPane.setPrefHeight(window.getHeight());
        });
    }
}

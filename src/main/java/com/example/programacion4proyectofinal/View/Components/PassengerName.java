package com.example.programacion4proyectofinal.View.Components;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

public class PassengerName {

    private VBox container;
    private Label label;
    private TextField textField;

    public PassengerName() {
        createContainer();
    }

    private void createContainer() {
        createLabel();
        createTextField();

        container = new VBox(0);
        container.setPrefWidth(740);
        container.setPrefHeight(120);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(label, textField);
    }

    private void createLabel() {
        label = new Label("NAME");
        label.setPrefWidth(740);
        label.setPrefHeight(40);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-font-size: 24px");
        label.setTextFill(Color.valueOf(WHITE));
    }

    private void createTextField() {
        textField = new TextField();
        textField.setPrefWidth(740);
        textField.setMaxWidth(740);
        textField.setPrefHeight(80);
        textField.setStyle("-fx-font-size: 24px");
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        textField.setBackground(backgroundGenerator.createBackground(WHITE));
    }

    public VBox getContainer() {
        return container;
    }
}

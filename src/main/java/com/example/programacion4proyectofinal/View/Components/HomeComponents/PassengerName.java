package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

/**
 * This class generates and configures a section for entering passenger's name.
 */
public class PassengerName {

    private VBox container;
    private Label label;
    private TextField textField;
    private GenerateFont generateFont;

    /**
     * Constructs a PassengerName section.
     */
    public PassengerName() {
        generateFont = new GenerateFont();
        createContainer();
    }

    /**
     * Creates the container for the PassengerName section.
     */
    private void createContainer() {
        createLabel();
        createTextField();

        container = new VBox(0);
        container.setPrefWidth(740);
        container.setPrefHeight(120);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(label, textField);
    }

    /**
     * Creates the label for the passenger's name.
     */
    private void createLabel() {
        label = new Label("NAME");
        label.setPrefWidth(740);
        label.setPrefHeight(40);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(generateFont.latoRegular(24));
        label.setTextFill(Color.valueOf(WHITE));
    }

    /**
     * Creates the text field for entering passenger's name.
     */
    private void createTextField() {
        textField = new TextField();
        textField.setPrefWidth(740);
        textField.setMaxWidth(740);
        textField.setPrefHeight(80);
        textField.setFont(generateFont.latoRegular(24));
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        textField.setBackground(backgroundGenerator.createBackground(WHITE));
    }

    /**
     * Gets the container of the PassengerName section.
     *
     * @return The VBox container.
     */
    public VBox getContainer() {
        return container;
    }
}
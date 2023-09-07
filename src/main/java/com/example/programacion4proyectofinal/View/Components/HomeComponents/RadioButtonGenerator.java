package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;

/**
 * This class generates and configures a radio button with a title.
 */
public class RadioButtonGenerator {

    private RadioButton radioButton;
    private String title;
    private HBox radioButtonSection;

    /**
     * Constructs a RadioButtonGenerator with the specified title.
     *
     * @param title The title of the radio button.
     */
    public RadioButtonGenerator(String title) {
        this.title = title;
        createRadioButtonSection();
    }

    /**
     * Creates and configures the radio button section containing the configured radio button.
     */
    private void createRadioButtonSection() {
        createRadioButton();

        radioButtonSection = new HBox(20);
        radioButtonSection.setPrefHeight(40);
        radioButtonSection.setPrefWidth(350);
        radioButtonSection.setAlignment(Pos.CENTER);
        radioButtonSection.getChildren().addAll(radioButton);
    }

    /**
     * Creates and configures the radio button.
     */
    private void createRadioButton() {
        radioButton = new RadioButton();
        radioButton.setPrefWidth(350);
        radioButton.setPrefHeight(40);
        radioButton.setMinHeight(40);
        radioButton.setMinWidth(350);
        radioButton.setId(title);
        radioButton.setPrefSize(40, 40);
        radioButton.setPadding(new Insets(4));
        radioButton.setStyle("-fx-font-size: 24px");
        radioButton.setText(title);
        radioButton.setTextFill(Color.valueOf(WHITE));
    }

    /**
     * Gets the radio button section containing the configured radio button.
     *
     * @return The HBox containing the radio button.
     */
    public HBox getRadioButtonSection() {
        return radioButtonSection;
    }

    /**
     * Gets the configured radio button.
     *
     * @return The radio button.
     */
    public RadioButton getRadioButton() {
        return radioButton;
    }
}

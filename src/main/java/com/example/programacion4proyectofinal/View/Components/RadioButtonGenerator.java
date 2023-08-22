package com.example.programacion4proyectofinal.View.Components;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

public class RadioButtonGenerator {

    private RadioButton radioButton;
    private String title;
    private HBox radioButtonSection;
    private BackgroundGenerator backgroundGenerator;

    public RadioButtonGenerator(String title) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.title = title;
        createRadioButtonSection();
    }

    private void createRadioButtonSection() {
        createRadioButton();

        radioButtonSection = new HBox(20);
        radioButtonSection.setPrefHeight(40);
        radioButtonSection.setPrefWidth(350);
        radioButtonSection.setAlignment(Pos.CENTER);
        radioButtonSection.getChildren().addAll(radioButton);
    }

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

    public HBox getRadioButtonSection() {
        return radioButtonSection;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}
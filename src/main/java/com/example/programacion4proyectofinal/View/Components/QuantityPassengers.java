package com.example.programacion4proyectofinal.View.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class QuantityPassengers {

    private Spinner<Integer> adults, children, babies;
    private Label adultsLabel, childrenLabel, babiesLabel;
    private HBox container, adultsSection, childrenSection, babiesSection;
    private final int WIDTH = 168, HEIGHT = 80;

    public QuantityPassengers() {
        createContainer();
    }

    private void createContainer() {
        createAdultsSection();
        createChildrenSection();
        createBabiesSection();

        container = new HBox(20);
        container.setPrefWidth(900);
        container.setPrefHeight(80);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(adultsSection, childrenSection, babiesSection);
    }

    private void createAdultsSection() {
        adultsLabel = new Label("ADULTS");
        generateLabels(adultsLabel);

        adults = new Spinner<>();
        generateSpinners(adults);

        adultsSection = new HBox(20);
        generateSections(adultsSection);
        adultsSection.getChildren().addAll(adultsLabel, adults);
    }

    private void createChildrenSection() {
        childrenLabel = new Label("CHILDREN");
        generateLabels(childrenLabel);

        children = new Spinner<>();
        generateSpinners(children);

        childrenSection = new HBox(20);
        generateSections(childrenSection);
        childrenSection.getChildren().addAll(childrenLabel, children);
    }

    private void createBabiesSection() {
        babiesLabel = new Label("BABIES");
        generateLabels(babiesLabel);

        babies = new Spinner<>();
        generateSpinners(babies);

        babiesSection = new HBox(20);
        generateSections(babiesSection);
        babiesSection.getChildren().addAll(babiesLabel, babies);
    }

    private void generateSections(HBox hBox) {
        hBox.setPrefHeight(HEIGHT);
        hBox.setAlignment(Pos.CENTER_LEFT);
    }

    private void generateLabels(Label label) {
        label.setTextFill(Color.valueOf(WHITE));
        label.setStyle("-fx-font-size: 24px");
        label.setPrefHeight(HEIGHT);
        label.setAlignment(Pos.CENTER_LEFT);
    }

    private void generateSpinners(Spinner<Integer> spinner) {
        spinner.setPrefHeight(HEIGHT);
        spinner.setPrefWidth(120);
        spinner.setStyle("-fx-font-size: 24px");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner.setValueFactory(valueFactory);
    }

    public HBox getContainer() {
        return container;
    }
}

package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.*;

/**
 * This class generates and configures a section for selecting the quantity of passengers.
 */
public class QuantityPassengers {

    private Spinner<Integer> adults, children, babies;
    private Label adultsLabel, childrenLabel, babiesLabel;
    private HBox container, adultsSection, childrenSection, babiesSection;
    private final int HEIGHT = 80;

    /**
     * Constructs a QuantityPassengers section.
     */
    public QuantityPassengers() {
        createContainer();
    }

    /**
     * Creates the container for the quantity passengers section.
     */
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

    /**
     * Creates the section for selecting the quantity of adults.
     */
    private void createAdultsSection() {
        adultsLabel = new Label("ADULTS");
        generateLabels(adultsLabel);

        adults = new Spinner<>();
        generateSpinners(adults);

        adultsSection = new HBox(20);
        generateSections(adultsSection);
        adultsSection.getChildren().addAll(adultsLabel, adults);
    }

    /**
     * Creates the section for selecting the quantity of children.
     */
    private void createChildrenSection() {
        childrenLabel = new Label("CHILDREN");
        generateLabels(childrenLabel);

        children = new Spinner<>();
        generateSpinners(children);

        childrenSection = new HBox(20);
        generateSections(childrenSection);
        childrenSection.getChildren().addAll(childrenLabel, children);
    }

    /**
     * Creates the section for selecting the quantity of babies.
     */
    private void createBabiesSection() {
        babiesLabel = new Label("BABIES");
        generateLabels(babiesLabel);

        babies = new Spinner<>();
        generateSpinners(babies);

        babiesSection = new HBox(20);
        generateSections(babiesSection);
        babiesSection.getChildren().addAll(babiesLabel, babies);
    }

    /**
     * Generates the styling for sections.
     *
     * @param hBox The HBox to apply styling to.
     */
    private void generateSections(HBox hBox) {
        hBox.setPrefHeight(HEIGHT);
        hBox.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Generates the styling for labels.
     *
     * @param label The label to apply styling to.
     */
    private void generateLabels(Label label) {
        label.setTextFill(Color.valueOf(WHITE));
        label.setStyle("-fx-font-size: 24px");
        label.setPrefHeight(HEIGHT);
        label.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Generates the styling for spinners and sets up their value factory.
     *
     * @param spinner The spinner to apply styling and value factory to.
     */
    private void generateSpinners(Spinner<Integer> spinner) {
        spinner.setPrefHeight(HEIGHT);
        spinner.setPrefWidth(120);
        spinner.setStyle("-fx-font-size: 24px");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner.setValueFactory(valueFactory);
    }

    /**
     * Gets the container of the quantity passengers section.
     *
     * @return The HBox container.
     */
    public HBox getContainer() {
        return container;
    }
}

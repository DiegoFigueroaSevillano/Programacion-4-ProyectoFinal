package com.example.programacion4proyectofinal.View.Components.ListComponents;


import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Model.Flight.Data.City;
import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightInfoButton {
    private Button buttonContainer;
    private HBox valueContainer;
    private Label originLabel, detinationLabel, departureLabel, arrivalDateLAbel,cotsLabel,airlineLabel;
    private int cost;
    private Airline airline;
    private City destination , origin;
    private LocalDateTime departureDate ,arrivalDate;
    private ScrollPane pane;
    private Button addButton;
    private BackgroundGenerator backgroundGenerator;
    private Flight user;



    public FlightInfoButton(Flight flight, ScrollPane pane) {
        this.user = flight;
        this.airline = flight.getAirline();
        this.origin = flight.getOrigin();
        this.destination = flight.getDestination();
        this.departureDate = flight.getDepartureDate();
        this.arrivalDate = flight.getArrivalDate();
        this.cost = flight.getCostOfTheFlight();
        this.backgroundGenerator = new BackgroundGenerator();
        this.pane = pane;
        createButtonContainer(this.pane);
    }

    /**
     * This method return us the pay button
     *
     * @return pay button
     */
    public Button getPayButton() {
        return addButton ;
    }

    /**
     * This method return us the user
     *
     * @return the user
     */
    public Flight getUser() {
        return user;
    }

    /**
     * This method return us the delete button
     *
     * @return delete button
     */

    /**
     * This method return us de button container
     *
     * @return button container
     */
    public Button getButtonContainer() {
        return buttonContainer;
    }

    /**
     * This method create the button with all values
     * @param pane the pane
     */
    private void createButtonContainer(ScrollPane pane){
        this.buttonContainer = new Button();
        this.buttonContainer.prefWidthProperty().bind(pane.widthProperty());
        this.buttonContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.05));

        createValueContainer();
        this.buttonContainer.setGraphic(this.valueContainer);
    }

    /**
     * This method create the ultra container
     */
    private void createValueContainer(){
        this.valueContainer = new HBox(5);
        this.valueContainer.setPadding(new Insets(10));
        this.valueContainer.setAlignment(Pos.CENTER);

        createAirlineNameLabel(valueContainer);
        createOriginCityLabel(valueContainer);
        createArrivalDateLabel(valueContainer);
        createDepartureDateLabel(valueContainer);
        createDestinationCityLabel(valueContainer);
        createCostLabel(valueContainer);
        createAddButton(valueContainer);

        this.valueContainer.getChildren().addAll(airlineLabel,originLabel, arrivalDateLAbel,departureLabel,detinationLabel, cotsLabel, addButton);
    }

    /**
     * Creates and configures the airline name Label for displaying the airline's name.
     *
     * @param container The parent HBox container where the airline name Label is added.
     */
    private void createAirlineNameLabel(HBox container) {
        this.airlineLabel = new Label();
        this.airlineLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.airlineLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.airlineLabel.setAlignment(Pos.CENTER);
        this.airlineLabel.setText(String.valueOf(airline));
        setTextResponsiveLabel(this.airlineLabel, 15);
    }

    /**
     * Creates and configures the origin city Label for displaying the flight's origin city.
     *
     * @param container The parent HBox container where the origin city Label is added.
     */
    private void createOriginCityLabel(HBox container) {
        this.originLabel = new Label();
        this.originLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.originLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.originLabel.setAlignment(Pos.CENTER);
        this.originLabel.setText(String.valueOf(origin));
        setTextResponsiveLabel(this.originLabel, 15);
    }

    /**
     * Creates and configures the departure date Label for displaying the flight's departure date.
     *
     * @param container The parent HBox container where the departure date Label is added.
     */
    private void createDepartureDateLabel(HBox container) {
        this.departureLabel = new Label();
        this.departureLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.departureLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.departureLabel.setAlignment(Pos.CENTER);
        this.departureLabel.setText(String.valueOf(departureDate.toLocalDate()));
        setTextResponsiveLabel(this.departureLabel, 15);
    }

    /**
     * Creates and configures the arrival date Label for displaying the flight's arrival date.
     *
     * @param container The parent HBox container where the arrival date Label is added.
     */
    private void createArrivalDateLabel(HBox container) {
        this.arrivalDateLAbel = new Label();
        this.arrivalDateLAbel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.arrivalDateLAbel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.arrivalDateLAbel.setAlignment(Pos.CENTER);
        this.arrivalDateLAbel.setText(String.valueOf(arrivalDate.toLocalDate()));
        setTextResponsiveLabel(this.arrivalDateLAbel, 15);
    }

    /**
     * Creates and configures the destination city Label for displaying the flight's destination city.
     *
     * @param container The parent HBox container where the destination city Label is added.
     */
    private void createDestinationCityLabel(HBox container) {
        this.detinationLabel = new Label();
        this.detinationLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.detinationLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.detinationLabel.setAlignment(Pos.CENTER);
        this.detinationLabel.setText(String.valueOf(destination));
        setTextResponsiveLabel(this.departureLabel, 15);
    }

    /**
     * Creates and configures the cost Label for displaying the flight's cost.
     *
     * @param container The parent HBox container where the cost Label is added.
     */
    private void createCostLabel(HBox container) {
        this.cotsLabel = new Label();
        this.cotsLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.cotsLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.cotsLabel.setAlignment(Pos.CENTER);
        this.cotsLabel.setText(String.valueOf(cost));
        setTextResponsiveLabel(this.cotsLabel, 15);
    }




    /**
     * This method create the Add button
     *
     * @param container the container
     */
    private void createAddButton(HBox container){
        this.addButton = new Button();
        this.addButton.prefHeightProperty().bind(container.heightProperty().multiply(0.5));
        this.addButton.prefWidthProperty().bind(container.widthProperty().multiply(0.10));
        this.addButton.setStyle("-fx-font-weight: bold; -fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, transparent,oldlace); -fx-text-fill: white;");


        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Logo/addButton.png")));

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(32);
        imageView.setFitHeight(32);

        this.addButton.setGraphic(imageView);
        setTextResponsiveLabel(this.addButton);
    }

    /**
     * This method set the responsive text into a label
     *
     * @param label the label
     * @param divisor the divisor
     */
    private void setTextResponsiveLabel(Label label, int divisor){
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / divisor;
            label.setStyle(label.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

    /**
     * This method set the responsive letter in a button
     *
     * @param button the button
     */
    private void setTextResponsiveLabel(Button button){
        button.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / 15;
            button.setStyle(button.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }



}

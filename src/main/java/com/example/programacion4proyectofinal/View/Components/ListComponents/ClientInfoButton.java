package com.example.programacion4proyectofinal.View.Components.ListComponents;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ClientInfoButton {
    private Button buttonContainer;
    private HBox valueContainer;
    private Label fullNameLabel, ciLabel, priorityLabel, statusLabel;
    private String fullNameText, priorityText, statusText;
    private int ciText;
    private ScrollPane pane;
    private final int HEIGHT = 30;

    public ClientInfoButton(Passenger passenger, ScrollPane pane) {
        this.fullNameText = passenger.getName(); //TODO: INGRESAR EL APELLIDO IGUAL
        this.ciText = passenger.getId();
        this.priorityText = "VIP"; //TODO: CAMBIAR POR LA PRIORIDAD CORRECTA
        this.statusText = "RESERVED";
        this.pane = pane;
        createButtonContainer(this.pane);
    }

    public Button getButtonContainer() {
        return buttonContainer;
    }

    private void createButtonContainer(ScrollPane pane){
        this.buttonContainer = new Button();
        this.buttonContainer.prefWidthProperty().bind(pane.widthProperty());
        this.buttonContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.05));

        createValueContainer();
        this.buttonContainer.setGraphic(this.valueContainer);
    }

    private void createValueContainer(){
        this.valueContainer = new HBox(5);
        this.valueContainer.setPadding(new Insets(10));
        this.valueContainer.setAlignment(Pos.CENTER);

        createFullNameLabel(valueContainer);
        createCILabel(valueContainer);
        createPriorityLabel(valueContainer);
        createStatusLabel(valueContainer);

        this.valueContainer.getChildren().addAll(fullNameLabel, ciLabel, priorityLabel, statusLabel);
    }

    private void createFullNameLabel(HBox container){
        this.fullNameLabel = new Label();
        this.fullNameLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.fullNameLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.4));
        this.fullNameLabel.setAlignment(Pos.CENTER);
        this.fullNameLabel.setText(fullNameText);
        setTextResponsiveLabel(this.fullNameLabel);
    }

    private void createCILabel(HBox container){
        this.ciLabel = new Label();
        this.ciLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.ciLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.2));
        this.ciLabel.setAlignment(Pos.CENTER);
        this.ciLabel.setText(String.valueOf(ciText));
        setTextResponsiveLabel(this.ciLabel);
    }

    private void createPriorityLabel(HBox container){
        this.priorityLabel = new Label();
        this.priorityLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.priorityLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.2));
        this.priorityLabel.setAlignment(Pos.CENTER);
        this.priorityLabel.setText(String.valueOf(priorityText));
        setTextResponsiveLabel(this.priorityLabel);
    }

    private void createStatusLabel(HBox container){
        this.statusLabel = new Label();
        this.statusLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.statusLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.2));
        this.statusLabel.setAlignment(Pos.CENTER);
        this.statusLabel.setText(String.valueOf(statusText));
        setTextResponsiveLabel(this.statusLabel);
    }

    private void setTextResponsiveLabel(Label label){
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / 10;
            label.setStyle(label.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

}

package com.example.programacion4proyectofinal.View.Components.ListComponents;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ClientInfoButton {
    private Button buttonContainer;
    private HBox valueContainer;
    private Label fullNameLabel, ciLabel, priorityLabel, statusLabel;
    private String fullNameText, priorityText, statusText;
    private int ciText;
    private ScrollPane pane;
    private VBox statusAndCategory;
    private Button payButton;
    private Button deleteButton;
    private BackgroundGenerator backgroundGenerator;


    public ClientInfoButton(UserFlightInfo userFlightInfo, ScrollPane pane) {
        this.fullNameText = userFlightInfo.getPassenger().getFullName();
        this.ciText = userFlightInfo.getPassenger().getId();
        this.priorityText = userFlightInfo.getPassenger().getCategory().toString();
        this.statusText = userFlightInfo.getStatus().toString();
        this.backgroundGenerator = new BackgroundGenerator();
        this.pane = pane;
        createButtonContainer(this.pane);
    }

    public Button getPayButton() {
        return payButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
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
       createStatusAndCategoryContainer(valueContainer);
       createPayButton(valueContainer);
       createDeleteButton(valueContainer);

        this.valueContainer.getChildren().addAll(fullNameLabel, ciLabel, statusAndCategory, payButton, deleteButton);
    }

    private void createFullNameLabel(HBox container){
        this.fullNameLabel = new Label();
        this.fullNameLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.fullNameLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.35));
        this.fullNameLabel.setAlignment(Pos.CENTER);
        this.fullNameLabel.setText(fullNameText);
        setTextResponsiveLabel(this.fullNameLabel, 15);
    }

    private void createCILabel(HBox container){
        this.ciLabel = new Label();
        this.ciLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.ciLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.ciLabel.setAlignment(Pos.CENTER);
        this.ciLabel.setText(String.valueOf(ciText));
        setTextResponsiveLabel(this.ciLabel, 5);
    }

    private void createPriorityLabel(VBox container){
        this.priorityLabel = new Label();
        this.priorityLabel.prefHeightProperty().bind(container.heightProperty().divide(2));
        this.priorityLabel.prefWidthProperty().bind(container.widthProperty());
        this.priorityLabel.setAlignment(Pos.CENTER);
        this.priorityLabel.setText(String.valueOf(priorityText));
        setTextResponsiveLabel(this.priorityLabel, 15);
    }

    private void createStatusLabel(VBox container){
        this.statusLabel = new Label();
        this.statusLabel.prefHeightProperty().bind(container.heightProperty().divide(2));
        this.statusLabel.prefWidthProperty().bind(container.widthProperty());
        this.statusLabel.setAlignment(Pos.CENTER);
        this.statusLabel.setText(String.valueOf(statusText));
        setTextResponsiveLabel(this.statusLabel, 15);
    }

    private void createStatusAndCategoryContainer(HBox container){
        this.statusAndCategory = new VBox(0);
        this.statusAndCategory.prefWidthProperty().bind(container.widthProperty().multiply(0.2));
        this.statusAndCategory.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.statusAndCategory.setAlignment(Pos.CENTER);

        createStatusLabel(statusAndCategory);
        createPriorityLabel(statusAndCategory);

        this.statusAndCategory.getChildren().addAll(priorityLabel, statusLabel);

    }

    private void createDeleteButton(HBox container){
        this.deleteButton = new Button("DELETE");
        this.deleteButton.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.deleteButton.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        deleteButton.setStyle("-fx-font-weight: bold; -fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, darkred, red); -fx-text-fill: white;");
        setTextResponsiveLabel(this.deleteButton);
    }

    private void createPayButton(HBox container){
        this.payButton = new Button("PAY");
        this.payButton.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.payButton.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.payButton.setStyle("-fx-font-weight: bold; -fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, gold, khaki);");
        setTextResponsiveLabel(this.payButton);
    }

    private void setTextResponsiveLabel(Label label, int divisor){
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / divisor;
            label.setStyle(label.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

    private void setTextResponsiveLabel(Button button){
        button.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / 15;
            button.setStyle(button.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

}

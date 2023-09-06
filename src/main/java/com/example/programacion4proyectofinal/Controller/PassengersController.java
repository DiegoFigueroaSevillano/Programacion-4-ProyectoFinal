package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class PassengersController {

    private Passengers passengers;
    private ArrayList<Passenger> passengersList;
    private ArrayList<HBox> passengersComponents;

    public PassengersController(Group root, Stage stage) {
        this.passengersList = obtainAllPassengers();
        this.passengersComponents = createPassangers(passengersList);
        this.passengers = new Passengers(root, stage, passengersComponents);
    }

    private ArrayList<HBox> createPassangers(ArrayList<Passenger> passengers) {
        ArrayList<HBox> passengersComponent = new ArrayList<>();
        for (Passenger passenger : passengers) {
            passengersComponent.add(generatePassenger(passenger.getId(), passenger.getFullName()));
        }
        return passengersComponent;
    }

    private HBox generatePassenger(int id, String name) {
        GenerateFont generateFont = new GenerateFont();
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();

        Label passengerName = new Label(name);
        passengerName.setFont(generateFont.latoLight(32));
        passengerName.setTextFill(Color.valueOf(WHITE));

        Button editInformation = generateButton("/com/example/programacion4proyectofinal/Icons/edit-icon.png", GREEN);
        editInformation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("EDIT");
            }
        });
        Button deleteButton = generateButton("/com/example/programacion4proyectofinal/Icons/delete-icon.png", RED);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("DELETE");
            }
        });

        HBox nameContainer = new HBox();
        nameContainer.getChildren().add(passengerName);
        nameContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(nameContainer, Priority.ALWAYS);

        HBox buttonsContainer = new HBox(10);
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.getChildren().addAll(editInformation, deleteButton);

        HBox passengerComponent = new HBox(10);
        passengerComponent.setPrefHeight(100);
        passengerComponent.setBackground(backgroundGenerator.createBackgroundRadius(10, SKY_BLUE));
        passengerComponent.setId(id + "");
        passengerComponent.setAlignment(Pos.CENTER);
        passengerComponent.setPadding(new Insets(20));
        passengerComponent.getChildren().addAll(nameContainer, buttonsContainer);

        nameContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(passengerComponent.getId() + " | " + passengerName.getText());
            }
        });

        return passengerComponent;
    }


    private Button generateButton(String pathImage, String color) {
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        Image iconImage = new Image(getClass().getResourceAsStream(pathImage));
        ImageView icon = new ImageView(iconImage);

        Button button = new Button();
        button.setGraphic(icon);
        button.setPrefSize(60, 60);
        button.setMinSize(60, 60);
        button.setMaxSize(60, 60);
        button.setBackground(backgroundGenerator.createBackgroundRadius(10, color));
        button.setCursor(Cursor.HAND);
        return button;
    }

    private ArrayList<Passenger> obtainAllPassengers() {
        ArrayList<Passenger> passengersList = new ArrayList<>();
        passengersList.add(new Passenger(1, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(2, "Juan", "Pena", "Bolivia", Category.FREQUENT_PASSENGER));
        passengersList.add(new Passenger(3, "Juan", "Pena", "Bolivia", Category.REGULAR_PASSENGER));
        passengersList.add(new Passenger(4, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(5, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(6, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(7, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(8, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(9, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(10, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(11, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(12, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(13, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(14, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(15, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(16, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(17, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(18, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(19, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(20, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(21, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(22, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(23, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(24, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(25, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(26, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(27, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(28, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(29, "Juan", "Pena", "Bolivia", Category.VIP));
        passengersList.add(new Passenger(30, "Juan", "Pena", "Bolivia", Category.VIP));
        return passengersList;
    }

    public Passengers getPassengers() {
        return passengers;
    }
}

package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList<HBox> passengersComponents;

    public PassengersController(Group root, Stage stage) {
        this.passengersList = obtainAllPassengers();
        this.passengersComponents = createPassengersComponents(passengersList);
        this.passengers = new Passengers(root, stage, passengersComponents);
    }

    private ObservableList<HBox> createPassengersComponents(ArrayList<Passenger> passengers) {
        ObservableList<HBox> passengersComponent = FXCollections.observableArrayList();
        for (Passenger passenger : passengers) {
            passengersComponent.add(generatePassenger(passenger, passengersComponent));
        }
        return passengersComponent;
    }

    private HBox generatePassenger(Passenger passenger, ObservableList<HBox> passengersComponents) {
        int id = passenger.getId();
        GenerateFont generateFont = new GenerateFont();
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();

        Label passengerName = new Label(passenger.getFullName());
        passengerName.setFont(generateFont.latoLight(32));
        passengerName.setTextFill(Color.valueOf(WHITE));

        Button editInformation = generateButton("/com/example/programacion4proyectofinal/Icons/user.png", GREEN);
        editInformation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passengersList.get(id - 1).setName("USER");
                passengersList.get(id - 1).setLastName("EDITED");
                passengerName.setText(passenger.getFullName());
            }
        });
        Button deleteButton = generateButton("/com/example/programacion4proyectofinal/Icons/delete-icon.png", RED);

        HBox nameContainer = new HBox();
        nameContainer.getChildren().add(passengerName);
        nameContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(nameContainer, Priority.ALWAYS);

        HBox buttonsContainer = new HBox(10);
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.getChildren().addAll(editInformation, deleteButton);

        HBox passengerComponent = new HBox(10);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passengersComponents.remove(passengerComponent);
                passengers.setPassengersComponents(passengersComponents);
                passengers.deleteAComponent();
            }
        });

        passengerComponent.setPrefHeight(100);
        passengerComponent.setBackground(backgroundGenerator.createBackgroundRadius(10, SKY_BLUE));
        passengerComponent.setId(id + "");
        passengerComponent.setAlignment(Pos.CENTER);
        passengerComponent.setPadding(new Insets(20));
        passengerComponent.getChildren().addAll(nameContainer, buttonsContainer);

        nameContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(passengersList.get(searchPositionById(passengerComponent.getId())).getId() + " | " + passengersList.get(id - 1).getFullName());
            }
        });

        return passengerComponent;
    }

    private int searchPositionById(String id) {
        int position = -1;
        for (int index = 0; index < passengersComponents.size(); index++) {
            if (id.equals(passengersComponents.get(index).getId())) {
                position = index;
            }
        }
        return position;
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
        passengersList.add(new Passenger(1, "John", "Doe", "United States", Category.VIP));
        passengersList.add(new Passenger(2, "Alice", "Smith", "United Kingdom", Category.FREQUENT_PASSENGER));
        passengersList.add(new Passenger(3, "Carlos", "González", "Spain", Category.REGULAR_PASSENGER));
        passengersList.add(new Passenger(4, "Marie", "Dubois", "France", Category.VIP));
        passengersList.add(new Passenger(5, "Hiroshi", "Tanaka", "Japan", Category.VIP));
        passengersList.add(new Passenger(6, "Maria", "Santos", "Brazil", Category.VIP));
        passengersList.add(new Passenger(7, "Luis", "Hernández", "Mexico", Category.VIP));
        passengersList.add(new Passenger(8, "Anna", "Müller", "Germany", Category.VIP));
        passengersList.add(new Passenger(9, "Elena", "Ivanova", "Russia", Category.VIP));
        passengersList.add(new Passenger(10, "Mohammed", "Ali", "Egypt", Category.VIP));
        passengersList.add(new Passenger(11, "David", "Lee", "Canada", Category.FREQUENT_PASSENGER));
        passengersList.add(new Passenger(12, "Sara", "Martinez", "Argentina", Category.REGULAR_PASSENGER));
        passengersList.add(new Passenger(13, "Sebastian", "Kowalski", "Poland", Category.VIP));
        passengersList.add(new Passenger(14, "Isabella", "López", "Mexico", Category.VIP));
        passengersList.add(new Passenger(15, "Andrei", "Ivanov", "Russia", Category.VIP));
        passengersList.add(new Passenger(16, "Sophie", "Dupont", "France", Category.VIP));
        passengersList.add(new Passenger(17, "Kenji", "Yamamoto", "Japan", Category.VIP));
        passengersList.add(new Passenger(18, "Lina", "Silva", "Brazil", Category.VIP));
        passengersList.add(new Passenger(19, "Hans", "Schmidt", "Germany", Category.VIP));
        passengersList.add(new Passenger(20, "Fatima", "Hassan", "Egypt", Category.VIP));
        passengersList.add(new Passenger(21, "Michael", "Brown", "United States", Category.FREQUENT_PASSENGER));
        passengersList.add(new Passenger(22, "Olivia", "Wilson", "United Kingdom", Category.REGULAR_PASSENGER));
        passengersList.add(new Passenger(23, "Jose", "Sánchez", "Spain", Category.VIP));
        passengersList.add(new Passenger(24, "Antoine", "Leroy", "France", Category.VIP));
        passengersList.add(new Passenger(25, "Yuki", "Nakamura", "Japan", Category.VIP));
        passengersList.add(new Passenger(26, "Luiz", "Ferreira", "Brazil", Category.VIP));
        passengersList.add(new Passenger(27, "Juan", "Hernández", "Mexico", Category.VIP));
        passengersList.add(new Passenger(28, "Helena", "Schneider", "Germany", Category.VIP));
        passengersList.add(new Passenger(29, "Ivan", "Petrov", "Russia", Category.VIP));
        passengersList.add(new Passenger(30, "Amr", "Abdel-Meguid", "Egypt", Category.VIP));
        return passengersList;
    }

    public Passengers getPassengers() {
        return passengers;
    }
}

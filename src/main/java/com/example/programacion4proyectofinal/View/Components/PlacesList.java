package com.example.programacion4proyectofinal.View.Components;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class PlacesList {

    private ComboBox<String> placesList;
    private ObservableList<String> places;
    private String title;
    private VBox container;
    private Label name;
    private BackgroundGenerator backgroundGenerator;

    public PlacesList(ObservableList<String> places, String title) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.title = title;
        this.places = places;
        createContainer();
    }

    private void createContainer() {
        createTitle();
        createPlacesList();

        container = new VBox(0);
        container.setPrefWidth(350);
        container.setPrefHeight(120);
        container.getChildren().addAll(name, placesList);
    }

    private void createTitle() {
        name = new Label(title);
        name.setPrefWidth(350);
        name.setPrefHeight(40);
        name.setTextFill(Color.valueOf(WHITE));
        name.setStyle("-fx-font-size: 24px");
    }

    private void createPlacesList() {
        placesList = new ComboBox<>();
        placesList.setItems(places);
        placesList.setPrefWidth(350);
        placesList.setPrefHeight(80);
        placesList.setBackground(backgroundGenerator.createBackground(WHITE));
        placesList.setStyle("-fx-font-size: 24px");
    }

    public VBox getContainer() {
        return container;
    }
}

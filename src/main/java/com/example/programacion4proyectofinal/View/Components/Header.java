package com.example.programacion4proyectofinal.View.Components;

import com.example.programacion4proyectofinal.View.Pages.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class Header {

    private HBox header, menu;
    private Button homeButton, passengersButton;
    private Stage stage;
    private String currentOption;
    private final int HEIGHT = 60;

    public Header(Stage stage, String currentOption) {
        this.stage = stage;
        this.currentOption = currentOption;
        createHeader();
    }

    private void createHeader() {

        createMenu();

        header = new HBox(0);
        header.setMinWidth(700);
        header.setPrefHeight(HEIGHT);
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(SKY_BLUE), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        header.setBackground(background);
        header.getChildren().add(menu);
    }

    private void createMenu() {
        createHomeButton();
        createPassengersButton();

        menu = new HBox(0);
        menu.setAlignment(Pos.CENTER_LEFT);
        menu.prefWidthProperty().bind(stage.widthProperty().subtract(HEIGHT));
        menu.setPrefHeight(HEIGHT);
        menu.getChildren().addAll(homeButton, passengersButton);
    }

    private void createHomeButton() {
        homeButton = new Button("HOME");
        generatorMenuOptions(homeButton, "home");
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("home")) {
                    Group root = new Group();
                    Home home = new Home(root, stage);
                    Scene homeScene = home.getHomeScene();
                    stage.setScene(homeScene);
                }
            }
        });
    }

    private void createPassengersButton() {
        passengersButton = new Button("PASSENGERS");
        generatorMenuOptions(passengersButton, "passengers");
    }

    private void generatorMenuOptions(Button button, String optionName) {
        button.setPrefHeight(HEIGHT);
        button.setPrefWidth(100);
        button.setCursor(Cursor.HAND);
        String color = LIGHT_CYAN;
        if (!currentOption.equals(optionName)) {
            color = SKY_BLUE;
        }
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        button.setBackground(background);
        button.setTextFill(Color.valueOf(WHITE));
        button.setStyle("-fx-font-size: 14px");
    }

    public HBox getHeader() {
        return header;
    }
}

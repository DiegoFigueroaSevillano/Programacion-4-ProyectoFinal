package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Utils.GenerateFont;
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

/**
 * This class generates and configures the header menu for the application.
 */
public class Header {

    private HBox header, menu;
    private Button homeButton, passengersButton, passengerRegisterButton;
    private Stage stage;
    private String currentOption;
    private final int HEIGHT = 60;
    private GenerateFont generateFont;

    /**
     * Constructs a Header menu.
     *
     * @param stage         The primary stage of the application.
     * @param currentOption The currently selected option (e.g., "home", "passengers").
     */
    public Header(Stage stage, String currentOption) {
        this.stage = stage;
        this.currentOption = currentOption;
        this.generateFont = new GenerateFont();
        createHeader();
    }

    /**
     * Creates the header menu.
     */
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

    /**
     * Creates the menu section containing buttons.
     */
    private void createMenu() {
        createHomeButton();
        createPassengersButton();
        createPassengersRegisterButton();
        menu = new HBox(0);
        menu.setAlignment(Pos.CENTER_LEFT);
        menu.prefWidthProperty().bind(stage.widthProperty().subtract(HEIGHT));
        menu.setPrefHeight(HEIGHT);
        menu.getChildren().addAll(homeButton, passengersButton,passengerRegisterButton);
    }

    /**
     * Creates the "HOME" button.
     */
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

    /**
     * Creates the "PASSENGERS" button.
     */
    private void createPassengersButton() {
        passengersButton = new Button("PASSENGERS");
        generatorMenuOptions(passengersButton, "passengers");
    }

    /**
     * Creates the "REGISTER" button.
     */
    private void createPassengersRegisterButton() {
        passengerRegisterButton = new Button("REGISTER");
        generatorMenuOptions(passengerRegisterButton, "Passengers Register");
    }

    /**
     * Generates the menu options for buttons.
     *
     * @param button     The button to style.
     * @param optionName The name of the option associated with the button.
     */
    private void generatorMenuOptions(Button button, String optionName) {
        button.setPrefHeight(HEIGHT);
        button.setPrefWidth(150);
        button.setCursor(Cursor.HAND);
        String color = LIGHT_CYAN;
        if (!currentOption.equals(optionName)) {
            color = SKY_BLUE;
        }
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        button.setBackground(background);
        button.setTextFill(Color.valueOf(WHITE));
        button.setFont(generateFont.latoRegular(14));
    }

    /**
     * Gets the header menu.
     *
     * @return The HBox containing the header menu.
     */
    public HBox getHeader() {
        return header;
    }

}
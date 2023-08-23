package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.PlacesListDB;
import com.example.programacion4proyectofinal.View.Components.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class Home {

    private final Scene homeScene;
    private VBox home, ticketForm, passenger;
    private HBox places, options, dates, quantityPassengers;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private Screen screen;
    private Rectangle2D screenSize;
    private Button createButton;

    public Home(Group root, Stage stage) {
        this.screen = Screen.getPrimary();
        this.screenSize = screen.getVisualBounds();
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("HOME -");
        this.homeScene = new Scene(root);
        this.header = new Header(stage, "home");
        createHome(homeScene);
        root.getChildren().add(home);
    }

    private void createHome(Scene scene) {
        createTicketSection(scene);
        home = new VBox(0);
        home.prefHeightProperty().bind(stage.heightProperty());
        home.prefWidthProperty().bind(stage.widthProperty());
        home.setAlignment(Pos.CENTER);
        home.layoutXProperty().bind(scene.widthProperty().subtract(home.widthProperty()).divide(2));
        home.layoutYProperty().bind(scene.heightProperty().subtract(home.heightProperty()).divide(2));
        home.getChildren().addAll(header.getHeader(), ticketSection);
    }

    private void createTicketSection(Scene scene) {
        createTicketForm();
        ticketSection = new StackPane();
        ticketSection.prefWidthProperty().bind(scene.widthProperty());
        ticketSection.prefHeightProperty().bind(scene.heightProperty().subtract(60));
        ticketSection.setBackground(backgroundGenerator.createBackgroundImage("img/Covers/home-cover.jpg"));
        ticketForm.setAlignment(Pos.CENTER);
        ticketSection.getChildren().add(ticketForm);
    }

    private void createTicketForm() {
        createPlacesSection();
        createOptionsSection();
        createDatesSection();
        createQuantityPassengers();
        createNameSection();
        createCreateButton();

        ticketForm = new VBox(40);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(850);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(850);
        ticketForm.setPadding(new Insets(10));
        ticketForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE_75));
        ticketForm.getChildren().addAll(places, options, dates, quantityPassengers, passenger, createButton);
    }

    private void createPlacesSection() {
        ObservableList<String> placesList = FXCollections.observableArrayList(PlacesListDB.PLACES_LIST);

        PlacesList fromList = new PlacesList(placesList, "FROM:");
        PlacesList toList = new PlacesList(placesList, "TO:");

        places = new HBox(40);
        places.setPrefWidth(900);
        places.setPrefHeight(120);
        places.getChildren().addAll(fromList.getContainer(), toList.getContainer());
        places.setAlignment(Pos.CENTER);
    }

    private void createOptionsSection() {
        RadioButtonGenerator oneWayOnly = new RadioButtonGenerator("ONE-WAY ONLY");
        RadioButtonGenerator roundTrip = new RadioButtonGenerator("ROUND TRIP");

        ToggleGroup optionsGroup = new ToggleGroup();
        oneWayOnly.getRadioButton().setToggleGroup(optionsGroup);
        roundTrip.getRadioButton().setToggleGroup(optionsGroup);

        options = new HBox(40);
        options.setPrefWidth(900);
        options.setPrefHeight(40);
        options.getChildren().addAll(oneWayOnly.getRadioButtonSection(), roundTrip.getRadioButtonSection());
        options.setAlignment(Pos.CENTER);
    }

    private void createDatesSection() {
        DateSection depurateDate = new DateSection("DEPARTURE DATE");
        DateSection returnDate = new DateSection("RETURN DATE");
        dates = new HBox(40);
        dates.setPrefWidth(900);
        dates.setPrefHeight(120);
        dates.getChildren().addAll(depurateDate.getContainer(), returnDate.getContainer());
        dates.setAlignment(Pos.CENTER);
    }

    private void createQuantityPassengers() {
        quantityPassengers = new QuantityPassengers().getContainer();
    }

    private void createNameSection() {
        PassengerName passengerName = new PassengerName();
        passenger = passengerName.getContainer();
    }

    private void createCreateButton() {
        createButton = new Button("CREATE");
        createButton.setTextFill(Color.valueOf(WHITE));
        createButton.setBackground(backgroundGenerator.createBackground(RED));
        createButton.setPrefWidth(740);
        createButton.setPrefHeight(80);
        createButton.setMaxHeight(740);
        createButton.setCursor(Cursor.HAND);
        createButton.setStyle("-fx-font-size: 24px");
    }

    public Scene getHomeScene() {
        return homeScene;
    }
}
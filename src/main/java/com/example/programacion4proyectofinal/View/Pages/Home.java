package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Model.Flight.Data.City;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import com.example.programacion4proyectofinal.Controller.HeaderController;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.*;

/**
 * This class represents the home page of the application.
 */
public class Home {

    private final Scene homeScene;
    private VBox home, ticketForm, airline;
    private HBox places, dates;
    private Stage stage;
    private HeaderController headerController;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private Button createButton;
    private DateSection depurateDate, returnDate;
    private GenerateFont generateFont;
    private PlacesList fromList, toList;
    private AirlinesList airlinesList;

    /**
     * Constructs the home page.
     *
     * @param root  The root group for UI components.
     * @param stage The primary stage of the application.
     */
    public Home(Group root, Stage stage) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.changePropertiesStage.changeToMaximizeSizeStage(this.stage);
        this.stage.setTitle("HOME - AEROLAB");
        this.homeScene = new Scene(root);
        this.headerController = new HeaderController(root, stage, "home");
        this.generateFont = new GenerateFont();
        createHome(homeScene);
        root.getChildren().add(home);
    }

    /**
     * Creates and configures the home page UI.
     *
     * @param scene The scene in which the home page will be displayed.
     */
    private void createHome(Scene scene) {
        createTicketSection(scene);
        home = new VBox(0);
        home.prefHeightProperty().bind(stage.heightProperty());
        home.prefWidthProperty().bind(stage.widthProperty());
        home.setAlignment(Pos.CENTER);
        home.layoutXProperty().bind(scene.widthProperty().subtract(home.widthProperty()).divide(2));
        home.layoutYProperty().bind(scene.heightProperty().subtract(home.heightProperty()).divide(2));
        home.getChildren().addAll(headerController.getHeader().getHeader(), ticketSection);
    }

    /**
     * Creates and configures the ticket section UI.
     *
     * @param scene The scene in which the ticket section will be displayed.
     */
    private void createTicketSection(Scene scene) {
        createTicketForm();
        ticketSection = new StackPane();
        ticketSection.prefWidthProperty().bind(scene.widthProperty());
        ticketSection.prefHeightProperty().bind(scene.heightProperty().subtract(60));
        ticketSection.setBackground(backgroundGenerator.createBackgroundImage("com/example/programacion4proyectofinal/Covers/home-cover.jpg"));
        ticketForm.setAlignment(Pos.CENTER);
        ticketSection.getChildren().add(ticketForm);
    }

    /**
     * Creates and configures the ticket form UI.
     */
    private void createTicketForm() {
        createPlacesSection();
        createDatesSection();
        createNameSection();
        createCreateButton();

        ticketForm = new VBox(40);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(850);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(850);
        ticketForm.setPadding(new Insets(10));
        ticketForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE_75));
        ticketForm.getChildren().addAll(places, dates, airline, createButton);
    }

    /**
     * Creates and configures the places section UI.
     */
    private void createPlacesSection() {
        ObservableList<String> placesList = FXCollections.observableArrayList();
        placesList.add(String.valueOf(City.PANDO));
        placesList.add(String.valueOf(City.BENI));
        placesList.add(String.valueOf(City.LA_PAZ));
        placesList.add(String.valueOf(City.COCHABAMBA));
        placesList.add(String.valueOf(City.SANTA_CRUZ));
        placesList.add(String.valueOf(City.ORURO));
        placesList.add(String.valueOf(City.POTOSI));
        placesList.add(String.valueOf(City.CHUQUISACA));
        placesList.add(String.valueOf(City.TARIJA));

        fromList = new PlacesList(placesList, "ORIGIN:",350,120);
        toList = new PlacesList(placesList, "DESTINATION:",350,120);

        places = new HBox(40);
        places.setPrefWidth(900);
        places.setPrefHeight(120);
        places.getChildren().addAll(fromList.getContainer(), toList.getContainer());
        places.setAlignment(Pos.CENTER);
    }

    /**
     * Creates and configures the dates section UI.
     */
    private void createDatesSection() {
        depurateDate = new DateSection("DEPARTURE DATE");
        returnDate = new DateSection("ARRIVAL DATE");
        dates = new HBox(40);
        dates.setPrefWidth(900);
        dates.setPrefHeight(120);
        dates.getChildren().addAll(depurateDate.getContainer(), returnDate.getContainer());
        dates.setAlignment(Pos.CENTER);
    }

    /**
     * Creates and configures the passenger name section UI.
     */
    private void createNameSection() {
        airlinesList = new AirlinesList();
        airline = airlinesList.getContainer();
    }

    /**
     * Creates and configures the create button UI.
     */
    private void createCreateButton() {
        createButton = new Button("CREATE");
        createButton.setTextFill(Color.valueOf(WHITE));
        createButton.setBackground(backgroundGenerator.createBackground(RED));
        createButton.setPrefWidth(740);
        createButton.setPrefHeight(80);
        createButton.setMaxHeight(740);
        createButton.setCursor(Cursor.HAND);
        createButton.setFont(generateFont.latoRegular(24));
    }

    /**
     * Gets the home scene.
     *
     * @return The home scene.
     */
    public Scene getHomeScene() {
        return homeScene;
    }

    /**
     * Gets the depurate date section.
     *
     * @return The depurate date section.
     */
    public DateSection getDepurateDate() {
        return depurateDate;
    }

    /**
     * Gets the return date section.
     *
     * @return The return date section.
     */
    public DateSection getReturnDate() {
        return returnDate;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public PlacesList getFromList() {
        return fromList;
    }

    public PlacesList getToList() {
        return toList;
    }

    public AirlinesList getAirlinesList() {
        return airlinesList;
    }
}

package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Model.Flight;
import com.example.programacion4proyectofinal.Utils.*;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

import static com.example.programacion4proyectofinal.Utils.Colors.*;


/**
 * This class represents the FlightView for the flight booking application.
 */
public class FlightView {

    private final Scene homeScene;
    private VBox home, ticketForm;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private FlightList flightList;

    /**
     * Constructor for the FlightView class.
     *
     * @param root  The root Group for the JavaFX scene.
     * @param stage The main Stage for the application.
     */
    public FlightView(Group root, Stage stage) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.flightList = new FlightList();
        this.stage = stage;
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("FLIGHT - AEROLAB");
        this.homeScene = new Scene(root);
        this.header = new Header(stage, "flights");
        createHome(homeScene);
        root.getChildren().add(home);
    }

    /**
     * Create the home view.
     *
     * @param scene The scene to which the home view will be added.
     */
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

    /**
     * Create the ticket section.
     *
     * @param scene The scene to which the ticket section will be added.
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
     * Create the ticket form.
     */
    private void createTicketForm() {
        ticketForm = new VBox(40);
        ticketForm.setPrefWidth(1100);
        ticketForm.setPrefHeight(950);
        ticketForm.setMaxWidth(1100);
        ticketForm.setMaxHeight(950);
        ticketForm.setStyle("-fx-background-color: transparent;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: transparent;");
        StackPane contentPane = new StackPane();
        contentPane.setPadding(new Insets(40)); // Add desired padding

        VBox vbox = new VBox(10);
        List<Flight> flightList1 = flightList.getListaVuelos();

        for (Flight flight : flightList1) {
            Button button = createButton(flight);
            vbox.getChildren().add(button);
        }

        vbox.setAlignment(Pos.CENTER);
        contentPane.getChildren().add(vbox);
        scrollPane.setPrefViewportWidth(1200);
        scrollPane.setPrefViewportHeight(850);
        scrollPane.setContent(contentPane);
        ticketForm.getChildren().add(scrollPane);
    }

    /**
     * Create a button for a flight.
     *
     * @param flight The flight for which the button is created.
     * @return The created button.
     */
    private Button createButton(Flight flight) {
        Button button = new Button();
        button.setPrefWidth(1000);
        button.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: #26C6DA75");
        button.setBackground(backgroundGenerator.createBackground(SKY_BLUE_75));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        Label departureLabel = new Label("Place Departure:");
        Label arrivalLabel = new Label("Place Arrival:");
        Label priceLabel = new Label("Price:");
        Label arrivalTimeLabel = new Label("Arrival Time:");
        Label departureTimeLabel = new Label("Departure Time:");

        Label departureValue = new Label(flight.getPlaceDeparture());
        Label arrivalValue = new Label(flight.getPlaceArrival());
        Label priceValue = new Label(String.valueOf(flight.getPrice()));
        Label arrivalTimeValue = new Label(flight.getArrivalTime());
        Label departureTimeValue = new Label(flight.getDepartureTime());

        String labelStyle = "-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #333;";
        String valueStyle = "-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #555;";

        departureLabel.setStyle(labelStyle);
        arrivalLabel.setStyle(labelStyle);
        priceLabel.setStyle(labelStyle);
        arrivalTimeLabel.setStyle(labelStyle);
        departureTimeLabel.setStyle(labelStyle);

        departureValue.setStyle(valueStyle);
        arrivalValue.setStyle(valueStyle);
        priceValue.setStyle(valueStyle);
        arrivalTimeValue.setStyle(valueStyle);
        departureTimeValue.setStyle(valueStyle);

        GridPane.setHalignment(departureLabel, HPos.CENTER);
        GridPane.setValignment(departureLabel, VPos.CENTER);
        GridPane.setHalignment(arrivalLabel, HPos.CENTER);
        GridPane.setValignment(arrivalLabel, VPos.CENTER);
        GridPane.setHalignment(priceLabel, HPos.CENTER);
        GridPane.setValignment(priceLabel, VPos.CENTER);
        GridPane.setHalignment(arrivalTimeLabel, HPos.CENTER);
        GridPane.setValignment(arrivalTimeLabel, VPos.CENTER);
        GridPane.setHalignment(departureTimeLabel, HPos.CENTER);
        GridPane.setValignment(departureTimeLabel, VPos.CENTER);

        GridPane.setHalignment(departureValue, HPos.CENTER);
        GridPane.setValignment(departureValue, VPos.CENTER);
        GridPane.setHalignment(arrivalValue, HPos.CENTER);
        GridPane.setValignment(arrivalValue, VPos.CENTER);
        GridPane.setHalignment(priceValue, HPos.CENTER);
        GridPane.setValignment(priceValue, VPos.CENTER);
        GridPane.setHalignment(arrivalTimeValue, HPos.CENTER);
        GridPane.setValignment(arrivalTimeValue, VPos.CENTER);
        GridPane.setHalignment(departureTimeValue, HPos.CENTER);
        GridPane.setValignment(departureTimeValue, VPos.CENTER);

        gridPane.addRow(0, departureLabel, arrivalLabel, priceLabel, arrivalTimeLabel, departureTimeLabel);
        gridPane.addRow(1, departureValue, arrivalValue, priceValue, arrivalTimeValue, departureTimeValue);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints);

        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(colConstraints, colConstraints, colConstraints, colConstraints, colConstraints);

        button.setGraphic(gridPane);
        return button;
    }

    /**
     * Get the FlightView's scene.
     *
     * @return The Scene object representing the FlightView.
     */
    public Scene getFlightScene() {
        return homeScene;
    }}
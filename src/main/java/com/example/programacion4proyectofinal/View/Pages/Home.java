package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.View.Components.Header;
import com.example.programacion4proyectofinal.View.Components.PlacesList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class Home {

    private final Scene homeScene;
    private VBox home, ticketForm;
    private HBox places, options, dates, quantityPassengers;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;

    public Home(Group root, Stage stage) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.changePropertiesStage.changeSizeStage(950, 900, this.stage);
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
        ticketSection.getChildren().add(ticketForm);
    }

    private void createTicketForm() {
        createPlacesSection();

        ticketForm = new VBox(20);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(850);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(850);
        ticketForm.setPadding(new Insets(40));
        ticketForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE_75));
        ticketForm.getChildren().addAll(places);
    }

    private void createPlacesSection() {
        ObservableList<String> placesList = FXCollections.observableArrayList();

        placesList.add("COCHABAMBA");
        placesList.add("SANTA CRUZ");
        placesList.add("ORURO");
        placesList.add("LA PAZ");
        placesList.add("PANDO");
        placesList.add("BENI");
        placesList.add("POTOSI");
        placesList.add("SUCRE");
        placesList.add("TARIJA");

        PlacesList fromList = new PlacesList(placesList, "FROM:");
        PlacesList toList = new PlacesList(placesList, "TO:");

        places = new HBox(40);
        places.setPrefWidth(900);
        places.setPrefHeight(120);
        places.getChildren().addAll(fromList.getContainer(), toList.getContainer());
        places.setAlignment(Pos.CENTER);
    }

    public Scene getHomeScene() {
        return homeScene;
    }
}

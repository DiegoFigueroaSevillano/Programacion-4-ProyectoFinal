package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.View.Components.Header;
import com.example.programacion4proyectofinal.View.Components.PlacesList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class Home {

    private final Scene homeScene;
    private VBox home, ticketForm;
    private HBox places, options, dates, quantityPassengers;
    private PlacesList placesList;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;

    public Home(Group root, Stage stage) {
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
        Image homeCover = new Image("img/Covers/home-cover.jpg");
        BackgroundImage homeBackground = new BackgroundImage(
                homeCover,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        Background homeBG = new Background(homeBackground);
        ticketSection = new StackPane();
        ticketSection.prefWidthProperty().bind(scene.widthProperty());
        ticketSection.prefHeightProperty().bind(scene.heightProperty().subtract(80));
        ticketSection.setBackground(homeBG);
        ticketSection.getChildren().add(ticketForm);
    }

    private void createTicketForm() {
        ticketForm = new VBox(20);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(850);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(850);
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(SKY_BLUE_75), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        ticketForm.setBackground(background);
    }

    public Scene getHomeScene() {
        return homeScene;
    }
}

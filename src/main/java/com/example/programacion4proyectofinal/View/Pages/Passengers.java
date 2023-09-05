package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Controller.HeaderController;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Passengers {

    private Scene passengersScene;
    private VBox generalContainer;
    private HeaderController headerController;
    private Group root;
    private Stage stage;
    private ChangePropertiesStage changePropertiesStage;

    public Passengers(Group root, Stage stage) {
        this.root = root;
        this.stage = stage;
        this.changePropertiesStage = new ChangePropertiesStage();
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("PASSENGERS - AEROLAB");
        this.passengersScene = new Scene(root);
        this.headerController = new HeaderController(root, stage, "passenger");
        createGeneralContainer(this.passengersScene);
        this.root.getChildren().add(generalContainer);
    }

    private void createGeneralContainer(Scene scene) {
        generalContainer = new VBox();
        generalContainer.prefHeightProperty().bind(stage.heightProperty());
        generalContainer.prefWidthProperty().bind(stage.widthProperty());
        generalContainer.setAlignment(Pos.CENTER);
        generalContainer.layoutXProperty().bind(scene.widthProperty().subtract(generalContainer.widthProperty()).divide(2));
        generalContainer.layoutYProperty().bind(scene.heightProperty().subtract(generalContainer.heightProperty()).divide(2));
        generalContainer.getChildren().addAll(headerController.getHeader().getHeader());
    }

    public Scene getPassengersScene() {
        return passengersScene;
    }
}

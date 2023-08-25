package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.Colors;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class ChangeDispenser {

    private final Scene changeDispenserScene;
    private HBox changeDispenserForm;
    private StackPane changeDispenserSection;
    private VBox changeDispenserPanel;
    private Header header;
    private BackgroundGenerator backgroundGenerator;
    private ChangePropertiesStage changePropertiesStage;
    private Stage stage;
    private GenerateFont generateFont;



    public ChangeDispenser(Group root, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("CHANGE DISPENSER - AEROLAB");
        this.changeDispenserScene = new Scene(root);
        this.header = new Header(stage, "home");
        this.generateFont = new GenerateFont();
        createChangeDispenser(changeDispenserScene);
        root.getChildren().add(changeDispenserPanel);
    }

    public Scene getChangeDispenserScene() {
        return changeDispenserScene;
    }

    public void createChangeDispenser(Scene scene){
        createChangeDispenserSection(scene);
        changeDispenserPanel = new VBox(0);
        changeDispenserPanel.prefHeightProperty().bind(stage.heightProperty());
        changeDispenserPanel.prefWidthProperty().bind(stage.widthProperty());
        changeDispenserPanel.setAlignment(Pos.CENTER);
        changeDispenserPanel.layoutXProperty().bind(scene.widthProperty().subtract(changeDispenserPanel.widthProperty()).divide(2));
        changeDispenserPanel.layoutYProperty().bind(scene.heightProperty().subtract(changeDispenserPanel.heightProperty()).divide(2));
        changeDispenserPanel.getChildren().addAll(header.getHeader(), changeDispenserSection);
    }

    public void createChangeDispenserSection(Scene scene){
        createChangeDispenserForm();
        changeDispenserSection = new StackPane();
        changeDispenserSection.prefWidthProperty().bind(scene.widthProperty());
        changeDispenserSection.prefHeightProperty().bind(scene.heightProperty().subtract(60));
        changeDispenserSection.setBackground(backgroundGenerator.createBackground(LIGHT_CYAN));
        changeDispenserSection.setAlignment(Pos.CENTER);
        changeDispenserSection.getChildren().add(changeDispenserForm);

    }

    public void createChangeDispenserForm(){

        //TODO: CREAR LAS DOS SECCIONES DE BILLETES Y MONEDAS

        changeDispenserForm = new HBox(40);
        changeDispenserForm.setPrefWidth(1000);
        changeDispenserForm.setPrefHeight(500);
        changeDispenserForm.setMaxWidth(1000);
        changeDispenserForm.setMaxHeight(500);
        changeDispenserForm.setPadding(new Insets(10));
        changeDispenserForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE_75));

    }

}

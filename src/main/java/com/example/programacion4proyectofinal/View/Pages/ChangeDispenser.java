package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.Colors;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import com.example.programacion4proyectofinal.View.Components.ChangeDispenserComponents.BillSection;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

public class ChangeDispenser {

    private final Scene changeDispenserScene;
    private HBox changeDispenserForm;
    private Pane changeDispenserSection;
    private VBox changeDispenserPanel;
    private Header header;
    private BackgroundGenerator backgroundGenerator;
    private ChangePropertiesStage changePropertiesStage;
    private Stage stage;
    private GenerateFont generateFont;
    private BillSection leftSection;
    private BillSection rightSection;



    public ChangeDispenser(Group root, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.stage.setTitle("CHANGE DISPENSER - AEROLAB");
        this.changeDispenserScene = new Scene(root);
        this.header = new Header(stage, "home");
        this.generateFont = new GenerateFont();

        createChangeDispenser(this.changeDispenserScene);
        root.getChildren().add(changeDispenserPanel);
    }

    public Scene getChangeDispenserScene() {
        return changeDispenserScene;
    }

    private void createChangeDispenser(Scene scene){
        this.changeDispenserPanel = new VBox(0);
        this.changeDispenserPanel.prefWidthProperty().bind(stage.widthProperty());
        this.changeDispenserPanel.prefHeightProperty().bind(stage.heightProperty());
        this.changeDispenserPanel.setBackground(backgroundGenerator.createBackground(LIGHT_CYAN));

        createChangeDispenserSection(scene);

        this.changeDispenserPanel.getChildren().addAll(header.getHeader(), changeDispenserSection);
    }

    private void createChangeDispenserSection(Scene scene){
        this.changeDispenserSection = new Pane();
        this.changeDispenserSection.prefWidthProperty().bind(scene.widthProperty());
        this.changeDispenserSection.prefHeightProperty().bind(scene.heightProperty());
        this.changeDispenserSection.setBackground(backgroundGenerator.createBackground(RED));

        createChangeDispenserForm(this.changeDispenserSection);

        this.changeDispenserSection.getChildren().addAll(this.changeDispenserForm);

    }

    private void createChangeDispenserForm(Pane stackPane){
        this.changeDispenserForm = new HBox(10);
        this.changeDispenserForm.prefHeightProperty().bind(stackPane.heightProperty().subtract(200));
        this.changeDispenserForm.prefWidthProperty().bind(stackPane.widthProperty().subtract(50));
        this.changeDispenserForm.layoutXProperty().bind(stackPane.widthProperty().subtract(this.changeDispenserForm.widthProperty()).divide(2));
        this.changeDispenserForm.layoutYProperty().bind(stackPane.heightProperty().subtract(this.changeDispenserForm.heightProperty()).divide(2));

        this.changeDispenserForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE));

        //INSERTAR LEFT Y RIGHT
        createComponent(this.changeDispenserForm);
        changeDispenserForm.getChildren().addAll(this.leftSection.getContainer(), this.rightSection.getContainer());


    }

    private void createComponent(HBox container){
        this.leftSection = new BillSection("com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/200bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/100bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/50bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/20bsBill.jpg",
                container);
        this.rightSection = new BillSection("com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/10bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/5bsCoin.png",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/2bsCoin.png",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/1bsCoin.png",
                container);
    }

}

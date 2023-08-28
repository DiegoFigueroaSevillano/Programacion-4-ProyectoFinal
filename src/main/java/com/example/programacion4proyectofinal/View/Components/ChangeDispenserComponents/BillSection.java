package com.example.programacion4proyectofinal.View.Components.ChangeDispenserComponents;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.Colors;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

public class BillSection {

    private VBox container;
    private String firstImagePath, secondImagePath, thirdImagePath, fourthImagePath;
    private Pane firstPane, secondPane, thirdPane, fourthPane;
    private HBox firstSection, secondSection, thirdSection, fourthSection;
    private Spinner<Integer> firstSpinner, secondSpinner, thirdSpinner, fourthSpinner;
    private BackgroundGenerator backgroundGenerator;


    public BillSection(String firstImagePath, String secondImagePath, String thirdImagePath,
                       String fourthImagePath, HBox ultraContainer){
        this.backgroundGenerator = new BackgroundGenerator();
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.thirdImagePath = thirdImagePath;
        this.fourthImagePath = fourthImagePath;

        createContainer(ultraContainer);
        this.container.getChildren().addAll(firstSection, secondSection, thirdSection, fourthSection);
    }

    public VBox getContainer() {
        return container;
    }

    public void createContainer(HBox ultraContainer){
        this.container = new VBox();
        this.container.prefHeightProperty().bind(ultraContainer.heightProperty());
        this.container.prefWidthProperty().bind(ultraContainer.widthProperty().divide(2));
        this.container.setAlignment(Pos.CENTER);

        this.firstSection = createSection(this.firstPane, this.firstImagePath, this.firstSpinner, this.firstSection, this.container);
        this.secondSection = createSection(this.secondPane, this.secondImagePath, this.secondSpinner, this.secondSection, this.container);
        this.thirdSection = createSection(this.thirdPane, this.thirdImagePath, this.thirdSpinner, this.thirdSection, this.container);
        this.fourthSection = createSection(this.fourthPane, this.fourthImagePath, this.fourthSpinner, this.fourthSection, this.container);

        System.out.println(firstSection);
        System.out.println(secondSection);
        System.out.println(thirdSection);
        System.out.println(fourthSection);
        System.out.println(container);
    }

    private HBox createSection(Pane imagePane, String imagePath, Spinner<Integer> spinner, HBox section, VBox container){
        section = new HBox(10);
        section.prefWidthProperty().bind(container.widthProperty());
        section.prefHeightProperty().bind(container.heightProperty().multiply(0.25));
        section.setAlignment(Pos.CENTER_LEFT);

        imagePane = new Pane();
        imagePane.setBackground(backgroundGenerator.createBackgroundImage(imagePath));
        imagePane.prefWidthProperty().bind(section.widthProperty().multiply(0.5));
        imagePane.prefHeightProperty().bind(section.heightProperty().multiply(500));

        spinner = new Spinner<>();
        spinner.prefHeightProperty().bind(section.heightProperty().multiply(0.05));
        spinner.prefWidthProperty().bind(section.widthProperty().multiply(0.1));
        spinner.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 300, 0);
        spinner.setValueFactory(valueFactory);

        section.getChildren().addAll(imagePane, spinner);
        return section;
    }



}

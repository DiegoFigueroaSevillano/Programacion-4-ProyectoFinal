package com.example.programacion4proyectofinal.View.Components.ChangeDispenserComponents;

import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

public class BillSection {

    private VBox container;
    private Image firstImage, secondImage, thirdImage, fourthImage;
    private Label firstLabel, secondLabel, thirdLabel, fourthLabel;
    private Spinner<Integer> firstSpinner, secondSpinner, thirdSpinner, fourthSpinner;
    private HBox firstSection, secondSection, thirdSection, fourthSection;
    private GenerateFont generateFont;
    private String title;
    private Label label;

    public BillSection(String title, Image firstImage, Image secondImage, Image thirdImage, Image fourthImage){
        this.generateFont = new GenerateFont();
        this.title = title;
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        createContainer();
    }

    public VBox getContainer() {
        return container;
    }

    public void createContainer(){
        createLabel();

        this.container = new VBox(5);
        container.setPrefHeight(120);
        container.setPrefWidth(350);
        container.getChildren().addAll(label);
    }

    public void createLabel(){
        this.label = new Label(title);
        this.label.setPrefWidth(350);
        this.label.setPrefHeight(40);
        this.label.setFont(generateFont.latoRegular(24));
        this.label.setTextFill(Color.valueOf(WHITE));
    }

}

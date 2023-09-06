package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;
import static com.example.programacion4proyectofinal.Utils.Styles.FONT_SIZE_24PX;

/**
 * This class generates and configures a section to fill in passenger information.
 */
public class PassengerInformation {
    private TextField textFIeld;
    private String title;
    private VBox container;
    private Label name;
    private BackgroundGenerator backgroundGenerator;
    private GenerateFont generateFont;
    private int width;
    private int height;


    public PassengerInformation(String title, int width , int height) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.title = title;
        this.height=height;
        this.width=width;
        this.generateFont = new GenerateFont();
        createContainer();
    }

    /**
     * Creates the container for the TextField section.
     */
    private void createContainer() {
        createTitle();
        createTextField();

        container = new VBox(0);
        container.setPrefWidth(width);
        container.setPrefHeight(height);
        container.getChildren().addAll(name, textFIeld);
    }

    /**
     * Creates the title label for the section.
     */
    private void createTitle() {
        name = new Label(title);
        name.setPrefWidth(350);
        name.setPrefHeight(40);
        name.setTextFill(Color.valueOf(WHITE));
        name.setFont(generateFont.latoRegular(24));
    }

    /**
     * Creates the TextField section.
     */
    private void createTextField() {
        textFIeld = new TextField();
        textFIeld.setPrefWidth(width);
        textFIeld.setPrefHeight(height);
        textFIeld.setBackground(backgroundGenerator.createBackground(WHITE));
        textFIeld.setStyle(FONT_SIZE_24PX);
    }

    /**
     * Gets the container of the TextField section.
     *
     * @return The VBox container.
     */
    public VBox getContainer() {
        return container;
    }

}

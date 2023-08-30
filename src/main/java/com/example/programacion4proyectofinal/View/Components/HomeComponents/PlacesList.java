package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Styles.FONT_SIZE_24PX;

/**
 * This class generates and configures a section for selecting places from a ComboBox.
 */
public class PlacesList {

    private ComboBox<String> placesList;
    private ObservableList<String> places;
    private String title;
    private VBox container;
    private Label name;
    private BackgroundGenerator backgroundGenerator;
    private GenerateFont generateFont;

    /**
     * Constructs a PlacesList section.
     *
     * @param places The list of places to populate the ComboBox with.
     * @param title The title for the section.
     */
    public PlacesList(ObservableList<String> places, String title) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.title = title;
        this.places = places;
        this.generateFont = new GenerateFont();
        createContainer();
    }

    /**
     * Creates the container for the PlacesList section.
     */
    private void createContainer() {
        createTitle();
        createPlacesList();

        container = new VBox(0);
        container.setPrefWidth(350);
        container.setPrefHeight(120);
        container.getChildren().addAll(name, placesList);
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
     * Creates the ComboBox for selecting places.
     */
    private void createPlacesList() {
        placesList = new ComboBox<>();
        placesList.setItems(places);
        placesList.setPrefWidth(350);
        placesList.setPrefHeight(80);
        placesList.setBackground(backgroundGenerator.createBackground(WHITE));
        placesList.setStyle(FONT_SIZE_24PX);
    }

    /**
     * Gets the container of the PlacesList section.
     *
     * @return The VBox container.
     */
    public VBox getContainer() {
        return container;
    }
}

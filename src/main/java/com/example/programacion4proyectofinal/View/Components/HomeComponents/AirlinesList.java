package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Styles.FONT_SIZE_24PX;

/**
 * This class generates and configures a section for entering passenger's name.
 */
public class AirlinesList {

    private VBox container;
    private Label label;
    private ComboBox<String> comboBox;
    private GenerateFont generateFont;

    /**
     * Constructs a PassengerName section.
     */
    public AirlinesList() {
        generateFont = new GenerateFont();
        createContainer();
    }

    /**
     * Creates the container for the PassengerName section.
     */
    private void createContainer() {
        createLabel();
        createTextField();

        container = new VBox(0);
        container.setPrefWidth(740);
        container.setPrefHeight(120);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(label, comboBox);
    }

    /**
     * Creates the label for the passenger's name.
     */
    private void createLabel() {
        label = new Label("NAME");
        label.setPrefWidth(740);
        label.setPrefHeight(40);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(generateFont.latoRegular(24));
        label.setTextFill(Color.valueOf(WHITE));
    }

    /**
     * Creates the text field for entering passenger's name.
     */
    private void createTextField() {
        ObservableList<String> airlines = FXCollections.observableArrayList();
        airlines.add(String.valueOf(Airline.AEROESTE));
        airlines.add(String.valueOf(Airline.ECOJET));
        airlines.add(String.valueOf(Airline.BOLIVIANA_DE_AVIACION));
        airlines.add(String.valueOf(Airline.LINEA_AEREA_AMASZONAS));
        airlines.add(String.valueOf(Airline.LINEAS_AEREAS_CANEDO));
        airlines.add(String.valueOf(Airline.SKY_TEAM_AVIATOR));
        airlines.add(String.valueOf(Airline.TRANSPORTES_AEREOS_BOLIVIANOS));

        comboBox = new ComboBox<>();
        comboBox.setPrefWidth(740);
        comboBox.setMaxWidth(740);
        comboBox.setPrefHeight(80);
        comboBox.setStyle(FONT_SIZE_24PX);
        comboBox.setItems(airlines);
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        comboBox.setBackground(backgroundGenerator.createBackground(WHITE));
    }

    /**
     * Gets the container of the PassengerName section.
     *
     * @return The VBox container.
     */
    public VBox getContainer() {
        return container;
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }
}
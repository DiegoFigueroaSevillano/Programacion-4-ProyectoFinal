package View.Components;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static Utils.Colors.*;

public class PlacesList {

    private ComboBox<String> placesList;
    private ObservableList<String> places;
    private String title;
    private VBox container;
    private Label name;

    public PlacesList(ObservableList<String> places, String title) {
        this.title = title;
        this.places = places;
        createContainer();
    }

    private void createContainer() {
        createTitle();
        createPlacesList();

        container = new VBox(0);
        container.setPrefWidth(350);
        container.setPrefHeight(120);
        container.getChildren().addAll(name, placesList);
    }

    private void createTitle() {
        name = new Label(title);
        name.setPrefWidth(350);
        name.setPrefHeight(40);
        name.setTextFill(Color.valueOf(WHITE));
        name.setStyle("-fx-font-size: 24px");
    }

    private void createPlacesList() {
        placesList = new ComboBox<>();
        placesList.setItems(places);
        placesList.setPrefWidth(350);
        placesList.setPrefHeight(80);
    }

    public ComboBox<String> getPlacesList() {
        return placesList;
    }
}

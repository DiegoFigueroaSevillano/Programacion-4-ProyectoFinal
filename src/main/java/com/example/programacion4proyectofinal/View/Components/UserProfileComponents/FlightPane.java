package com.example.programacion4proyectofinal.View.Components.UserProfileComponents;

import com.example.programacion4proyectofinal.Utils.ViewUtils.ComponentsFX;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FlightPane {

    private Pane pane;

    public Pane createContentInformationFlight(String enumeration, String city, String date, int maxWidth, int minWidth, VBox mainContainer) {
        pane = new Pane();
        pane.setMinWidth(minWidth);

        HBox contentPane = createContentFlightPane(mainContainer);
        Label enumLabel = createEnumartionLabel(enumeration);
        Label cityLabel = createCityLabel(city);
        Label dateLabel = createDateLabel(date);
        Label semiColonLabel = createSemiColonLabel();

        HBox.setHgrow(enumLabel, Priority.NEVER);
        HBox.setHgrow(cityLabel, Priority.ALWAYS);
        HBox.setHgrow(dateLabel, Priority.NEVER);
        HBox.setHgrow(semiColonLabel, Priority.NEVER);

        contentPane.getChildren().addAll(enumLabel, cityLabel, dateLabel, semiColonLabel);
        pane.getChildren().add(contentPane);
        return pane;
    }

    private HBox createContentFlightPane(VBox mainContainer) {
        HBox hBox = new HBox(10);
        hBox.setStyle("-fx-border-color: #333;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-color:  #28c4dc;" +
                "-fx-background-radius: 10px;");
        hBox.setMinWidth(650);
        hBox.setPadding(new Insets(25, 0, 25, 0));
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.prefWidthProperty().bind(mainContainer.widthProperty());
        return hBox;
    }

    private Label createEnumartionLabel(String enumeration) {
        Label enumerationLabel = ComponentsFX.getInstance().createLabel(enumeration, 150, 20,
                Pos.CENTER, 0, 0, "#000000", 20,
                0, "Arial", "Bold");
        enumerationLabel.setPadding(new Insets(1, 0, 0, 0));
        return enumerationLabel;
    }

    private Label createCityLabel(String city) {
        Label cityLabel = ComponentsFX.getInstance().createLabel(city, 300, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 18,
                0, "Arial", "Normal");
        cityLabel.setPadding(new Insets(0, 0, 0, 0));
        return cityLabel;
    }

    private Label createDateLabel(String date) {
        Label dateLabel = ComponentsFX.getInstance().createLabel(date, 270, 20,
                Pos.CENTER, 0, 0, "#000000", 17,
                0, "Arial", "Normal");
        dateLabel.setPadding(new Insets(2, 0, 0, 0));
        return dateLabel;
    }

    private Label createSemiColonLabel() {
        Label semiColonLabel = ComponentsFX.getInstance().createLabel(":", 10, 20,
                Pos.CENTER, 0, 0, "#000000", 20,
                0, "Arial", "Bold");
        semiColonLabel.setPadding(new Insets(0, 20, 0, 0));
        return semiColonLabel;
    }

    public Pane getPane() {
        return pane;
    }
}

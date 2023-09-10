package com.example.programacion4proyectofinal.View.Components.UserProfileComponents;

import com.example.programacion4proyectofinal.Utils.ViewUtils.ComponentsFX;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FlightPane {

    private Pane pane;
    private Label enumLabel;
    private Label enumLabelExpanded;
    private Label cityLabel;
    private Label cityLabelExpanded;
    private Label dateLabel;
    private Label dateLabelExpanded;
    private Label semiColonLabel;
    private final HBox contentFlightHBox;
    private final VBox expandedBox;
    private Label priceLabel;
    private Label passengerAmount;
    private boolean isExpanded;
    private boolean isMenuOpen;


    public FlightPane() {
        this.contentFlightHBox = new HBox(10);
        this.expandedBox = new VBox();
        this.isExpanded = false;
        this.isMenuOpen = false;
    }

    public Pane createContentInformationFlight(String enumeration, String city, String date, int minWidth, VBox mainContainer, String price) { // TODO: Insert into params the price and amount of passengers
        pane = new Pane();
        pane.setMinWidth(minWidth);
        pane.setMinHeight(70);

        HBox contentPane = createFirstContentFlight(mainContainer);
        createFirstLabel(enumeration, city, date, price, "2");

        HBox.setHgrow(enumLabel, Priority.NEVER);
        HBox.setHgrow(cityLabel, Priority.ALWAYS);
        HBox.setHgrow(dateLabel, Priority.NEVER);
        HBox.setHgrow(semiColonLabel, Priority.NEVER);
        HBox.setHgrow(enumLabelExpanded, Priority.NEVER);
        HBox.setHgrow(expandedBox, Priority.ALWAYS);
        HBox.setHgrow(priceLabel, Priority.NEVER);

        clickActions();

        contentPane.getChildren().addAll(enumLabel, cityLabel, dateLabel);
        pane.getChildren().add(contentPane);
        return pane;
    }

    private void createFirstLabel(String enumeration, String city, String date, String price, String amountPassengers) {
        enumLabel = createEnumartionLabel(enumeration, 20, 1, 0, 150);
        enumLabelExpanded = createEnumartionLabel(enumeration, 40, 20, 10, 220);
        cityLabel = createCityLabel(city);
        dateLabel = createDateLabel(date);
        semiColonLabel = createSemiColonLabel();
        priceLabel = createPriceLabel(price);
        dateLabelExpanded = createDateLabel(date);
        cityLabelExpanded = createCityLabel(city);
        passengerAmount = createPassengerAmount(amountPassengers);
        createSecondInformation();
    }

    private void createSecondInformation() {
        expandedBox.setSpacing(14);
        expandedBox.setPadding(new Insets(4, 0, 0, 0));
        expandedBox.setAlignment(Pos.CENTER_LEFT);
        expandedBox.getChildren().addAll(cityLabelExpanded, dateLabelExpanded, passengerAmount);
    }

    private void clickActions() {
        contentFlightHBox.setOnMouseEntered(event -> contentFlightHBox.setCursor(javafx.scene.Cursor.HAND));
        contentFlightHBox.setOnMouseExited(event -> contentFlightHBox.setCursor(javafx.scene.Cursor.DEFAULT));

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setStyle("-fx-background-color: #555; -fx-text-fill: white;");

        deleteItem.setOnAction(e -> {
            System.out.println("Delete button clicked");
        });
        contextMenu.getItems().addAll(deleteItem);

        contentFlightHBox.setOnMouseClicked(event -> {
            if (semiColonLabel.isVisible() && semiColonLabel.getBoundsInParent().contains(event.getX(), event.getY())) {
                Point2D p = semiColonLabel.localToScreen(semiColonLabel.getLayoutBounds().getMaxX(), semiColonLabel.getLayoutBounds().getMaxY());
                if (p != null) {
                    if (isMenuOpen) {
                        contextMenu.hide();
                    } else {
                        contextMenu.show(semiColonLabel, p.getX() - 10, p.getY() - 60);
                    }
                    isMenuOpen = !isMenuOpen;
                    event.consume();
                }
            } else {
                if (isMenuOpen) {
                    contextMenu.hide();
                    isMenuOpen = false;
                }
                toggleContent();
            }
        });
    }


    private void toggleContent() {
        if (isExpanded) {
            contentFlightHBox.setMinHeight(70);
            contentFlightHBox.getChildren().clear();
            contentFlightHBox.getChildren().addAll(enumLabel, cityLabel, dateLabel);
        } else {
            contentFlightHBox.setMinHeight(150);
            contentFlightHBox.getChildren().clear();
            contentFlightHBox.getChildren().addAll(enumLabelExpanded, expandedBox, priceLabel, semiColonLabel);
        }
        isExpanded = !isExpanded;
    }


    private HBox createFirstContentFlight(VBox mainContainer) {
        contentFlightHBox.setStyle("-fx-border-color: #333;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-color:  #28c4dc;" +
                "-fx-background-radius: 10px;");
        contentFlightHBox.setMinWidth(650);
        contentFlightHBox.setPadding(new Insets(25, 0, 25, 0));
        contentFlightHBox.setMaxWidth(Double.MAX_VALUE);
        contentFlightHBox.prefWidthProperty().bind(mainContainer.widthProperty());
        return contentFlightHBox;
    }

    private Label createEnumartionLabel(String enumeration, int fontSize, int topPadding, int leftPadding, int labelWidth) {
        Label enumerationLabel = ComponentsFX.getInstance().createLabel(enumeration, labelWidth, 20,
                Pos.CENTER, 0, 0, "#000000", fontSize,
                0, "Arial", "Bold");
        enumerationLabel.setPadding(new Insets(topPadding, 0, 0, leftPadding));
        return enumerationLabel;
    }

    private Label createCityLabel(String city) {
        Label cityLabel = ComponentsFX.getInstance().createLabel(city, 300, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 18,
                0, "Arial", "Normal");
        cityLabel.setPadding(new Insets(0, 0, 0, 0));
        return cityLabel;
    }

    private Label createPriceLabel(String price) {
        Label priceLabel = ComponentsFX.getInstance().createLabel(price, 340, 20,
                Pos.CENTER, 0, 0, "#000000", 50,
                0, "Arial", "Bold");
        priceLabel.setPadding(new Insets(17, 0, 0, 0));
        return priceLabel;
    }

    private Label createDateLabel(String date) {
        Label dateLabel = ComponentsFX.getInstance().createLabel(date, 270, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 17,
                0, "Arial", "Normal");
        dateLabel.setPadding(new Insets(2, 0, 0, 0));
        return dateLabel;
    }

    private Label createSemiColonLabel() {
        Label semiColonLabel = ComponentsFX.getInstance().createLabel(":", 10, 20,
                Pos.CENTER, 0, 0, "#000000", 40,
                0, "Arial", "Bold");
        semiColonLabel.setPadding(new Insets(20, 20, 0, 0));
        return semiColonLabel;
    }

    private Label createPassengerAmount(String amount) {
        Label passengerAmount = ComponentsFX.getInstance().createLabel(amount + " passengers", 10, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 20,
                0, "Arial", "Bold");
        passengerAmount.setPadding(new Insets(0, 20, 0, 0));
        return passengerAmount;
    }

    public Pane getPane() {
        return pane;
    }
}

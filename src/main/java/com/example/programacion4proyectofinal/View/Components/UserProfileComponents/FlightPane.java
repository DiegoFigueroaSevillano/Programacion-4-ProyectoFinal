package com.example.programacion4proyectofinal.View.Components.UserProfileComponents;

import com.example.programacion4proyectofinal.Controller.UserProfileController;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ComponentsFX;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * This class represents a pane that displays information about a person. It contains a person's image, name, and year of birth or death.
 */
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
    private int idFlight;
    private int idPassenger;
    private UserProfileController userProfileController;

    /**
     * Creates a pane that displays information about a person, including their image, name, and year of birth.
     * @param idFlight the id of the flight
     * @param idPassenger the id of the passenger
     * @param userProfileController the controller of the user profile
     */
    public FlightPane(int idFlight, int idPassenger, UserProfileController userProfileController) {
        this.contentFlightHBox = new HBox(10);
        this.expandedBox = new VBox();
        this.isExpanded = false;
        this.isMenuOpen = false;
        this.idFlight = idFlight;
        this.idPassenger = idPassenger;
        this.userProfileController = userProfileController;
    }

    /**
     * Creates a pane that displays information about a person, including their image, name, and year of birth.
     * @param enumeration the enumeration of the flight
     * @param city the city of the flight
     * @param date the date of the flight
     * @param minWidth the min width of the pane
     * @param mainContainer the main container of the pane
     * @param price the price of the flight
     * @param secondDataLabel the second data label of the flight
     * @param airlineName the airline name of the flight
     * @return a Pane object that contains the person's image, name, and year of birth
     */
    public Pane createContentInformationFlight(String enumeration, String city, String date, int minWidth, VBox mainContainer, String price, String secondDataLabel, String airlineName) {
        pane = new Pane();
        pane.setMinWidth(minWidth);
        pane.setMinHeight(70);

        HBox contentPane = createFirstContentFlight(mainContainer);
        createFirstLabel(enumeration, city, date, price, airlineName, secondDataLabel);

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

    /**
     * Creates a pane that displays information about a person, including their image, name, and year of birth.
     * @param enumeration the enumeration of the flight
     * @param city the city of the flight
     * @param date the date of the flight
     * @param price the price of the flight
     * @param airlineName the airline name of the flight
     * @param secondDataLabel the second data label of the flight
     */
    private void createFirstLabel(String enumeration, String city, String date, String price, String airlineName, String secondDataLabel) {
        enumLabel = createEnumartionLabel(enumeration, 20, 1, 0, 150);
        enumLabelExpanded = createEnumartionLabel(enumeration, 40, 20, 10, 220);
        cityLabel = createCityLabel(city);
        dateLabel = createDateLabel(date);
        semiColonLabel = createSemiColonLabel();
        priceLabel = createPriceLabel(price);
        dateLabelExpanded = createDateLabel(secondDataLabel);
        cityLabelExpanded = createCityLabel(city);
        passengerAmount = createAirlineName(airlineName);
        createSecondInformation();
    }

    /**
     * Creates the second information of the pane
     */
    private void createSecondInformation() {
        expandedBox.setSpacing(14);
        expandedBox.setPadding(new Insets(4, 0, 0, 0));
        expandedBox.setAlignment(Pos.CENTER_LEFT);
        expandedBox.getChildren().addAll(cityLabelExpanded, dateLabelExpanded, passengerAmount);
    }

    /**
     * Deletes a flight
     * @throws IOException if the file not exist
     */
    private void deleteActionButton() throws IOException {
        UserFlightInfoOperations.delete(idPassenger, idFlight);
        userProfileController.refreshFlightInformation();
    }

    /**
     * Creates the click actions of the pane
     */
    private void clickActions() {
        contentFlightHBox.setOnMouseEntered(event -> contentFlightHBox.setCursor(javafx.scene.Cursor.HAND));
        contentFlightHBox.setOnMouseExited(event -> contentFlightHBox.setCursor(javafx.scene.Cursor.DEFAULT));

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setStyle("-fx-background-color: #555; -fx-text-fill: white;");

        deleteItem.setOnAction(e -> {
            try {
                deleteActionButton();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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

    /**
     * Toggles the content of the pane
     */
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

    /**
     * Creates the first content of the pane
     * @param mainContainer the main container of the pane
     * @return the first content of the pane
     */
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

    /**
     * Creates the enumeration label
     * @param enumeration the enumeration of the flight
     * @param fontSize the font size of the label
     * @param topPadding the top padding of the label
     * @param leftPadding the left padding of the label
     * @param labelWidth the width of the label
     * @return the enumeration label
     */
    private Label createEnumartionLabel(String enumeration, int fontSize, int topPadding, int leftPadding, int labelWidth) {
        Label enumerationLabel = ComponentsFX.getInstance().createLabel(enumeration, labelWidth, 20,
                Pos.CENTER, 0, 0, "#000000", fontSize,
                0, "Arial", "Bold");
        enumerationLabel.setPadding(new Insets(topPadding, 0, 0, leftPadding));
        return enumerationLabel;
    }

    /**
     * Creates the city label
     * @param city the city of the flight
     * @return the city label
     */
    private Label createCityLabel(String city) {
        Label cityLabel = ComponentsFX.getInstance().createLabel(city, 300, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 18,
                0, "Arial", "Normal");
        cityLabel.setPadding(new Insets(0, 0, 0, 0));
        return cityLabel;
    }

    /**
     * Creates the price label
     * @param price the price of the flight
     * @return the price label
     */
    private Label createPriceLabel(String price) {
        Label priceLabel = ComponentsFX.getInstance().createLabel(price, 340, 20,
                Pos.CENTER, 0, 0, "#000000", 50,
                0, "Arial", "Bold");
        priceLabel.setPadding(new Insets(17, 0, 0, 0));
        return priceLabel;
    }

    /**
     * Creates the date label
     * @param date the date of the flight
     * @return the date label
     */
    private Label createDateLabel(String date) {
        Label dateLabel = ComponentsFX.getInstance().createLabel(date, 270, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 17,
                0, "Arial", "Normal");
        dateLabel.setPadding(new Insets(2, 0, 0, 0));
        return dateLabel;
    }

    /**
     * Creates the semicolon label
     * @return the semicolon label
     */
    private Label createSemiColonLabel() {
        Label semiColonLabel = ComponentsFX.getInstance().createLabel(":", 10, 20,
                Pos.CENTER, 0, 0, "#000000", 40,
                0, "Arial", "Bold");
        semiColonLabel.setPadding(new Insets(20, 20, 0, 0));
        return semiColonLabel;
    }

    /**
     * Creates the airline name label
     * @param airline the name of the airline
     * @return the airline name label
     */
    private Label createAirlineName(String airline) {
        Label passengerAmount = ComponentsFX.getInstance().createLabel(airline, 450, 20,
                Pos.CENTER_LEFT, 0, 0, "#000000", 20,
                0, "Arial", "Bold");
        passengerAmount.setPadding(new Insets(0, 17, 0, 0));
        return passengerAmount;
    }

    /**
     * Returns the pane containing the person's image, name, and year of birth.
     * @return the pane containing the person's image, name, and year of birth
     */
    public Pane getPane() {
        return pane;
    }
}

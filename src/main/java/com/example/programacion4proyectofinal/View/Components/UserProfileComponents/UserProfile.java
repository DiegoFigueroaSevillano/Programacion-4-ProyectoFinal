package com.example.programacion4proyectofinal.View.Components.UserProfileComponents;

import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {
    @FXML
    private Label CILabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label fullnameLabel;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Circle profileAvatarCircle;

    @FXML
    private ScrollPane scrollPaneHistory;
    private final BackgroundGenerator backgroundGenerator;
    private Passenger passenger;

    public UserProfile() {
        this.backgroundGenerator = new BackgroundGenerator();
    }

    public void setLabels(Passenger passenger) {
        this.passenger = passenger;
        CILabel.setText(passenger.getId() + "");
        categoryLabel.setText(passenger.getCategory() + "");
        countryLabel.setText(passenger.getCountry());
        fullnameLabel.setText(passenger.getFullName());
    }

    public void loadInformationFlights(List<Flight> flights) {
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(5);
        mainContainer.setPadding(new Insets(2,0,0,0));

        for (Flight flight : flights) {
            String city = flight.getOrigin() + " - " + flight.getDestination();
            String date = flight.getDepartureDataTime() + " - " + flight.getArrivalDataTime();

            FlightPane flightPane = new FlightPane();
            Pane pane = flightPane.createContentInformationFlight(flight.getIdFlight() + "", city, date, 680, mainContainer, flight.getCostOfTheFlight() + "");

            mainContainer.getChildren().add(pane);
        }

        scrollPaneHistory.setStyle("-fx-background-color: transparent;");

        scrollPaneHistory.setContent(mainContainer);
        scrollPaneHistory.setFitToWidth(true);
        scrollPaneHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }


    private void initProfileAvatarImage() {
        Image avatarImage = new Image("/com/example/programacion4proyectofinal/Views/Images/profile-avatar.png");
        profileAvatarCircle.setFill(new ImagePattern(avatarImage));
    }

    private void initBackgroundImage() {
        Image img = new Image("/com/example/programacion4proyectofinal/Views/Images/background-profile.png");
        mainAnchorPane.setBackground(backgroundGenerator.createBackgroundImage(img.getUrl()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBackgroundImage();
        initProfileAvatarImage();
        //loadInformationFlights();
    }
}

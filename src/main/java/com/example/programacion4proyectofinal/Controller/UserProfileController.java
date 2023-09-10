package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.View.Components.UserProfileComponents.FlightPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button goBackButton;
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
    private int idFlight;
    private Stage stage;


    public UserProfileController() {
        this.backgroundGenerator = new BackgroundGenerator();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
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
            String secondDate = flight.getDepartureTime() + " - " + flight.getArrivalTime();

            FlightPane flightPane = new FlightPane(flight.getIdFlight(), passenger.getId(), this);
            Pane pane = flightPane.createContentInformationFlight(flight.getIdFlight() + "", city, date, 680, mainContainer, flight.getCostOfTheFlight() + "", secondDate);

            mainContainer.getChildren().add(pane);
        }

        scrollPaneHistory.setStyle("-fx-background-color: transparent;");

        scrollPaneHistory.setContent(mainContainer);
        scrollPaneHistory.setFitToWidth(true);
        scrollPaneHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    public void refreshFlightInformation() throws IOException {
        List<Flight> updatedFlights = UserFlightInfoOperations.getAllFlightOfTheUser(passenger.getId());
        loadInformationFlights(updatedFlights);
    }


    private void initProfileAvatarImage() {
        Image avatarImage = new Image("/com/example/programacion4proyectofinal/Views/Images/profile-avatar.png");
        profileAvatarCircle.setFill(new ImagePattern(avatarImage));
    }

    private void initBackgroundImage() {
        Image img = new Image("/com/example/programacion4proyectofinal/Views/Images/background-profile.png");
        mainAnchorPane.setBackground(backgroundGenerator.createBackgroundImage(img.getUrl()));
    }

    private void setGoBackButton() {
        goBackButton.setOnAction(event -> passengerFlightControllerView());
    }

    private void passengerFlightControllerView() {
        Group root = new Group();

        PassengerOfAFlightController passenger = new PassengerOfAFlightController(root, stage, idFlight);

        Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");

        Scene currentScene = passenger.getView().getPassengerOfAFlightScene();

        ChangePropertiesStage changePropertiesStage = new ChangePropertiesStage();
        changePropertiesStage.changeSizeStage(800, 700, stage);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.getIcons().add(iconApp);
        stage.show();
    }

    private void setDeleteUserButton() {
        deleteUserButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete User");
            alert.setContentText("Are you sure you want to delete this user?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        UserFlightInfoOperations.deleteAll(passenger.getId());
                        deleteUserFiles(passenger);
                        passengerFlightControllerView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
    }

    private void deleteUserFiles(Passenger passenger) {
        BTree<Passenger> passengerBTree = new BTree<>(10, new FileHandlerBTree());
        passengerBTree.remove(passenger);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBackgroundImage();
        initProfileAvatarImage();
        setGoBackButton();
        setDeleteUserButton();
    }
}

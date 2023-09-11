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
import javafx.scene.Node;
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

/**
 * This method was created for control the user profile view
 */
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


    /**
     * Constructor method were we initialize the values
     */
    public UserProfileController() {
        this.backgroundGenerator = new BackgroundGenerator();
    }

    /**
     * This method set the stage
     * @param stage the stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * This method set the id of the flight
     * @param idFlight the id of the flight
     */
    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    /**
     * This method set the labels of the user
     * @param passenger the passenger
     */
    public void setLabels(Passenger passenger) {
        this.passenger = passenger;
        CILabel.setText(passenger.getId() + "");
        categoryLabel.setText(passenger.getCategory() + "");
        countryLabel.setText(passenger.getCountry());
        fullnameLabel.setText(passenger.getFullName());
    }

    /**
     * This method load the information of the flights
     * @param flights the flights
     */
    public void loadInformationFlights(List<Flight> flights) {
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(5);
        mainContainer.setPadding(new Insets(2, 0, 0, 0));

        for (Flight flight : flights) {
            String city = flight.getOrigin() + " - " + flight.getDestination();
            String date = flight.getDepartureDataTime() + " - " + flight.getArrivalDataTime();
            String secondDate = flight.getDepartureTime() + " - " + flight.getArrivalTime();

            FlightPane flightPane = new FlightPane(flight.getIdFlight(), passenger.getId(), this);
            Pane pane = flightPane.createContentInformationFlight(flight.getIdFlight() + "", city, date,
                    680, mainContainer, flight.getCostOfTheFlight() + "", secondDate, flight.getAirline() + "");

            mainContainer.getChildren().add(pane);
        }

        scrollPaneHistory.setStyle("-fx-background-color: transparent;");

        scrollPaneHistory.setContent(mainContainer);
        scrollPaneHistory.setFitToWidth(true);
        scrollPaneHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    /**
     * This method refresh the information of the flights
     * @throws IOException if the file not exist
     */
    public void refreshFlightInformation() throws IOException {
        List<Flight> updatedFlights = UserFlightInfoOperations.getAllFlightOfTheUser(passenger.getId());
        loadInformationFlights(updatedFlights);
    }

    /**
     * This method initialize the profile avatar image
     */
    private void initProfileAvatarImage() {
        Image avatarImage = new Image("/com/example/programacion4proyectofinal/Views/Images/profile-avatar.png");
        profileAvatarCircle.setFill(new ImagePattern(avatarImage));
    }

    /**
     * This method initialize the background image
     */
    private void initBackgroundImage() {
        Image img = new Image("/com/example/programacion4proyectofinal/Views/Images/background-profile.png");
        mainAnchorPane.setBackground(backgroundGenerator.createBackgroundImage(img.getUrl()));
    }

    /**
     * This method set the go back button
     */
    private void setGoBackButton() {
        goBackButton.setOnAction(event -> {
            passengerFlightControllerView();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    /**
     * This method set the passenger flight controller view
     */
    private void passengerFlightControllerView() {
        Group root = new Group();

        PassengerOfAFlightController passenger = new PassengerOfAFlightController(root, stage, idFlight);

        Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");

        Scene currentScene = passenger.getView().getPassengerOfAFlightScene();

        ChangePropertiesStage changePropertiesStage = new ChangePropertiesStage();
        changePropertiesStage.changeToMaximizeSizeStage(stage);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.getIcons().add(iconApp);
        stage.show();
    }

    /**
     * This method set the delete user button
     */
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
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    /**
     * This method delete the user files
     * @param passenger the passenger
     */
    private void deleteUserFiles(Passenger passenger) {
        BTree<Passenger> passengerBTree = new BTree<>(10, new FileHandlerBTree());
        passengerBTree.remove(passenger);
    }

    /**
     * This method initialize the view
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBackgroundImage();
        initProfileAvatarImage();
        setGoBackButton();
        setDeleteUserButton();
    }
}

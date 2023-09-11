package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.DataStructure.FlightPriorityQueue;
import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;
import com.example.programacion4proyectofinal.View.Components.ListComponents.ClientInfoButton;
import com.example.programacion4proyectofinal.View.Pages.PassengerOfAFlight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * This method was created for control the passenger flight view
 */
public class PassengerOfAFlightController {
    PassengerOfAFlight view;
    int flightID;
    Group group;
    Stage stage;

    /**
     * Constructor method were we initialize the values
     *
     * @param group the group of the stage
     * @param stage the stage
     * @param flightID the flightID
     */
    public PassengerOfAFlightController(Group group, Stage stage, int flightID){
        this.view = new PassengerOfAFlight(group, stage);
        this.group = group;
        this.stage = stage;
        this.flightID = flightID;
        chargeThePeople();
        chargeTheFlightInformation();
    }

    /**
     * This method return the view
     *
     * @return the view
     */
    public PassengerOfAFlight getView() {
        return view;
    }

    /**
     * This method charge the button with the information of the people in the scroll pane
     * This people stay in the flight id
     */
    public void chargeThePeople(){
        view.getItemsContainer().getChildren().clear();
        FlightPriorityQueue flightPriorityQueue = new FlightPriorityQueue(flightID);
        while (flightPriorityQueue.getPriorityQueue().peek() != null){
            UserFlightInfo userFlightInfo = flightPriorityQueue.getPriorityQueue().poll();
            ClientInfoButton clientInfoButton = new ClientInfoButton(userFlightInfo, view.getScrollPane());
            deleteAction(clientInfoButton);
            payAction(clientInfoButton);
            principalButtonAction(clientInfoButton);
            verifyTheButtonActions(clientInfoButton);
            view.getItemsContainer().getChildren().add(clientInfoButton.getButtonContainer());
        }
    }

    /**
     * This method verify if the passenger have a buy status and disable their buttons aux
     *
     * @param clientInfoButton the client info button
     */
    private void verifyTheButtonActions(ClientInfoButton clientInfoButton){
        if (clientInfoButton.getUser().getStatus().equals(Status.BUY)){
            clientInfoButton.getDeleteButton().setDisable(true);
            clientInfoButton.getPayButton().setDisable(true);
        }
    }

    /**
     * This method set the pay action to the respective button.
     * This method change the scene to the pay scene for change the status of a buy
     *
     * @param clientInfoButton the client info button
     */
    public void payAction(ClientInfoButton clientInfoButton){
        clientInfoButton.getPayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group = new Group();
                ChangeDispenserController changeDispenserController = new ChangeDispenserController(group, stage,
                        clientInfoButton.getUser().getPassenger().getId(), flightID, true);
                Scene scene = changeDispenserController.getChangeDispenserView().getChangeDispenserScene();
                stage.setScene(scene);
                chargeThePeople();
                event.consume();
            }
        });
    }

    /**
     * This method set the action of the delete button
     * This button delete the passenger in a flight
     *
     * @param button client info button
     */
    public void deleteAction(ClientInfoButton button){
        button.getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    UserFlightInfoOperations.delete(button.getUser().getPassenger().getId()
                            , button.getUser().getFlightID());
                    chargeThePeople();
                    event.consume();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * This method change the view to the client info view
     *
     * @param button the client info button
     */
    public void principalButtonAction(ClientInfoButton button){
        button.getButtonContainer().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Passenger user = button.getUser().getPassenger();
                List<Flight> flights;
                try {
                    flights = UserFlightInfoOperations.getAllFlightOfTheUser(user.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/programacion4proyectofinal/Views/user-profile.fxml")));
                    Parent root = loader.load();
                    UserProfileController userProfileController = loader.getController();
                    userProfileController.setPassengerView(false);
                    userProfileController.setLabels(user);
                    userProfileController.loadInformationFlights(flights);
                    userProfileController.setIdFlight(flightID);
                    userProfileController.setStage(stage);
                    Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
                    newStage.getIcons().add(iconApp);
                    newStage.setMinWidth(1100);
                    newStage.setMinHeight(800);
                    newStage.setTitle("User Profile");
                    newStage.show();
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method charge the information of a flight in their respective label
     */
    public void chargeTheFlightInformation(){
        Flight flight = null;
        try {
            flight = FlightJsonOperations.get(flightID);
            view.getAirlineLabel().setText(flight.getAirline().toString());
            view.getOriginLabel().setText("ORIGIN: " + flight.getOrigin().toString());
            view.getDestinyLabel().setText("DESTINY: " + flight.getDestination().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

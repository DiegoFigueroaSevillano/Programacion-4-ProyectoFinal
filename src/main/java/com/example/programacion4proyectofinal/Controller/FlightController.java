package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;
import com.example.programacion4proyectofinal.View.Components.ListComponents.FlightInfoButton;
import com.example.programacion4proyectofinal.View.Pages.FlightView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The FlightController class manages flight information and interactions with flight views.
 */
public class FlightController {

    private FlightView flightView;
    private Group group;
    private Stage stage;

    /**
     * Constructs a new FlightController with the specified group and stage.
     *
     * @param group The JavaFX Group to which flight information will be displayed.
     * @param stage The JavaFX Stage for managing scenes and views.
     */
    public FlightController(Group group, Stage stage) {
        this.flightView = new FlightView(group, stage);
        this.group = group;
        this.stage = stage;
        chargeInfoFlight();
    }

    /**
     * Gets the FlightView associated with this controller.
     *
     * @return The FlightView instance.
     */
    public FlightView getFlightView() {
        return flightView;
    }

    /**
     * Loads and displays flight information in the FlightView.
     */
    public void chargeInfoFlight() {
        flightView.getItemsContainer().getChildren().clear();
        try {
            ArrayList<Flight> flight = FlightJsonOperations.getFlight();
            for (Flight flight1 : flight) {
                FlightInfoButton clientInfoButton = new FlightInfoButton(flight1, flightView.getScrollPane());
                buyFlightAction(clientInfoButton);
                principalButtonAction(clientInfoButton);
                flightView.getItemsContainer().getChildren().add(clientInfoButton.getButtonContainer());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Defines the action to perform when the "Buy Flight" button is clicked.
     *
     * @param flightInfoButton The FlightInfoButton associated with the action.
     */
    public void buyFlightAction(FlightInfoButton flightInfoButton) {

        flightInfoButton.getPayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group = new Group();
                BuyAFlightController buyAFlightController = new BuyAFlightController(group, stage, flightInfoButton.getFlight().getIdFlight());
                Scene scene = buyAFlightController.getView().getScene();
                stage.setScene(scene);
                chargeInfoFlight();
                event.consume();
            }
        });
    }


    /**
     * Defines the action to perform when the flight info button is clicked.
     *
     * @param button The FlightInfoButton associated with the action.
     */
    public void principalButtonAction(FlightInfoButton button) {
        button.getButtonContainer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group = new Group();
                PassengerOfAFlightController passengerOfAFlightController = new PassengerOfAFlightController(group, stage, button.getFlight().getIdFlight());
                Scene scene = passengerOfAFlightController.getView().getPassengerOfAFlightScene();
                stage.setScene(scene);
                chargeInfoFlight();
            }
        });
    }
}


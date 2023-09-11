package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Model.Flight.Data.City;
import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;
import com.example.programacion4proyectofinal.View.Pages.Home;
import javafx.scene.Group;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightDataGenerator.createRandomCost;
import static com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightDataGenerator.createRandomFlightID;

/**
 * Controller class for the Home view.
 */
public class HomeController {

    private Home homeView;

    /**
     * Constructor for the HomeController.
     *
     * @param root  The root Group node.
     * @param stage The primary Stage.
     */
    public HomeController(Group root, Stage stage) {
        homeView = new Home(root, stage);
        addActionToCreate();
    }

    /**
     * Adds an action to the "Create" button in the home view.
     * When the "Create" button is clicked, this method generates random flight information,
     * creates a new Flight object, and inserts it into a JSON file using FlightJsonOperations.
     *
     * @throws RuntimeException if an IOException occurs while inserting the flight data into the JSON file.
     */
    private void addActionToCreate() {
        homeView.getCreateButton().setOnAction(event -> {
            int id = createRandomFlightID();
            City origin = City.valueOf(homeView.getFromList().getPlacesList().getValue());
            City destination = City.valueOf(homeView.getToList().getPlacesList().getValue());
            Airline airline = Airline.valueOf(homeView.getAirlinesList().getComboBox().getValue());
            DatePicker depurate = homeView.getDepurateDate().getDatePicker();
            LocalDateTime depurateTime = depurate.getValue().atStartOfDay();
            DatePicker arrival = homeView.getReturnDate().getDatePicker();
            LocalDateTime arrivalTime = arrival.getValue().atStartOfDay();
            int cost = createRandomCost();
            Flight flight = new Flight(id, origin, destination, airline, depurateTime, arrivalTime, cost);
            try {
                FlightJsonOperations.insert(flight);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    /**
     * Retrieves the Home view associated with this controller.
     *
     * @return The Home view.
     */
    public Home getHomeView() {
        return homeView;
    }
}
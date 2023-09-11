package com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase;


import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Model.Flight.Data.City;
import com.example.programacion4proyectofinal.Model.Flight.Flight;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was created for generate a json flight with values
 */
public class FlightJsonGenerator {

    /**
     * This method generate the Json with her values
     *
     * @param quantity the quantity of values
     */
    public static void generateJson(int quantity){
        int idFlight;
        List<Integer> ids = new ArrayList<>();
        City origin;
        City destination;
        Airline airline;
        LocalDateTime departureDate;
        LocalDateTime arrivalDate;
        int randomCost;
        for (int i = 0; i < quantity; i++){
            idFlight = FlightDataGenerator.createRandomFlightID();
            if (!ids.contains(idFlight)){
                ids.add(idFlight);
                origin = FlightDataGenerator.getRandomCity();
                destination = FlightDataGenerator.getRandomCity(origin);
                airline = FlightDataGenerator.getRandomAirline();
                departureDate = FlightDataGenerator.getRandomDateTime();
                arrivalDate = FlightDataGenerator.getRandomDateTime(departureDate);
                randomCost = FlightDataGenerator.createRandomCost();
                Flight flight = new Flight(idFlight, origin, destination, airline, departureDate, arrivalDate, randomCost);
                try {
                    FlightJsonOperations.insert(flight);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

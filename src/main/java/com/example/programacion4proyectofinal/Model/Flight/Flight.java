package com.example.programacion4proyectofinal.Model.Flight;

import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Model.Flight.Data.City;

import java.time.LocalDateTime;


/**
 * This class represent a flight
 */
public class Flight {
    int idFlight;
    City origin;
    City destination;
    Airline airline;
    LocalDateTime departureDate;
    LocalDateTime arrivalDate;
    int costOfTheFlight;

    /**
     * Constructor method that initialize the flight class
     *
     * @param idFlight the id of the flight
     * @param origin the origin of the flight
     * @param destination the destination fo the flight
     * @param airline the airline of the flight
     * @param departureDate the departure date of the flight
     * @param arrivalDate the arrival date of the flight
     */
    public Flight(int idFlight, City origin, City destination, Airline airline, LocalDateTime departureDate, LocalDateTime arrivalDate, int costOfTheFlight) {
        this.idFlight = idFlight;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.costOfTheFlight = costOfTheFlight;
    }

    /**
     * This method return us the cost of a flight
     *
     * @return the cost of a flight
     */
    public int getCostOfTheFlight() {
        return costOfTheFlight;
    }

    /**
     * This method return us the flight id
     *
     * @return flight id
     */
    public int getIdFlight() {
        return idFlight;
    }

    /**
     * This method return us the origin of the flight
     *
     * @return the origin of the flight
     */
    public City getOrigin() {
        return origin;
    }

    /**
     * this method return us the destination of the flight
     *
     * @return the destination of the flight
     */
    public City getDestination() {
        return destination;
    }

    /**
     * This method return us the airline of the flight
     *
     * @return the airline of the flight
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * This method return us the departure date of the flight
     *
     * @return the departure date of the flight
     */
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * This method return us the arrival date of the flight
     *
     * @return the arrival date of the flight
     */
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }
}

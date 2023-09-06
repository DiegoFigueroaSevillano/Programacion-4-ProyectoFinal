package com.example.programacion4proyectofinal.Model.Flight;

import java.util.ArrayList;
import java.util.Date;

public class Flight {
    Integer idFlight;
    String origin;
    String destination;
    String airline;
    Date departureDate;
    Date departureTime;
    Date arrivalDate;
    Date arrivalTime;
    ArrayList<Seat> seatsFlight = new ArrayList();


    public Flight(Integer idFlight, String origin, String destination, String airline, Date departureDate, Date departureTime, Date arrivalDate, Date arrivalTime) {
        this.idFlight = idFlight;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }




}

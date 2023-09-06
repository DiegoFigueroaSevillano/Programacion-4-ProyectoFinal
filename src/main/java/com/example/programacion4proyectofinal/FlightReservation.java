package com.example.programacion4proyectofinal;

import java.util.Date;

public class FlightReservation {
    Integer idFlight;
    Integer numberSeat;
    String categorySeat;
    Integer numberReservation;
    String origin;
    String destination;
    String airline;
    Date departureDate;
    Date departureTime;
    Date arrivalDate;
    Date arrivalTime;
    String namePassenger;
    String lastNamePassenger;


    public FlightReservation(Integer numberFlight, Integer numberReservation, Integer numberSeat, String categorySeat, String origin, String destination, String airline, Date departureDate, Date departureTime, Date arrivalDate, Date arrivalTime, String namePassenger, String lastNamePassenger) {
        this.idFlight = numberFlight;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.namePassenger = namePassenger;
        this.lastNamePassenger = lastNamePassenger;
        this.numberReservation = numberReservation;
        this.numberSeat = numberSeat;
        this.categorySeat = categorySeat;
    }


    @Override

    public boolean equals(Object anObject){
        boolean result = false;
        if (anObject == null)
                return false;
        if (anObject instanceof FlightReservation){
            FlightReservation aux = ((FlightReservation) anObject);
            result= idFlight.equals(aux.idFlight)
                    && numberSeat.equals(aux.numberSeat)
                    && numberReservation.equals(aux.numberReservation)
                    && namePassenger.equals(aux.namePassenger)
                    && lastNamePassenger.equals(aux.lastNamePassenger)
                    && lastNamePassenger.equals(aux.lastNamePassenger)
                    && airline.equals(aux.airline)
                    && destination.equals(aux.destination)
                    && origin.equals(aux.origin)
                    && categorySeat.equals(aux.categorySeat)
                    && departureDate.equals(aux.departureDate)
                    && departureTime.equals(aux.departureTime)
                    && arrivalDate.equals(aux.arrivalDate)
                    && arrivalTime.equals(aux.arrivalTime) ;
        }
        return result;
    }





}

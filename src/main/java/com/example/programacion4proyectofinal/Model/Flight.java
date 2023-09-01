package com.example.programacion4proyectofinal.Model;

import java.util.Date;

public class Flight {
    Integer numberFlight;
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


    public Flight(Integer numberFlight,Integer numberReservation,Integer numberSeat,String categorySeat, String origin, String destination, String airline, Date departureDate, Date departureTime, Date arrivalDate, Date arrivalTime, String namePassenger, String lastNamePassenger) {
        this.numberFlight = numberFlight;
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
        if (anObject instanceof Flight){
            Flight aux = ((Flight) anObject);
            result=numberFlight.equals(aux.numberFlight)
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

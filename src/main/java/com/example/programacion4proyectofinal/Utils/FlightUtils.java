package com.example.programacion4proyectofinal.Utils;

import com.example.programacion4proyectofinal.FlightReservation;
import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Model.Flight.Seat;
import com.example.programacion4proyectofinal.Model.Person.Passenger;

import java.util.ArrayList;

public class FlightUtils {
    public void AssignFlightReservation(Passenger namePassenger, Flight flight, Seat seat){
        FlightReservation flightReservation = new FlightReservation(234,35,seat.getIdSeat(),seat.getCategorySeat(),flight.getOrigin(), flight.getDestination(), flight.getAirline(),flight.getDepartureDate(),flight.getDepartureTime(),flight.getArrivalDate(),flight.getArrivalTime(), seat.getNamePassenger(), seat.getLastNamePassenger());
        namePassenger.getFlightArrayList().add(flightReservation);
    }
}

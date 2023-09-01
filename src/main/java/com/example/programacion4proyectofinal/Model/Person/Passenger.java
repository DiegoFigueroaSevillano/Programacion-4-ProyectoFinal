package com.example.programacion4proyectofinal.Model.Person;

import com.example.programacion4proyectofinal.Model.Flight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The Passenger class represents a passenger who is associated with a flight ticket.
 * It inherits attributes and methods from the Person class.
 */
public class Passenger extends Person {
    Set<Flight> flightHistory = new HashSet<Flight>();


    /**
     * Constructs a new Passenger object with the given name and ID.
     *
     * @param name The name of the passenger.
     * @param id The ID of the passenger.
     */
    public Passenger(String name, int id) {
        super(name, id);
    }

    public void addFlight(Flight flight){
        flightHistory.add(flight);
    }

    public void showFlights(){
        Iterator<Flight> iterator = flightHistory.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }


}
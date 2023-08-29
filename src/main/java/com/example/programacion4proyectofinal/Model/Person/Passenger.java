package com.example.programacion4proyectofinal.Model.Person;

/**
 * The Passenger class represents a passenger who is associated with a flight ticket.
 * It inherits attributes and methods from the Person class.
 */
public class Passenger extends Person implements Comparable<Passenger> {

    /**
     * Constructs a new Passenger object with the given name and ID.
     *
     * @param name The name of the passenger.
     * @param id The ID of the passenger.
     */
    public Passenger(String name, int id) {
        super(name, id);
    }

    @Override
    public int compareTo(Passenger o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
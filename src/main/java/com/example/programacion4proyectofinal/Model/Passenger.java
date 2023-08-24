package com.example.programacion4proyectofinal.Model;

/**
 * The Passenger class represents a passenger who is associated with a flight ticket.
 */
public class Passenger {

    private String name;

    /**
     * Constructs a new Passenger with the given name.
     *
     * @param name The name of the passenger.
     */
    public Passenger(String name) {
        this.name = name;
    }

    /**
     * Get the name of the passenger.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the passenger.
     *
     * @param name The name of the passenger.
     */
    public void setName(String name) {
        this.name = name;
    }
}

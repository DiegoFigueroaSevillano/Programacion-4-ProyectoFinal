package com.example.programacion4proyectofinal.Model.Person;

/**
 * The Passenger class represents a passenger who is associated with a flight ticket.
 * It inherits attributes and methods from the Person class.
 */
public class Passenger extends Person implements Comparable<Passenger> {
    private Category category;

    /**
     * Constructs a new Passenger object with the given name and ID.
     *
     * @param name The name of the passenger.
     * @param id The ID of the passenger.
     */
    public Passenger(int id, String name, String lastName, String country, Category category) {
        super(name, lastName, country, id);
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return getFullName() + " id: " + getId();
    }

    @Override
    public int compareTo(Passenger o) {
        if (this.getFullName().equals(o.getFullName())) {
            if (this.getId() < o.getId()) {
                return -1;
            } else if (this.getId() > o.getId()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return this.getFullName().compareTo(o.getFullName());
        }
    }

}
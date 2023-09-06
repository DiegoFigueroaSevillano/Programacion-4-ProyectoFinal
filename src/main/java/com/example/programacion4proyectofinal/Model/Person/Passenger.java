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
     * @param id   The ID of the passenger.
     */
    public Passenger(int id, String name, String lastName, String country, Category category) {
        super(name, lastName, country, id);
        this.category = category;
    }

    /**
     * Sets the category of the passenger.
     *
     * @param category The new category for the passenger.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retrieves the category of the passenger.
     *
     * @return The category of the passenger.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * This method returns in a String the full name of the passenger.
     *
     * @return The full name of the passenger.
     */
    @Override
    public String toString() {
        return getFullName() + " id: " + getId();
    }

    /**
     * This method compares two passengers by their id, if they have the same id, then it compares them by their full name.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Passenger o) {
        if (this.getId() < o.getId()) {
            return -1;
        } else if (this.getId() > o.getId()) {
            return 1;
        }
        return this.getFullName().compareTo(o.getFullName());
    }
}
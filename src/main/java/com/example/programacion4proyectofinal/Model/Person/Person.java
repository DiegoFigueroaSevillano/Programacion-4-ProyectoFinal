package com.example.programacion4proyectofinal.Model.Person;

/**
 * This class represents a person with a name and an ID.
 */
public class Person {
    private String name;
    private String lastName;
    private int id;
    private String country;

    /**
     * Constructs a new Person object with the given name and ID.
     *
     * @param name The name of the person.
     * @param id   The ID of the person.
     */
    public Person(String name, String lastName, String country, int id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.country = country;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The new name for the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the ID of the person.
     *
     * @return The ID of the person.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id The new ID for the person.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the last name of the person.
     * @param lastName The new last name for the person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the last name of the person.
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the country of the person.
     * @param country The new country for the person.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the country of the person.
     * @return The country of the person.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the full name of the person.
     * @return The full name of the person.
     */
    public String getFullName() {
        return name + " " + lastName;
    }
}

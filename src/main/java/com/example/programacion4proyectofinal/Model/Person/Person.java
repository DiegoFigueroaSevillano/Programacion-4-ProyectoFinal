package com.example.programacion4proyectofinal.Model.Person;

/**
 * This class represents a person with a name and an ID.
 */
public class Person {

    private String name;
    private int id;

    /**
     * Constructs a new Person object with the given name and ID.
     *
     * @param name The name of the person.
     * @param id The ID of the person.
     */
    public Person(String name, int id) {
        this.name = name;
        this.id = id;
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
}

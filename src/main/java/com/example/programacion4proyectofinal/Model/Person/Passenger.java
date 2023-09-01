package com.example.programacion4proyectofinal.Model.Person;

/**
 * The Passenger class represents a passenger who is associated with a flight ticket.
 * It inherits attributes and methods from the Person class.
 */
public class Passenger extends Person {

    String name;
    String lastName;
    Integer reservationTime;
    public Enum priority;

    public Passenger(String name, String lastName, int id , Integer reservationTime,Enum priority) {
        super(name,id);
        this.name = name;
        this.lastName = lastName;
        this.reservationTime = reservationTime;
        this.priority = priority;
    }

    /**
     * This method is used to get the passenger's information.
     * @return Passenger's information.
     */
    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", apellido='" + lastName + '\'' +
                ", reservationTime=" + reservationTime +
                ", priority=" + priority +
                '}';
    }

    /**
     * This methos is used to get the passenger's name.
     * @return Passenger's name.
     */
    public String getNombre() {
        return name;
    }
    /**
     * This methos is used to get the passenger's last name.
     * @return passenger's last name.
     */

    public String getLastName() {
        return lastName;
    }


}
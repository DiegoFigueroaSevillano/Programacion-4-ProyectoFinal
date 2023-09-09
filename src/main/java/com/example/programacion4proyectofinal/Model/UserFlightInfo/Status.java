package com.example.programacion4proyectofinal.Model.UserFlightInfo;

import com.example.programacion4proyectofinal.Model.Person.Category;

/**
 * Enum class created for the Status of a buy in a flight
 */
public enum Status{
    BUY(2),
    RESERVED(1);

    private int value;

    /**
     * Constructor method for the Status Enum
     *
     * @param value the value of the status
     */
    private Status(int value) {
        this.value = value;
    }

    /**
     * Compare to method created for compare to Status items
     *
     * @param other the other value
     * @return the comparator value
     */
    public int compare(Status other) {
        return other.value - this.value;
    }


}

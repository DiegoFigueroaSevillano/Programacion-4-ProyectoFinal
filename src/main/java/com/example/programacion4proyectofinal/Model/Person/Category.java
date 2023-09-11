package com.example.programacion4proyectofinal.Model.Person;

/**
 * Enumerated class that represents the category of a passenger
 */
public enum Category {
    VIP(3),
    FREQUENT_PASSENGER(2),
    REGULAR_PASSENGER(1);

    private int value;

    /**
     * Constructor class for the category enum
     *
     * @param value the value of the category
     */
    private Category(int value) {
        this.value = value;
    }

    /**
     * Compare to method
     *
     * @param other the other value to compare
     * @return the comparator value
     */
    public int compare(Category other) {
        return other.value - this.value;
    }
}

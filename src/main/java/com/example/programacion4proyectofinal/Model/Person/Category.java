package com.example.programacion4proyectofinal.Model.Person;

/**
 * Enumerated class that represents the category of a passenger
 */
public enum Category {
    VIP(3),
    FREQUENT_PASSENGER(2),
    REGULAR_PASSENGER(1);

    private int value;

    private Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }



    public int compare(Category other) {
        return other.value - this.value;
    }
}

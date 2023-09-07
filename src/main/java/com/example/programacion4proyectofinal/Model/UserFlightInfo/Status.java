package com.example.programacion4proyectofinal.Model.UserFlightInfo;

import com.example.programacion4proyectofinal.Model.Person.Category;

public enum Status{
    BUY(2),
    RESERVED(1);

    private int value;

    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compare(Status other) {
        return other.value - this.value;
    }


}

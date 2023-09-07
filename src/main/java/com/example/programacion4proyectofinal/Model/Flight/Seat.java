package com.example.programacion4proyectofinal.Model.Flight;

import java.util.ArrayList;

public class Seat {
    Integer idSeat;
    int numberRow;
    int numberColumn;
    String categorySeat;

    String namePassenger;
    String lastNamePassenger;

    public Seat(Integer idSeat, int numberRow, int numberColumn, String categorySeat, String namePassenger, String lastNamePassenger) {
        this.idSeat = idSeat;
        this.numberRow = numberRow;
        this.numberColumn = numberColumn;
        this.categorySeat = categorySeat;
        this.namePassenger = namePassenger;
        this.lastNamePassenger = lastNamePassenger;
    }

    public Integer getIdSeat() {
        return idSeat;
    }

    public int getNumberRow() {
        return numberRow;
    }

    public int getNumberColumn() {
        return numberColumn;
    }

    public String getCategorySeat() {
        return categorySeat;
    }

    public String getNamePassenger() {
        return namePassenger;
    }

    public String getLastNamePassenger() {
        return lastNamePassenger;
    }
}

package com.example.programacion4proyectofinal.Model.Flight;

public class Seat {
    Integer idSeat;
    int numberRow;
    int numberColumn;
    String categorySeat;

    String namePassenger;

    public Seat(Integer idSeat, int numberRow, int numberColumn, String categorySeat, String namePassenger) {
        this.idSeat = idSeat;
        this.numberRow = numberRow;
        this.numberColumn = numberColumn;
        this.categorySeat = categorySeat;
        this.namePassenger = namePassenger;
    }
}

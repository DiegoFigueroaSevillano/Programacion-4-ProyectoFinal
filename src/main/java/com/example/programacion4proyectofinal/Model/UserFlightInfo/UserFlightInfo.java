package com.example.programacion4proyectofinal.Model.UserFlightInfo;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.Generators.UserGenerator.DataGenerator;

import java.time.LocalDateTime;

public class UserFlightInfo implements Comparable<UserFlightInfo> {
    private Passenger passenger;
    private int flightID;
    private Status status;
    private LocalDateTime localDateTime;

    public UserFlightInfo(int userCI, int flightID, Status status, LocalDateTime localDateTime) {
        BTree<Passenger> bTree = new BTree<>(100, new FileHandlerBTree()); //TODO: CAMBIAR POR EL DEGREE REAL

        DataGenerator dataGenerator = new DataGenerator();

        this.passenger = new Passenger(userCI, "A", "A", "A", dataGenerator.generateCategory());//TODO: METODO PARA OBTENER EL PASAJERO DEL BTREE
        this.flightID = flightID;
        this.status = status;
        this.localDateTime = localDateTime;
    }


    public Passenger getPassenger() {
        return passenger;
    }

    public int getFlightID() {
        return flightID;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }


    @Override
    public int compareTo(UserFlightInfo o) {
        int statusComparison = this.status.compare(o.status);
        if (statusComparison == 0) {
            int passengerComparison = this.passenger.compareTo(o.passenger);
            if (passengerComparison == 0) {
                return this.localDateTime.compareTo(o.localDateTime);
            } else {
                return passengerComparison;
            }
        } else {
            return statusComparison;
        }
    }


}

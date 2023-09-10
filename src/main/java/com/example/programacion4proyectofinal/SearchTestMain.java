package com.example.programacion4proyectofinal;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.Search;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/*
* Is only for test the Search Logic
*/
public class SearchTestMain {

    public static void main(String[] args) {
        Search search = new Search();
        long startById = System.currentTimeMillis();
        Passenger passenger = search.searchById(9999396);
        long endById = System.currentTimeMillis();
        System.out.println("===============================================");
        if (passenger == null) {
            System.out.println("NODE IS NULL");
        } else {
            System.out.println(passenger.toString());
        }
        System.out.println("===============================================");

        long startByName = System.currentTimeMillis();
        ArrayList<Passenger> result = search.searchByName("PACHECO");
        long endByName = System.currentTimeMillis();

        System.out.println("===============================================");
        if (!result.isEmpty()) {
            for (int index = 0; index < result.size(); index++) {
                System.out.println(result.get(index).toString());
                System.out.println("===============================================");
            }
        } else {
            System.out.println("USER NOT FOUND!!!\n===============================================");
        }

        System.out.println("===============================================");

        long startAll = System.currentTimeMillis();
        ArrayList<Passenger> allPassengers = null;
        try {
            allPassengers = search.obtainAllPassengers();
        } catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException(exception);
        }
        long endAll = System.currentTimeMillis();

        System.out.println(allPassengers.size() + " PASSENGERS");

        System.out.println("===============================================");

        System.out.println("===============================================");
        System.out.println("BY ID TIME: " + ((endById - startById) / 1000) + " seg");
        System.out.println("BY NAME TIME: " + ((endByName - startByName) / 1000) + " seg");
        System.out.println("ALL PASSENGERS TIME: " + ((endAll - startAll) / 1000) + " seg");
        System.out.println("===============================================");
    }

}

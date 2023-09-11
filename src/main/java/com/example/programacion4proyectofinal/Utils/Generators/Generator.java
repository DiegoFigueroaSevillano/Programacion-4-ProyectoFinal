package com.example.programacion4proyectofinal.Utils.Generators;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonGenerator;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightDataGenerator;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightJsonGenerator;
import com.example.programacion4proyectofinal.Utils.Generators.UserGenerator.UserGenerator;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class was created for the generation of the all started data
 */
public class Generator {
    /**
     * This method generate all data
     *
     * @param degree the degree of the b tree
     * @param quantityOfUsersInTheBtree the quantity of users into the btree
     */
    public static void generateAll(int degree, int quantityOfUsersInTheBtree,int quantityOfFlights){
        BTree<Integer> bTree = new BTree<>(degree);
        HashMap<Integer, Passenger> hashMap = new HashMap<>();
        UserGenerator.generateUsers(bTree, quantityOfUsersInTheBtree, hashMap);
        FlightJsonGenerator.generateJson(quantityOfFlights);
        int[] users = UserFlightDataGenerator.getRandomKeys(hashMap, quantityOfFlights*30);
        System.out.println("TOTAL USERS: " +  users.length);
        int[] flight;
        try {
            flight = FlightJsonOperations.getAllIDs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("TOTAL FLIGHTS: " + flight.length);
        UserFlightJsonGenerator.generateJson(flight, users);
    }

    public static void main(String[] args) {
        generateAll(10, 500, 5);
    }
}

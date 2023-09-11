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
import java.util.Stack;

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
        long start;
        long end;
        long result;

        start = System.nanoTime();
        BTree<Integer> bTree = new BTree<>(degree);
        HashMap<Integer, Passenger> hashMap = new HashMap<>();
        UserGenerator.generateUsers(bTree, quantityOfUsersInTheBtree, hashMap);
        end = System.nanoTime();
        result = end - start;
        System.out.println("TIEMPO DE CREACION DE USUARIOS: "  + result);

        start = System.nanoTime();
        FlightJsonGenerator.generateJson(quantityOfFlights);
        end = System.nanoTime();
        result = end - start;
        System.out.println("CREACION DE VUELOS: " + result);

        start = System.nanoTime();
        int[] users = UserFlightDataGenerator.getRandomKeys(hashMap, quantityOfFlights*50);
        int[] flight;
        try {
            flight = FlightJsonOperations.getAllIDs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserFlightJsonGenerator.generateJson(flight, users);
        result = end - start;
        System.out.println("CREACION DE USUARIOS DENTRO DE VUELOS: " + result);
    }

    public static void main(String[] args) {
        generateAll(100, 1000000, 20);
    }
}

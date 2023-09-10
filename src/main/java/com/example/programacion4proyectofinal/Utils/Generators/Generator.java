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
     * @param quantityOfUsersWithAFlight the quantity of users with a flight
     */
    public static void generateAll(int degree, int quantityOfUsersInTheBtree,int quantityOfFlights, int quantityOfUsersWithAFlight){

        //GENERADOR DE USUARIOS
        BTree<Integer> bTree = new BTree<>(degree);
        HashMap<Integer, Passenger> hashMap = new HashMap<>();
        UserGenerator.generateUsers(bTree, quantityOfUsersInTheBtree, hashMap);

        //GENERAR VUELOS
        FlightJsonGenerator.generateJson(quantityOfFlights);

        //GENERAR USUARIOS DENTRO DE VUELOS
        int[] users = UserFlightDataGenerator.getRandomKeys(hashMap, quantityOfUsersWithAFlight);

        //OBTENER LA LISTA DE VUELOS
        int[] flight;
        try {
            flight = FlightJsonOperations.getAllIDs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserFlightJsonGenerator.generateJson(40, flight, users);
    }
}

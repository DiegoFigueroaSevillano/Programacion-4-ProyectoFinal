package com.example.programacion4proyectofinal.Model.UserGenerator;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Utils.PassengerDataGenerator.Generator;
import com.example.programacion4proyectofinal.Model.Person.Passenger;

import java.util.HashMap;

public class UserGenerator {

    /**
     * Method that will perform the first generation of data within the b-tree
     *
     * @param bTree the respective b-tree
     * @param quantityOfUsers the number of users to initialize
     */
    public static void generateUsers(BTree<Integer> bTree, int quantityOfUsers){
        String name;
        int CI;
        Generator generator = new Generator();
        HashMap<Integer, Passenger> hashMap = new HashMap<>();

        for (int i = 0; i < quantityOfUsers; i++){
            name = generator.generateName();
            CI = generator.generateCI();
            Passenger passenger = new Passenger(name, CI);
            hashMap.put(CI, passenger);
            bTree.insert(CI);
        }

        long start = System.nanoTime();
        bTree.JsonCreationAndFill(hashMap);
        long end = System.nanoTime();
        long result = end - start;

        System.out.println(result); //TODO: Delete the line finished the demonstration
    }

}

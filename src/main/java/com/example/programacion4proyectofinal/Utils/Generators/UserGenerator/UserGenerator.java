package com.example.programacion4proyectofinal.Utils.Generators.UserGenerator;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;

import java.util.HashMap;

/**
 * This class is used to create the initial user quantity
 */
public class UserGenerator {

    /**
     * Method that will perform the first generation of data within the b-tree
     *
     * @param bTree the respective b-tree
     * @param quantityOfUsers the number of users to initialize
     */
    public static void generateUsers(BTree<Integer> bTree, int quantityOfUsers){
        String name;
        String lastName;
        int CI;
        String country;
        Category category;
        DataGenerator generator = new DataGenerator();
        HashMap<Integer, Passenger> hashMap = new HashMap<>();

        for (int i = 0; i < quantityOfUsers; i++){
            name = generator.generateName();
            lastName = generator.generateLasName();
            CI = generator.generateCI();
            country = generator.generateCountry();
            category = generator.generateCategory();
            Passenger passenger = new Passenger(CI, name, lastName, country, category);
            if (bTree.insertKey(CI)){
                hashMap.put(CI, passenger);
            }
        }

        long start = System.nanoTime();
        FileHandlerBTree.createAndFillJson(bTree, hashMap);
        long end = System.nanoTime();
        long result = end - start;

        System.out.println(result); //TODO: Delete the line finished the demonstration
    }

}

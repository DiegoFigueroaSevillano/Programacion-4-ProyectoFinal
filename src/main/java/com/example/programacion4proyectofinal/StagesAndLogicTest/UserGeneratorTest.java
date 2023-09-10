package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.Generators.UserGenerator.DataGenerator;
import com.example.programacion4proyectofinal.Utils.Generators.UserGenerator.UserGenerator;

import java.util.HashMap;

public class UserGeneratorTest {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        BTree<Integer> bTree = new BTree<>(100);
        HashMap<Integer, Passenger> hashMap = new HashMap<>();

        UserGenerator.generateUsers(bTree, 1000000, hashMap);
    }

}

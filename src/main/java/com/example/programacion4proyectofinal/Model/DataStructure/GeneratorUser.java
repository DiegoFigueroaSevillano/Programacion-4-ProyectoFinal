package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.DataStructure.GeneradorDeNombreYCI.Generator;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GeneratorUser<T extends Comparable<T>> {


    public static void main(String[] args) {

        BTree<Integer> bTree = new BTree<>(200);
        String name;
        int CI;
        Generator generator = new Generator();
        HashMap<Integer, Passenger> hashMap = new HashMap<>();

        for (int i = 0; i < 1000000; i++){
            name = generator.generateName();
            CI = generator.generateCI();
            Passenger passenger = new Passenger(name, CI);
            hashMap.put(CI, passenger);
            bTree.insert(CI);
        }

        long start = System.nanoTime();
        bTree.JsonCreationAndFill(hashMap);
        bTree.printBTree();
        long end = System.nanoTime();
        long result = end - start;

        System.out.println(result);

    }

}

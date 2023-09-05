package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.UserGenerator.DataGenerator;
import com.example.programacion4proyectofinal.Utils.UserGenerator.UserGenerator;

public class UserGeneratorTest {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        BTree<Integer> bTree = new BTree<>(3);

        UserGenerator.generateUsers(bTree, 30);
    }

}

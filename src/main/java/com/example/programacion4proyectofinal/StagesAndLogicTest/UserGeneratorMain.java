package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.UserGenerator.UserGenerator;

public class UserGeneratorMain {
    public static void main(String[] args) {

        BTree<Integer> bTree = new BTree<>(3);

        UserGenerator.generateUsers(bTree, 10);
        bTree.printBTree();
    }
}

package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(3);
        // Inserting data
        long startTime = System.nanoTime();
        for (int i = 1; i <= 1000000; i++) {
            bTree.insert(i);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;

        System.out.printf("Time execution (Insertion): %.4f seconds%n", durationInSeconds);
        //bTree.printBTree();

        // Searching data
        System.out.println(bTree.search(bTree.getRoot(), 9));
        System.out.println(bTree.search(bTree.getRoot(), 4));
        System.out.println(bTree.search(bTree.getRoot(), 1));
        System.out.println(bTree.search(bTree.getRoot(), 5));

        // Updating data
        System.out.println(bTree.update(999989, 10000000));

        // Deleting data
        long startTime2 = System.nanoTime();
        for (int i = 1; i <= 999980; i++) {
            bTree.remove(i);
        }
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;

        double durationInSeconds2 = (double)duration2 / 1_000_000_000.0;
        System.out.printf("Time execution: %.4f seconds%n", durationInSeconds2);

        bTree.printBTree();
    }
}

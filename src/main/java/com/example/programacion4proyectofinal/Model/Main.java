package com.example.programacion4proyectofinal.Model;
public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(5);
        // Inserting data
        long startTime = System.nanoTime();
        for (int i = 1; i < 10; i++) {
            bTree.insert((int) (Math.random() * 10));
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;

        System.out.printf("Time execution: %.4f seconds%n", durationInSeconds);
        bTree.printBTree();

        // Searching data
        System.out.println(bTree.search(bTree.getRoot(), 9));
        System.out.println(bTree.search(bTree.getRoot(), 20));
        System.out.println(bTree.search(bTree.getRoot(), 1));
        System.out.println(bTree.search(bTree.getRoot(), 5));

        // Updating data
        System.out.println(bTree.update(1, 10000));
        bTree.printBTree();
    }
}

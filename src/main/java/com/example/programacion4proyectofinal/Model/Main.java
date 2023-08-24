package com.example.programacion4proyectofinal.Model;
public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(2);
        // Inserting data
        long startTime = System.nanoTime();
        for (int i = 1; i < 10; i++) {
            bTree.insert(i);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;

        System.out.printf("Time execution: %.4f seconds%n", durationInSeconds);
        bTree.printBTree();

        // Searching data
        System.out.println(bTree.search(bTree.getRoot(), 9));
        System.out.println(bTree.search(bTree.getRoot(), 4));
        System.out.println(bTree.search(bTree.getRoot(), 1));
        System.out.println(bTree.search(bTree.getRoot(), 5));

        // Updating data
        //System.out.println(bTree.update(30, 10000));
        bTree.printBTree();

        System.out.println(bTree.remove(1));
        System.out.println(bTree.remove(9));
        System.out.println(bTree.remove(3));
        System.out.println(bTree.remove(5));
        System.out.println(bTree.remove(6));
        System.out.println(bTree.remove(7));
        System.out.println(bTree.remove(8));
       /* System.out.println(bTree.remove(4));
        System.out.println(bTree.remove(2));*/
        bTree.printBTree();
    }
}

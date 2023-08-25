package com.example.programacion4proyectofinal.Model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Utils utilsComparator = new Utils();
        Comparator<Client> customComparator = new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return utilsComparator.compareClient(o1, o2);
            }
        };

        // Instantiate a PriorityQueue with the custom Comparator
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(customComparator);

        // Add elements to the PriorityQueue
        priorityQueue.addElement(new Client("A", "A1", "252", 500, PRIORITY.VIP));
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("C", "B1", "252", 50, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        priorityQueue.poll();
        // Remove elements from the PriorityQueue
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.peek().tiempoReserva);
            System.out.println(priorityQueue.poll().tiempoReserva);
            System.out.println("status : " + priorityQueue);
        }
    }

}

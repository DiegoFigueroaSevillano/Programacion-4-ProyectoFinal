package com.example.programacion4proyectofinal.Model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // Create a custom Comparator that orders integers in descending order
        Comparator<Client> customComparator = new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                if (o2.prioridad == o1.prioridad) {
                    return o2.tiempoReserva - o1.tiempoReserva;
                }
                return o2.prioridad - o1.prioridad;
                //return o1 - o2;
            }
        };

        // Instantiate a PriorityQueue with the custom Comparator
        PriorityQueue<Client> priorityQueue = new PriorityQueue<>(customComparator);

        // Add elements to the PriorityQueue
        priorityQueue.add(new Client("A", "A1", "252", 500, 2));
        priorityQueue.add(new Client("B", "B1", "252", 10, 1));
        priorityQueue.add(new Client("B2", "B2", "252", 1500, 3));
        priorityQueue.add(new Client("C", "B1", "252", 50, 3));
        priorityQueue.add(new Client("D", "B1", "252", 10, 3));

        // Remove elements from the PriorityQueue
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.peek().tiempoReserva);
            System.out.println(priorityQueue.poll().tiempoReserva);
            System.out.println("status : "+ priorityQueue);
        }
    }
}

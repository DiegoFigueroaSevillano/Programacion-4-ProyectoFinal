package com.example.programacion4proyectofinal.Model;

import com.example.programacion4proyectofinal.Model.Person.Passenger;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * This class is used for priority queue's functions.
 * @param <T>
 */
public class PriorityQueueManager<T>{
    private PriorityQueue<T> priorityQueue;

    public PriorityQueueManager(Comparator<T> comparator) {
        this.priorityQueue = new PriorityQueue<>(comparator);
    }

    /**
     * This method is used for add an element.
     * @param element, element that is going to be added.
     */
    public void addElement(T element){
        priorityQueue.add(element);

    }

    /**
     * This method is used for poll an element.
     * @return priority queue but without the element.
     */
    public T poll(){
        T result = priorityQueue.poll();
        return result;
    }
    /**
     * This method is used for peek an element.
     * @return the element
     */
    public T peek(){
        T aux = priorityQueue.peek();
        return aux;
    }

    /**
     * This method is for return the passengers' list.
     * @return Passengers' list
     */
    public List<T> getList(){
        List<T> clientList = priorityQueue.stream().collect(Collectors.toCollection(ArrayList::new));
        return clientList;
    }

    /**
     * This method is used when a client cancel a flight.
     * @param client, client whose flight is going to be canceled.
     */
    public void cancelFlight(T client){
        priorityQueue.remove(client);
    }

    /**
     * This method is used to verify the flight.
     * @param client, client whose flight is going to be verified.
     * @return
     */
    public boolean verifyFlight(Passenger client){
        return priorityQueue.contains(client);

    }
    /**
     * This method is used to verify if the priority queue is empty.
     */
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }


}

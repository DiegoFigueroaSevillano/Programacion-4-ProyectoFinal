package com.example.programacion4proyectofinal.Model;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class PriorityQueueManager<T>{
    private PriorityQueue<T> priorityQueue;

    public PriorityQueueManager(Comparator<T> comparator) {
        this.priorityQueue = new PriorityQueue<>(comparator);
    }
    public void addElement(T element){
        priorityQueue.add(element);

    }
    public T poll(){
        T result = priorityQueue.poll();
        return result;
    }
    public T peek(){
        T aux = priorityQueue.peek();
        return aux;
    }

    public List<T> getList(){
        List<T> clientList = priorityQueue.stream().collect(Collectors.toCollection(ArrayList::new));
        return clientList;
    }
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }


}

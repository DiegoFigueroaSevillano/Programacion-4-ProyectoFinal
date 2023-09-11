package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This is a class created for the management to the passengers into a flight
 * This flight order the passengers by de status of her buy, date time and category of the passenger
 */
public class FlightPriorityQueue {

    private int flightID;
    private PriorityQueue<UserFlightInfo> priorityQueue;

    /**
     * Constructor method that initialize the priorityQueue and fill it
     *
     * @param flightID the flight ID
     */
    public FlightPriorityQueue(int flightID){
        this.flightID = flightID;
        this.priorityQueue = new PriorityQueue<>();
        chargePriorityQueue();
    }

    /**
     * This method charge the priorityQueue with her respective data into his json
     */
    public void chargePriorityQueue(){
        try {
            List<UserFlightInfo> userFlightInfoList = UserFlightInfoOperations.getUserFlightInfosByFlightID(this.flightID);
            priorityQueue.addAll(userFlightInfoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method return us the priority queue
     *
     * @return priority queue
     */
    public PriorityQueue<UserFlightInfo> getPriorityQueue() {
        return priorityQueue;
    }

    /**
     * This method print the queue fot the test
     */
    public void printPriorityQueue(){
        PriorityQueue<UserFlightInfo> aux = new PriorityQueue<>();
        while (priorityQueue.peek() != null){
            UserFlightInfo userFlightInfo = priorityQueue.poll();
            System.out.println(userFlightInfo.getPassenger().getName() + " " + userFlightInfo.getStatus() + " " +
                    userFlightInfo.getPassenger().getCategory() + " " + userFlightInfo.getLocalDateTime());
            aux.add(userFlightInfo);
        }

    }
}

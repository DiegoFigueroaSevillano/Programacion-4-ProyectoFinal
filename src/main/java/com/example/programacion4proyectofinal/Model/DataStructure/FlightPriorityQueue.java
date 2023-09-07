package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

public class FlightPriorityQueue {

    public int flightID;
    public PriorityQueue<UserFlightInfo> priorityQueue;

    public FlightPriorityQueue(int flightID){
        this.flightID = flightID;
        this.priorityQueue = new PriorityQueue<>();
        chargePriorityQueue();
    }

    public void chargePriorityQueue(){
        try {
            List<UserFlightInfo> userFlightInfoList = UserFlightInfoOperations.getUserFlightInfosByFlightID(this.flightID);
            priorityQueue.addAll(userFlightInfoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(int userCI, int flightID){
        try {
            UserFlightInfoOperations.delete(userCI, flightID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        FlightPriorityQueue priorityQueue = new FlightPriorityQueue(1);
        while (priorityQueue.priorityQueue.peek() != null){
            UserFlightInfo userFlightInfo = priorityQueue.priorityQueue.poll();
            System.out.println(userFlightInfo.getPassenger().getName() + " " + userFlightInfo.getStatus() + " " +  userFlightInfo.getPassenger().getCategory() + " " + userFlightInfo.getLocalDateTime());
        }
    }

}

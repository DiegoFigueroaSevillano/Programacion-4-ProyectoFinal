package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Model.DataStructure.FlightPriorityQueue;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightJsonGenerator;

public class ThirdPartLogicTest {
    public static void main(String[] args) {

        int[] users = new int[50];
        for (int i = 0; i < users.length; i++){
            users[i] = i * 10;
        }

        int[] flight = new int[5];
        for (int i = 0; i < flight.length; i++){
            flight[i] = i;
        }

        UserFlightJsonGenerator.generateJson(40, flight, users);

        FlightPriorityQueue one = new FlightPriorityQueue(0);
        one.printPriorityQueue();
        System.out.println("---------------------------------------");
        FlightPriorityQueue two = new FlightPriorityQueue(1);
        two.printPriorityQueue();
        System.out.println("---------------------------------------");
        FlightPriorityQueue three = new FlightPriorityQueue(2);
        three.printPriorityQueue();
        System.out.println("---------------------------------------");

    }

}

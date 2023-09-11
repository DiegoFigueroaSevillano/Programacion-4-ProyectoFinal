package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;


import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This method was created for the generation of the first passenger into a respective flight
 */
public class UserFlightJsonGenerator {

    /**
     * This method was created for generate the Json with all data
     *
     * @param users the quantity of users
     * @param flights the list of flight
     */
    public static void generateJson(int[] flights, int[] users){
        int userCI;
        int flightID;
        Status status;
        int start = 0;
        int end = users.length / flights.length;
        int divisor = end;

        for (int i = 0; i < flights.length; i++){
            flightID = flights[i];
            System.out.println("index avion: " + i);
            for (int j = start; j < end; j++) {
                System.out.println("index: " + j);
                userCI = users[j];
                status = UserFlightDataGenerator.getRandomStatus();
                LocalDateTime localDateTime = UserFlightDataGenerator.getRandomDateTime();

                UserFlightInfo userFlightInfo = new UserFlightInfo(userCI, flightID, status, localDateTime);
                try {
                    UserFlightInfoOperations.insert(userFlightInfo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            start = end;
            end = end + divisor;
        }
    }
}

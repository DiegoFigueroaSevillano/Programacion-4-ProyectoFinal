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
     * @param quantity the quantity of data
     * @param flights the list of flight
     */
    public static void generateJson(int quantity, int[] flights, int[] users){
        int userCI;
        int flightID;
        Status status;

        for (int i = 0; i < quantity; i++){
            userCI = UserFlightDataGenerator.getRandomPassengerID(users);
            flightID = UserFlightDataGenerator.getRandomFlightID(flights);
            status = UserFlightDataGenerator.getRandomStatus();
            LocalDateTime localDateTime = UserFlightDataGenerator.getRandomDateTime();

            UserFlightInfo userFlightInfo = new UserFlightInfo(userCI, flightID, status, localDateTime);
            try {
                UserFlightInfoOperations.insert(userFlightInfo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

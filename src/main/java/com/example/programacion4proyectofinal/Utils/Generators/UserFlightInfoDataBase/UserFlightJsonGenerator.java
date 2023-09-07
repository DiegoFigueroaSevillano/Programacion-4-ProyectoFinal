package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;


import java.io.IOException;
import java.time.LocalDateTime;

public class UserFlightJsonGenerator {

    public static void generateJson(int quantity, int[] flights){
        int userCI;
        int flightID;
        Status status;

        for (int i = 0; i < quantity; i++){
            userCI = 1;
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

    public static void main(String[] args) {
        generateJson(30);
        try {
            System.out.println(UserFlightInfoOperations.getUserFlightInfosByFlightID(1).get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class was created for the obtain random values for the creation of new items
 */
public class UserFlightDataGenerator {

    /**
     * This method generate a random date time
     *
     * @return a random date time
     */
    public static LocalDateTime getRandomDateTime(){
        LocalDateTime minDateTime = LocalDateTime.now().minusMonths(1);
        LocalDateTime maxDateTime = LocalDateTime.now().plusMonths(1);
        long secondsBetween = Duration.between(minDateTime, maxDateTime).getSeconds();
        long randomSeconds = ThreadLocalRandom.current().nextLong(secondsBetween + 1);
        return minDateTime.plusSeconds(randomSeconds);
    }

    /**
     * This method take a random flight id
     *
     * @param flight the id of all flight into data base
     * @return a random id
     */
    public static int getRandomFlightID(int[] flight){
        int randomIndex = ThreadLocalRandom.current().nextInt(flight.length);
        return flight[randomIndex];
    }

    /**
     * This method take a random flight id
     *
     * @param passengers the id of all flight into data base
     * @return a random id
     */
    public static int getRandomPassengerID(int[] passengers){
        int randomIndex = ThreadLocalRandom.current().nextInt(passengers.length);
        return passengers[randomIndex];
    }

    /**
     * This method generate a random status of the buy
     *
     * @return a random status of the buy
     */
    public static Status getRandomStatus(){
        Status[] values = Status.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        return values[randomIndex];
    }

}

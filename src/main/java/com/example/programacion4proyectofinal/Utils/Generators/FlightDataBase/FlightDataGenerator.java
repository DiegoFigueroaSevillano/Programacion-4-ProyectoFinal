package com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase;


import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Model.Flight.Data.City;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class FlightDataGenerator {

    /**
     * This method generate a random date time
     *
     * @return a random date time
     */
    public static LocalDateTime getRandomDateTime(){
        LocalDateTime minDateTime = LocalDateTime.now();
        LocalDateTime maxDateTime = LocalDateTime.now().plusMonths(5);
        long secondsBetween = Duration.between(minDateTime, maxDateTime).getSeconds();
        long randomSeconds = ThreadLocalRandom.current().nextLong(secondsBetween + 1);
        return minDateTime.plusSeconds(randomSeconds);
    }

    /**
     * This method generate a random date time
     *
     * @return a random date time
     */
    public static LocalDateTime getRandomDateTime(LocalDateTime localDateTime){
        LocalDateTime minDateTime = localDateTime;
        LocalDateTime maxDateTime = minDateTime.plusHours(7);
        long secondsBetween = Duration.between(minDateTime, maxDateTime).getSeconds();
        long randomSeconds = ThreadLocalRandom.current().nextLong(secondsBetween + 1);
        return minDateTime.plusSeconds(randomSeconds);
    }

    /**
     * This method take a random flight id
     *
     * @return a random id
     */
    public static int createRandomFlightID(){
        int randomIndex = ThreadLocalRandom.current().nextInt(1000);
        return randomIndex;
    }

    /**
     * This method generate a random City
     *
     * @return a random City
     */
    public static City getRandomCity(){
        City[] values = City.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        return values[randomIndex];
    }

    /**
     * This method generate a random Airline
     *
     * @return a random Airline
     */
    public static Airline getRandomAirline(){
        Airline[] values = Airline.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        return values[randomIndex];
    }

    /**
     * This method generate a random City
     *
     * @return a random City
     */
    public static City getRandomCity(City eliminatedCity){
        City[] values = City.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        while (values[randomIndex].equals(eliminatedCity)){
            randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        }
        return values[randomIndex];
    }






}

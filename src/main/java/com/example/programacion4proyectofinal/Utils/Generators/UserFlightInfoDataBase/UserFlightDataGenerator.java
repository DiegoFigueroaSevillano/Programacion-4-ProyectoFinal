package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class UserFlightDataGenerator {


    public static LocalDateTime getRandomDateTime(){
        LocalDateTime minDateTime = LocalDateTime.now().minusMonths(1);
        LocalDateTime maxDateTime = LocalDateTime.now().plusMonths(1);
        long secondsBetween = Duration.between(minDateTime, maxDateTime).getSeconds();
        long randomSeconds = ThreadLocalRandom.current().nextLong(secondsBetween + 1);
        return minDateTime.plusSeconds(randomSeconds);
    }

    public static int getRandomFlightID(int[] flight){
        int randomIndex = ThreadLocalRandom.current().nextInt(flight.length);
        return flight[randomIndex];
    }

    public static Status getRandomStatus(){
        Status[] values = Status.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        return values[randomIndex];
    }

}

package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
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
     * This method generate a random status of the buy
     *
     * @return a random status of the buy
     */
    public static Status getRandomStatus(){
        Status[] values = Status.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        return values[randomIndex];
    }

    /**
     * This method takes a random ci for the hashmap
     *
     * @param map the hashmap with de data
     * @param size the quantity of passengers
     * @return the array of passengers in totally
     */
    public static int[] getRandomKeys(HashMap<Integer, Passenger> map, int size) {
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.shuffle(keys);
        int[] passengersList = new int[size];
        for (int i = 0; i < Math.min(size, keys.size()); i++) {
            passengersList[i] = keys.get(i);
        }
        return passengersList;
    }

}

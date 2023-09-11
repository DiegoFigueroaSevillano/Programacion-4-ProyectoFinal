package com.example.programacion4proyectofinal.Utils.Generators.UserGenerator;

import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Utils.UserData.Country;
import com.example.programacion4proyectofinal.Utils.UserData.LastName;
import com.example.programacion4proyectofinal.Utils.UserData.Name;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This method is used to create a random passager values
 */
public class DataGenerator {
    public Random random;

    /**
     * Constructor method to the data generator
     * We initialize the Random class here
     */
    public DataGenerator(){
        this.random = new Random();
    }

    /**
     * This method take a random name to the name enum
     *
     * @return a random name
     */
    public String generateName(){
        return Name.values()[random.nextInt(Name.values().length)].toString();
    }

    /**
     * This method take a random last name to the last name enum
     *
     * @return a random last name
     */
    public String generateLasName(){
        return LastName.values()[random.nextInt(LastName.values().length)].toString();
    }

    /**
     * This method take a random country to the country enum
     *
     * @return a random country
     */
    public String generateCountry(){
        return Country.values()[random.nextInt(Country.values().length)].toString();
    }

    /**
     * This method create a random CI
     *
     * @return a random CI
     */
    public int generateCI(){
        int CI = random.nextInt(9000000) + 1000000;
        return CI;
    }

    /**
     * This method generate a random category
     *
     * @return a random category
     */
    public Category generateCategory(){
        Category[] categories = Category.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(categories.length);
        return categories[randomIndex];
    }
}

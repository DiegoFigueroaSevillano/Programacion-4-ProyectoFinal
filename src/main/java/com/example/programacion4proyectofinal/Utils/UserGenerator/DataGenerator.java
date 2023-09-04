package com.example.programacion4proyectofinal.Utils.UserGenerator;

import com.example.programacion4proyectofinal.Utils.UserData.Country;
import com.example.programacion4proyectofinal.Utils.UserData.LastName;
import com.example.programacion4proyectofinal.Utils.UserData.Name;

import java.util.Random;

public class DataGenerator {
    public Random random;

    public DataGenerator(){
        this.random = new Random();
    }

    public String generateName(){
        return Name.values()[random.nextInt(Name.values().length)].toString();
    }

    public String generateLasName(){
        return LastName.values()[random.nextInt(LastName.values().length)].toString();
    }

    public String generateCountry(){
        return Country.values()[random.nextInt(Country.values().length)].toString();
    }

    public int generateCI(){
        int CI = random.nextInt(9000000) + 1000000;
        return CI;
    }
}

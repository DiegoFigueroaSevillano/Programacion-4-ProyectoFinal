package com.example.programacion4proyectofinal.Utils.PassengerDataGenerator;

import com.example.programacion4proyectofinal.Utils.PassengerDataGenerator.Values.LastName;
import com.example.programacion4proyectofinal.Utils.PassengerDataGenerator.Values.Name;

import java.util.Random;

public class Generator {

    public Random random;

    public Generator(){
        this.random = new Random();
    }

    public String generateName(){
        String name = Name.values()[random.nextInt(Name.values().length)].toString();
        String lastName = LastName.values()[random.nextInt(LastName.values().length)].toString();
        return name + " " + lastName;
    }

    public int generateCI(){
        int CI = random.nextInt(9000000) + 1000000;
        return CI;
    }

    public static void main(String[] args) {
        Generator generator = new Generator();

        System.out.println(generator.generateName() + " " + generator.generateCI());
    }
}

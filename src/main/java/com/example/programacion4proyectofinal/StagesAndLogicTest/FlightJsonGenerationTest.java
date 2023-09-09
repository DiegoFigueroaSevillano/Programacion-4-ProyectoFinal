package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;

import java.io.IOException;

public class FlightJsonGenerationTest {
    public static void main(String[] args) {
        //FlightJsonGenerator.generateJson(10);
        try {
            System.out.println(FlightJsonOperations.get(837).getAirline());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

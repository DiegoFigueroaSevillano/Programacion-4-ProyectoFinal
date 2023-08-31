package com.example.programacion4proyectofinal.Utils.UUIDGenerator;

/**
 * This class is used to generate a UUID for the user
 */
public class GeneratorUUID {
    /**
     * This method is used to generate a UUID for the user
     * @return a UUID
     */
    public static String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }
}
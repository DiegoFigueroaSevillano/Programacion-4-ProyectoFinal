package com.example.programacion4proyectofinal.Model.FileHandler.Writer;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

/**
 * This class writes the values of a passenger in a JSON file using a JsonGenerator.
 */
public class WriterPassenger {
    /**
     * This method will write the values of a passenger in a JSON file using a JsonGenerator.
     * @param generator JsonGenerator to write the values of the passenger.
     * @param passenger Passenger to write its values.
     * @throws IOException If there is an error writing the values.
     */
    public static void writePassenger(JsonGenerator generator, Passenger passenger) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", passenger.getId());
        generator.writeStringField("name", passenger.getName());
        generator.writeStringField("lastName", passenger.getLastName());
        generator.writeStringField("country", passenger.getCountry());
        generator.writeStringField("category", passenger.getCategory().name());
        generator.writeEndObject();
    }
}

package com.example.programacion4proyectofinal.Model.FileHandler;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public class WriterPassenger {
    public void writePassenger(JsonGenerator generator, Passenger passenger) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", passenger.getId());
        generator.writeStringField("name", passenger.getName());
        generator.writeStringField("lastName", passenger.getLastName());
        generator.writeStringField("country", passenger.getCountry());
        generator.writeStringField("category", passenger.getCategory().name());
        generator.writeEndObject();
    }
}

package com.example.programacion4proyectofinal.Model.FileHandler.Deserializer;

import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDeserializerUtils {
    public String[] deserializeArrayString(JsonNode node) {
        String[] array = new String[node.size()];
        for (int i = 0; i < node.size(); i++) {
            array[i] = node.get(i).asText();
        }
        return array;
    }

    public List<Passenger> deserializeArrayPassenger(JsonNode node, ObjectMapper mapper) throws IOException {
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < node.size(); i++) {
            int id = node.get(i).get("id").asInt();
            String name = node.get(i).get("name").asText();
            String lastName = node.get(i).get("lastName").asText();
            String country = node.get(i).get("country").asText();
            Category category = mapper.readValue(node.get("category").traverse(), Category.class);
            Passenger passenger = new Passenger(name, lastName, country, id, category);
            passengers.add(passenger);
        }
        return passengers;
    }
}

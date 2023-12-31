package com.example.programacion4proyectofinal.Utils;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

/**
 * Utility class for parsing JSON data and converting it into Java objects.
 */
public class ParserJson {

    /**
     * Converts a JSON file into a Node object.
     *
     * @param filename The name of the JSON file to be converted.
     * @return A Node object representing the parsed JSON data, or null if the filename is null or invalid.
     */
    public Node convertJsonToNode(String filename) {
        Node node = null;

        if (filename != null && !filename.equals("null")) {
            String path = "src/main/resources/com/example/programacion4proyectofinal/JSON/Users/" + filename + ".json";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(new File(path));
                String id = jsonNode.get("id").asText();
                int keysNumber = jsonNode.get("keysNumber").asInt();
                boolean isLeaf = jsonNode.get("isLeaf").asBoolean();
                int order = jsonNode.get("order").asInt();
                ArrayList<Passenger> keys = convertJsonArrayToListOfPassengers(jsonNode.get("keys"));
                ArrayList<String> children = convertJsonArrayToListOfString(jsonNode.get("childrenIds"));
                node = new Node(id, keysNumber, isLeaf, order, keys, children);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return node;
    }

    /**
     * Converts a JSON array of passengers into an ArrayList of Passenger objects.
     *
     * @param node The JSON node containing an array of passengers.
     * @return An ArrayList of Passenger objects parsed from the JSON data.
     */
    private ArrayList<Passenger> convertJsonArrayToListOfPassengers(JsonNode node) {
        ArrayList<Passenger> keys = new ArrayList<>();
        for (JsonNode currentPassenger : node) {
            int id = currentPassenger.get("id").asInt();
            String name = currentPassenger.get("name").asText();
            String lastName = currentPassenger.get("lastName").asText();
            String country = currentPassenger.get("country").asText();
            Category category = Category.valueOf(currentPassenger.get("category").asText());
            keys.add(new Passenger(id, name, lastName, country, category));
        }
        return keys;
    }

    /**
     * Converts a JSON array of strings into an ArrayList of strings.
     *
     * @param node The JSON node containing an array of strings.
     * @return An ArrayList of strings parsed from the JSON data.
     */
    private ArrayList<String> convertJsonArrayToListOfString(JsonNode node) {
        ArrayList<String> list = new ArrayList<>();
        for (int index = 0; index < node.size(); index++) {
            list.add(String.valueOf(node.get(index).asText()));
        }
        return list;
    }
}
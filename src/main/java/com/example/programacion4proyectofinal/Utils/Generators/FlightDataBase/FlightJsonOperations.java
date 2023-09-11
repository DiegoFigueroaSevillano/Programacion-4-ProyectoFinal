package com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase;

import com.example.programacion4proyectofinal.Model.Flight.Data.Airline;
import com.example.programacion4proyectofinal.Model.Flight.Data.City;
import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class FlightJsonOperations {

    public final static String path = "src/main/resources/com/example/programacion4proyectofinal/JSON/Flight/Flight.json";

    public static ArrayList<Flight> flights;
    /**
     * This method obtain one flight to the database
     *
     * @param flightID the flight id
     * @return all the user for it id
     */
    public static Flight get(int flightID) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File(path);
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);

        for (JsonNode node : arrayNode) {
            if (node.get("idFlight").asInt() == flightID) {
                City origin = City.valueOf(node.get("origin").asText());
                City destination = City.valueOf(node.get("destination").asText());
                Airline airline = Airline.valueOf(node.get("airline").asText());
                LocalDateTime departureDate = objectMapper.convertValue(node.get("departureDate"), LocalDateTime.class);
                LocalDateTime arrivalDate = objectMapper.convertValue(node.get("arrivalDate"), LocalDateTime.class);
                int cost = node.get("cost").asInt();
                return new Flight(flightID, origin, destination, airline, departureDate, arrivalDate, cost);
            }
        }
        return null;
    }

    /**
     * Retrieves a list of Flight objects from a JSON file.
     *
     * @return An ArrayList containing Flight objects.
     * @throws IOException If an I/O error occurs while reading the JSON file.
     */

    public static ArrayList<Flight> getFlight() throws IOException {
        flights = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File(path);
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);

        for (JsonNode node : arrayNode) {
            int flightId = node.get("idFlight").asInt();

            City origin = City.valueOf(node.get("origin").asText());
            City destination = City.valueOf(node.get("destination").asText());
            Airline airline = Airline.valueOf(node.get("airline").asText());
            LocalDateTime departureDate = objectMapper.convertValue(node.get("departureDate"), LocalDateTime.class);
            LocalDateTime arrivalDate = objectMapper.convertValue(node.get("arrivalDate"), LocalDateTime.class);
            int cost = node.get("cost").asInt();
            flights.add(new Flight(flightId, origin, destination, airline, departureDate, arrivalDate, cost));

        }
        return flights;
    }
    /**
     * This method obtain all the id of the flight into their database
     *
     * @return all the user for it id
     */
    public static int[] getAllIDs() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File(path);
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
        int[] flightIDs = new int[arrayNode.size()];
        int index = 0;

        for (JsonNode node : arrayNode) {
            int flightID = node.get("idFlight").asInt();
            flightIDs[index] = flightID;
            index++;
        }
        return flightIDs;
    }

    /**
     * This method insert new value into their respective json
     *
     * @param flight a new value
     */
    public static void insert(Flight flight) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ObjectNode nodeObject = objectMapper.createObjectNode();
        nodeObject.put("idFlight", flight.getIdFlight());
        nodeObject.put("origin", flight.getOrigin().toString());
        nodeObject.put("destination", flight.getDestination().toString());
        nodeObject.put("airline", flight.getAirline().toString());
        nodeObject.putPOJO("departureDate", flight.getDepartureDate());
        nodeObject.putPOJO("arrivalDate", flight.getArrivalDate());
        nodeObject.put("cost", flight.getCostOfTheFlight());
        File file = new File(path);
        ArrayNode arrayNode;
        if (file.exists()) {
            arrayNode = (ArrayNode) objectMapper.readTree(file);
        } else {
            arrayNode = objectMapper.createArrayNode();
        }
        arrayNode.add(nodeObject);
        objectMapper.writeValue(file, arrayNode);
    }

    /**
     * This method delete a value for the database
     *
     * @param flightID the flight ID
     */
    public static void delete(int flightID) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File(path);
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
        boolean isRemoved = false;
        Iterator<JsonNode> iterator = arrayNode.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            if (node.get("idFlight").asInt() == flightID) {
                iterator.remove();
                isRemoved = true;
                break;
            }
        }
        if (isRemoved) {
            objectMapper.writeValue(file, arrayNode);
        }
    }
}

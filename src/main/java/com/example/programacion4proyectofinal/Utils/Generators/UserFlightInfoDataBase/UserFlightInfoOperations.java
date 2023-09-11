package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * This method was created for the management of the flight indo database
 */
public class UserFlightInfoOperations {

    /**
     * This method obtain all the user for one flight
     *
     * @param flightID the flight id
     * @return all the user for it id
     */
    public static List<UserFlightInfo> getUserFlightInfosByFlightID(int flightID) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("src/main/resources/com/example/programacion4proyectofinal/JSON/" +
                "Flight/UserFlightInfo/UserFlightInfo.json");
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);

        List<UserFlightInfo> result = new ArrayList<>();
        for (JsonNode node : arrayNode) {
            if (node.get("flightID").asInt() == flightID) {
                int userCI = node.get("userCI").asInt();
                Status status = Status.valueOf(node.get("status").asText());
                LocalDateTime localDateTime = objectMapper.convertValue(node.get("localDateTime"), LocalDateTime.class);
                UserFlightInfo userFlightInfo = new UserFlightInfo(userCI, flightID, status, localDateTime);
                if (userFlightInfo.getPassenger() != null) {
                    result.add(userFlightInfo);
                }
            }
        }
        return result;
    }


  /**
     * This method obtain all the user for one flight
     *
     * @param userID the flight id
     * @return all the user for it id
     */
    public static List<Flight> getAllFlightOfTheUser(int userID) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("src/main/resources/com/example/programacion4proyectofinal/JSON/" +
                "Flight/UserFlightInfo/UserFlightInfo.json");
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);

        List<Flight> result = new ArrayList<>();
        for (JsonNode node : arrayNode) {
            if (node.get("userCI").asInt() == userID) {
                Flight flight = FlightJsonOperations.get(node.get("flightID").asInt());
                result.add(flight);
            }
        }
        return result;
    }


    /**
     * This method insert new value into their respective json
     *
     * @param userFlightInfo a new value
     */
    public static void insert(UserFlightInfo userFlightInfo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ObjectNode nodeObject = objectMapper.createObjectNode();
        nodeObject.put("userCI", userFlightInfo.getPassenger().getId());
        nodeObject.put("flightID", userFlightInfo.getFlightID());
        nodeObject.put("status", userFlightInfo.getStatus().toString());
        nodeObject.putPOJO("localDateTime", userFlightInfo.getLocalDateTime());
        File file = new File("src/main/resources/com/example/programacion4proyectofinal/JSON/" +
                "Flight/UserFlightInfo/UserFlightInfo.json");
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
     * @param userCI the user to be eliminated
     * @param flightID the flight ID
     */
    public static void delete(int userCI, int flightID) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("src/main/resources/com/example/programacion4proyectofinal/JSON/" +
                "Flight/UserFlightInfo/UserFlightInfo.json");
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
        boolean isRemoved = false;
        Iterator<JsonNode> iterator = arrayNode.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            if (node.get("userCI").asInt() == userCI && node.get("flightID").asInt() == flightID) {
                iterator.remove();
                isRemoved = true;
                break;
            }
        }
        if (isRemoved) {
            objectMapper.writeValue(file, arrayNode);
        }
    }

    /**
     * This method delete all users whit a CI in the data base
     *
     * @param userCI the user to be eliminated
     */
    public static void deleteAll(int userCI) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("src/main/resources/com/example/programacion4proyectofinal/JSON/" +
                "Flight/UserFlightInfo/UserFlightInfo.json");
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
        boolean isRemoved = false;
        Iterator<JsonNode> iterator = arrayNode.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            if (node.get("userCI").asInt() == userCI) {
                iterator.remove();
                isRemoved = true;
            }
        }
        if (isRemoved) {
            objectMapper.writeValue(file, arrayNode);
        }
    }

    /**
     * This method change the status of a passenger in their respective flight
     *
     * @param userCI the user CI
     * @param flightID the flight ID
     * @param newStatus the new status
     */
    public static void changeStatus(int userCI, int flightID, Status newStatus) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("src/main/resources/com/example/programacion4proyectofinal/JSON/" +
                "Flight/UserFlightInfo/UserFlightInfo.json");
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
        boolean isChanged = false;
        for (JsonNode node : arrayNode) {
            if (node.get("userCI").asInt() == userCI && node.get("flightID").asInt() == flightID) {
                ((ObjectNode) node).put("status", newStatus.toString());
                isChanged = true;
            }
        }
        if (isChanged) {
            objectMapper.writeValue(file, arrayNode);
        }
    }
}

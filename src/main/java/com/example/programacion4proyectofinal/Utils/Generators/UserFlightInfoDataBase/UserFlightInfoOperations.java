package com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase;

import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.fasterxml.jackson.core.JsonGenerator;
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
import java.util.stream.Collectors;

public class UserFlightInfoOperations {

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
                result.add(userFlightInfo);
            }
        }
        return result;
    }

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


}

package com.example.programacion4proyectofinal.Model.FileHandler.Deserializer;

import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class DeserializerNode extends JsonDeserializer<Node<Passenger>> {
    private final JsonDeserializerUtils jsonDeserializerUtils = new JsonDeserializerUtils();

    @Override
    public Node<Passenger> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = p.getCodec().readTree(p);
        String id = node.get("id").asText();
        int keysNumber = node.get("keysNumber").asInt();
        boolean isLeaf = node.get("isLeaf").asBoolean();
        int order = node.get("order").asInt();
        List<Passenger> keys = jsonDeserializerUtils.deserializeArrayPassenger(node.get("keys"), mapper);
        String[] childrenIds = jsonDeserializerUtils.deserializeArrayString(node.get("childrenIds"));
        Node<Passenger> newNode = new Node<>(order);
        newNode.setId(id);
        newNode.setKeysNumber(keysNumber);
        newNode.setLeaf(isLeaf);
        for (int i = 0; i < keys.size(); i++) {
            newNode.getKeys()[i] = keys.get(i);
        }
        newNode.setChildrenIds(childrenIds);
        return newNode;
    }
}

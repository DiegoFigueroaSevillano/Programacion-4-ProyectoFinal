package com.example.programacion4proyectofinal.Model.FileHandler.Writer;

import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public class WriterNode {
    private static WriterNode instance;
    private WriterPassenger writerPassenger;

    public static WriterNode getInstance() {
        if (instance == null) instance = new WriterNode();
        return instance;
    }

    public void writeNode(JsonGenerator generator, Node<Passenger> node) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("id", node.getId());
        generator.writeNumberField("keysNumber", node.getKeysNumber());
        generator.writeBooleanField("isLeaf", node.isLeaf());
        generator.writeNumberField("order", node.getDegree());
        generator.writeFieldName("keys");
        generator.writeStartArray();
        for (int i = 0; i < node.getKeysNumber(); i++) {
            writerPassenger.writePassenger(generator, node.getKeys()[i]);
        }
        generator.writeEndArray();
        generator.writeFieldName("childrenIds");
        generator.writeStartArray();
        for (String id : node.getChildrenIds()) {
            generator.writeString(id);
        }
        generator.writeEndArray();
        generator.writeEndObject();
        generator.close();
    }
}

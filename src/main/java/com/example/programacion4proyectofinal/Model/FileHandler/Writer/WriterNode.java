package com.example.programacion4proyectofinal.Model.FileHandler.Writer;

import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

/**
 * This class writes the values of a node in a JSON file using a JsonGenerator.
 */
public class WriterNode {
    private static WriterNode instance;

    /**
     * This is the singleton constructor of the class.
     * @return The instance of the class.
     */
    public static WriterNode getInstance() {
        if (instance == null) instance = new WriterNode();
        return instance;
    }

    /**
     * This method will write the values of a node in a JSON file using a JsonGenerator.
     * @param generator JsonGenerator to write the values of the node.
     * @param node Node to write its values.
     * @throws IOException If there is an error writing the values.
     */
    public void writeNode(JsonGenerator generator, Node<Passenger> node) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("id", node.getId());
        generator.writeNumberField("keysNumber", node.getKeysNumber());
        generator.writeBooleanField("isLeaf", node.isLeaf());
        generator.writeNumberField("order", node.getDegree());
        generator.writeFieldName("keys");
        generator.writeStartArray();
        for (int i = 0; i < node.getKeysNumber(); i++) {
            WriterPassenger.writePassenger(generator, node.getKey(i));
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

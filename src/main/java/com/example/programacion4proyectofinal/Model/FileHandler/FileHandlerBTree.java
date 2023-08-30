package com.example.programacion4proyectofinal.Model.FileHandler;

import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;

public class FileHandlerBTree implements IFileHandlerBTree<Passenger> {
    private static final String PATH_USERS = "src/main/resources/com/example/programacion4proyectofinal/Users";
    private final JsonFactory jsonFactory;

    public FileHandlerBTree() {
        jsonFactory = new JsonFactory();
    }

    @Override
    public boolean saveNode(Node<Passenger> node) {
        String fileName = getPathName(node.getId());
        FileOutputStream fileOutputStream;
        JsonGenerator jsonGenerator;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            jsonGenerator = jsonFactory.createGenerator(fileOutputStream);
            jsonGenerator.useDefaultPrettyPrinter();
            WriterNode.getInstance().writeNode(jsonGenerator, node);
        } catch (Exception e) {
            System.out.println("Error to save the node" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Node<Passenger> readNodeById(String id) {
        String fileName = getPathName(id);
        File file = new File(fileName);
        Node<Passenger> node = null;
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                node = objectMapper.readValue(file, Node.class);
            } catch (Exception e) {
                System.out.println("Error to read the node" + e.getMessage());
            }
        }
        return node;
    }

    @Override
    public boolean deleteNode(Node<Passenger> node) {
        String fileName = getPathName(node.getId());
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    private String getPathName(String fileName) {
        return PATH_USERS + "/" + fileName + ".json";
    }
}

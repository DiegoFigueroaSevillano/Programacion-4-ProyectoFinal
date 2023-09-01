package com.example.programacion4proyectofinal.Model.FileHandler;

import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.FileHandler.Writer.WriterNode;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;

/**
 * This class implements the interface IFileHandlerBTree and is responsible for saving, reading and deleting nodes.
 */
public class FileHandlerBTree implements IFileHandlerBTree<Passenger> {
    /**
     * The path where the nodes will be saved.
     */
    private static final String PATH_USERS = "src/main/resources/com/example/programacion4proyectofinal/Users";
    private final JsonFactory jsonFactory;

    /**
     * Constructor of the class where the jsonFactory is initialized.
     */
    public FileHandlerBTree() {
        jsonFactory = new JsonFactory();
    }

    /**
     * Save a node in the secondary memory of the system in json format.
     * @param node Node to save. It must be a node of the BTree.
     * @return The result of the operation.
     */
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

    /**
     * Reads a node from the secondary memory of the system that was saved in json format.
     * @param id of the node to read.
     * @return The node found.
     */
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

    /**
     * Deletes a node from the secondary memory of the system that was saved in json format.
     * @param node Node to delete.
     * @return The result of the operation.
     */
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

    /**
     * This method returns the path where the nodes will be saved.
     * @param fileName Name of the file to save.
     * @return The path where the nodes will be saved.
     */
    private String getPathName(String fileName) {
        return PATH_USERS + "/" + fileName + ".json";
    }
}

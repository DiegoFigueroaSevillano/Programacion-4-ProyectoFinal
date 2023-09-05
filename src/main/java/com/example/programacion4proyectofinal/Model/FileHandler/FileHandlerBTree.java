package com.example.programacion4proyectofinal.Model.FileHandler;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.FileHandler.Writer.WriterNode;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.DataOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * This class implements the interface IFileHandlerBTree and is responsible for saving, reading and deleting nodes.
 */
public class FileHandlerBTree implements IFileHandlerBTree<Passenger> {
    /**
     * The path where the nodes will be saved.
     */
    private static final String PATH_USERS = "src/main/resources/com/example/programacion4proyectofinal/JSON/Users";
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
        if (node == null) return false;
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

    /**
     * This method deletes the files of the nodes that are not root and have no children.
     * @param node Node to check. It must be a node of the BTree.
     */
    @Override
    public void deleteNonRootFilesIfChildrenNull(Node<Passenger> node) {
        String[] childrenIds = node.getChildrenIds();
        boolean allChildrenNull = Arrays.stream(childrenIds).allMatch(Objects::isNull);

        if (allChildrenNull) {
            File folder = new File(PATH_USERS);
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (!"root.json".equals(file.getName())) {
                        if (!file.delete()) {
                            System.out.println("Failed to delete " + file.getName());
                        }
                    }
                }
            }
        }
    }

    /**
     * This method was created for the creation of the first passengers
     *
     * @param bTree the completed bTree
     * @param hashMap the data list
     */
    public static void createAndFillJson(BTree bTree, HashMap<Integer, Passenger> hashMap){
        bTree.getRoot().setId("root");
        createAndFillJsonBFS(bTree.getRoot(), hashMap);
    }

    /**
     * This method was created for the generation of the first random users in the app
     *
     * @param root the root of the bTree
     * @param hashMap the hash map with our data
     */
    private static void createAndFillJsonBFS(Node root, HashMap<Integer, Passenger> hashMap) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node == null) {
                continue;
            }

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode nodeObject = mapper.createObjectNode();

            // Set node properties
            nodeObject.put("id", node.getId());
            nodeObject.put("keysNumber", node.getKeysNumber());
            nodeObject.put("isLeaf", node.isLeaf());
            nodeObject.put("order", node.getDegree());

            // Set keys
            ArrayNode keysArray = mapper.createArrayNode();
            for (int i = 0; i < node.getKeysNumber(); i++) {
                Passenger passenger = hashMap.get(node.getKeys()[i]);
                ObjectNode passengerObject = mapper.createObjectNode();
                passengerObject.put("id", passenger.getId());
                passengerObject.put("name", passenger.getName());
                passengerObject.put("lastName", passenger.getLastName());
                passengerObject.put("country", passenger.getCountry());
                passengerObject.put("category", passenger.getCategory().toString());
                keysArray.add(passengerObject);
            }
            nodeObject.set("keys", keysArray);

            // Set childrenIds
            ArrayNode childrenIdsArray = mapper.createArrayNode();
            for (int i = 0; i <= node.getKeysNumber(); i++) {
                if (node.getChildren()[i] != null) {
                    childrenIdsArray.add(node.getChildren()[i].getId());
                    queue.add(node.getChildren()[i]);
                } else {
                    childrenIdsArray.addNull();
                }
            }
            nodeObject.set("childrenIds", childrenIdsArray);

            // Write to file
            try {
                mapper.writeValue(
                        new File("src/main/resources/com/example/programacion4proyectofinal/JSON/Users/"
                                + node.getId() + ".json"), nodeObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

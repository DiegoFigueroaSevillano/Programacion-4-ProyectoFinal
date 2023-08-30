package com.example.programacion4proyectofinal.Model;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Utils.ParserJson;

import java.util.ArrayList;

/**
 * The Search class provides methods for searching nodes in a node data structure.
 */
public class Search {

    private final String PATH_ROOT = "src/main/resources/com/example/programacion4proyectofinal/JSON/Users/Node_1.json";
    private ParserJson parserJson;

    /**
     * Constructor to initialize a Search object.
     */
    public Search() {
        this.parserJson = new ParserJson();
    }

    /**
     * Searches for a node by its ID in the data structure.
     *
     * @param id The ID of the node to search for.
     * @return The node with the provided ID, or null if not found.
     */
    public Node searchById(int id) {
        return searchById(PATH_ROOT, id);
    }

    /**
     * Searches for a node by its ID in the data structure from a specific path.
     *
     * @param path The path to the JSON data.
     * @param id The ID of the node to search for.
     * @return The node with the provided ID, or null if not found.
     */
    private Node searchById(String path, int id) {
        Node passenger = null;

        ArrayList<Node> nodeList = parserJson.convertJsonToListOfNode(path);

        passenger = searchInTheNode(nodeList, id);

        if (passenger == null && !nodeList.isEmpty()) {
            if (id < nodeList.get(0).getId()) {
                if (nodeList.get(0).getPathLeftSon() != null) {
                    passenger = searchById(nodeList.get(0).getPathLeftSon(), id);
                }
            } else if (id > nodeList.get(nodeList.size() - 1).getId()) {
                if (nodeList.get(nodeList.size() - 1).getPathRightSon() != null) {
                    passenger = searchById(nodeList.get(nodeList.size() - 1).getPathRightSon(), id);
                }
            } else {
                for (int index = 1; index < nodeList.size(); index++) {
                    if (id > nodeList.get(index - 1).getId() && id < nodeList.get(index).getId()) {
                        if (nodeList.get(0).getPathLeftSon() != null) {
                            passenger = searchById(nodeList.get(index).getPathLeftSon(), id);
                        }
                    }
                }
            }
        }

        return passenger;
    }

    /**
     * Searches for a node by its ID in a set of nodes.
     *
     * @param nodes The list of nodes to search in.
     * @param id The ID of the node to search for.
     * @return The node with the provided ID, or null if not found.
     */
    private Node searchInTheNode(ArrayList<Node> nodes, int id) {
        Node node = null;
        for (int index = 0; index < nodes.size(); index++) {
            if (id == nodes.get(index).getId()){
                String name = nodes.get(index).getName();
                String leftSon = nodes.get(index).getPathLeftSon();
                String rightSon = nodes.get(index).getPathRightSon();
                node = new Node(name, id, leftSon, rightSon);
            }
        }
        return node;
    }

    /**
     * Searches for nodes by their name in the data structure.
     *
     * @param name The name of the node to search for.
     * @return A list of nodes with the provided name.
     */
    public ArrayList<Node> searchByName(String name) {
        ArrayList<Node> result = new ArrayList<>();
        searchByName(PATH_ROOT, name, result);
        return result;
    }

    /**
     * Searches for nodes by their name in the data structure from a specific path.
     *
     * @param path The path to the JSON data.
     * @param name The name of the node to search for.
     * @param result The list where found nodes will be added.
     */
    private void searchByName(String path, String name, ArrayList<Node> result) {
        ArrayList<Node> nodes = parserJson.convertJsonToListOfNode(path);

        searchNameInNode(name, result, nodes);

        if (!nodes.isEmpty()) {
            for (int index = 0; index < nodes.size(); index++) {
                if (index == nodes.size() - 1) {
                    searchByName(nodes.get(index).getPathLeftSon(), name, result);
                    searchByName(nodes.get(index).getPathRightSon(), name, result);
                } else {
                    searchByName(nodes.get(index).getPathLeftSon(), name, result);
                }
            }
        }
    }

    /**
     * Searches for the name of a node in a set of nodes and adds matches to the result list.
     *
     * @param name The name of the node to search for.
     * @param result The list where found nodes will be added.
     * @param nodes The list of nodes to search in.
     */
    private void searchNameInNode(String name, ArrayList<Node> result, ArrayList<Node> nodes) {
        String nameInLowerCase = name.toUpperCase();
        for (int index = 0; index < nodes.size(); index++) {
            if (nameInLowerCase.equals(nodes.get(index).getName())) {
                result.add(nodes.get(index));
            }
        }
    }
}
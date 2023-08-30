package com.example.programacion4proyectofinal.Model;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Utils.ParserJson;

import java.util.ArrayList;

public class Search {

    private final String PATH_ROOT = "src/main/resources/com/example/programacion4proyectofinal/JSON/Users/Node_1.json";
    private ParserJson parserJson;

    public Search() {
        this.parserJson = new ParserJson();
    }

    public Node searchById(int id) {
        return searchById(PATH_ROOT, id);
    }

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
}
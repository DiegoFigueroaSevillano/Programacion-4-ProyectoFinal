package com.example.programacion4proyectofinal.Utils;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

public class ParserJson {

    public ArrayList<Node> convertJsonToListOfNode(String path) {
        ArrayList<Node> result = new ArrayList<>();

        if (path != null && !path.equals("null")) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                JsonNode root = objectMapper.readTree(new File(path));

                for (JsonNode node : root) {
                    String name = node.get("name").asText();
                    int id = node.get("id").asInt();
                    String leftSon = node.get("leftSon").asText();
                    String rightSon = node.get("rightSon").asText();
                    Node auxiliarNode = new Node(name, id, leftSon, rightSon);
                    result.add(auxiliarNode);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return result;
    }

}

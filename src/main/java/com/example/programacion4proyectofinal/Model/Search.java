package com.example.programacion4proyectofinal.Model;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.ParserJson;

import java.util.ArrayList;
import java.util.Objects;

public class Search {

    private final String PATH_ROOT = "root";
    private ParserJson parserJson;

    public Search() {
        this.parserJson = new ParserJson();
    }

    public Passenger searchById(int id) {
        return searchById(PATH_ROOT, id);
    }

    private Passenger searchById(String path, int id) {
        Passenger passenger = null;
        Node node = parserJson.convertJsonToNode(path);
        if (node != null && (path != null)) {
            passenger = searchInTheNode(node.getKeys(), id);
            if (passenger == null && !node.getChildren().isEmpty()) {
                for (int index = 0; index < node.getChildren().size(); index++) {
                    if (node.getChildren().get(index) != null && !node.getChildren().get(index).equals("null")) {
                        passenger = searchById(node.getChildren().get(index), id);
                        if (passenger != null) {
                            break;
                        }
                    }
                }
            }
        }

        return passenger;
    }

    private Passenger searchInTheNode(ArrayList<Passenger> nodes, int id) {
        Passenger passenger = null;
        for (int index = 0; index < nodes.size(); index++) {
            if (id == nodes.get(index).getId()){
                String name = nodes.get(index).getName();
                String lastName = nodes.get(index).getLastName();
                String country = nodes.get(index).getCountry();
                Category category = nodes.get(index).getCategory();
                passenger = new Passenger(id, name, lastName, country, category);
            }
        }
        return passenger;
    }

    public ArrayList<Passenger> searchByName(String name) {
        ArrayList<Passenger> result = new ArrayList<>();
        searchByName(PATH_ROOT, name, result);
        return result;
    }

    private void searchByName(String path, String name, ArrayList<Passenger> result) {
        Node node = parserJson.convertJsonToNode(path);
        if (node != null) {
            ArrayList<Passenger> passengers = node.getKeys();
            ArrayList<String> children = node.getChildren();

            searchNameInNode(name, result, passengers);

            if (!passengers.isEmpty()) {
                for (int index = 0; index < passengers.size(); index++) {
                    if (index == passengers.size() - 1) {
                        searchByName(children.get(index), name, result);
                        searchByName(children.get(index + 1), name, result);
                    } else {
                        searchByName(children.get(index), name, result);
                    }
                }
            }
        }
    }

    private void searchNameInNode(String name, ArrayList<Passenger> result, ArrayList<Passenger> passengers) {
        String nameInLowerCase = name.toUpperCase();
        for (int index = 0; index < passengers.size(); index++) {
            String passengerName = passengers.get(index).getName().toUpperCase();
            String passengerLastName = passengers.get(index).getLastName().toUpperCase();
            if (nameInLowerCase.equals(passengerName) || nameInLowerCase.equals(passengerLastName)) {
                result.add(passengers.get(index));
            }
        }
    }
}
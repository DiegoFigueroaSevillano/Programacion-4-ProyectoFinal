package com.example.programacion4proyectofinal.Model;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.ParserJson;

import java.util.ArrayList;

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
        if (node != null && path != null) {
            System.out.println(path);
            System.out.println(id < node.getKeys().get(0).getId());
            System.out.println(id > node.getKeys().get(node.getKeys().size() - 1).getId());
            passenger = searchInTheNode(node.getKeys(), id);
            if (passenger == null && !node.getChildren().isEmpty()) {
                if (id < node.getKeys().get(0).getId()) {
                    passenger = searchById(node.getChildren().get(0), id);
                } else if (id > node.getKeys().get(node.getKeys().size() - 1).getId()) {
                    passenger = searchById(node.getChildren().get(node.getKeys().size()), id);
                } else {
                    for (int index = 0; index < node.getKeys().size(); index++) {
                        if (node.getKeys().get(index).getId() < id && node.getKeys().get(index + 1).getId() > id) {
                            passenger = searchById(node.getChildren().get(index + 1), id);
                            if (passenger != null) {
                                break;
                            }
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
                for (int index = 0; index <= passengers.size(); index++) {
                    searchByName(children.get(index), name, result);
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

    public ArrayList<Passenger> obtainAllPassengers() {
        return obtainAllPassengers(PATH_ROOT);
    }

    private ArrayList<Passenger> obtainAllPassengers(String path) {
        ArrayList<Passenger> result = new ArrayList<>();
        Node node = parserJson.convertJsonToNode(path);
        if (node != null && !node.getKeys().isEmpty()) {
            for (int index = 0; index < node.getChildren().size(); index++) {
                result.addAll(obtainAllPassengers(node.getChildren().get(index)));
            }
            result.addAll(node.getKeys());
        }
        return result;
    }
}
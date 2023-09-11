package com.example.programacion4proyectofinal.Model;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.ParserJson;

import java.util.concurrent.*;
import java.util.ArrayList;

/**
 * The Search class is responsible for searching and retrieving passenger information from a hierarchical data structure.
 */
public class Search {

    private static final String PATH_ROOT = "root";
    private ParserJson parserJson;

    /**
     * Initializes a new instance of the Search class.
     */
    public Search() {
        this.parserJson = new ParserJson();
    }

    /**
     * Searches for a passenger by their unique ID.
     *
     * @param id The ID of the passenger to search for.
     * @return The Passenger object if found, or null if not found.
     */
    public Passenger searchById(int id) {
        return searchById(PATH_ROOT, id);
    }

    /**
     * Searches for a passenger by their unique ID within a specified path.
     *
     * @param path The path within the hierarchical data structure to search.
     * @param id   The ID of the passenger to search for.
     * @return The Passenger object if found, or null if not found.
     */
    private Passenger searchById(String path, int id) {
        Passenger passenger = null;
        Node node = parserJson.convertJsonToNode(path);
        if (node != null && path != null) {
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

    /**
     * Searches for a passenger within a list of passengers.
     *
     * @param nodes The list of passengers to search within.
     * @param id    The ID of the passenger to search for.
     * @return The Passenger object if found, or null if not found.
     */
    private Passenger searchInTheNode(ArrayList<Passenger> nodes, int id) {
        Passenger passenger = null;
        for (int index = 0; index < nodes.size(); index++) {
            if (id == nodes.get(index).getId()) {
                String name = nodes.get(index).getName();
                String lastName = nodes.get(index).getLastName();
                String country = nodes.get(index).getCountry();
                Category category = nodes.get(index).getCategory();
                passenger = new Passenger(id, name, lastName, country, category);
            }
        }
        return passenger;
    }

    /**
     * Searches for passengers with the given name.
     *
     * @param name The name to search for.
     * @return An ArrayList of Passenger objects matching the name.
     */
    public ArrayList<Passenger> searchByName(String name) {
        return searchByName(PATH_ROOT, name);
    }

    /**
     * Searches for passengers with the given name within a specified path.
     *
     * @param path   The path within the hierarchical data structure to search.
     * @param name   The name to search for.
     */
    private ArrayList<Passenger> searchByName(String path, String name) {
        ArrayList<Passenger> result = new ArrayList<>();
        Node node = parserJson.convertJsonToNode(path);
        if (node != null) {
            ArrayList<Passenger> passengers = node.getKeys();
            ArrayList<String> children = node.getChildren();

            searchNameInNode(name, result, passengers);

            if (!children.isEmpty()) {
                for (int index = 0; index < children.size(); index++) {
                    result.addAll(searchByName(children.get(index), name));
                }
            }
        }
        return result;
    }

    /**
     * Searches for passenger names within a node and adds matching passengers to the result list.
     *
     * @param name       The name to search for.
     * @param result     An ArrayList to store the matching Passenger objects.
     * @param passengers The list of passengers within the node.
     */
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

    /**
     * Obtains a list of all passengers from the hierarchical data structure.
     *
     * @return An ArrayList containing all Passenger objects.
     * @throws InterruptedException When the thread is interrupted during execution.
     * @throws ExecutionException   When an execution exception occurs.
     */
    public ArrayList<Passenger> obtainAllPassengers() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Callable<ArrayList<Passenger>>> tasks = new ArrayList<>();
        ArrayList<Passenger> result = new ArrayList<>();
        Node node = parserJson.convertJsonToNode(PATH_ROOT);

        if (node != null && !node.getKeys().isEmpty()) {
            for (String childPath : node.getChildren()) {
                Callable<ArrayList<Passenger>> task = () -> obtainAllPassengers(childPath);
                tasks.add(task);
            }
            result.addAll(node.getKeys());
        }

        try {
            ArrayList<Future<ArrayList<Passenger>>> futures = (ArrayList<Future<ArrayList<Passenger>>>) executorService.invokeAll(tasks);

            for (Future<ArrayList<Passenger>> future : futures) {
                result.addAll(future.get());
            }
        } finally {
            executorService.shutdown();
        }

        return result;
    }

    /**
     * Obtains a list of all passengers from a specific path within the hierarchical data structure.
     *
     * @param path The path within the hierarchical data structure to start the search.
     * @return An ArrayList containing all Passenger objects within the specified path.
     */
    private ArrayList<Passenger> obtainAllPassengers(String path) {
        ArrayList<Passenger> result = new ArrayList<>();
        Node node = parserJson.convertJsonToNode(path);

        if (node != null && !node.getKeys().isEmpty()) {
            for (String childPath : node.getChildren()) {
                result.addAll(obtainAllPassengers(childPath));
            }
            result.addAll(node.getKeys());
        }

        return result;
    }
}
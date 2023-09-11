package com.example.programacion4proyectofinal.Model.Objects;

import com.example.programacion4proyectofinal.Model.FileHandler.Deserializer.DeserializerNode;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

/**
 * Represents a node in a hierarchical data structure. This class is used to store information about a node
 * in a tree-like structure, which includes an ID, key count, leaf status, order, keys, and children.
 */
@JsonDeserialize(using = DeserializerNode.class)
public class Node {

    private String id;
    private int keysNumbers;
    private boolean isLeaf;
    private int order;
    private ArrayList<Passenger> keys;
    private ArrayList<String> children;

    /**
     * Constructs a new Node object with the specified parameters.
     *
     * @param id          The unique identifier of the node.
     * @param keysNumbers The number of keys (data elements) in the node.
     * @param isLeaf      A boolean indicating whether the node is a leaf node.
     * @param order       The order of the node in the hierarchical structure.
     * @param keys        An ArrayList of Passenger objects representing the keys stored in the node.
     * @param children    An ArrayList of String IDs representing the children nodes.
     */
    public Node(String id, int keysNumbers, boolean isLeaf, int order, ArrayList<Passenger> keys, ArrayList<String> children) {
        this.id = id;
        this.keysNumbers = keysNumbers;
        this.isLeaf = isLeaf;
        this.order = order;
        this.keys = keys;
        this.children = children;
    }

    /**
     * Gets the unique identifier of the node.
     *
     * @return The ID of the node.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the node.
     *
     * @param id The ID to set for the node.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the number of keys (data elements) in the node.
     *
     * @return The number of keys in the node.
     */
    public int getKeysNumbers() {
        return keysNumbers;
    }

    /**
     * Sets the number of keys (data elements) in the node.
     *
     * @param keysNumbers The number of keys to set for the node.
     */
    public void setKeysNumbers(int keysNumbers) {
        this.keysNumbers = keysNumbers;
    }

    /**
     * Checks if the node is a leaf node.
     *
     * @return true if the node is a leaf node, false otherwise.
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Sets the leaf status of the node.
     *
     * @param leaf A boolean indicating whether the node is a leaf node.
     */
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    /**
     * Gets the order of the node in the hierarchical structure.
     *
     * @return The order of the node.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order of the node in the hierarchical structure.
     *
     * @param order The order to set for the node.
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the ArrayList of Passenger objects representing the keys stored in the node.
     *
     * @return An ArrayList of keys (Passenger objects) in the node.
     */
    public ArrayList<Passenger> getKeys() {
        return keys;
    }

    /**
     * Sets the keys (Passenger objects) for the node.
     *
     * @param keys An ArrayList of keys (Passenger objects) to set for the node.
     */
    public void setKeys(ArrayList<Passenger> keys) {
        this.keys = keys;
    }

    /**
     * Gets the ArrayList of String IDs representing the children nodes.
     *
     * @return An ArrayList of String IDs representing the children of the node.
     */
    public ArrayList<String> getChildren() {
        return children;
    }

    /**
     * Sets the children nodes for the node.
     *
     * @param children An ArrayList of String IDs representing the children nodes.
     */
    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }
}

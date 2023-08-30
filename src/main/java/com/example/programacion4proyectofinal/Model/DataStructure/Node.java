package com.example.programacion4proyectofinal.Model.DataStructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 * The Node class represents a node in a B-Tree.
 *
 * @param <T> The type of the keys that the node stores.
 */
public class Node<T extends Comparable<T>> {
    private final int degree;
    private int keysNumber;
    private final T[] keys;
    private final Node<T>[] children;
    private boolean isLeaf;
    private String jsonPath;

    /**
     * Constructs a new Node object with the given degree.
     *
     * @param degree degree The minimum degree for the B-tree node.
     */
    @SuppressWarnings("unchecked")
    public Node(int degree, Class<T> clazz) {
        this.degree = degree;
        this.keys = (T[]) Array.newInstance(clazz, 2 * this.degree - 1);
        this.children = (Node<T>[]) Array.newInstance(Node.class, 2 * this.degree);
        this.isLeaf = true;
        this.keysNumber = 0;
        this.jsonPath = null;
    }

    public Node(int degree) {
        ObjectMapper mapper = new ObjectMapper();
        this.degree = degree;
        this.keys = (T[]) new Comparable[2 * this.degree - 1];
        this.children = new Node[2 * this.degree];
        this.isLeaf = true;
        this.keysNumber = 0;
        this.jsonPath = null;
    }

    public Node(int degree, Class<T> clazz, String jsonPath) {
        this.degree = degree;
        this.keys = (T[]) Array.newInstance(clazz, 2 * this.degree - 1);
        this.children = (Node<T>[]) Array.newInstance(Node.class, 2 * this.degree);
        this.isLeaf = true;
        this.keysNumber = 0;
        this.jsonPath = jsonPath;
    }

    public String getJsonPath() {
        return jsonPath;
    }



    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    /**
     * Finds the position of a specific key within the node.
     *
     * @param key The key to search for.
     * @return The position index or -1 if not found.
     */
    public int findKeyPosition(T key) {
        for (int keyIndex = 0; keyIndex < keysNumber; keyIndex++) {
            if (keys[keyIndex].compareTo(key) == 0) {
                return keyIndex;
            }
        }
        return -1;
    }

    /**
     * Decrements the number of keys in the node.
     */
    public void decrementKeysNumber() {
        keysNumber -= 1;
    }

    /**
     * Increments the number of keys in the node.
     */
    public void incrementKeysNumber() {
        keysNumber += 1;
    }

    /**
     * Checks if the node is full.
     *
     * @return True if the node is full, false otherwise.
     */
    public boolean isFull() {
        return keysNumber == 2 * degree - 1;
    }

    /**
     * Gets the number of keys in the node.
     *
     * @return The number of keys.
     */
    public int getKeysNumber() {
        return keysNumber;
    }

    /**
     * Sets the number of keys in the node.
     *
     * @param keysNumber The new number of keys.
     */
    public void setKeysNumber(int keysNumber) {
        this.keysNumber = keysNumber;
    }

    /**
     * Gets the array of keys in the node.
     *
     * @return The keys array.
     */
    public T[] getKeys() {
        return keys;
    }

    /**
     * Gets the array of child nodes.
     *
     * @return The children array.
     */
    public Node<T>[] getChildren() {
        return children;
    }

    /**
     * Checks if the node is a leaf.
     *
     * @return True if the node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Sets whether the node is a leaf or not.
     *
     * @param leaf True if the node should be a leaf, false otherwise.
     */
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    /**
     * Gets the minimum degree of the node.
     *
     * @return The minimum degree.
     */
    public int getDegree() {
        return degree;
    }
}

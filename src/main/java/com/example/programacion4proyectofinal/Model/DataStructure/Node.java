package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.FileHandler.Deserializer.DeserializerNode;
import com.example.programacion4proyectofinal.Utils.UUIDGenerator.GeneratorUUID;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * The Node class represents a node in a B-Tree.
 *
 * @param <T> The type of the keys that the node stores.
 */
@JsonDeserialize(using = DeserializerNode.class)
public class Node<T extends Comparable<T>> {
    private final int degree;
    private int keysNumber;
    private final T[] keys;
    private final Node<T>[] children;
    private boolean isLeaf;
    private String id;
    private String[] childrenIds;

    /**
     * Constructs a new Node object with the given degree.
     *
     * @param degree degree The minimum degree for the B-tree node.
     */
    @SuppressWarnings(value = "unchecked")
    public Node(int degree) {
        if (degree <= 1) {
            throw new IllegalArgumentException("Order must be greater than 1");
        }
        this.degree = degree;
        this.keys = (T[]) new Comparable[2 * degree - 1];
        this.children = (Node<T>[]) new Node<?>[2 * degree];
        this.childrenIds = new String[2 * degree];
        this.isLeaf = true;
        this.keysNumber = 0;
        this.id = GeneratorUUID.generateUUID();
    }

    public String getId() {
        return id;
    }

    public void setChildrenIds(String[] childrenIds) {
        this.childrenIds = childrenIds;
    }

    public void setChildrenId(int index, String id) {
        this.childrenIds[index] = id;
    }

    public String[] getChildrenIds() {
        return childrenIds;
    }

    public void setId(String id) {
        this.id = id;
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

    public T getKey(final int index) {
        return keys[index];
    }

    /**
     * Gets the array of child nodes.
     *
     * @return The children array.
     */
    public Node<T>[] getChildren() {
        return children;
    }

    public void setKey(final int index, final T key) {
        this.keys[index] = key;
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

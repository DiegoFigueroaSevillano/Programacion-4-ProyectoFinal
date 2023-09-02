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

    /**
     * This method returns the unique id of the node.
     * @return The unique id of the node.
     */
    public String getId() {
        return id;
    }

    /**
     * This method sets the array of children ids of the node.
     * @param childrenIds The array of children ids of the node.
     */
    public void setChildrenIds(String[] childrenIds) {
        this.childrenIds = childrenIds;
    }

    /**
     * This method puts a children id in a specific index of the array of children ids of the node.
     * @param index The index of the array of children ids of the node.
     * @param id The children id to put in the array of children ids of the node.
     */
    public void setChildrenId(int index, String id) {
        this.childrenIds[index] = id;
    }

    /**
     * This method returns the array of children ids of the node.
     * @return The array of children ids of the node.
     */
    public String[] getChildrenIds() {
        return childrenIds;
    }

    /**
     * This method sets the unique id of the node.
     * @param id The unique id of the node.
     */
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

    public Node<T> getChild(final int index) {
        return children[index];
    }

    public void setChild(final int index, final Node<T> child) {
        children[index] = child;
        if (child != null) {
            childrenIds[index] = child.getId();
        }
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

    public void setIdChild(int i, String id) {
        childrenIds[i] = id;
    }

    public String getIdChild(int index) {
        return childrenIds[index];
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

    public int findKeyPositionInNode(Node<T> node, T targetKey) {
        int left = 0;
        int right = node.getKeysNumber() - 1;
        int middleIndex;
        int comparison;
        while (left <= right) {
            middleIndex = left + (right - left) / 2;
            comparison = targetKey.compareTo(node.getKey(middleIndex));
            if (comparison == 0) return middleIndex;
            else if (comparison < 0) right = middleIndex - 1;
            else left = middleIndex + 1;
        }
        return left;
    }
}

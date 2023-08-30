package com.example.programacion4proyectofinal.Model.Objects;

/**
 * The Node class represents a node in a tree data structure.
 */
public class Node {

    private String name;
    private int id;
    private String pathLeftSon;
    private String pathRightSon;

    /**
     * Constructor to create a Node object.
     *
     * @param name The name of the node.
     * @param id The unique ID of the node.
     * @param pathLeftSon The path to the left child node.
     * @param pathRightSon The path to the right child node.
     */
    public Node(String name, int id, String pathLeftSon, String pathRightSon) {
        this.name = name;
        this.id = id;
        this.pathLeftSon = pathLeftSon;
        this.pathRightSon = pathRightSon;
    }

    /**
     * Get the name of the node.
     *
     * @return The name of the node.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the node.
     *
     * @param name The new name for the node.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the ID of the node.
     *
     * @return The ID of the node.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the node.
     *
     * @param id The new ID for the node.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the path to the left child node.
     *
     * @return The path to the left child node.
     */
    public String getPathLeftSon() {
        return pathLeftSon;
    }

    /**
     * Set the path to the left child node.
     *
     * @param pathLeftSon The new path to the left child node.
     */
    public void setPathLeftSon(String pathLeftSon) {
        this.pathLeftSon = pathLeftSon;
    }

    /**
     * Get the path to the right child node.
     *
     * @return The path to the right child node.
     */
    public String getPathRightSon() {
        return pathRightSon;
    }

    /**
     * Set the path to the right child node.
     *
     * @param pathRightSon The new path to the right child node.
     */
    public void setPathRightSon(String pathRightSon) {
        this.pathRightSon = pathRightSon;
    }

    /**
     * Check if the node is a leaf node (has no children).
     *
     * @return True if the node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return (pathLeftSon == null) && (pathRightSon == null);
    }

    /**
     * Returns a formatted string representing the node's information.
     *
     * @return A string containing the node's name and ID.
     */
    public String printNode() {
        return "NAME: " + name + "\n" +
                "ID: " + id;
    }
}

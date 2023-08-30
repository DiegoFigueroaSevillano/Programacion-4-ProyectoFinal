package com.example.programacion4proyectofinal.Model.Objects;

public class Node {

    private String name;
    private int id;
    private String pathLeftSon;
    private String pathRightSon;

    public Node(String name, int id, String pathLeftSon, String pathRightSon) {
        this.name = name;
        this.id = id;
        this.pathLeftSon = pathLeftSon;
        this.pathRightSon = pathRightSon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathLeftSon() {
        return pathLeftSon;
    }

    public void setPathLeftSon(String pathLeftSon) {
        this.pathLeftSon = pathLeftSon;
    }

    public String getPathRightSon() {
        return pathRightSon;
    }

    public void setPathRightSon(String pathRightSon) {
        this.pathRightSon = pathRightSon;
    }

    public String printNode() {
        return "NAME: " + name + "\n" +
                "ID: " + id;
    }
}

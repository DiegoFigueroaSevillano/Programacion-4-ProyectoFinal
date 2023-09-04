package com.example.programacion4proyectofinal.Model.Objects;

import com.example.programacion4proyectofinal.Model.FileHandler.Deserializer.DeserializerNode;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

@JsonDeserialize(using = DeserializerNode.class)
public class Node {

    private String id;
    private int keysNumbers;
    private boolean isLeaf;
    private int order;
    private ArrayList<Passenger> keys;
    private ArrayList<String> children;

    public Node(String id, int keysNumbers, boolean isLeaf, int order, ArrayList<Passenger> keys, ArrayList<String> children) {
        this.id = id;
        this.keysNumbers = keysNumbers;
        this.isLeaf = isLeaf;
        this.order = order;
        this.keys = keys;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKeysNumbers() {
        return keysNumbers;
    }

    public void setKeysNumbers(int keysNumbers) {
        this.keysNumbers = keysNumbers;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ArrayList<Passenger> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Passenger> keys) {
        this.keys = keys;
    }

    public ArrayList<String> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }
}
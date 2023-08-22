package com.example.programacion4proyectofinal.Model;

public class Node<T extends Comparable<T>> {
    private final int degree;
    private int keysNumber;
    private final T[] keys;
    private final Node<T>[] children;
    private boolean isLeaf;

    @SuppressWarnings("unchecked")
    public Node(int degree) {
        this.degree = degree;
        this.keys = (T[]) new Comparable[2 * this.degree - 1];
        this.children = new Node[2 * this.degree];
        this.isLeaf = true;
        this.keysNumber = 0;
    }

    public int findKey(T k) {
        for (int i = 0; i < this.keysNumber; i++) {
            if (this.keys[i].compareTo(k) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int getKeysNumber() {
        return keysNumber;
    }

    public void setKeysNumber(int keysNumber) {
        this.keysNumber = keysNumber;
    }

    public T[] getKeys() {
        return keys;
    }

    public Node<T>[] getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        this.isLeaf = leaf;
    }

    public int getDegree() {
        return degree;
    }
}


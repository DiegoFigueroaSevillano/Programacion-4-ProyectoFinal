package com.example.programacion4proyectofinal.Model.FileHandler;


import com.example.programacion4proyectofinal.Model.DataStructure.Node;

public interface IFileHandlerBTree<T extends Comparable<T>> {
    boolean saveNode(Node<T> node);

    boolean deleteNode(Node<T> node);

    Node<T> readNodeById(String id);
}

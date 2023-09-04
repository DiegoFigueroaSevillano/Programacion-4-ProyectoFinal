package com.example.programacion4proyectofinal.Model.FileHandler;


import com.example.programacion4proyectofinal.Model.DataStructure.Node;

/**
 * This interface is used to save and delete nodes from the BTree and to read nodes by id.
 * @param <T> The type of the data that the nodes contain.
 */
public interface IFileHandlerBTree<T extends Comparable<T>> {
    /**
     * Saves a node in the secondary memory of the system in json format.
     * @param node Node to save.
     * @return The result of the operation.
     */
    boolean saveNode(Node<T> node);

    /**
     * Deletes a node from the secondary memory of the system that was saved in json format.
     * @param node Node to delete.
     * @return The result of the operation.
     */
    boolean deleteNode(Node<T> node);

    /**
     * Reads a node from the secondary memory of the system that was saved in json format.
     * @param id of the node to read.
     * @return The node found.
     */
    Node<T> readNodeById(String id);
    void deleteNonRootFilesIfChildrenNull(Node<T> node);
}

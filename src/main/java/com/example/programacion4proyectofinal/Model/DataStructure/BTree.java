package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.FileHandler.IFileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The BTree class represents a B-Tree data structure.
 *
 * @param <T> The type of the keys that the tree stores.
 */
public class BTree<T extends Comparable<T>> {
    private final int degree;
    private Node<T> root;
    private IFileHandlerBTree<T> fileHandler;
    private boolean fileHandlerEnabled;

    /**
     * Constructs a new BTree object with the given degree.
     *
     * @param degree The minimum degree for the B-tree.
     */
    public BTree(int degree) {
        if (degree <= 1) {
            throw new IllegalArgumentException("Order must be greater than 1");
        }
        this.degree = degree;
        this.root = new Node<>(this.degree);
    }

    /**
     * This is a second constructor of the BTree class that receives a fileHandler as a parameter.
     * @param degree The minimum degree for the B-tree.
     * @param fileHandler The fileHandler to save the nodes of the BTree.
     */
    public BTree(int degree, IFileHandlerBTree<T> fileHandler) {
        if (degree <= 1) {
            throw new IllegalArgumentException("Order must be greater than 1");
        }
        this.degree = degree;
        this.root = new Node<>(this.degree);
        this.fileHandler = fileHandler;
        Node<T> node = fileHandler.readNodeById("root");
        if (node != null) {
            this.root = node;
        } else {
            this.root = new Node<>(this.degree);
            this.root.setId("root");
        }
        this.fileHandlerEnabled = true;
    }

    /**
     * Inserts a new key into the B-Tree.
     *
     * @param key The key to insert.
     */
    public void insert(T key) {
        Node<T> currentNode = root;
        if (currentNode.isFull()) {
            handleFullRoot(key, currentNode);
        } else {
            insertNonFull(currentNode, key);
        }
    }

    /**
     * Removes a key from the B-Tree.
     *
     * @param key The key to remove.
     * @return True if the key was removed, false otherwise.
     */
    public boolean remove(T key) {
        Node<T> valueExists = search(root, key);
        if (valueExists == null) return false;
        remove(root, key);
        fileHandler.deleteNonRootFilesIfChildrenNull(root);
        return true;
    }

    /**
     * Removes a key from the B-Tree following the cases of deletion on a B-Tree.
     *
     * @param currentNode The current node where the key is located.
     * @param keyToRemove The key to remove.
     */
    private void remove(Node<T> currentNode, T keyToRemove) {
        int keyPosition = currentNode.findKeyPosition(keyToRemove);
        if (keyPosition != -1) {
            if (currentNode.isLeaf()) {
                removeKeyFromLeafNode(currentNode, keyToRemove);
            } else {
                handleInternalNodeCase(currentNode, keyPosition, keyToRemove);
            }
        } else {
            handleMissingKeyCase(currentNode, keyToRemove);
        }
    }

    /**
     * Removes a key from a leaf node.
     *
     * @param node The node where the key is located.
     * @param key  The key to remove.
     */
    private void removeKeyFromLeafNode(Node<T> node, T key) {
        int keyPosition = node.findKeyPositionInNode(node, key);
        if (keyPosition != -1) {
            for (int i = keyPosition; i < node.getKeysNumber() - 1; i++) {
                if (i != 2 * degree - 2) {
                    node.setKey(i, node.getKey(i + 1));
                }
            }
            node.decrementKeysNumber();
            if (fileHandlerEnabled) saveDataNode(node);
        }
    }

    /**
     * Handles the case where the key to remove is in an internal node.
     *
     * @param currentNode The current node where the key is located.
     * @param keyPosition The position of the key to remove.
     * @param keyToRemove The key to remove.
     */
    private void handleInternalNodeCase(Node<T> currentNode, int keyPosition, T keyToRemove) {
        Node<T> predecessorNode = currentNode.getChild(keyPosition);

        if (fileHandlerEnabled && predecessorNode == null) {
            predecessorNode = loadNodeFromFiles(keyPosition, currentNode);
        }

        if (predecessorNode.getKeysNumber() >= degree) {
            T predecessorKey = getPredecessorKey(predecessorNode);
            remove(predecessorNode, predecessorKey);
            currentNode.setKey(keyPosition, predecessorKey);

            if (fileHandlerEnabled) saveDataNode(currentNode, predecessorNode);
        } else {
            Node<T> successorNode = currentNode.getChild(keyPosition + 1);

            if (fileHandlerEnabled && successorNode == null) {
                successorNode = loadNodeFromFiles(keyPosition + 1, currentNode);
            }

            if (successorNode.getKeysNumber() >= degree) {
                T nextKey = getNextKey(successorNode);
                remove(successorNode, nextKey);
                currentNode.setKey(keyPosition, nextKey);

                if (fileHandlerEnabled) saveDataNode(currentNode, successorNode);
            } else {
                mergeNodes(currentNode, keyPosition, predecessorNode, successorNode);
                remove(predecessorNode, keyToRemove);
            }
        }
    }

    /**
     * Handles the case where the key to be removed is not found in the current node.
     *
     * @param currentNode The node in which to look for the key.
     * @param keyToRemove The key to be removed.
     */
    private void handleMissingKeyCase(Node<T> currentNode, T keyToRemove) {
        int keyPosition = currentNode.findKeyPositionInNode(currentNode, keyToRemove);
        if (currentNode.isLeaf()) return;

        Node<T> targetNode = currentNode.getChild(keyPosition);

        if (fileHandlerEnabled && targetNode == null) targetNode = loadNodeFromFiles(keyPosition, currentNode);
        if (targetNode.getKeysNumber() >= this.degree) remove(targetNode, keyToRemove);
        else {
            borrowOrMergeNodes(currentNode, keyPosition, targetNode, keyToRemove);
            remove(targetNode, keyToRemove);
        }
    }

    /**
     * Borrows a key from a neighbor node or merges nodes depending on their keys count.
     *
     * @param currentNode The parent node of the target node.
     * @param keyPosition The position of the key in the parent node.
     * @param targetNode  The node that needs to borrow a key or be merged.
     */
    private void borrowOrMergeNodes(Node<T> currentNode, int keyPosition, Node<T> targetNode, T keyToRemove) {
        Node<T> neighborNode;
        T dividerKey;

        if (redistributionFromRightSiblingIsAvailable(currentNode, keyPosition)) {
            dividerKey = currentNode.getKey(keyPosition);
            neighborNode = currentNode.getChild(keyPosition + 1);
            if (fileHandlerEnabled && neighborNode == null) {
                neighborNode = loadNodeFromFiles(keyPosition + 1, currentNode);
            }
            borrowKeyFromNextNode(currentNode, keyPosition, targetNode, neighborNode, dividerKey);
        } else if (redistributionFromLeftSiblingIsAvailable(currentNode, keyPosition)) {
            dividerKey = currentNode.getKey(keyPosition - 1);
            neighborNode = currentNode.getChild(keyPosition - 1);
            if (fileHandlerEnabled && neighborNode == null) {
                neighborNode = loadNodeFromFiles(keyPosition - 1, currentNode);
            }
            borrowKeyFromPrevNode(currentNode, keyPosition, targetNode, neighborNode, dividerKey);
        } else {
            mergeNodes(currentNode, keyPosition, keyToRemove);
        }
    }

    /**
     * Returns the next key from the given node following the tree's structure.
     *
     * @param node The node from which to start the search for the next key.
     * @return The next key in the node.
     */
    private T getNextKey(Node<T> node) {
        Node<T> aux;
        while (!node.isLeaf()) {
            aux = node.getChild(0);
            if (fileHandlerEnabled && aux == null) aux = loadNodeFromFiles(0, node);
            node = aux;
        }
        return node.getKey(0);
    }

    /**
     * Merges two nodes and updates their parent node accordingly.
     *
     * @param parentNode      The parent node of the nodes to be merged.
     * @param positionToMerge The position of the key that divides the nodes to be merged in the parent node.
     * @param predecessorNode        The predecessor node that will absorb the nextNode.
     * @param successorNode       The node to be absorbed into the predecessor node.
     */
    private void mergeNodes(Node<T> parentNode, int positionToMerge, Node<T> predecessorNode, Node<T> successorNode) {
        int leftNodeKeysCount = predecessorNode.getKeysNumber();
        predecessorNode.setKey(leftNodeKeysCount, parentNode.getKey(positionToMerge));
        predecessorNode.incrementKeysNumber();

        for (int i = 0, j = predecessorNode.getKeysNumber(); i < successorNode.getKeysNumber(); i++) {
            predecessorNode.setKey(j++, successorNode.getKey(i));
            predecessorNode.incrementKeysNumber();
        }

        int mergePositionForChildren = leftNodeKeysCount + 1;
        for (int i = 0; i < successorNode.getKeysNumber() + 1; i++) {
            predecessorNode.setChild(mergePositionForChildren, successorNode.getChild(i));
            predecessorNode.setChildrenId(mergePositionForChildren, successorNode.getIdChild(i));
            mergePositionForChildren++;
        }

        parentNode.setChild(positionToMerge, predecessorNode);

        int parentNodeKeysCount = parentNode.getKeysNumber();
        for (int parentIndex = positionToMerge; parentIndex < parentNodeKeysCount; parentIndex++) {
            parentNode.setKey(parentIndex, parentNode.getKey(parentIndex + 1));
        }

        for (int parentIndex = positionToMerge + 1; parentIndex < parentNodeKeysCount + 1; parentIndex++) {
            parentNode.setChild(parentIndex, parentNode.getChild(parentIndex + 1));
            parentNode.setChildrenId(parentIndex, parentNode.getChildrenIds()[parentIndex + 1]);
        }

        parentNode.decrementKeysNumber();
        if (parentNode.getKeysNumber() == 0){
            decreaseBTree(parentNode, predecessorNode, successorNode);
        } else if (fileHandlerEnabled) {
            fileHandler.deleteNode(successorNode);
            saveDataNode(parentNode, predecessorNode);
        }
    }

    /**
     * Returns the predecessor key for the given node.
     *
     * @param predecessorNode The node from which to get the predecessor key.
     * @return The predecessor key.
     */
    private T getPredecessorKey(Node<T> predecessorNode) {
        Node<T> nodeAux;
        while (!predecessorNode.isLeaf()) {
            nodeAux = predecessorNode.getChild(predecessorNode.getKeysNumber());
            if (fileHandlerEnabled && nodeAux == null) nodeAux = loadNodeFromFiles(predecessorNode.getKeysNumber(), predecessorNode);
            predecessorNode = nodeAux;
        }
        return predecessorNode.getKey(predecessorNode.getKeysNumber() - 1);
    }

    /**
     * Borrows a key from the next sibling node.
     *
     * @param currentNode   The parent node of the nodes involved in the operation.
     * @param position      The position of the key to be replaced in the parent node.
     * @param temporaryNode The node that will borrow the key.
     * @param rightSibling   The node from which a key will be borrowed.
     * @param dividerKey    The key that divides the temporaryNode and rightSibling in the parent node.
     */
    private void borrowKeyFromNextNode(Node<T> currentNode, int position, Node<T> temporaryNode, Node<T> rightSibling, T dividerKey) {
        currentNode.setKey(position, rightSibling.getKey(0));
        temporaryNode.setKey(temporaryNode.getKeysNumber(), dividerKey);
        temporaryNode.incrementKeysNumber();
        temporaryNode.setChild(temporaryNode.getKeysNumber(), rightSibling.getChild(0));
        temporaryNode.setChildrenId(temporaryNode.getKeysNumber(), rightSibling.getIdChild(0));

        int numberOfKeysInNextSibling = rightSibling.getKeysNumber();
        for (int nextKeyIndex = 1; nextKeyIndex < numberOfKeysInNextSibling; nextKeyIndex++) {
            rightSibling.setKey(nextKeyIndex - 1, rightSibling.getKey(nextKeyIndex));
        }
        for (int i = 1; i <= numberOfKeysInNextSibling; i++) {
            rightSibling.setChild(i - 1, rightSibling.getChild(i));
            rightSibling.setChildrenId(i - 1, rightSibling.getIdChild(i));
        }

        rightSibling.decrementKeysNumber();
        if (fileHandlerEnabled) saveDataNode(currentNode, temporaryNode, rightSibling);
    }

    /**
     * Merges child nodes at the given positionToMerge in the parent node, taking into
     * account the divider key between them.
     *
     * @param node            The parent node containing the children to be merged.
     * @param positionToMerge The positionToMerge of the divider key in the parent node.
     */
    private void mergeNodes(Node<T> node, int positionToMerge, T keyToRemove) {
        if (positionToMerge == node.getKeysNumber()) positionToMerge--;

        T dividerKey = node.getKey(positionToMerge);
        Node<T> leftChild = node.getChild(positionToMerge);
        Node<T> rightChild = node.getChild(positionToMerge + 1);

        for (int i = positionToMerge + 1; i < node.getKeysNumber(); i++) {
            node.setKey(i - 1, node.getKey(i));
        }

        for (int i = positionToMerge + 2; i <= node.getKeysNumber(); i++) {
            node.setChild(i - 1, node.getChild(i));
            node.setChildrenId(i - 1, node.getChildrenIds()[i]);
        }

        node.decrementKeysNumber();
        leftChild.getKeys()[leftChild.getKeysNumber()] = dividerKey;
        remove(leftChild, keyToRemove);
        mergeKeysAndChildren(node, leftChild, rightChild);
    }

    /**
     * This method merges the keys and children of two nodes into one node.
     * @param node The parent node of the nodes to be merged.
     * @param leftChild The left child node.
     * @param rightChild The right child node.
     */
    private void mergeKeysAndChildren(Node<T> node, Node<T> leftChild, Node<T> rightChild) {
        for (int i = 0, j = leftChild.getKeysNumber(); i < rightChild.getKeysNumber() + 1; i++, j++) {
            if (i < rightChild.getKeysNumber()) {
                leftChild.setKey(j, rightChild.getKey(i));
            }
            leftChild.setChild(j, rightChild.getChild(i));
            leftChild.setIdChild(j, rightChild.getIdChild(i));
        }
        leftChild.setKeysNumber(leftChild.getKeysNumber() + rightChild.getKeysNumber());

        if (node.getKeysNumber() == 0 && node == root) {
            decreaseBTree(node, leftChild, rightChild);
        } else if (fileHandlerEnabled) {
            fileHandler.deleteNode(rightChild);
            saveDataNode(leftChild, node);
        }
    }

    /**
     * Borrows a key from the previous sibling node.
     *
     * @param currentNode  The parent node of the nodes involved in the operation.
     * @param position     The position of the key to be replaced in the parent node.
     * @param targetNode   The node that will borrow the key.
     * @param leftSibling The node from which a key will be borrowed.
     * @param dividerKey   The key that divides the targetNode and leftSibling in the parent node.
     */
    private void borrowKeyFromPrevNode(Node<T> currentNode, int position, Node<T> targetNode, Node<T> leftSibling, T dividerKey) {
        currentNode.setKey(position - 1, leftSibling.getKey(leftSibling.getKeysNumber() - 1));
        Node<T> childToMove = leftSibling.getChild(leftSibling.getKeysNumber());

        if (fileHandlerEnabled && childToMove == null) childToMove = loadNodeFromFiles(leftSibling.getKeysNumber(), leftSibling);

        leftSibling.decrementKeysNumber();

        for (int i = targetNode.getKeysNumber() - 1; i >= 0; i--) {
            targetNode.setKey(i + 1, targetNode.getKey(i));
        }
        targetNode.setKey(0, dividerKey);
        for (int i = targetNode.getKeysNumber(); i >= 0; i--) {
            targetNode.setChild(i + 1, targetNode.getChild(i));
            targetNode.setChildrenId(i + 1, targetNode.getIdChild(i));
        }
        targetNode.setChild(0, childToMove);

        if (childToMove != null) targetNode.setChildrenId(0, childToMove.getId());
        targetNode.incrementKeysNumber();
        if (fileHandlerEnabled) saveDataNode(currentNode, targetNode, leftSibling);
    }

    /**
     * Updates the key in the B-tree. Removes the old key and inserts the new key.
     *
     * @param oldKey The key to be replaced.
     * @param newKey The new key to replace the old key with.
     * @return true if the update was successful, false otherwise.
     */
    public boolean update(T oldKey, T newKey) {
        Node<T> nodeContainingOldKey = search(root, oldKey);
        if (nodeContainingOldKey == null) {
            return false;
        }
        remove(nodeContainingOldKey, oldKey);
        insert(newKey);
        return true;
    }

    /**
     * Searches for a key in the B-tree, starting from a given node.
     *
     * @param node The node from which the search starts.
     * @param key  The key to be searched for.
     * @return The node containing the key if found, null otherwise.
     */
    public Node<T> search(Node<T> node, T key) {
        int keyIndex = 0;
        while (keyIndex < node.getKeysNumber() && key.compareTo(node.getKeys()[keyIndex]) > 0) {
            keyIndex++;
        }
        if (keyIndex < node.getKeysNumber() && key.compareTo(node.getKeys()[keyIndex]) == 0) {
            return node;
        } else if (node.isLeaf()) {
            return null;
        } else {
            if (fileHandlerEnabled && node.getChildren()[keyIndex] == null) {
                node.getChildren()[keyIndex] = fileHandler.readNodeById(node.getChildrenIds()[keyIndex]);
                node.setChildrenId(keyIndex, node.getChildren()[keyIndex].getId());
            }
            return search(node.getChildren()[keyIndex], key);
        }
    }

    /**
     * Handles the case when the root node is full and needs to be split.
     *
     * @param key         The key to be inserted.
     * @param currentNode The current root node that is full.
     */
    private void handleFullRoot(final T key, Node<T> currentNode) {
        Node<T> newNode = new Node<>(degree);
        String temp = newNode.getId();
        newNode.setId(currentNode.getId());
        this.root = newNode;
        currentNode.setId(temp);
        newNode.setLeaf(false);
        newNode.setKeysNumber(0);
        newNode.getChildren()[0] = currentNode;
        newNode.setChildrenId(0, currentNode.getId());
        split(newNode, 0, currentNode);
        insertNonFull(newNode, key);
    }

    /**
     * Inserts a key into a node which is not full.
     *
     * @param node The node into which the key will be inserted.
     * @param key  The key to be inserted.
     */
    private void insertNonFull(Node<T> node, T key) {
        if (node.isLeaf()) insertIntoLeafNode(node, key);
        else insertIntoInternalNode(node, key);
    }

    /**
     * Inserts a key into a leaf node.
     *
     * @param node        The leaf node into which the key will be inserted.
     * @param keyToInsert The key to be inserted into the leaf node.
     */
    private void insertIntoLeafNode(Node<T> node, T keyToInsert) {
        int keyIndex;
        for (keyIndex = node.getKeysNumber() - 1; keyIndex >= 0 && keyToInsert.compareTo(node.getKeys()[keyIndex]) < 0; keyIndex--) {
            node.getKeys()[keyIndex + 1] = node.getKeys()[keyIndex];
        }
        node.getKeys()[keyIndex + 1] = keyToInsert;
        node.incrementKeysNumber();
        if (fileHandlerEnabled) saveDataNode(node);
    }

    /**
     * Finds the appropriate position for inserting a new key into an array of keys.
     *
     * @param keys        The array of keys.
     * @param keyCount    The number of keys currently in the array.
     * @param keyToInsert The key to be inserted.
     * @return The position where the new key should be inserted.
     */
    private int findInsertPosition(T[] keys, int keyCount, T keyToInsert) {
        int position;
        for (position = keyCount - 1; position >= 0; position--) {
            if (keyToInsert.compareTo(keys[position]) >= 0) {
                break;
            }
        }
        return position + 1;
    }

    /**
     * Shifts keys to the right in the array, starting from a specified position.
     *
     * @param node    The Node to be shifted.
     * @param fromPos The index position from which to start the shift.
     */
    private void shiftKeysRight(Node<T> node, int fromPos) {
        for (int shitKeyIndex = node.getKeysNumber() - 1; shitKeyIndex >= fromPos; shitKeyIndex--) {
            node.getKeys()[shitKeyIndex + 1] = node.getKeys()[shitKeyIndex];
        }
    }

    /**
     * Inserts a key into an internal (non-leaf) node of the B-tree.
     *
     * @param node The internal node into which the key will be inserted.
     * @param key  The key to be inserted.
     */
    private void insertIntoInternalNode(Node<T> node, T key) {
        T[] keys = node.getKeys();
        int positionFound = findInsertPosition(keys, node.getKeysNumber(), key);
        Node<T> tmp = node.getChildren()[positionFound];
        if (fileHandlerEnabled && tmp == null) {
            tmp = fileHandler.readNodeById(node.getChildrenIds()[positionFound]);
            node.getChildren()[positionFound] = tmp;
            node.setChildrenId(positionFound, tmp.getId());
        }
        if (tmp.isFull()) {
            split(node, positionFound, tmp);
            if (key.compareTo(node.getKeys()[positionFound]) > 0) {
                positionFound++;
            }
        }
        insertNonFull(node.getChildren()[positionFound], key);
    }

    /**
     * Splits a full child node, distributing its keys between itself and a new node.
     *
     * @param parent          The parent node of the node to be split.
     * @param positionToSplit The index of the child node to be split in the parent's children array.
     * @param nodeToSplit     The node to be split.
     */
    private void split(Node<T> parent, int positionToSplit, Node<T> nodeToSplit) {
        Node<T> newNode = createNewNodeFromSplit(nodeToSplit);

        shiftChildrenRight(parent, positionToSplit);
        parent.getChildren()[positionToSplit + 1] = newNode;
        parent.setChildrenId(positionToSplit + 1, newNode.getId());

        shiftKeysRight(parent, positionToSplit);
        parent.getKeys()[positionToSplit] = nodeToSplit.getKeys()[degree - 1];

        parent.setKeysNumber(parent.getKeysNumber() + 1);

        if (fileHandlerEnabled) {
            fileHandlerOperations(parent, nodeToSplit, newNode);
        }
    }

    /**
     * Creates a new node by splitting an existing node.
     *
     * @param nodeToSplit The node to be split.
     * @return The new node containing keys from the node being split.
     */
    private Node<T> createNewNodeFromSplit(Node<T> nodeToSplit) {
        Node<T> newNode = new Node<>(degree);
        newNode.setLeaf(nodeToSplit.isLeaf());
        newNode.setKeysNumber(degree - 1);

        for (int keyIndex = 0; keyIndex < degree - 1; keyIndex++) {
            newNode.getKeys()[keyIndex] = nodeToSplit.getKeys()[keyIndex + degree];
        }

        if (!nodeToSplit.isLeaf()) {
            for (int childIndex = 0; childIndex < degree; childIndex++) {
                newNode.getChildren()[childIndex] = nodeToSplit.getChildren()[childIndex + degree];
                newNode.setChildrenId(childIndex, nodeToSplit.getChildren()[childIndex + degree].getId());
            }
        }

        nodeToSplit.setKeysNumber(degree - 1);
        return newNode;
    }

    /**
     * Shifts children nodes to the right in a parent node starting from a specific position.
     *
     * @param node         The parent node whose children will be shifted.
     * @param fromPosition The index from which to start the shift.
     */
    private void shiftChildrenRight(Node<T> node, int fromPosition) {
        for (int childIndex = node.getKeysNumber(); childIndex >= fromPosition + 1; childIndex--) {
            node.getChildren()[childIndex + 1] = node.getChildren()[childIndex];
            node.setChildrenId(childIndex + 1, node.getChildren()[childIndex].getId());
        }
    }

    /**
     * Retrieves the root node of the B-tree.
     *
     * @return The root node of the B-tree.
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Prints the B-tree structure to the standard output.
     */
    public void printBTree() {
        printBTreeRecursive(root, "", true);
    }

    /**
     * Recursively prints the B-tree structure, including node keys, starting from a given node.
     *
     * @param node        The node from which the printing starts.
     * @param prefix      The string prefix for the current level of the tree.
     * @param isLastChild Indicates if the node is the last child of its parent.
     */
    private void printBTreeRecursive(Node<T> node, String prefix, boolean isLastChild) {
        if (node != null) {
            System.out.println(prefix + (isLastChild ? "\\-- " : "|-- ") + keysToString(node));
            prefix += isLastChild ? "    " : "    |";
            if (!node.isLeaf()) {
                for (int i = 0; i <= node.getKeysNumber(); i++) {
                    printBTreeRecursive(node.getChildren()[i], prefix, i == node.getKeysNumber());
                }
            }
        }
    }

    /**
     * Converts the keys in a node to a string representation.
     *
     * @param x The node whose keys will be converted.
     * @return A string representation of the keys in the node.
     */
    private String keysToString(Node<T> x) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < x.getKeysNumber(); i++) {
            sb.append(x.getKeys()[i]);
            if (i < x.getKeysNumber() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method sets the fileHandler of the BTree when that is split.
     * @param parent The parent node of the node to be split.
     * @param child The node to be split.
     * @param newChild The new node containing keys from the node being split.
     */
    private void fileHandlerOperations(Node<T> parent, Node<T> child, Node<T> newChild) {
        if (child.getKeysNumber() == 0) {
            fileHandler.deleteNode(child);
            fileHandler.deleteNode(newChild);
            fileHandler.saveNode(parent);
        } else if (newChild.getKeysNumber() == 0) {
            fileHandler.deleteNode(child);
            fileHandler.deleteNode(newChild);
            fileHandler.saveNode(parent);
        } else {
            saveDataNode(parent, child, newChild);
        }
    }

    /**
     * Checks if a redistribution from the left sibling is possible.
     * @param node The parent node of the node to be split.
     * @param position The position of the node to be split.
     * @return True if a redistribution from the left sibling is possible, false otherwise.
     */
    private boolean redistributionFromLeftSiblingIsAvailable(Node<T> node, int position) {
        if (position == 0) return false;
        Node<T> leftSibling = node.getChild(position - 1);
        if (fileHandlerEnabled && leftSibling == null) {
            leftSibling = loadNodeFromFiles(position - 1, node);
        }
        return leftSibling.getKeysNumber() > degree;
    }

    /**
     * Checks if a redistribution from the right sibling is possible.
     * @param node The parent node of the node to be split.
     * @param position The position of the node to be split.
     * @return True if a redistribution from the right sibling is possible, false otherwise.
     */
    private boolean redistributionFromRightSiblingIsAvailable(Node<T> node, int position) {
        if (position == node.getKeysNumber()) return false;
        Node<T> rightSibling = node.getChild(position + 1);
        if (fileHandlerEnabled && rightSibling == null) {
            rightSibling = loadNodeFromFiles(position + 1, node);
        }
        return rightSibling.getKeysNumber() > degree;
    }

    /**
     * This method decreases the BTree when the root node is empty.
     * @param parent The parent node of the node to be split.
     * @param leftChild The node to be split.
     * @param rightChild The new node containing keys from the node being split.
     */
    private void decreaseBTree(Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
        root = parent.getChild(0);
        if (fileHandlerEnabled) {
            fileHandler.deleteNode(leftChild);
            fileHandler.deleteNode(rightChild);
            fileHandler.deleteNode(parent);
            root.setId("root");
            saveDataNode(root);
        }
    }

    /**
     * This method loads the node from the fileHandler.
     * @param index The index of the child.
     * @param node The node to be loaded.
     * @return The node loaded.
     */
    private Node<T> loadNodeFromFiles(int index, Node<T> node) {
        Node<T> newNode = fileHandler.readNodeById(node.getIdChild(index));
        node.setChild(index, newNode);
        return newNode;
    }

    /**
     * This method saves the nodes in the fileHandler.
     * @param nodes The nodes to be saved.
     */
    @SafeVarargs
    private void saveDataNode(Node<T>... nodes) {
        for (Node<T> node : nodes) {
            if (node.getKeysNumber() == 0) fileHandler.deleteNode(node);
            else fileHandler.saveNode(node);
        }
    }

    public void createAndFillJson(HashMap<Integer, Passenger> hashMap){
        createAndFillJsonBFS(root, hashMap);
    }

    private void createAndFillJsonBFS(Node<T> root, HashMap<Integer, Passenger> hashMap) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();

            if (node == null) {
                continue;
            }

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode nodeObject = mapper.createObjectNode();

            // Set node properties
            nodeObject.put("id", node.getId());
            nodeObject.put("keysNumber", node.getKeysNumber());
            nodeObject.put("isLeaf", node.isLeaf());
            nodeObject.put("order", node.getDegree());

            // Set keys
            ArrayNode keysArray = mapper.createArrayNode();
            for (int i = 0; i < node.getKeysNumber(); i++) {
                Passenger passenger = hashMap.get(node.getKeys()[i]);
                ObjectNode passengerObject = mapper.createObjectNode();
                passengerObject.put("id", passenger.getId());
                passengerObject.put("name", passenger.getName());
                passengerObject.put("lastName", passenger.getLastName());
                passengerObject.put("country", passenger.getCountry());
                passengerObject.put("category", passenger.getCategory().toString());
                keysArray.add(passengerObject);
            }
            nodeObject.set("keys", keysArray);

            // Set childrenIds
            ArrayNode childrenIdsArray = mapper.createArrayNode();
            for (int i = 0; i <= node.getKeysNumber(); i++) {
                if (node.getChildren()[i] != null) {
                    childrenIdsArray.add(node.getChildren()[i].getId());
                    queue.add(node.getChildren()[i]);
                } else {
                    childrenIdsArray.addNull();
                }
            }
            nodeObject.set("childrenIds", childrenIdsArray);

            // Write to file
            try {
                mapper.writeValue(new File("src/main/resources/com/example/programacion4proyectofinal/JSON/Users/" + node.getId() + ".json"), nodeObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

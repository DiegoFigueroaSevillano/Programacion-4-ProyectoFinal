package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.FileHandler.IFileHandlerBTree;

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
        int keyPosition = 0;
        while (keyPosition < node.getKeysNumber() && !node.getKeys()[keyPosition].equals(key)) {
            keyPosition++;
        }
        for (int keyIndex = keyPosition; keyIndex < node.getKeysNumber(); keyIndex++) {
            if (keyIndex != 2 * degree - 2) node.getKeys()[keyIndex] = node.getKeys()[keyIndex + 1];
        }
        node.decrementKeysNumber();
    }

    /**
     * Handles the case where the key to remove is in an internal node.
     *
     * @param currentNode The current node where the key is located.
     * @param keyPosition The position of the key to remove.
     * @param keyToRemove The key to remove.
     */
    private void handleInternalNodeCase(Node<T> currentNode, int keyPosition, T keyToRemove) {
        Node<T> predecessorNode = currentNode.getChildren()[keyPosition];

        if (predecessorNode.getKeysNumber() >= degree) {
            T predecessorKey = getPredecessorKey(predecessorNode);
            remove(predecessorNode, predecessorKey);
            currentNode.getKeys()[keyPosition] = predecessorKey;
        } else {
            Node<T> nextNode = currentNode.getChildren()[keyPosition + 1];

            if (nextNode.getKeysNumber() >= degree) {
                T nextKey = getNextKey(nextNode);
                remove(nextNode, nextKey);
                currentNode.getKeys()[keyPosition] = nextKey;
            } else {
                mergeNodes(currentNode, keyPosition, predecessorNode, nextNode);
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
        int keyPosition;
        Node<T> targetNode;
        int currentNodeKeysNumber = currentNode.getKeysNumber();

        for (keyPosition = 0; keyPosition < currentNodeKeysNumber; keyPosition++) {
            if (currentNode.getKeys()[keyPosition].compareTo(keyToRemove) > 0) break;
        }

        targetNode = currentNode.getChildren()[keyPosition];
        if (targetNode.getKeysNumber() >= degree) remove(targetNode, keyToRemove);
        else {
            borrowOrMergeNodes(currentNode, keyPosition, targetNode);
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
    private void borrowOrMergeNodes(Node<T> currentNode, int keyPosition, Node<T> targetNode) {
        Node<T> neighborNode;
        T dividerKey;

        if (keyPosition != currentNode.getKeysNumber() && currentNode.getChildren()[keyPosition + 1].getKeysNumber() >= degree) {
            dividerKey = currentNode.getKeys()[keyPosition];
            neighborNode = currentNode.getChildren()[keyPosition + 1];
            borrowKeyFromNextNode(currentNode, keyPosition, targetNode, neighborNode, dividerKey);
        } else if (keyPosition != 0 && currentNode.getChildren()[keyPosition - 1].getKeysNumber() >= degree) {
            dividerKey = currentNode.getKeys()[keyPosition - 1];
            neighborNode = currentNode.getChildren()[keyPosition - 1];
            borrowKeyFromPrevNode(currentNode, keyPosition, targetNode, neighborNode, dividerKey);
        } else {
            mergeNodes(currentNode, keyPosition);
        }
    }

    /**
     * Returns the next key from the given node following the tree's structure.
     *
     * @param node The node from which to start the search for the next key.
     * @return The next key in the node.
     */
    private T getNextKey(Node<T> node) {
        Node<T> current = node;
        int currentKeysCount = current.getKeysNumber();
        if (current.isLeaf()) return current.getKeys()[0];
        current = current.getChildren()[0];
        while (!current.isLeaf()) {
            currentKeysCount = current.getKeysNumber();
            current = current.getChildren()[currentKeysCount];
        }
        return current.getKeys()[currentKeysCount - 1];
    }

    /**
     * Merges two nodes and updates their parent node accordingly.
     *
     * @param parentNode      The parent node of the nodes to be merged.
     * @param positionToMerge The position of the key that divides the nodes to be merged in the parent node.
     * @param leftNode        The predecessor node that will absorb the nextNode.
     * @param rightNode       The node to be absorbed into the predecessor node.
     */
    private void mergeNodes(Node<T> parentNode, int positionToMerge, Node<T> leftNode, Node<T> rightNode) {
        int leftNodeKeysCount = leftNode.getKeysNumber();
        leftNode.getKeys()[leftNodeKeysCount] = parentNode.getKeys()[positionToMerge];
        leftNode.incrementKeysNumber();

        for (int nextKeyIndex = 0; nextKeyIndex < rightNode.getKeysNumber(); nextKeyIndex++) {
            leftNode.getKeys()[leftNodeKeysCount + 1 + nextKeyIndex] = rightNode.getKeys()[nextKeyIndex];
            leftNode.incrementKeysNumber();
        }

        int mergePositionForChildren = leftNodeKeysCount + 1;
        for (int nextChildIndex = 0; nextChildIndex <= rightNode.getKeysNumber(); nextChildIndex++) {
            leftNode.getChildren()[mergePositionForChildren + nextChildIndex] = rightNode.getChildren()[nextChildIndex];
        }

        int parentNodeKeysCount = parentNode.getKeysNumber();
        for (int parentIndex = positionToMerge; parentIndex < parentNodeKeysCount - 1; parentIndex++) {
            parentNode.getKeys()[parentIndex] = parentNode.getKeys()[parentIndex + 1];
            parentNode.getChildren()[parentIndex + 1] = parentNode.getChildren()[parentIndex + 2];
        }
        parentNode.decrementKeysNumber();
        if (parentNode.getKeysNumber() == 0 && parentNode.equals(root)) root = parentNode.getChildren()[0];
    }

    /**
     * Returns the predecessor key for the given node.
     *
     * @param predecessorNode The node from which to get the predecessor key.
     * @return The predecessor key.
     */
    private T getPredecessorKey(Node<T> predecessorNode) {
        T predecessorKey;
        int keysInNode;
        while (true) {
            keysInNode = predecessorNode.getKeysNumber();
            if (predecessorNode.isLeaf()) {
                predecessorKey = predecessorNode.getKeys()[keysInNode - 1];
                break;
            }
            predecessorNode = predecessorNode.getChildren()[keysInNode];
        }
        return predecessorKey;
    }

    /**
     * Borrows a key from the next sibling node.
     *
     * @param currentNode   The parent node of the nodes involved in the operation.
     * @param position      The position of the key to be replaced in the parent node.
     * @param temporaryNode The node that will borrow the key.
     * @param nextSibling   The node from which a key will be borrowed.
     * @param dividerKey    The key that divides the temporaryNode and nextSibling in the parent node.
     */
    private void borrowKeyFromNextNode(Node<T> currentNode, int position, Node<T> temporaryNode, Node<T> nextSibling, T dividerKey) {
        currentNode.getKeys()[position] = nextSibling.getKeys()[0];
        temporaryNode.getKeys()[temporaryNode.getKeysNumber()] = dividerKey;
        temporaryNode.incrementKeysNumber();
        temporaryNode.getChildren()[temporaryNode.getKeysNumber()] = nextSibling.getChildren()[0];

        int numberOfKeysInNextSibling = nextSibling.getKeysNumber();
        for (int nextKeyIndex = 1; nextKeyIndex < numberOfKeysInNextSibling; nextKeyIndex++) {
            nextSibling.getKeys()[nextKeyIndex - 1] = nextSibling.getKeys()[nextKeyIndex];
        }
        for (int nextChildrenIndex = 1; nextChildrenIndex <= numberOfKeysInNextSibling; nextChildrenIndex++) {
            nextSibling.getChildren()[nextChildrenIndex - 1] = nextSibling.getChildren()[nextChildrenIndex];
        }
        nextSibling.decrementKeysNumber();
    }

    /**
     * Merges child nodes at the given positionToMerge in the parent node, taking into
     * account the divider key between them.
     *
     * @param node            The parent node containing the children to be merged.
     * @param positionToMerge The positionToMerge of the divider key in the parent node.
     */
    private void mergeNodes(Node<T> node, int positionToMerge) {
        Node<T> leftChild;
        Node<T> rightChild;
        T dividerKey;

        if (positionToMerge != node.getKeysNumber()) {
            dividerKey = node.getKeys()[positionToMerge];
            leftChild = node.getChildren()[positionToMerge];
            rightChild = node.getChildren()[positionToMerge + 1];
        } else {
            dividerKey = node.getKeys()[positionToMerge - 1];
            rightChild = node.getChildren()[positionToMerge];
            leftChild = node.getChildren()[positionToMerge - 1];
            positionToMerge--;
        }
        for (int parentKeyIndex = positionToMerge; parentKeyIndex < node.getKeysNumber() - 1; parentKeyIndex++) {
            node.getKeys()[parentKeyIndex] = node.getKeys()[parentKeyIndex + 1];
        }
        for (int parentChildIndex = positionToMerge + 1; parentChildIndex < node.getKeysNumber(); parentChildIndex++) {
            node.getChildren()[parentChildIndex] = node.getChildren()[parentChildIndex + 1];
        }
        node.decrementKeysNumber();
        leftChild.getKeys()[leftChild.getKeysNumber()] = dividerKey;
        leftChild.setKeysNumber(leftChild.getKeysNumber() + 1);
        for (int rightKeyIndex = 0, j = leftChild.getKeysNumber(); rightKeyIndex < rightChild.getKeysNumber() + 1; rightKeyIndex++, j++) {
            if (rightKeyIndex < rightChild.getKeysNumber())
                leftChild.getKeys()[j] = rightChild.getKeys()[rightKeyIndex];
            leftChild.getChildren()[j] = rightChild.getChildren()[rightKeyIndex];
        }
        leftChild.setKeysNumber(leftChild.getKeysNumber() + rightChild.getKeysNumber());

        if (node.getKeysNumber() == 0) {
            if (node == root) root = node.getChildren()[0];
            node = node.getChildren()[0];
        }
    }

    /**
     * Borrows a key from the previous sibling node.
     *
     * @param currentNode  The parent node of the nodes involved in the operation.
     * @param position     The position of the key to be replaced in the parent node.
     * @param targetNode   The node that will borrow the key.
     * @param neighborNode The node from which a key will be borrowed.
     * @param dividerKey   The key that divides the targetNode and neighborNode in the parent node.
     */
    private void borrowKeyFromPrevNode(Node<T> currentNode, int position, Node<T> targetNode, Node<T> neighborNode, T dividerKey) {
        currentNode.getKeys()[position - 1] = neighborNode.getKeys()[neighborNode.getKeysNumber() - 1];
        Node<T> lastChildOfNeighbor = neighborNode.getChildren()[neighborNode.getKeysNumber()];
        neighborNode.decrementKeysNumber();

        for (int targetKeyIndex = targetNode.getKeysNumber(); targetKeyIndex > 0; targetKeyIndex--) {
            targetNode.getKeys()[targetKeyIndex] = targetNode.getKeys()[targetKeyIndex - 1];
        }
        targetNode.getKeys()[0] = dividerKey;
        for (int targetChildrenIndex = targetNode.getKeysNumber() + 1; targetChildrenIndex > 0; targetChildrenIndex--) {
            targetNode.getChildren()[targetChildrenIndex] = targetNode.getChildren()[targetChildrenIndex - 1];
        }
        targetNode.getChildren()[0] = lastChildOfNeighbor;
        targetNode.incrementKeysNumber();
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
}

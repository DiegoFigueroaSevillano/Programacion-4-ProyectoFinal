package com.example.programacion4proyectofinal.Model.DataStructure;

/**
 * The BTree class represents a B-Tree data structure.
 *
 * @param <T> The type of the keys that the tree stores.
 */
public class BTree<T extends Comparable<T>> {
    private final int degree;
    private Node<T> root;

    /**
     * Constructs a new BTree object with the given degree.
     *
     * @param degree The minimum degree for the B-tree.
     */
    public BTree(int degree) {
        this.degree = degree;
        this.root = new Node<>(this.degree);
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
        for (int i = keyPosition; i < node.getKeysNumber(); i++) {
            if (i != 2 * degree - 2) node.getKeys()[i] = node.getKeys()[i + 1];
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
     * @param parentNode The parent node of the nodes to be merged.
     * @param pos        The position of the key that divides the nodes to be merged in the parent node.
     * @param predNode   The predecessor node that will absorb the nextNode.
     * @param nextNode   The node to be absorbed into the predecessor node.
     */
    private void mergeNodes(Node<T> parentNode, int pos, Node<T> predNode, Node<T> nextNode) {
        int initialPredKeysCount = predNode.getKeysNumber();
        predNode.getKeys()[initialPredKeysCount] = parentNode.getKeys()[pos];
        predNode.incrementKeysNumber();

        for (int i = 0; i < nextNode.getKeysNumber(); i++) {
            predNode.getKeys()[initialPredKeysCount + 1 + i] = nextNode.getKeys()[i];
            predNode.incrementKeysNumber();
        }

        int childIndex = initialPredKeysCount + 1;
        for (int i = 0; i <= nextNode.getKeysNumber(); i++) {
            predNode.getChildren()[childIndex + i] = nextNode.getChildren()[i];
        }

        int parentKeysCount = parentNode.getKeysNumber();
        for (int i = pos; i < parentKeysCount - 1; i++) {
            parentNode.getKeys()[i] = parentNode.getKeys()[i + 1];
            parentNode.getChildren()[i + 1] = parentNode.getChildren()[i + 2];
        }
        parentNode.decrementKeysNumber();
        if (parentNode.getKeysNumber() == 0 && parentNode.equals(root)) root = parentNode.getChildren()[0];
    }

    /**
     * Returns the predecessor key for the given node.
     *
     * @param predNode The node from which to get the predecessor key.
     * @return The predecessor key.
     */
    private T getPredecessorKey(Node<T> predNode) {
        T predecessorKey;
        int keysInNode;
        while (true) {
            keysInNode = predNode.getKeysNumber();
            if (predNode.isLeaf()) {
                predecessorKey = predNode.getKeys()[keysInNode - 1];
                break;
            }
            predNode = predNode.getChildren()[keysInNode];
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
        for (int i = 1; i < numberOfKeysInNextSibling; i++) {
            nextSibling.getKeys()[i - 1] = nextSibling.getKeys()[i];
        }
        for (int i = 1; i <= numberOfKeysInNextSibling; i++) {
            nextSibling.getChildren()[i - 1] = nextSibling.getChildren()[i];
        }
        nextSibling.decrementKeysNumber();
    }

    /**
     * Merges child nodes at the given position in the parent node, taking into
     * account the divider key between them.
     *
     * @param node The parent node containing the children to be merged.
     * @param pos  The position of the divider key in the parent node.
     */
    private void mergeNodes(Node<T> node, int pos) {
        Node<T> leftChild;
        Node<T> rightChild;
        T dividerKey;

        if (pos != node.getKeysNumber()) {
            dividerKey = node.getKeys()[pos];
            leftChild = node.getChildren()[pos];
            rightChild = node.getChildren()[pos + 1];
        } else {
            dividerKey = node.getKeys()[pos - 1];
            rightChild = node.getChildren()[pos];
            leftChild = node.getChildren()[pos - 1];
            pos--;
        }
        for (int i = pos; i < node.getKeysNumber() - 1; i++) {
            node.getKeys()[i] = node.getKeys()[i + 1];
        }
        for (int i = pos + 1; i < node.getKeysNumber(); i++) {
            node.getChildren()[i] = node.getChildren()[i + 1];
        }
        node.decrementKeysNumber();
        leftChild.getKeys()[leftChild.getKeysNumber()] = dividerKey;
        leftChild.setKeysNumber(leftChild.getKeysNumber() + 1);
        for (int i = 0, j = leftChild.getKeysNumber(); i < rightChild.getKeysNumber() + 1; i++, j++) {
            if (i < rightChild.getKeysNumber()) leftChild.getKeys()[j] = rightChild.getKeys()[i];
            leftChild.getChildren()[j] = rightChild.getChildren()[i];
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

        for (int i = targetNode.getKeysNumber(); i > 0; i--) {
            targetNode.getKeys()[i] = targetNode.getKeys()[i - 1];
        }
        targetNode.getKeys()[0] = dividerKey;
        for (int i = targetNode.getKeysNumber() + 1; i > 0; i--) {
            targetNode.getChildren()[i] = targetNode.getChildren()[i - 1];
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
        int i = 0;
        while (i < node.getKeysNumber() && key.compareTo(node.getKeys()[i]) > 0) {
            i++;
        }
        if (i < node.getKeysNumber() && key.compareTo(node.getKeys()[i]) == 0) {
            return node;
        } else if (node.isLeaf()) {
            return null;
        } else {
            return search(node.getChildren()[i], key);
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
        root = newNode;
        newNode.setLeaf(false);
        newNode.setKeysNumber(0);
        newNode.getChildren()[0] = currentNode;
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
        int keyCount = node.getKeysNumber();
        T[] keys = node.getKeys();
        int insertPosition = findInsertPosition(keys, keyCount, keyToInsert);
        shiftKeysRight(keys, insertPosition, keyCount);
        keys[insertPosition] = keyToInsert;
        node.setKeysNumber(keyCount + 1);
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
     * @param keys          The array of keys to be shifted.
     * @param startPosition The index position from which to start the shift.
     * @param keyCount      The number of keys currently in the array.
     */
    private void shiftKeysRight(T[] keys, int startPosition, int keyCount) {
        for (int i = keyCount; i > startPosition; i--) {
            keys[i] = keys[i - 1];
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
        int i = findInsertPosition(keys, node.getKeysNumber(), key);
        Node<T> tmp = node.getChildren()[i];
        if (tmp.isFull()) {
            split(node, i, tmp);
            if (key.compareTo(node.getKeys()[i]) > 0) {
                i++;
            }
        }
        insertNonFull(node.getChildren()[i], key);
    }

    /**
     * Splits a full child node, distributing its keys between itself and a new node.
     *
     * @param parent      The parent node of the node to be split.
     * @param pos         The index of the child node to be split in the parent's children array.
     * @param nodeToSplit The node to be split.
     */
    private void split(Node<T> parent, int pos, Node<T> nodeToSplit) {
        T[] keys = nodeToSplit.getKeys();
        Node<T> newNode = createNewNodeFromSplit(nodeToSplit);

        shiftChildrenRight(parent, pos);
        parent.getChildren()[pos + 1] = newNode;

        shiftKeysRight(keys, degree - 1, degree);
        parent.getKeys()[pos] = nodeToSplit.getKeys()[degree - 1];

        parent.setKeysNumber(parent.getKeysNumber() + 1);
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

        for (int j = 0; j < degree - 1; j++) {
            newNode.getKeys()[j] = nodeToSplit.getKeys()[j + degree];
        }

        if (!nodeToSplit.isLeaf()) {
            for (int j = 0; j < degree; j++) {
                newNode.getChildren()[j] = nodeToSplit.getChildren()[j + degree];
            }
        }

        nodeToSplit.setKeysNumber(degree - 1);
        return newNode;
    }

    /**
     * Shifts children nodes to the right in a parent node starting from a specific position.
     *
     * @param node    The parent node whose children will be shifted.
     * @param fromPos The index from which to start the shift.
     */
    private void shiftChildrenRight(Node<T> node, int fromPos) {
        for (int j = node.getKeysNumber(); j >= fromPos + 1; j--) {
            node.getChildren()[j + 1] = node.getChildren()[j];
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
}

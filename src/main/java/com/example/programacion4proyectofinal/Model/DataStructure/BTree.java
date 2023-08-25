package com.example.programacion4proyectofinal.Model.DataStructure;

public class BTree<T extends Comparable<T>> {
    private final int degree;
    private Node<T> root;

    public BTree(int degree) {
        this.degree = degree;
        this.root = new Node<>(this.degree);
    }

    public void insert(final T key) {
        Node<T> currentNode = root;
        if (currentNode.isFull()) {
            handleFullRoot(key, currentNode);
        } else {
            insertNonFull(currentNode, key);
        }
    }

    public boolean remove(T key) {
        Node<T> valueExists = search(root, key);
        if (valueExists == null) return false;
        remove(root, key);
        return true;
    }

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

    public boolean update(T oldKey, T newKey) {
        Node<T> nodeContainingOldKey = search(root, oldKey);
        if (nodeContainingOldKey == null) {
            return false;
        }
        remove(nodeContainingOldKey, oldKey);
        insert(newKey);
        return true;
    }


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

    private void handleFullRoot(final T key, Node<T> currentNode) {
        Node<T> newNode = new Node<>(degree);
        root = newNode;
        newNode.setLeaf(false);
        newNode.setKeysNumber(0);
        newNode.getChildren()[0] = currentNode;
        split(newNode, 0, currentNode);
        insertNonFull(newNode, key);
    }

    private void insertNonFull(Node<T> node, T key) {
        if (node.isLeaf()) insertIntoLeafNode(node, key);
        else insertIntoInternalNode(node, key);
    }

    private void insertIntoLeafNode(Node<T> node, T keyToInsert) {
        int keyCount = node.getKeysNumber();
        T[] keys = node.getKeys();
        int insertPosition = findInsertPosition(keys, keyCount, keyToInsert);
        shiftKeysRight(keys, insertPosition, keyCount);
        keys[insertPosition] = keyToInsert;
        node.setKeysNumber(keyCount + 1);
    }

    private int findInsertPosition(T[] keys, int keyCount, T keyToInsert) {
        int position;
        for (position = keyCount - 1; position >= 0; position--) {
            if (keyToInsert.compareTo(keys[position]) >= 0) {
                break;
            }
        }
        return position + 1;
    }

    private void shiftKeysRight(T[] keys, int startPosition, int keyCount) {
        for (int i = keyCount; i > startPosition; i--) {
            keys[i] = keys[i - 1];
        }
    }

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

    private void split(Node<T> parent, int pos, Node<T> nodeToSplit) {
        T[] keys = nodeToSplit.getKeys();
        Node<T> newNode = createNewNodeFromSplit(nodeToSplit);

        shiftChildrenRight(parent, pos);
        parent.getChildren()[pos + 1] = newNode;

        shiftKeysRight(keys, degree - 1, degree);
        parent.getKeys()[pos] = nodeToSplit.getKeys()[degree - 1];

        parent.setKeysNumber(parent.getKeysNumber() + 1);
    }

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

    private void shiftChildrenRight(Node<T> node, int fromPos) {
        for (int j = node.getKeysNumber(); j >= fromPos + 1; j--) {
            node.getChildren()[j + 1] = node.getChildren()[j];
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public void printBTree() {
        printBTreeRecursive(root, "", true);
    }

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

package com.example.programacion4proyectofinal.Model;

public class BTree<T extends Comparable<T>> {
    private final int T;
    private Node<T> root;

    public BTree(int degree) {
        this.T = degree;
        this.root = new Node<>(T);
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

    private void remove(Node<T> node, T key) {
        int i = 0;
        while (i < node.getKeysNumber() && key.compareTo(node.getKeys()[i]) > 0) {
            i++;
        }
        if (i < node.getKeysNumber() && key.compareTo(node.getKeys()[i]) == 0) {
            if (node.isLeaf()) {
                for (int j = i; j < node.getKeysNumber() - 1; j++) {
                    node.getKeys()[j] = node.getKeys()[j + 1];
                }
                node.setKeysNumber(node.getKeysNumber() - 1);
            } else {
                Node<T> leftChild = node.getChildren()[i];
                Node<T> rightChild = node.getChildren()[i + 1];

                if (leftChild.getKeysNumber() >= T) {
                    Node<T> tempNode = leftChild;
                    while (!tempNode.isLeaf()) {
                        tempNode = tempNode.getChildren()[tempNode.getKeysNumber()];
                    }
                    T predecessor = tempNode.getKeys()[tempNode.getKeysNumber() - 1];
                    node.getKeys()[i] = predecessor;
                    remove(leftChild, predecessor);
                } else if (rightChild.getKeysNumber() >= T) {
                    Node<T> tempNode = rightChild;
                    while (!tempNode.isLeaf()) {
                        tempNode = tempNode.getChildren()[0];
                    }
                    T successor = tempNode.getKeys()[0];
                    node.getKeys()[i] = successor;
                    remove(rightChild, successor);
                } else {
                    leftChild.getKeys()[T - 1] = node.getKeys()[i];
                    for (int j = 0; j < T - 1; j++) {
                        leftChild.getKeys()[T + j] = rightChild.getKeys()[j];
                    }
                    if (!leftChild.isLeaf()) {
                        for (int j = 0; j < T; j++) {
                            leftChild.getChildren()[T + j] = rightChild.getChildren()[j];
                        }
                    }
                    leftChild.setKeysNumber(2 * T - 1);
                    for (int j = i; j < node.getKeysNumber() - 1; j++) {
                        node.getKeys()[j] = node.getKeys()[j + 1];
                        node.getChildren()[j + 1] = node.getChildren()[j + 2];
                    }
                    node.setKeysNumber(node.getKeysNumber() - 1);
                    remove(leftChild, key);
                }
            }
            return;
        }
        if (node.isLeaf()) {
            return;
        }

        Node<T> child = node.getChildren()[i];
        if (child.getKeysNumber() == T - 1) {
            Node<T> leftSibling = getLeftSibling(node, i);
            Node<T> rightSibling = getRightSibling(node, i);

            if (leftSibling != null && leftSibling.getKeysNumber() >= T) {
                for (int j = child.getKeysNumber() - 1; j >= 0; j--) {
                    child.getKeys()[j + 1] = child.getKeys()[j];
                }
                child.getKeys()[0] = node.getKeys()[i - 1];

                if (!child.isLeaf()) {
                    for (int j = child.getKeysNumber(); j >= 0; j--) {
                        child.getChildren()[j + 1] = child.getChildren()[j];
                    }
                    child.getChildren()[0] = leftSibling.getChildren()[leftSibling.getKeysNumber()];
                }

                node.getKeys()[i - 1] = leftSibling.getKeys()[leftSibling.getKeysNumber() - 1];

                leftSibling.setKeysNumber(leftSibling.getKeysNumber() - 1);
                child.setKeysNumber(child.getKeysNumber() + 1);
            } else if (rightSibling != null && rightSibling.getKeysNumber() >= T) {
                child.getKeys()[child.getKeysNumber()] = node.getKeys()[i];

                if (!child.isLeaf()) {
                    child.getChildren()[child.getKeysNumber() + 1] = rightSibling.getChildren()[0];
                }
                node.getKeys()[i] = rightSibling.getKeys()[0];

                for (int j = 1; j < rightSibling.getKeysNumber(); j++) {
                    rightSibling.getKeys()[j - 1] = rightSibling.getKeys()[j];
                }
                if (!rightSibling.isLeaf()) {
                    for (int j = 1; j <= rightSibling.getKeysNumber(); j++) {
                        rightSibling.getChildren()[j - 1] = rightSibling.getChildren()[j];
                    }
                }
                child.setKeysNumber(child.getKeysNumber() + 1);
                rightSibling.setKeysNumber(rightSibling.getKeysNumber() - 1);

            } else {
                child.getKeys()[T - 1] = node.getKeys()[i];
                for (int j = 0; j < rightSibling.getKeysNumber(); j++) {
                    child.getKeys()[T + j] = rightSibling.getKeys()[j];
                }
                if (!child.isLeaf()) {
                    for (int j = 0; j <= rightSibling.getKeysNumber(); j++) {
                        child.getChildren()[T + j] = rightSibling.getChildren()[j];
                    }
                }
                for (int j = i; j < node.getKeysNumber() - 1; j++) {
                    node.getKeys()[j] = node.getKeys()[j + 1];
                    node.getChildren()[j + 1] = node.getChildren()[j + 2];
                }
                child.setKeysNumber(2 * T - 1);
                node.setKeysNumber(node.getKeysNumber() - 1);
            }
        }
        remove(child, key);
    }

    public Node<T> getLeftSibling(Node<T> parentNode, int childIndex) {
        if (childIndex == 0) return null;
        return parentNode.getChildren()[childIndex - 1];
    }

    public Node<T> getRightSibling(Node<T> parentNode, int childIndex) {
        if (childIndex == parentNode.getKeysNumber()) return null;
        return parentNode.getChildren()[childIndex + 1];
    }

    public boolean update(T oldKey, T newKey) {
        Node<T> node = search(root, oldKey);
        if (node != null) {
            for (int i = 0; i < node.getKeysNumber(); i++) {
                if (node.getKeys()[i].compareTo(oldKey) == 0) {
                    node.getKeys()[i] = newKey;
                    return true;
                }
            }
        }
        return false;
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
        Node<T> newNode = new Node<>(T);
        root = newNode;
        newNode.setLeaf(false);
        newNode.setKeysNumber(0);
        newNode.getChildren()[0] = currentNode;
        split(newNode, 0, currentNode);
        insertNonFull(newNode, key);
    }

    private void insertNonFull(Node<T> node, T key) {
        if (node.isLeaf()) {
            insertIntoLeafNode(node, key);
        } else {
            insertIntoInternalNode(node, key);
        }
    }

    private void insertIntoLeafNode(Node<T> node, T key) {
        int i;
        for (i = node.getKeysNumber() - 1; i >= 0 && key.compareTo(node.getKeys()[i]) < 0; i--) {
            node.getKeys()[i + 1] = node.getKeys()[i];
        }
        node.getKeys()[i + 1] = key;
        node.setKeysNumber(node.getKeysNumber() + 1);
    }

    private void insertIntoInternalNode(Node<T> node, T key) {
        int i = findInsertPosition(node, key);
        Node<T> tmp = node.getChildren()[i];
        if (tmp.isFull()) {
            split(node, i, tmp);
            if (key.compareTo(node.getKeys()[i]) > 0) {
                i++;
            }
        }
        insertNonFull(node.getChildren()[i], key);
    }

    private int findInsertPosition(Node<T> node, T key) {
        int i;
        for (i = node.getKeysNumber() - 1; i >= 0 && key.compareTo(node.getKeys()[i]) < 0; i--) {
        }
        return i + 1;
    }

    private void split(Node<T> parent, int pos, Node<T> nodeToSplit) {
        Node<T> newNode = createNewNodeFromSplit(nodeToSplit);

        shiftChildrenRight(parent, pos);
        parent.getChildren()[pos + 1] = newNode;

        shiftKeysRight(parent, pos);
        parent.getKeys()[pos] = nodeToSplit.getKeys()[T - 1];

        parent.setKeysNumber(parent.getKeysNumber() + 1);
    }

    private Node<T> createNewNodeFromSplit(Node<T> nodeToSplit) {
        Node<T> newNode = new Node<>(T);
        newNode.setLeaf(nodeToSplit.isLeaf());
        newNode.setKeysNumber(T - 1);

        for (int j = 0; j < T - 1; j++) {
            newNode.getKeys()[j] = nodeToSplit.getKeys()[j + T];
        }

        if (!nodeToSplit.isLeaf()) {
            for (int j = 0; j < T; j++) {
                newNode.getChildren()[j] = nodeToSplit.getChildren()[j + T];
            }
        }

        nodeToSplit.setKeysNumber(T - 1);
        return newNode;
    }

    private void shiftChildrenRight(Node<T> node, int fromPos) {
        for (int j = node.getKeysNumber(); j >= fromPos + 1; j--) {
            node.getChildren()[j + 1] = node.getChildren()[j];
        }
    }

    private void shiftKeysRight(Node<T> node, int fromPos) {
        for (int j = node.getKeysNumber() - 1; j >= fromPos; j--) {
            node.getKeys()[j + 1] = node.getKeys()[j];
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

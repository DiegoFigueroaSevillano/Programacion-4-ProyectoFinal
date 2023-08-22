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

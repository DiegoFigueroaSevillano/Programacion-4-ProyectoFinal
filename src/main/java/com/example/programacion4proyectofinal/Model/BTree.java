package com.example.programacion4proyectofinal.Model;

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

    private void remove(Node<T> node, T key) {
        int pos = node.findKeyPosition(key);
        if (pos != -1) {
            if (node.isLeaf()) {
                int i = 0;
                for (i = 0; i < node.getKeysNumber() && !node.getKeys()[i].equals(key); i++) {
                }
                for (; i < node.getKeysNumber(); i++) {
                    if (i != 2 * degree - 2) {
                        node.getKeys()[i] = node.getKeys()[i + 1];
                    }
                }
                node.decrementKeysNumber();
                return;
            }
            if (!node.isLeaf()) {
                Node<T> pred = node.getChildren()[pos];
                int predKeysNumber = pred.getKeysNumber();
                T predKey = null;

                if (predKeysNumber >= degree) {
                    for (;;) {
                        if (pred.isLeaf()) {
                            predKey = pred.getKeys()[predKeysNumber - 1];
                            break;
                        } else {
                            pred = pred.getChildren()[predKeysNumber];
                        }
                    }
                    remove(pred, predKey);
                    node.getKeys()[pos] = predKey;
                    return;
                }

                Node<T> nextNode = node.getChildren()[pos + 1];
                int nextKeysNumber = nextNode.getKeysNumber();
                if (nextKeysNumber >= degree) {
                    T nextKey = nextNode.getKeys()[0];
                    if (!nextNode.isLeaf()) {
                        nextNode = nextNode.getChildren()[0];
                        for (;;) {
                            if (nextNode.isLeaf()) {
                                nextKey = nextNode.getKeys()[nextKeysNumber - 1];
                                break;
                            } else {
                                nextNode = nextNode.getChildren()[nextKeysNumber];
                            }
                        }
                    }
                    remove(nextNode, nextKey);
                    node.getKeys()[pos] = nextKey;
                    return;
                }

                int temp = predKeysNumber + 1;
                pred.getKeys()[predKeysNumber] = node.getKeys()[pos];
                pred.incrementKeysNumber();
                for (int i = 0, j = pred.getKeysNumber(); i < nextKeysNumber; i++) {
                    pred.getKeys()[j++] = nextNode.getKeys()[i];
                    pred.incrementKeysNumber();
                }
                for (int i = 0; i < nextKeysNumber + 1; i++) {
                    pred.getChildren()[temp++] = nextNode.getChildren()[i];
                }
                node.getChildren()[pos] = pred;
                int nodeKeysNumber = node.getKeysNumber();
                for (int i = pos; i < nodeKeysNumber; i++) {
                    if (i != 2 * degree - 2) {
                        node.getKeys()[i] = node.getKeys()[i + 1];
                    }
                }
                for (int i = pos + 1; i < nodeKeysNumber + 1; i++) {
                    if (i != 2 * degree - 1) {
                        node.getChildren()[i] = node.getChildren()[i + 1];
                    }
                }
                node.decrementKeysNumber();
                if (node.getKeysNumber() == 0) {
                    if (node.equals(root)) {
                        root = node.getChildren()[0];
                    }
                    node = node.getChildren()[0];
                }
                remove(pred, key);
                return;
            }
        } else {
            int nodeKeysNumber = node.getKeysNumber();
            T[] keys = node.getKeys();
            Node<T>[] children = node.getChildren();

            for (pos = 0; pos < nodeKeysNumber; pos++) {
                if (keys[pos].compareTo(key) > 0) {
                    break;
                }
            }
            Node<T> tmp = children[pos];
            if (tmp.getKeysNumber() >= degree) {
                remove(tmp, key);
                return;
            }
            if (true) {
                Node<T> nb = null;
                T devider = null;

                if (pos != node.getKeysNumber() && node.getChildren()[pos + 1].getKeysNumber() >= degree) {
                    devider = node.getKeys()[pos];
                    nb = node.getChildren()[pos + 1];
                    node.getKeys()[pos] = nb.getKeys()[0];
                    tmp.getKeys()[tmp.getKeysNumber()] = devider;
                    tmp.incrementKeysNumber();
                    tmp.getChildren()[tmp.getKeysNumber()] = nb.getChildren()[0];
                    for (int i = 1; i < nb.getKeysNumber(); i++) {
                        nb.getKeys()[i - 1] = nb.getKeys()[i];
                    }
                    for (int i = 1; i <= nb.getKeysNumber(); i++) {
                        nb.getChildren()[i - 1] = nb.getChildren()[i];
                    }
                    nb.decrementKeysNumber();
                    remove(tmp, key);
                    return;
                } else if (pos != 0 && node.getChildren()[pos - 1].getKeysNumber() >= degree) {
                    devider = node.getKeys()[pos - 1];
                    nb = node.getChildren()[pos - 1];
                    node.getKeys()[pos - 1] = nb.getKeys()[nb.getKeysNumber() - 1];
                    Node<T> child = nb.getChildren()[nb.getKeysNumber()];
                    nb.decrementKeysNumber();

                    for (int i = tmp.getKeysNumber(); i > 0; i--) {
                        tmp.getKeys()[i] = tmp.getKeys()[i - 1];
                    }
                    tmp.getKeys()[0] = devider;
                    for (int i = tmp.getKeysNumber() + 1; i > 0; i--) {
                        tmp.getChildren()[i] = tmp.getChildren()[i - 1];
                    }
                    tmp.getChildren()[0] = child;
                    tmp.incrementKeysNumber();
                    remove(tmp, key);
                    return;
                } else {
                    Node<T> lt = null;
                    Node<T> rt = null;
                    boolean last = false;
                    if (pos != node.getKeysNumber()) {
                        devider = node.getKeys()[pos];
                        lt = node.getChildren()[pos];
                        rt = node.getChildren()[pos + 1];
                    } else {
                        devider = node.getKeys()[pos - 1];
                        rt = node.getChildren()[pos];
                        lt = node.getChildren()[pos - 1];
                        last = true;
                        pos--;
                    }
                    for (int i = pos; i < node.getKeysNumber() - 1; i++) {
                        node.getKeys()[i] = node.getKeys()[i + 1];
                    }
                    for (int i = pos + 1; i < node.getKeysNumber(); i++) {
                        node.getChildren()[i] = node.getChildren()[i + 1];
                    }
                    node.decrementKeysNumber();
                    lt.getKeys()[lt.getKeysNumber()] = devider;
                    lt.setKeysNumber(lt.getKeysNumber() + 1);

                    for (int i = 0, j = lt.getKeysNumber(); i < rt.getKeysNumber() + 1; i++, j++) {
                        if (i < rt.getKeysNumber()) {
                            lt.getKeys()[j] = rt.getKeys()[i];
                        }
                        lt.getChildren()[j] = rt.getChildren()[i];
                    }
                    lt.setKeysNumber(lt.getKeysNumber() + rt.getKeysNumber());
                    if (node.getKeysNumber() == 0) {
                        if (node == root) {
                            root = node.getChildren()[0];
                        }
                        node = node.getChildren()[0];
                    }
                    remove(lt, key);
                    return;
                }
            }
        }
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
        Node<T> newNode = new Node<>(degree);
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

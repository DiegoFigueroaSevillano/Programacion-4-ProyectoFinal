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
        int pos = node.findKeyPosition(key);
        if (pos != -1) {
            if (node.isLeaf()) {
                int i = 0;
                for (i = 0; i < node.getKeysNumber() && node.getKeys()[i] != key; i++) {
                }
                ;
                for (; i < node.getKeysNumber(); i++) {
                    if (i != 2 * T - 2) {
                        node.getKeys()[i] = node.getKeys()[i + 1];
                    }
                }
                node.restKeysNumber(); // TODO: VER CON MAS DETALLE
                return;
            } // TODO COMPLETE
            if (!node.isLeaf()) {
                Node<T> pred = node.getChildren()[pos];
                T predKey = null;
                if (pred.getKeysNumber() >= T) {
                    for (;;) {
                        if (pred.isLeaf()) {
                            System.out.println(pred.getKeysNumber());
                            predKey = pred.getKeys()[pred.getKeysNumber() - 1];
                            break;
                        } else {
                            pred = pred.getChildren()[pred.getKeysNumber()];
                        }
                    }
                    remove(pred, predKey);
                    node.getKeys()[pos] = predKey;
                    return;
                } // TODO COMPLETE

                Node<T> nextNode = node.getChildren()[pos + 1];
                if (nextNode.getKeysNumber() >= T) {
                    T nextKey = nextNode.getKeys()[0];
                    if (!nextNode.isLeaf()) {
                        nextNode = nextNode.getChildren()[0];
                        for (;;) {
                            if (nextNode.isLeaf()) {
                                nextKey = nextNode.getKeys()[nextNode.getKeysNumber() - 1];
                                break;
                            } else {
                                nextNode = nextNode.getChildren()[nextNode.getKeysNumber()];
                            }
                        }
                    }
                    remove(nextNode, nextKey);
                    node.getKeys()[pos] = nextKey;
                    return;
                } // TODO COMPLETE

                int temp = pred.getKeysNumber() + 1;
                //pred.getKeys()[pred.getKeysNumber() + 1] = node.getKeys()[pos]; // TODO: ver mas detalles
                pred.getKeys()[pred.getKeysNumber()] = node.getKeys()[pos];
                pred.setKeysNumber(pred.getKeysNumber() + 1);
                for (int i = 0, j = pred.getKeysNumber(); i < nextNode.getKeysNumber(); i++) {
                    pred.getKeys()[j++] = nextNode.getKeys()[i];
                    //pred.setKeysNumber(pred.getKeysNumber() + 1); TODO: VER MAS DETALLES
                    pred.sumKeysNumber();
                }
                for (int i = 0; i < nextNode.getKeysNumber() + 1; i++) {
                    pred.getChildren()[temp + 1] = nextNode.getChildren()[i]; // TODO: ver mas detalles
                }

                node.getChildren()[pos] = pred;
                for (int i = pos; i < node.getKeysNumber(); i++) {
                    if (i != 2 * T - 2) {
                        node.getKeys()[i] = node.getKeys()[i + 1];
                    }
                }
                for (int i = pos + 1; i < node.getKeysNumber() + 1; i++) {
                    if (i != 2 * T - 1) {
                        node.getChildren()[i] = node.getChildren()[i + 1];
                    }
                }
                //node.setKeysNumber(node.getKeysNumber() - 1); // TODO: ver mas detalles
                node.restKeysNumber();
                if (node.getKeysNumber() == 0) {
                    if (node == root) {
                        root = node.getChildren()[0];
                    }
                    node = node.getChildren()[0];
                }
                remove(pred, key);
                return;
            }
        } else {
            for (pos = 0; pos < node.getKeysNumber(); pos++) {
                if (node.getKeys()[pos].compareTo(key) > 0) { // TODO: see later
                    break;
                }
            }
            Node<T> tmp = node.getChildren()[pos];
            if (tmp.getKeysNumber() >= T) {
                remove(tmp, key);
                return;
            }
            if (true) {
                Node<T> nb = null;
                T devider = null;

                if (pos != node.getKeysNumber() && node.getChildren()[pos + 1].getKeysNumber() >= T) {
                    devider = node.getKeys()[pos];
                    nb = node.getChildren()[pos + 1];
                    node.getKeys()[pos] = nb.getKeys()[0];
                    //tmp.getKeys()[tmp.getKeysNumber() + 1] = devider; // TODO: see later
                    tmp.getKeys()[tmp.getKeysNumber()] = devider;
                    tmp.setKeysNumber(tmp.getKeysNumber() + 1);
                    tmp.getChildren()[tmp.getKeysNumber()] = nb.getChildren()[0];
                    for (int i = 1; i < nb.getKeysNumber(); i++) {
                        nb.getKeys()[i - 1] = nb.getKeys()[i];
                    }
                    for (int i = 1; i <= nb.getKeysNumber(); i++) {
                        nb.getChildren()[i - 1] = nb.getChildren()[i];
                    }
                    //nb.setKeysNumber(nb.getKeysNumber() - 1); // TODO: see later
                    nb.restKeysNumber();
                    remove(tmp, key);
                    return;
                } else if (pos != 0 && node.getChildren()[pos - 1].getKeysNumber() >= T) {
                    devider = node.getKeys()[pos - 1];
                    nb = node.getChildren()[pos - 1];
                    node.getKeys()[pos - 1] = nb.getKeys()[nb.getKeysNumber() - 1];
                    Node<T> child = nb.getChildren()[nb.getKeysNumber()];
                    //nb.setKeysNumber(nb.getKeysNumber() - 1); // TODO: see later
                    nb.restKeysNumber();

                    for (int i = tmp.getKeysNumber(); i > 0; i--) {
                        tmp.getKeys()[i] = tmp.getKeys()[i - 1];
                    }
                    tmp.getKeys()[0] = devider;
                    for (int i = tmp.getKeysNumber() + 1; i > 0; i--) {
                        tmp.getChildren()[i] = tmp.getChildren()[i - 1];
                    }
                    tmp.getChildren()[0] = child;
                    //tmp.setKeysNumber(tmp.getKeysNumber() + 1); // TODO: see later
                    tmp.sumKeysNumber();
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
                    // node.setKeysNumber(node.getKeysNumber() - 1);// TODO: see later
                    node.restKeysNumber();
                    //lt.getKeys()[lt.getKeysNumber() + 1] = devider; // TODO: see later
                    lt.getKeys()[lt.getKeysNumber()] = devider;
                    lt.setKeysNumber(lt.getKeysNumber() + 1);

                    for (int i = 0, j = lt.getKeysNumber(); i < rt.getKeysNumber() + 1; i++, j++) {
                        if (i < rt.getKeysNumber()) {
                            lt.getKeys()[j] = rt.getKeys()[i];
                        }
                        lt.getChildren()[j] = rt.getChildren()[i];
                    }
                    //lt.setKeysNumber(+ rt.getKeysNumber()); // TODO: see later
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

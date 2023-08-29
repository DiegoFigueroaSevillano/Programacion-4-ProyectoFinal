package com.example.programacion4proyectofinal.Model.DataStructure;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InsertBTreeJson<T extends Comparable<T>>{

    private final int degree;
    public Node<Passenger> root;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String folderPath = "src/main/resources/com/example/programacion4proyectofinal/JSON/Users/";
    int jsonCounter = 0;

    public InsertBTreeJson(int degree) {
        this.degree = degree;
        this.root = new Node<>(this.degree, Passenger.class);
    }

    public void insert(Passenger passenger) {
        Node<Passenger> currentNode = root;
        if (currentNode.isFull()) {
            handleFullRoot(passenger, currentNode);
        } else {
            insertNonFull(currentNode, passenger);
        }
    }

    private void handleFullRoot(final Passenger passenger, Node<Passenger> currentNode) {
        Node<Passenger> newNode = new Node<>(degree, Passenger.class);
        root = newNode;
        newNode.setLeaf(false);
        newNode.setKeysNumber(0);
        newNode.getChildren()[0] = currentNode;
        split(newNode, 0, currentNode);
        insertNonFull(newNode, passenger);
    }


    private void insertNonFull(Node<Passenger> node, Passenger passenger) {
        if (node.isLeaf()) insertIntoLeafNode(node, passenger);
        else insertIntoInternalNode(node, passenger);
    }

    private void insertIntoLeafNode(Node<Passenger> node, Passenger passengerToInsert) {
        int keyIndex;
        for (keyIndex = node.getKeysNumber() - 1; keyIndex >= 0 && passengerToInsert.compareTo(node.getKeys()[keyIndex]) < 0; keyIndex--) {
            node.getKeys()[keyIndex + 1] = node.getKeys()[keyIndex];
        }
        node.getKeys()[keyIndex + 1] = passengerToInsert;
        node.incrementKeysNumber();

        // Update the JSON file
        updateJsonFile(node);
    }

    private void insertIntoInternalNode(Node<Passenger> node, Passenger passenger) {
        Passenger[] keys = node.getKeys();
        int positionFound = findInsertPosition(keys, node.getKeysNumber(), passenger);
        Node<Passenger> tmp = node.getChildren()[positionFound];
        if (tmp.isFull()) {
            split(node, positionFound, tmp);
            if (passenger.compareTo(node.getKeys()[positionFound]) > 0) {
                positionFound++;
            }
        }
        insertNonFull(node.getChildren()[positionFound], passenger);
    }

    private int findInsertPosition(Passenger[] keys, int keyCount, Passenger passengerToInsert) {
        int position;
        for (position = keyCount - 1; position >= 0; position--) {
            if (passengerToInsert.compareTo(keys[position]) >= 0) {
                break;
            }
        }
        return position + 1;
    }

    private void split(Node<Passenger> parent, int positionToSplit, Node<Passenger> nodeToSplit) {
        Node<Passenger> newNode = createNewNodeFromSplit(nodeToSplit);

        shiftChildrenRight(parent, positionToSplit);
        parent.getChildren()[positionToSplit + 1] = newNode;

        shiftKeysRight(parent, positionToSplit);
        parent.getKeys()[positionToSplit] = nodeToSplit.getKeys()[degree - 1];

        parent.setKeysNumber(parent.getKeysNumber() + 1);


        updateJsonFile(parent);
        updateJsonFile(newNode);


        eliminatedTheRepetitiveValues(nodeToSplit, newNode);


        updateJsonFile(nodeToSplit);

        setJsonPath(newNode);
    }

    private boolean eliminatedTheRepetitiveValues(Node<Passenger> nodeWithTheEliminatedData, Node<Passenger> dataWhoEliminated){
        Passenger auxEliminatedData;
        Passenger auxData;
        for (int i = 0; i < dataWhoEliminated.getKeys().length; i++){
            auxEliminatedData = dataWhoEliminated.getKeys()[i];
            if (auxEliminatedData != null){
                for (int j = 0; j < nodeWithTheEliminatedData.getKeys().length; j++){
                    if (nodeWithTheEliminatedData.getKeys()[j] != null){
                        if (auxEliminatedData.compareTo(nodeWithTheEliminatedData.getKeys()[j]) == 0){
                            nodeWithTheEliminatedData.getKeys()[j] = null;
                        }
                    }
                }
            }
        }
        return true;
    }

    private Node<Passenger> createNewNodeFromSplit(Node<Passenger> nodeToSplit) {
        Node<Passenger> newNode = new Node<>(degree, Passenger.class);
        newNode.setLeaf(nodeToSplit.isLeaf());
        newNode.setKeysNumber(degree - 1);

        for (int keyIndex = 0; keyIndex < degree - 1; keyIndex++) {
            newNode.getKeys()[keyIndex] = nodeToSplit.getKeys()[keyIndex + degree];
        }

        if (!nodeToSplit.isLeaf()) {
            for (int childIndex = 0; childIndex < degree; childIndex++) {
                newNode.getChildren()[childIndex] = nodeToSplit.getChildren()[childIndex + degree];
            }
        }

        nodeToSplit.setKeysNumber(degree - 1);

        return newNode;
    }

    public void clearAJson(Node<Passenger> node){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode emptyObject = objectMapper.createObjectNode();
        try {
            objectMapper.writeValue(new File(node.getJsonPath()), emptyObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shiftChildrenRight(Node<Passenger> node, int fromPosition) {
        for (int childIndex = node.getKeysNumber(); childIndex >= fromPosition + 1; childIndex--) {
            node.getChildren()[childIndex + 1] = node.getChildren()[childIndex];
        }
    }

    private void shiftKeysRight(Node<Passenger> node, int fromPos) {
        for (int shitKeyIndex = node.getKeysNumber() - 1; shitKeyIndex >= fromPos; shitKeyIndex--) {
            node.getKeys()[shitKeyIndex + 1] = node.getKeys()[shitKeyIndex];
        }
    }

    private void updateJsonFile(Node<Passenger> node) {
        if (node.getJsonPath() == null) {
            setJsonPath(node);
        }
        File file = new File(node.getJsonPath());
        try {
            objectMapper.writeValue(file, node.getKeys());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setJsonPath(Node<Passenger> node) {
        String jsonFileName = folderPath + "node" + jsonCounter + ".json";
        jsonCounter++;
        node.setJsonPath(jsonFileName);
    }

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
    private void printBTreeRecursive(Node<Passenger> node, String prefix, boolean isLastChild) {
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

    private String keysToString(Node<Passenger> x) {
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

    public static void main(String[] args) {
        InsertBTreeJson btree = new InsertBTreeJson(3);

        btree.insert(new Passenger("DIEGO", 123));
        btree.insert(new Passenger("JOSE", 453));
        btree.insert(new Passenger("ALBERTO", 575));
        btree.insert(new Passenger("PEDRO", 789));
        btree.insert(new Passenger("NICOLAS", 108));
        btree.insert(new Passenger("DENIS", 45345));
        btree.insert(new Passenger("AXEL", 434344));

        btree.insert(new Passenger("EMMA", 111));
        btree.insert(new Passenger("OLIVIA", 222));
        btree.insert(new Passenger("AVA", 333));
        btree.insert(new Passenger("SOPHIA", 444));
        btree.insert(new Passenger("ISABELLA", 555));
        btree.insert(new Passenger("MIA", 666));
        btree.insert(new Passenger("CHARLOTTE", 777));
        btree.insert(new Passenger("AMELIA", 888));
        btree.insert(new Passenger("HARPER", 999));
        btree.insert(new Passenger("EVELYN", 1010));
        btree.insert(new Passenger("ABIGAIL", 1111));
        btree.insert(new Passenger("EMILY", 1222));
        btree.insert(new Passenger("ELIZABETH", 1333));
        btree.insert(new Passenger("SOFIA", 1444));
        btree.insert(new Passenger("AVERY", 1555));
        btree.insert(new Passenger("ELLA", 1666));
        btree.insert(new Passenger("SCARLETT", 1777));
        btree.insert(new Passenger("GRACE", 1888));
        btree.insert(new Passenger("CHLOE", 1999));
        btree.insert(new Passenger("VICTORIA", 2000));
        btree.insert(new Passenger("RILEY", 2111));
        btree.insert(new Passenger("ARIA", 2222));
        btree.insert(new Passenger("LILY", 2333));
        btree.insert(new Passenger("AUBREY", 2444));
        btree.insert(new Passenger("ZOEY", 2555));
        btree.insert(new Passenger("PENELOPE", 2666));
        btree.insert(new Passenger("LILLIAN", 2777));
        btree.insert(new Passenger("ADDISON", 2888));
        btree.insert(new Passenger("LAYLA", 2999));
        btree.insert(new Passenger("NATALIE", 3000));
        btree.insert(new Passenger("CAMILA", 3111));
        btree.insert(new Passenger("HANNAH", 3222));
        btree.insert(new Passenger("BROOKLYN", 3333));
        btree.insert(new Passenger("ZOE", 3444));
        btree.insert(new Passenger("NORA", 3555));
        btree.insert(new Passenger("LEAH", 3666));
        btree.insert(new Passenger("SAVANNAH", 3777));
        btree.insert(new Passenger("AUDREY", 3888));
        btree.insert(new Passenger("CLAIRE", 3999));
        btree.insert(new Passenger("ELEANOR", 4000));
        btree.insert(new Passenger("SKYLAR", 4111));
        btree.insert(new Passenger("ELLIE", 4222));
        btree.insert(new Passenger("SAMANTHA", 4333));
        btree.insert(new Passenger("STELLA", 4444));
        btree.insert(new Passenger("PIPER", 4555));
        btree.insert(new Passenger("HAILEY", 4666));
        btree.insert(new Passenger("ANNABELLE", 4777));
        btree.insert(new Passenger("CAROLINE", 4888));
        btree.insert(new Passenger("GENESIS", 4999));
        btree.insert(new Passenger("EMERY", 5000));
        btree.insert(new Passenger("KENNEDY", 5111));
        btree.insert(new Passenger("SADIE", 5222));
        btree.insert(new Passenger("GABRIELLA", 5333));
        btree.insert(new Passenger("MADISON", 5444));
        btree.insert(new Passenger("KHALEESI", 5555));


        System.out.println(btree.root.getJsonPath());
        System.out.println(btree.root.getChildren()[0].getJsonPath());
        System.out.println(btree.root.getChildren()[1].getJsonPath());
        System.out.println(btree.root.getChildren()[2].getJsonPath());

        btree.printBTree();

    }
}

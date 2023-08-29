package com.example.programacion4proyectofinal.Model;

/**
 * This class is used for Client's priority.
 */
public enum PRIORITY {
    VIP("VIP", 1),
    FREQUENT("FREQUENT", 2),
    REGULAR("REGULAR", 3);

    private String name;
    private int index;

    /**
     * This is the constructor method for Priority.
     * @param name
     * @param index
     */
    PRIORITY(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * This method returns the index.
     * @return
     */
    public int getIndex() {
        return index;
    }
}

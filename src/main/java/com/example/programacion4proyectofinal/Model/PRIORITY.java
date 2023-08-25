package com.example.programacion4proyectofinal.Model;

public enum PRIORITY {
    VIP("VIP", 1),
    FREQUENT("FREQUENT", 2),
    REGULAR("REGULAR", 3);

    private String name;
    private int index;

    PRIORITY(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
}

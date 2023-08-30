package com.example.programacion4proyectofinal;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Search;

public class SearchTestMain {

    public static void main(String[] args) {
        Search search = new Search();

        Node node = search.searchById(333);

        if (node == null) {
            System.out.println("NODE IS NULL");
        } else {
            System.out.println(node.printNode());
        }

    }

}

package com.example.programacion4proyectofinal;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Search;

import java.util.ArrayList;

/*
* Is only for test the Search Logic
*/
public class SearchTestMain {

    public static void main(String[] args) {
        Search search = new Search();

        Node node = search.searchById(333);

        System.out.println("===============================================");
        if (node == null) {
            System.out.println("NODE IS NULL");
        } else {
            System.out.println(node.printNode());
        }
        System.out.println("===============================================");

        ArrayList<Node> result = search.searchByName("juan");

        System.out.println("===============================================");
        if (!result.isEmpty()) {
            for (int index = 0; index < result.size(); index++) {
                System.out.println(result.get(index).printNode());
                System.out.println("===============================================");
            }
        } else {
            System.out.println("USER NOT FOUND!!!\n===============================================");
        }
    }

}

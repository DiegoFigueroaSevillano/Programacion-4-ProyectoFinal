package com.example.programacion4proyectofinal;

import com.example.programacion4proyectofinal.Model.Objects.Node;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.Search;

import java.util.ArrayList;

/*
* Is only for test the Search Logic
*/
public class SearchTestMain {

    public static void main(String[] args) {
        Search search = new Search();
        Passenger passenger = search.searchById(1);
        System.out.println("===============================================");
        if (passenger == null) {
            System.out.println("NODE IS NULL");
        } else {
            System.out.println(passenger.toString());
        }
        System.out.println("===============================================");

        ArrayList<Passenger> result = search.searchByName("Axel");

        System.out.println("===============================================");
        if (!result.isEmpty()) {
            for (int index = 0; index < result.size(); index++) {
                System.out.println(result.get(index).toString());
                System.out.println("===============================================");
            }
        } else {
            System.out.println("USER NOT FOUND!!!\n===============================================");
        }
    }

}

package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PassengersController {

    private Passengers passengers;

    public PassengersController(Group root, Stage stage) {
        ArrayList<HBox> passengers = new ArrayList<>();
        this.passengers = new Passengers(root, stage, passengers);
    }



    public Passengers getPassengers() {
        return passengers;
    }
}

package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.scene.Group;
import javafx.stage.Stage;

public class PassengersController {

    private Passengers passengers;

    public PassengersController(Group root, Stage stage) {
        this.passengers = new Passengers(root, stage);
    }

    public Passengers getPassengers() {
        return passengers;
    }
}

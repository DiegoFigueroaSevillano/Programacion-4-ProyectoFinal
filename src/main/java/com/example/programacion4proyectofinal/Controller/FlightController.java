package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Pages.FlightView;
import javafx.scene.Group;
import javafx.stage.Stage;

public class FlightController {
    private FlightView flightView;
    private Group root;
    private Stage stage;

    public FlightController(Group root, Stage stage) {
        this.root = root;
        this.stage = stage;
        this.flightView = new FlightView(root, stage);
    }

    public FlightView getFlightView() {
        return flightView;
    }

}

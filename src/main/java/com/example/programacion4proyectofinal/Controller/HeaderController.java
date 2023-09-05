package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Components.General.Header;
import com.example.programacion4proyectofinal.View.Pages.Home;
import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HeaderController {

    private Header header;
    private String currentOption;
    private Stage stage;

    public HeaderController(Group root, Stage stage, String pageName) {
        this.stage = stage;
        this.currentOption = pageName;
        this.header = new Header(stage, pageName);
        addActionToOptionsMenu();
    }

    private void addActionToOptionsMenu() {
        header.getHomeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("home")) {
                    Group root = new Group();
                    HomeController homeController = new HomeController(root, stage);
                    Scene homeScene = homeController.getHomeView().getHomeScene();
                    stage.setScene(homeScene);
                }
            }
        });
        header.getPassengersButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("passengers")) {
                    Group root = new Group();
                    PassengersController passengersController = new PassengersController(root, stage);
                    Scene passengersScene = passengersController.getPassengers().getPassengersScene();
                    stage.setScene(passengersScene);
                }
            }
        });
    }

    public Header getHeader() {
        return header;
    }
}

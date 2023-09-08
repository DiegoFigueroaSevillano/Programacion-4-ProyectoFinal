package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Components.General.Header;
import com.example.programacion4proyectofinal.View.Pages.Home;
import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HeaderController {

    private Header header;
    private String currentOption;
    private Stage stage;
    private Group root;

    public HeaderController(Group root, Stage stage, String pageName) {
        this.stage = stage;
        this.currentOption = pageName;
        this.root = root;
        this.header = new Header(this.root, this.stage, this.currentOption);
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

        header.getPassengerRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("register")) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            root = new Group();
                            RegisterController register = new RegisterController(root, stage);
                            Scene registerScene = register.getRegisterView().getRegisterScene();
                            stage.setScene(registerScene);
                        }
                    });
                }
            }
        });
    }

    public Header getHeader() {
        return header;
    }
}

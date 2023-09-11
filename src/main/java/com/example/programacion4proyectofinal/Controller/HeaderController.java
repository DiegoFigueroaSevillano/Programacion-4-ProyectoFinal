package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Components.General.Header;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The HeaderController class manages the header of the application, providing navigation options.
 */
public class HeaderController {

    private Header header;
    private String currentOption;
    private Stage stage;
    private Group root;

    /**
     * Initializes a new instance of the HeaderController class.
     *
     * @param root     The root Group of the application's UI.
     * @param stage    The primary Stage of the application.
     * @param pageName The current page name for initializing the header.
     */
    public HeaderController(Group root, Stage stage, String pageName) {
        this.stage = stage;
        this.currentOption = pageName;
        this.root = root;
        this.header = new Header(this.root, this.stage, this.currentOption);
        addActionToHomeButton();
        addActionToPassengersButton();
        addActionToRegistrationButton();
        addActionToChangeDispenserButton();
        addActionToFlightButton();
    }

    /**
     * Adds an action event to the "Change Dispenser" button in the header.
     * When the button is clicked and the current option is not "changeDispenser",
     * it switches to the change dispenser view.
     */
    private void addActionToChangeDispenserButton() {
        header.getChangeDispenserButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!currentOption.equals("changeDispenser")) {
                    Group root = new Group();
                    ChangeDispenserController changeDispenserController = new ChangeDispenserController(root, stage, 0, 0, false);
                    Scene passengersScene = changeDispenserController.getChangeDispenserView().getChangeDispenserScene();
                    stage.setScene(passengersScene);
                }
            }
        });
    }

    /**
     * Adds an action event to the "Home" button in the header.
     * When the button is clicked and the current option is not "home",
     * it switches to the home view.
     */
    private void addActionToHomeButton() {
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
    }

    /**
     * Adds an action event to the "Passengers" button in the header.
     * When the button is clicked and the current option is not "passengers",
     * it switches to the passengers view.
     */
    private void addActionToPassengersButton() {
        header.getPassengersButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("passengers")) {
                    Group root = new Group();
                    PassengersController passengersController = PassengersController.getPassengersControllerInstance();
                    passengersController.start(root, stage);
                    Scene passengersScene = passengersController.getPassengers().getPassengersScene();
                    stage.setScene(passengersScene);
                }
            }
        });
    }

    /**
     * Adds an action event to the "Passenger Registration" button in the header.
     * When the button is clicked and the current option is not "register",
     * it switches to the passenger registration view.
     */
    private void addActionToRegistrationButton() {
        header.getPassengerRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("register")) {
                    root = new Group();
                    RegisterController register = new RegisterController(root, stage);
                    Scene registerScene = register.getRegisterView().getRegisterScene();
                    stage.setScene(registerScene);
                }
            }
        });
    }

    /**
     * Adds an action handler to the "Flight" button in the header.
     * When the button is clicked, it checks if the current option is not "flight,"
     * and if so, it creates a new FlightController instance and sets the scene
     * to the PassengerOfAFlight view, effectively switching to the flight-related
     * functionality.
     */
    private void addActionToFlightButton() {
        header.getFlightButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!currentOption.equals("flight")) {
                    root = new Group();
                    FlightController flightController = new FlightController(root, stage);
                    Scene flightScene = flightController.getFlightView().getPassengerOfAFlightScene();
                    stage.setScene(flightScene);
                }
            }
        });
    }

    /**
     * Gets the Header view.
     *
     * @return The Header view.
     */
    public Header getHeader() {
        return header;
    }
}

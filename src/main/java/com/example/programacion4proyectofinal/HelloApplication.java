package com.example.programacion4proyectofinal;

import com.example.programacion4proyectofinal.Controller.LogInController;
import com.example.programacion4proyectofinal.Controller.PassengersController;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Controller.PassengersController.getPassengersControllerInstance;

/**
 * This is the main class that launches the application.
 */
public class HelloApplication extends Application {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) throws InterruptedException {
        Thread obtainPassengers = new Thread(() -> {
            System.out.println("SEARCHING PASSENGERS...");
            PassengersController passengersController = getPassengersControllerInstance();
            System.out.println("SEARCHING FINISHING...");
        });
        Thread mainThread = new Thread(() -> {
            System.out.println("OPEN UI...");
            launch(args);
            System.out.println("GOOD BYE :D");
        });

        obtainPassengers.start();
        mainThread.start();

        try {
            obtainPassengers.join();
            mainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that configures and displays the application's graphical user interface.
     *
     * @param stage The main stage of the application.
     * @throws Exception If an exception occurs during the interface setup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        LogInController logIn = new LogInController(root, stage);

        Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");

        Scene currentScene = logIn.getLogInView().getLogInScene();

        ChangePropertiesStage changePropertiesStage = new ChangePropertiesStage();
        changePropertiesStage.changeSizeStage(800, 700, stage);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.getIcons().add(iconApp);
        stage.setResizable(false);
        stage.show();
    }
}

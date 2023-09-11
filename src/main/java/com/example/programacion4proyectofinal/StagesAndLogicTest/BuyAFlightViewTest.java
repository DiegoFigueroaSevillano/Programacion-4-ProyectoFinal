package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Controller.BuyAFlightController;
import com.example.programacion4proyectofinal.Controller.ChangeDispenserController;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.View.Pages.BuyAFlight;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BuyAFlightViewTest extends Application {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
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

        BuyAFlightController buyAFlightController = new BuyAFlightController(root, stage, 786);

        Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");

        Scene currentScene = buyAFlightController.getView().getScene();

        ChangePropertiesStage changePropertiesStage = new ChangePropertiesStage();
        changePropertiesStage.changeSizeStage(1800, 1000, stage);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.getIcons().add(iconApp);
        stage.show();

    }
}

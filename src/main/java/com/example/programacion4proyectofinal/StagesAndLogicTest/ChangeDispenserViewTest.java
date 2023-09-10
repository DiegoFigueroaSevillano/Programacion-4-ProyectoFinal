package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Controller.ChangeDispenserController;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChangeDispenserViewTest extends Application {

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

        ChangeDispenserController logIn = new ChangeDispenserController(root, stage, 300);

        Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");

        Scene currentScene = logIn.getChangeDispenserView().getChangeDispenserScene();

        ChangePropertiesStage changePropertiesStage = new ChangePropertiesStage();
        changePropertiesStage.changeSizeStage(1800, 1000, stage);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.getIcons().add(iconApp);
        stage.show();
    }
}

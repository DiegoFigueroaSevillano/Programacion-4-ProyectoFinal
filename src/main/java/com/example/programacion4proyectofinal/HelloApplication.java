package com.example.programacion4proyectofinal;

import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.View.Pages.LogIn;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        LogIn logIn = new LogIn(root, stage);
        Scene currentScene = logIn.getLogInScene();
        ChangePropertiesStage changePropertiesStage = new ChangePropertiesStage();
        changePropertiesStage.changeSizeStage(800, 700, stage);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.show();
    }
}
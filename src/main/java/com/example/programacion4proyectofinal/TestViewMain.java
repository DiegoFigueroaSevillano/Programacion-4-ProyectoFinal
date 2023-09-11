package com.example.programacion4proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class TestViewMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/programacion4proyectofinal/Views/user-profile.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(900);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Test View");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

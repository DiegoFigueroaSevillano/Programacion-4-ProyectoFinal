package com.example.programacion4proyectofinal.StagesAndLogicTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejemplo extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox hbox = new HBox();
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        Pane innerPane = new Pane();

        HBox auxBox = new HBox();
        Pane aux = new Pane();

        // Establecer colores de fondo para diferenciar los elementos
        vbox1.setStyle("-fx-background-color: red");
        vbox2.setStyle("-fx-background-color: green");
        innerPane.setStyle("-fx-background-color: blue");

        aux.setStyle("-fx-background-color: purple");
        auxBox.setStyle("-fx-background-color: white");

        vbox1.setAlignment(Pos.CENTER);

        // Enlazar la propiedad de ancho y altura del VBox1 a la mitad de la propiedad de ancho y altura del HBox
        vbox1.prefWidthProperty().bind(hbox.widthProperty().divide(2));
        vbox1.prefHeightProperty().bind(hbox.heightProperty());

        // Enlazar la propiedad de ancho y altura del VBox2 a la mitad de la propiedad de ancho y altura del HBox
        vbox2.prefWidthProperty().bind(hbox.widthProperty().divide(2));
        vbox2.prefHeightProperty().bind(hbox.heightProperty());

        // Enlazar la propiedad de ancho del innerPane a un poco más de la mitad de la propiedad de ancho del VBox1
        auxBox.prefWidthProperty().bind(vbox1.widthProperty());
        auxBox.prefHeightProperty().bind(vbox1.heightProperty().multiply(0.1));

        aux.prefWidthProperty().bind(auxBox.widthProperty().multiply(0.4));
        aux.prefHeightProperty().bind(auxBox.heightProperty());

        innerPane.prefWidthProperty().bind(auxBox.widthProperty().multiply(0.6));
        innerPane.prefHeightProperty().bind(auxBox.heightProperty());

        auxBox.getChildren().addAll(innerPane, aux);

        // Agregar el innerPane al VBox1
        vbox1.getChildren().add(auxBox);

        // Agregar el VBox1 y el VBox2 al HBox
        hbox.getChildren().addAll(vbox1, vbox2);

        Scene scene = new Scene(hbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

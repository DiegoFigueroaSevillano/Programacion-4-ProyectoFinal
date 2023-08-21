package View.Components;

import View.Pages.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static Utils.Colors.*;

public class Header {

    private HBox header, menu;
    private Button homeButton, passengersButton;
    private Stage stage;

    public Header(Stage stage) {
        this.stage = stage;
        createHeader();

    }

    private void createHeader() {

        createMenu();

        header = new HBox(20);
        header.setMinWidth(700);
        header.setPrefHeight(100);
        header.setBackground(Background.fill(Color.valueOf(SKY_BLUE)));
        header.getChildren().add(menu);
    }

    private void createMenu() {
        createHomeButton();
        createPassengersButton();

        menu = new HBox(20);
        menu.setAlignment(Pos.CENTER_LEFT);
        menu.prefWidthProperty().bind(stage.widthProperty().subtract(100));
        menu.setPrefHeight(100);
        menu.getChildren().addAll(homeButton, passengersButton);
    }

    private void createHomeButton() {
        homeButton = new Button("HOME");
        generatorMenuOptions(homeButton);
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Group root = new Group();
                Home home = new Home(root, stage);
                Scene homeScene = home.getHomeScene();
                stage.setScene(homeScene);
            }
        });
    }

    private void createPassengersButton() {
        passengersButton = new Button("PASSENGERS");
        generatorMenuOptions(passengersButton);
    }

    private void generatorMenuOptions(Button button) {
        button.setPrefHeight(80);
        button.setPrefWidth(200);
        button.setCursor(Cursor.HAND);
        button.setBackground(Background.fill(Color.valueOf(SKY_BLUE)));
        button.setTextFill(Color.valueOf(WHITE));
        button.setStyle("-fx-font-size: 24px");
    }

    public HBox getHeader() {
        return header;
    }
}

package View.Components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import static Utils.Colors.*;

public class Header {

    private StackPane header;
    private Button homeButton, passengersButton;

    public Header() {
    }

    private void createHeader() {
        header = new StackPane();
        header.setMinWidth(700);
        header.setPrefHeight(100);
        header.setBackground(Background.fill(Color.valueOf(SKY_BLUE)));
    }

    public StackPane getHeader() {
        return header;
    }
}

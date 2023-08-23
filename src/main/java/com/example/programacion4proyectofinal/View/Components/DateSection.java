package com.example.programacion4proyectofinal.View.Components;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDate;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

public class DateSection {

    private DatePicker datePicker;
    private Label label;
    private String title;
    private VBox container;

    public DateSection(String title) {
        this.title = title;
        createContainer();
    }

    private void createContainer() {
        createLabel();
        createDatePicker();

        container = new VBox(0);
        container.setPrefHeight(120);
        container.setPrefWidth(350);
        container.getChildren().addAll(label, datePicker);
    }

    private void createLabel() {
        label = new Label(title);
        label.setPrefWidth(350);
        label.setPrefHeight(40);
        label.setStyle("-fx-font-size: 24px");
        label.setTextFill(Color.valueOf(WHITE));
    }

    private void createDatePicker() {
        datePicker = new DatePicker();
        datePicker.setPrefWidth(350);
        datePicker.setPrefHeight(80);
        datePicker.setStyle("-fx-font-size: 24px");
        datePicker.setDayCellFactory(getDatePickerDayCellFactory(LocalDate.now()));
    }

    public VBox getContainer() {
        return container;
    }

    private Callback<DatePicker, DateCell> getDatePickerDayCellFactory(LocalDate minDate) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(minDate)) {
                    setDisable(true);
                }
            }
        };
    }
}

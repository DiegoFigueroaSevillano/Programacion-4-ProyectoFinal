package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDate;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;
import static com.example.programacion4proyectofinal.Utils.Styles.FONT_SIZE_24PX;

/**
 * This class represents a section with a date picker for selecting dates.
 */
public class DateSection {

    private DatePicker datePicker;
    private Label label;
    private String title;
    private VBox container;
    private GenerateFont generateFont;

    /**
     * Constructs a DateSection instance with a given title.
     *
     * @param title The title of the date section.
     */
    public DateSection(String title) {
        this.generateFont = new GenerateFont();
        this.title = title;
        createContainer();
    }

    /**
     * Creates the container for the date section.
     */
    private void createContainer() {
        createLabel();
        createDatePicker();

        container = new VBox(0);
        container.setPrefHeight(120);
        container.setPrefWidth(350);
        container.getChildren().addAll(label, datePicker);
    }

    /**
     * Creates the label for the date section.
     */
    private void createLabel() {
        label = new Label(title);
        label.setPrefWidth(350);
        label.setPrefHeight(40);
        label.setFont(generateFont.latoRegular(24));
        label.setTextFill(Color.valueOf(WHITE));
    }

    /**
     * Creates the date picker for selecting dates.
     */
    private void createDatePicker() {
        datePicker = new DatePicker();
        datePicker.setPrefWidth(350);
        datePicker.setPrefHeight(80);
        datePicker.setStyle(FONT_SIZE_24PX);
        datePicker.setDayCellFactory(getDatePickerDayCellFactory(LocalDate.now()));
    }

    /**
     * Gets the container containing the date picker and label.
     *
     * @return The VBox container.
     */
    public VBox getContainer() {
        return container;
    }

    /**
     * Gets the date picker component.
     *
     * @return The DatePicker instance.
     */
    public DatePicker getDatePicker() {
        return datePicker;
    }

    /**
     * Returns a day cell factory that disables days before a certain date.
     *
     * @param minDate The minimum allowed date.
     * @return The Callback for creating DateCells.
     */
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

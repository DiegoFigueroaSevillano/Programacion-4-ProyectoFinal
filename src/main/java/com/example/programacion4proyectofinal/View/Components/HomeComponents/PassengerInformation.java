package com.example.programacion4proyectofinal.View.Components.HomeComponents;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.StringConverter;
import java.util.function.UnaryOperator;

public class PassengerInformation {
    private TextField textField;
    private String title;
    private VBox container;
    private Label nameLabel;
    private Label errorLabel;
    private BackgroundGenerator backgroundGenerator;
    private GenerateFont generateFont;
    private int width;
    private int height;


    /**
     * Constructs a PassengerInformation object with the given title, width, and height.
     *
     * @param title  The title to be displayed.
     * @param width  The preferred width of the input field.
     * @param height The preferred height of the input field.
     */
    public PassengerInformation(String title, int width, int height) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.title = title;
        this.height = height;
        this.width = width;
        this.generateFont = new GenerateFont();
        createContainer();
    }

    /**
     * Validates input in the text field to allow only letters.
     *
     * @param textField The text field to be validated.
     */
    private void validateLetters(TextField textField) {
        UnaryOperator<Change> filter = change -> {
            if (change.isAdded()) {
                if (!change.getText().matches("[a-zA-Z]*")) {
                    change.setText("");
                    showMessageError("Only letters are allowed");
                } else {
                    hideMessageError();
                }
            }
            return change;
        };

        StringConverter<String> converter = new StringConverter<String>() {
            @Override
            public String fromString(String string) {
                return string;
            }

            @Override
            public String toString(String object) {
                return object;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(converter, null, filter);
        textField.setTextFormatter(textFormatter);
    }

    /**
     * Creates the container for the input field and associated labels.
     */
    private void createContainer() {
        createTitle();
        createTextField();

        container = new VBox(0);
        container.setPrefWidth(width);
        container.setPrefHeight(height);
        container.getChildren().addAll(nameLabel, textField, errorLabel);
    }

    /**
     * Creates and configures the title label.
     */
    private void createTitle() {
        nameLabel = new Label(title);
        nameLabel.setPrefWidth(350);
        nameLabel.setPrefHeight(40);
        nameLabel.setTextFill(Color.valueOf("WHITE"));
        nameLabel.setFont(generateFont.latoRegular(24));
    }

    /**
     * Creates and configures the input field.
     */
    private void createTextField() {
        textField = new TextField();
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        textField.setBackground(backgroundGenerator.createBackground("WHITE"));
        textField.setStyle("-fx-font-size: 24px;");

        if (title.equals("IDENTIFY CARD:")) {
            validateNumbers(textField);
        } else {
            validateLetters(textField);
        }

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
    }

    /**
     * Validates input in the text field to allow only numbers.
     *
     * @param textField The text field to be validated.
     */
    private void validateNumbers(TextField textField) {
        UnaryOperator<Change> filter = change -> {
            if (change.isAdded()) {
                if (!change.getText().matches("[0-9]*")) {
                    change.setText("");
                    showMessageError("Only numbers are allowed");
                } else {
                    hideMessageError();
                }
            }
            return change;
        };

        StringConverter<String> converter = new StringConverter<String>() {
            @Override
            public String fromString(String string) {
                return string;
            }

            @Override
            public String toString(String object) {
                return object;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(converter, null, filter);
        textField.setTextFormatter(textFormatter);
    }

    /**
     * Displays an error message.
     *
     * @param messageError The error message to be displayed.
     */
    private void showMessageError(String messageError) {
        errorLabel.setText(messageError);
        errorLabel.setVisible(true);
    }

    /**
     * Hides the error message.
     */
    private void hideMessageError() {
        errorLabel.setVisible(false);
    }

    /**
     * Gets the container that holds the UI elements.
     *
     * @return The container containing the input field and labels.
     */
    public VBox getContainer() {
        return container;
    }
}

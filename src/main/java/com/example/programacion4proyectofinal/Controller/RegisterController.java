package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Pages.Register;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * The RegisterController class is responsible for controlling the interaction between the
 * user interface components in the registration screen and the underlying data logic.
 */
public class RegisterController {
    private Register register;
    private Group root;
    private Stage stage;

    /**
     * Constructs a new RegisterController with the specified root Group and Stage.
     *
     * @param root  The root Group element of the JavaFX scene.
     * @param stage The primary Stage for the application.
     */
    public RegisterController(Group root, Stage stage) {
        this.root = root;
        this.stage = stage;
        this.register = new Register(root, stage);
        addActionRegisterButton();
    }

    /**
     * Sets up the action for the "Register" button, defining what happens when the button is clicked.
     */
    private void addActionRegisterButton() {
        register.getRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // For example, navigate to the next screen or process registration data.
            }
        });
    }

    /**
     * Gets the Register view associated with this controller.
     *
     * @return The Register view managed by this controller.
     */
    public Register getRegisterView() {
        return register;
    }
}

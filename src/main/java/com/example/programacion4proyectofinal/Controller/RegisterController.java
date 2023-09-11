package com.example.programacion4proyectofinal.Controller;


import com.example.programacion4proyectofinal.View.Pages.Register;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * The RegisterController class is responsible for controlling the interaction between the
 * user interface components in the registration screen and the underlying data logic.
 */
public class RegisterController {
    private Register registerView;

    /**
     * Constructs a new RegisterController with the specified root Group and Stage.
     *
     * @param root  The root Group element of the JavaFX scene.
     * @param stage The primary Stage for the application.
     */
    public RegisterController(Group root, Stage stage) {
        registerView = new Register(root, stage);
    }



    /**
     * Gets the Register view associated with this controller.
     *
     * @return The Register view managed by this controller.
     */
    public Register getRegisterView() {
        return registerView;
    }

}

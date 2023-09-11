package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Pages.LogIn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.GREEN;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.RED;

/**
 * The LogInController class handles the logic behind the login view.
 */
public class LogInController {

    private LogIn logInView;
    private Group root;
    private Stage stage;

    /**
     * Constructs a new LogInController with the specified root and stage.
     *
     * @param root  The root group of the application.
     * @param stage The primary stage of the application.
     */
    public LogInController(Group root, Stage stage) {
        this.root = root;
        this.stage = stage;
        this.logInView = new LogIn(root, stage);

        addActionLogInButton();
    }

    /**
     * Adds the action handler to the login button.
     * Validates the input fields and handles login functionality.
     */
    private void addActionLogInButton() {
        TextField usernameField = logInView.getUsernameField();
        PasswordField passwordField = logInView.getPasswordField();
        Label errorMessage = logInView.getErrorMessage();
        logInView.getLogInButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
                    errorMessage.setText("Empty fields!!!");
                } else if (usernameField.getText().isEmpty()) {
                    errorMessage.setText("Username field is empty!!!");
                } else if (passwordField.getText().isEmpty()) {
                    errorMessage.setText("Password field is empty!!!");
                } else {
                    errorMessage.setTextFill(Color.valueOf(GREEN));
                    errorMessage.setText("Loading...");
                    if (verifyUser()) {
                        root = new Group();
                        HomeController home = new HomeController(root, stage);
                        Scene homeScene = home.getHomeView().getHomeScene();
                        stage.setScene(homeScene);
                    } else {
                        errorMessage.setTextFill(Color.valueOf(RED));
                        errorMessage.setText("User not valid!!!");
                    }
                }
            }
        });
    }

    /**
     * Verifies the user's credentials by comparing them with the stored values.
     *
     * @return true if the user is verified, false otherwise.
     */
    private boolean verifyUser() {
        boolean result = false;
        String jsonFilePath = "src/main/resources/com/example/programacion4proyectofinal/JSON/AdminUser.json";
        ObjectMapper objectMapper = new ObjectMapper();

        String username = "";
        String password = "";

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(jsonFilePath));
            username = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        boolean verifyUsername = logInView.getUsernameField().getText().equals(username);
        boolean verifyPassword = logInView.getPasswordField().getText().equals(password);

        if (verifyUsername && verifyPassword) {
            result = true;
        }

        return result;
    }

    /**
     * Get the login view associated with this controller.
     *
     * @return The LogIn view.
     */
    public LogIn getLogInView() {
        return logInView;
    }
}
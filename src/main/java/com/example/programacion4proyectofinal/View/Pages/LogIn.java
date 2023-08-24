package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;
import static com.example.programacion4proyectofinal.Utils.Styles.*;

/**
 * This class represents the login page of the application.
 */
public class LogIn {

    private final Scene logInScene;
    private VBox logInForm;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label username, password, errorMessage;
    private Button logInButton;
    private final  Stage stage;
    private Group root;
    private final String STYLE_FIELDS = BORDER_COLOR_SKY_BLUE + " " + BORDER_SIZE_2PX;
    private GenerateFont generateFont;
    private BackgroundGenerator backgroundGenerator;

    /**
     * Constructs the login page.
     *
     * @param root  The root group for UI components.
     * @param stage The primary stage of the application.
     */
    public LogIn(Group root, Stage stage) {
        this.root = root;
        this.logInScene = new Scene(this.root, Color.valueOf(LIGHT_BLUE));
        this.stage = stage;
        this.stage.setTitle("AEROLAB");
        this.generateFont = new GenerateFont();
        this.backgroundGenerator = new BackgroundGenerator();
        createLogInForm(logInScene);
        root.getChildren().add(logInForm);
    }

    /**
     * Creates and configures the login form UI.
     *
     * @param scene The scene in which the login form will be displayed.
     */
    private void createLogInForm(Scene scene) {
        createUsernameSection();
        createPasswordSection();
        createErrorMessage();
        createLogInButton();
        logInForm = new VBox(20);
        logInForm.setPrefWidth(700);
        logInForm.setPrefHeight(600);
        logInForm.setBackground(backgroundGenerator.createBackground(WHITE));
        logInForm.setPadding(new Insets(60, 40, 60, 40));
        logInForm.setAlignment(Pos.CENTER);
        logInForm.layoutXProperty().bind(scene.widthProperty().subtract(logInForm.widthProperty()).divide(2));
        logInForm.layoutYProperty().bind(scene.heightProperty().subtract(logInForm.heightProperty()).divide(2));
        logInForm.getChildren().addAll(username, usernameField, password, passwordField, errorMessage, logInButton);
    }

    /**
     * Creates and configures the username section of the login form.
     */
    private void createUsernameSection() {
        username = new Label("USERNAME");
        generateLabel(username);
        usernameField = new TextField();
        usernameField.setPrefWidth(600);
        usernameField.setPrefHeight(80);
        usernameField.setStyle(STYLE_FIELDS);
        usernameField.setFont(generateFont.latoBlack(24));
    }

    /**
     * Creates and configures the password section of the login form.
     */
    private void createPasswordSection() {
        password = new Label("PASSWORD");
        generateLabel(password);
        passwordField = new PasswordField();
        passwordField.setPrefWidth(600);
        passwordField.setPrefHeight(80);
        passwordField.setStyle(STYLE_FIELDS + FONT_SIZE_24PX);
    }

    /**
     * Creates and configures the login button in the login form.
     */
    private void createLogInButton() {
        logInButton = new Button("LOG IN");
        logInButton.setPrefWidth(300);
        logInButton.setPrefHeight(100);
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(SKY_BLUE), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        logInButton.setBackground(background);
        logInButton.setCursor(Cursor.HAND);
        logInButton.setTextFill(Color.valueOf(WHITE));
        logInButton.setFont(generateFont.latoBlack(24));
    }

    /**
     * Generates label styling.
     *
     * @param label The label to apply styling to.
     */
    private void generateLabel(Label label) {
        label.setPrefWidth(600);
        label.setTextFill(Color.valueOf(SKY_BLUE));
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(generateFont.latoBlack(24));
    }

    /**
     * Creates and configures the error message label in the login form.
     */
    private void createErrorMessage() {
        errorMessage = new Label();
        errorMessage.setPrefWidth(600);
        errorMessage.setWrapText(true);
        errorMessage.setFont(generateFont.latoRegular(14));
        errorMessage.setTextFill(Color.valueOf(RED));
    }

    /**
     * Gets the login scene.
     *
     * @return The login scene.
     */
    public Scene getLogInScene() {
        return logInScene;
    }

    /**
     * Gets the username field.
     *
     * @return The username field.
     */
    public TextField getUsernameField() {
        return usernameField;
    }

    /**
     * Gets the password field.
     *
     * @return The password field.
     */
    public PasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * Gets the login button.
     *
     * @return The login button.
     */
    public Button getLogInButton() {
        return logInButton;
    }

    /**
     * Gets the error message label.
     *
     * @return The error message label.
     */
    public Label getErrorMessage() {
        return errorMessage;
    }
}

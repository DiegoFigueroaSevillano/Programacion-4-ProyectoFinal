package View.Pages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static Utils.Colors.*;

public class LogIn {

    private final Scene logInScene;
    private VBox logInForm;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label username, password, errorMessage;
    private Button logInButton;
    private final  Stage stage;
    private Group root;

    public LogIn(Group root, Stage stage) {
        this.root = root;
        this.logInScene = new Scene(this.root, Color.valueOf(LIGHT_BLUE));
        this.stage = stage;

        createLogInForm(logInScene);

        root.getChildren().add(logInForm);
    }

    private void createLogInForm(Scene scene) {
        createUsernameSection();
        createPasswordSection();
        createErrorMessage();
        createLogInButton();

        logInForm = new VBox(20);
        logInForm.setPrefWidth(700);
        logInForm.setPrefHeight(600);
        logInForm.setBackground(Background.fill(Color.valueOf(WHITE)));
        logInForm.setPadding(new Insets(60, 40, 60, 40));
        logInForm.setAlignment(Pos.CENTER);
        logInForm.layoutXProperty().bind(scene.widthProperty().subtract(logInForm.widthProperty()).divide(2));
        logInForm.layoutYProperty().bind(scene.heightProperty().subtract(logInForm.heightProperty()).divide(2));
        logInForm.getChildren().addAll(username, usernameField, password, passwordField, errorMessage, logInButton);
    }

    private void createUsernameSection() {
        username = new Label("USERNAME");
        generateLabel(username);

        usernameField = new TextField();
        usernameField.setPrefWidth(600);
        usernameField.setPrefHeight(80);
        usernameField.setStyle("-fx-font-size: 24px; -fx-border-color: #26C6DA; -fx-border-width: 2px");
    }

    private void createPasswordSection() {
        password = new Label("PASSWORD");
        generateLabel(password);

        passwordField = new PasswordField();
        passwordField.setPrefWidth(600);
        passwordField.setPrefHeight(80);
        passwordField.setStyle("-fx-font-size: 24px; -fx-border-color: #26C6DA; -fx-border-width: 2px");
    }

    private void createLogInButton() {
        logInButton = new Button("LOG IN");
        logInButton.setPrefWidth(300);
        logInButton.setPrefHeight(100);
        logInButton.setBackground(Background.fill(Color.valueOf(SKY_BLUE)));
        logInButton.setCursor(Cursor.HAND);
        logInButton.setTextFill(Color.valueOf(WHITE));
        logInButton.setStyle("-fx-font-size: 24px");
        logInButton.setOnAction(new EventHandler<ActionEvent>() {
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
                    root = new Group();
                    Home home = new Home(root, stage);
                    Scene homeScene = home.getHomeScene();
                    stage.setScene(homeScene);
                }
            }
        });
    }

    private void generateLabel(Label label) {
        label.setPrefWidth(600);
        label.setStyle("-fx-font-size: 24px; -fx-content-display: left");
        label.setTextFill(Color.valueOf(SKY_BLUE));
    }

    private void createErrorMessage() {
        errorMessage = new Label();
        errorMessage.setPrefWidth(600);
        errorMessage.setWrapText(true);
        errorMessage.setStyle("-fx-font-size: 14px;");
        errorMessage.setTextFill(Color.valueOf(RED));
    }

    public Scene getLogInScene() {
        return logInScene;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }
}

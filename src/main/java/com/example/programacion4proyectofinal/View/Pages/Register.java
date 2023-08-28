package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.PassengerType;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.Header;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.PlacesList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

/**
 * Class representing a passenger registration form.
 */
public class Register {
    private final Scene registerScene;
    private VBox register, ticketForm, name, ci, passengerType , container;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private Button createButton;
    private Label label ;
    private TextField textField ;

    /**
     * Constructor for the Register class.
     *
     * @param root  The root node group.
     * @param stage The main application window.
     */
    public Register(Group root, Stage stage) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("Register");
        this.registerScene = new Scene(root);
        this.header = new Header(stage, "register");
        createRegister(registerScene);
        root.getChildren().add(register);
    }

    /**
     * Creates the main registration interface.
     *
     * @param scene The scene for the registration interface.
     */
    private void createRegister(Scene scene) {
        createTicketSection(scene);
        register = new VBox(0);
        register.prefHeightProperty().bind(stage.heightProperty());
        register.prefWidthProperty().bind(stage.widthProperty());
        register.setAlignment(Pos.CENTER);
        register.layoutXProperty().bind(scene.widthProperty().subtract(register.widthProperty()).divide(2));
        register.layoutYProperty().bind(scene.heightProperty().subtract(register.heightProperty()).divide(2));
        register.getChildren().addAll(header.getHeader(), ticketSection);
    }

    /**
     * Creates the ticket section within the interface.
     *
     * @param scene The scene for the ticket section.
     */
    private void createTicketSection(Scene scene) {
        createTicketForm();
        ticketSection = new StackPane();
        ticketSection.prefWidthProperty().bind(scene.widthProperty());
        ticketSection.prefHeightProperty().bind(scene.heightProperty().subtract(60));
        ticketSection.setBackground(backgroundGenerator.createBackground(WHITE));
        ticketForm.setAlignment(Pos.CENTER);
        ticketSection.getChildren().add(ticketForm);
    }

    /**
     * Creates the ticket form within the ticket section.
     */
    private void createTicketForm() {
        createNameSection();
        createPlacesSection();
        createCiSection();
        createCreateButton();

        ticketForm = new VBox(40);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(850);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(850);
        ticketForm.setPadding(new Insets(10));
        ticketForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE));
        ticketForm.getChildren().addAll(name, ci,passengerType,createButton);
    }

    /**
     * Creates the passenger type section within the ticket form.
     */
    private void createPlacesSection() {
        ObservableList<String> placesList = FXCollections.observableArrayList(PassengerType.PASSENGER_TYPE);
        PlacesList fromList = new PlacesList(placesList, "TYPE:");
        passengerType = fromList.getContainer();
    }

    /**
     * Creates the CI (Identification Card) section within the ticket form.
     */

    private void createCiSection() {
        createContainer("CI :");
        ci = getContainer();
    }

    /**
     * Creates the name section within the ticket form.
     */
    private void createNameSection() {
        createContainer("NAME :");
        name = getContainer();
    }

    /**
     * Creates the "CREATE" button within the ticket form.
     */
    private void createCreateButton() {
        createButton = new Button("CREATE");
        createButton.setTextFill(Color.valueOf(WHITE));
        createButton.setBackground(backgroundGenerator.createBackground(BLACK));
        createButton.setPrefWidth(740);
        createButton.setPrefHeight(80);
        createButton.setMaxHeight(740);
        createButton.setCursor(Cursor.HAND);
        createButton.setStyle("-fx-font-size: 24px");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

    }

    /**
     * Creates a container with a label and text field.
     *
     * @param name The name to be displayed in the label.
     */
    private void createContainer(String name) {
        createLabel(name);
        createTextField();
        container = new VBox(0);
        container.setPrefWidth(740);
        container.setPrefHeight(120);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(label, textField);
    }

    /**
     * Creates a label within a container.
     *
     * @param name The text to be displayed in the label.
     */
    private void createLabel(String name) {
        label = new Label(name);
        label.setPrefWidth(740);
        label.setPrefHeight(40);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-font-size: 24px");
        label.setTextFill(Color.valueOf(WHITE));
    }

    /**
     * Creates a text field within a container.
     */
    private void createTextField() {
        textField = new TextField();
        textField.setPrefWidth(740);
        textField.setMaxWidth(740);
        textField.setPrefHeight(80);
        textField.setStyle("-fx-font-size: 24px");
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        textField.setBackground(backgroundGenerator.createBackground(WHITE));
    }

    /**
     * Retrieves the container VBox containing registration form elements.
     *
     * @return The container VBox for registration form elements.
     */
    public VBox getContainer() {
        return container;
    }

    /**
     * Retrieves the Scene associated with the Register view.
     *
     * @return The Scene object representing the registration screen.
     */
    public Scene getRegisterScene() {
        return registerScene;
    }

    /**
     * Retrieves the "CREATE" button used for registration submission.
     *
     * @return The "CREATE" Button element for registration submission.
     */
    public Button getRegisterButton() {
        return createButton;
    }
}

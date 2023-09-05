package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.*;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.*;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
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
    private VBox register, ticketForm, name, container,ci;
    private HBox passengerType;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private Button createButton;
    private Label label ;
    private TextField textField ;

    private GenerateFont generateFont;

    /**
     * Constructs the register page.
     *
     * @param root  The root group for UI components.
     * @param stage The primary stage of the application.
     */
    public Register(Group root, Stage stage) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage=new ChangePropertiesStage();
        this.generateFont = new GenerateFont();
        this.stage = stage;
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("REGISTER - AEROLAB");

        this.registerScene = new Scene(root);
        this.header = new Header(root,stage, "register");

        createHome(registerScene);
        root.getChildren().add(register);
    }

    /**
     * Creates and configures the home page UI.
     *
     * @param scene The scene in which the home page will be displayed.
     */
    private void createHome(Scene scene) {
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
     * Creates and configures the ticket section UI.
     *
     * @param scene The scene in which the ticket section will be displayed.
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
     * Creates and configures the ticket form UI.
     */
    private void createTicketForm() {
        createPlacesSection();
        createNameSection();
        createCiSection();
        createCreateButton();

        ticketForm = new VBox(40);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(700);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(700);
        ticketForm.setPadding(new Insets(10));
        ticketForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE));
        ticketForm.getChildren().addAll(name, ci,passengerType,createButton);
    }

    /**
     * Creates and configures the places section UI.
     */
    private void createPlacesSection() {
        ObservableList<String> placesList = FXCollections.observableArrayList(PassengerType.PASSENGER_TYPE);

        PlacesList fromList = new PlacesList(placesList, "REGISTER:" , 740 ,740);
        passengerType = new HBox(40);
        passengerType.setPrefWidth(900);
        passengerType.setPrefHeight(120);
        passengerType.getChildren().addAll(fromList.getContainer());
        passengerType.setAlignment(Pos.CENTER);


    }

    /**
     * Creates the name section
     */
    private void createNameSection() {
        createContainer("NAME: :");
        name = getContainer();
    }

    /**
     * Creates the ci section
     */
    private void createCiSection() {
        createContainer("CI :");
        ci = getContainer();
    }
    /**
     * Creates and configures the create button UI.
     */
    private void createCreateButton() {
        createButton = new Button("CREATE");
        createButton.setTextFill(Color.valueOf(WHITE));
        createButton.setBackground(backgroundGenerator.createBackground(BLACK));
        createButton.setPrefWidth(740);
        createButton.setPrefHeight(80);
        createButton.setMaxHeight(740);
        createButton.setCursor(Cursor.HAND);
        createButton.setFont(generateFont.latoRegular(24));
    }

    /**
     * Gets the Register scene.
     *
     * @return The home scene.
     */
    public Scene getRegisterScene() {
        return registerScene;
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
        label.setFont(generateFont.latoRegular(24));
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
        textField.setFont(generateFont.latoRegular(24));
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
}

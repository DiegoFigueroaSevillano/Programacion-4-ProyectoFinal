package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Controller.HeaderController;
import com.example.programacion4proyectofinal.Controller.HomeController;
import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import com.example.programacion4proyectofinal.Utils.ViewUtils.PassengerType;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.PassengerInformation;
import com.example.programacion4proyectofinal.View.Components.HomeComponents.PlacesList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.*;

/**
 * Class representing a passenger registration form.
 */
public class Register {
    private final Scene registerScene;
    private VBox register, ticketForm;
    private HBox passengerType , sectionNames , sectionCountryCi;
    private Stage stage;
    private HeaderController header;
    private StackPane ticketSection;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private Button createButton;
    private PassengerInformation passengerName,passengerLastName,passengerCi,passengerCountry;
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
        this.changePropertiesStage.changeToMaximizeSizeStage(this.stage);
        this.stage.setTitle("REGISTER - AEROLAB");

        this.registerScene = new Scene(root);
        this.header = new HeaderController(root, stage, "register");

        creationRegister(registerScene);
        root.getChildren().add(register);
        setButtonRegisterAction();
    }

    /**
     * Creates and configures the register page UI.
     *
     * @param scene The scene in which the home page will be displayed.
     */
    private void creationRegister(Scene scene) {
        createTicketSection(scene);
        register = new VBox(0);
        register.prefHeightProperty().bind(stage.heightProperty());
        register.prefWidthProperty().bind(stage.widthProperty());
        register.setAlignment(Pos.CENTER);
        register.layoutXProperty().bind(scene.widthProperty().subtract(register.widthProperty()).divide(2));
        register.layoutYProperty().bind(scene.heightProperty().subtract(register.heightProperty()).divide(2));
        register.getChildren().addAll(header.getHeader().getHeader(), ticketSection);
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
        createCountryAndCiSection();
        createCreateButton();

        ticketForm = new VBox(40);
        ticketForm.setPrefWidth(900);
        ticketForm.setPrefHeight(700);
        ticketForm.setMaxWidth(900);
        ticketForm.setMaxHeight(700);
        ticketForm.setPadding(new Insets(10));
        ticketForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE));
        ticketForm.getChildren().addAll(sectionNames, sectionCountryCi,passengerType,createButton);
    }
    private PlacesList fromList;

    /**
     * Creates and configures the places section UI.
     */
    private void createPlacesSection() {
        ObservableList<String> placesList = FXCollections.observableArrayList(PassengerType.PASSENGER_TYPE);
        fromList = new PlacesList(placesList, "TYPE PASSENGER:" , 740 ,740);
        passengerType = new HBox(40);
        passengerType.setPrefWidth(900);
        passengerType.setPrefHeight(120);
        passengerType.getChildren().addAll(fromList.getContainer());
        passengerType.setAlignment(Pos.CENTER);


    }

    /**
     * Gets the category of the passenger.
     * @return The category of the passenger.
     */
    private Category getCategory() {
        Category category = null;
        switch (fromList.getPlacesList().getValue()) {
            case "VIP":
                category = Category.VIP;
                break;
            case "FREQUENT_PASSENGER":
                category = Category.FREQUENT_PASSENGER;
                break;
            case "REGULAR_PASSENGER":
                category = Category.REGULAR_PASSENGER;
                break;
        }
        return category;
    }

    /**
     * Creates the names section
     */
    private void createNameSection() {
        passengerName=new PassengerInformation("NAME:" , 350,120);
        passengerLastName = new PassengerInformation("LAST NAME:" ,350,120);
        sectionNames =new HBox(40);
        sectionNames.setPrefHeight(120);
        sectionNames.setPrefWidth(900);
        sectionNames.getChildren().addAll(passengerName.getContainer(), passengerLastName.getContainer());
        sectionNames.setAlignment(Pos.CENTER);
    }

    /**
     * Creates the ci section
     */
    private void createCountryAndCiSection() {
        passengerCi=new PassengerInformation("IDENTIFY CARD:" , 350,120);
        passengerCountry = new PassengerInformation("COUNTRY:" ,350,120);
        sectionCountryCi =new HBox(40);
        sectionCountryCi.setPrefHeight(120);
        sectionCountryCi.setPrefWidth(900);
        sectionCountryCi.getChildren().addAll(passengerCi.getContainer(), passengerCountry.getContainer());
        sectionCountryCi.setAlignment(Pos.CENTER);
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
     * @return The register scene.
     */
    public Scene getRegisterScene() {
        return registerScene;
    }

    /**
     * Sets the action for the create button.
     */
    private void setButtonRegisterAction() {
        createButton.setOnAction(event -> {
            BTree<Passenger> passengerBTree = new BTree<>(10, new FileHandlerBTree());
            Passenger passenger = new Passenger(Integer.parseInt(passengerCi.getTextField().getText()), passengerName.getTextField().getText(),
                    passengerLastName.getTextField().getText(), passengerCountry.getTextField().getText(), getCategory());
            if (passengerBTree.insertKey(passenger)) {
                passengersView();
            } else {
                if (ticketForm.getChildren().size() == 5)
                    ticketForm.getChildren().remove(4);
                ticketForm.getChildren().add(new Label("The passenger already exists"));
            }
        });
    }

    /**
     * This method change the view to the passengers view
     */
    private void passengersView() {
        Group root = new Group();
        HomeController home = new HomeController(root, stage);
        Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");

        Scene homeScene = home.getHomeView().getHomeScene();
        stage.setScene(homeScene);
        stage.getIcons().add(iconApp);
        stage.show();
    }
}

package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Controller.HeaderController;
import com.example.programacion4proyectofinal.Utils.GeneratorBorders;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.SKY_BLUE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;

public class Passengers {

    private Scene passengersScene;
    private VBox generalContainer;
    private HeaderController headerController;
    private Group root;
    private Stage stage;
    private ChangePropertiesStage changePropertiesStage;
    private HBox searchContainer, paginationContainer;
    private TextField searchField, paginationField;
    private Button searchButton, leftTenPaginationButton, leftPaginationButton, rightPaginationButton, rightTenPaginationButton;
    private BackgroundGenerator backgroundGenerator;
    private GenerateFont generateFont;
    private GeneratorBorders generatorBorders;
    private ScrollPane containerPassengers;
    private VBox passengersList;
    private ObservableList<HBox> passengersComponents;

    public Passengers(Group root, Stage stage, ObservableList<HBox> passengers) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.generateFont = new GenerateFont();
        this.generatorBorders = new GeneratorBorders();
        this.root = root;
        this.stage = stage;
        this.changePropertiesStage = new ChangePropertiesStage();
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("PASSENGERS - AEROLAB");
        this.passengersScene = new Scene(root);
        this.headerController = new HeaderController(root, stage, "passengers");
        this.passengersComponents = passengers;
        createGeneralContainer(this.passengersScene);
        this.root.getChildren().add(generalContainer);
    }

    private void createGeneralContainer(Scene scene) {
        createSearchContainer();
        createPagination();

        generalContainer = new VBox();
        generalContainer.prefHeightProperty().bind(stage.heightProperty());
        generalContainer.prefWidthProperty().bind(stage.widthProperty());
        generalContainer.setAlignment(Pos.CENTER);
        generalContainer.layoutXProperty().bind(scene.widthProperty().subtract(generalContainer.widthProperty()).divide(2));
        generalContainer.layoutYProperty().bind(scene.heightProperty().subtract(generalContainer.heightProperty()).divide(2));
        createPassengersContainer();
        generalContainer.getChildren().addAll(headerController.getHeader().getHeader(), searchContainer, containerPassengers, paginationContainer);
    }

    private void createSearchContainer() {
        searchField = new TextField();
        searchField.setPrefHeight(60);
        searchField.setPrefWidth(700);
        searchField.setFont(generateFont.latoLight(24));
        searchField.setBackground(backgroundGenerator.createBackground(WHITE));
        searchField.setBorder(generatorBorders.createBorderRadiusSolid(40, 2, SKY_BLUE));
        searchField.setPromptText("Search...");

        Image searchIconImage = new Image(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Icons/lupa.png"));
        ImageView searchIcon = new ImageView(searchIconImage);

        searchButton = new Button();
        searchButton.setBackground(backgroundGenerator.createBackgroundRadius(40, SKY_BLUE));
        searchButton.setPrefWidth(60);
        searchButton.setPrefHeight(60);
        searchButton.setCursor(Cursor.HAND);
        searchButton.setGraphic(searchIcon);

        searchContainer = new HBox(10);
        searchContainer.prefWidthProperty().bind(stage.widthProperty());
        searchContainer.setPrefHeight(100);
        searchContainer.setAlignment(Pos.CENTER);
        searchContainer.getChildren().addAll(searchField, searchButton);
    }

    private void createPassengersContainer() {
        containerPassengers = new ScrollPane();
        containerPassengers.prefWidthProperty().bind(generalContainer.widthProperty());
        containerPassengers.prefHeightProperty().bind(generalContainer.heightProperty().subtract(300));
        containerPassengers.setPadding(new Insets(20));
        containerPassengers.setBackground(backgroundGenerator.createBackground(WHITE));
        containerPassengers.setFitToWidth(true);
        containerPassengers.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        containerPassengers.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        containerPassengers.setPannable(true);

        ScrollBar vScrollBar = (ScrollBar) containerPassengers.lookup(".scroll-bar:vertical");
        if (vScrollBar != null) {
            vScrollBar.setStyle("-fx-control-inner-background: #" + SKY_BLUE + ";");
        }

        createPassengersList();

        containerPassengers.setContent(passengersList);
    }

    private void createPassengersList() {
        passengersList = new VBox(10);
        passengersList.prefWidthProperty().bind(containerPassengers.widthProperty().subtract(200));
        passengersList.prefHeightProperty().bind(containerPassengers.heightProperty().subtract(60));
        passengersList.setBackground(backgroundGenerator.createBackground(WHITE));
        deleteAComponent();
        passengersList.setPadding(new Insets(40));
        passengersList.setFillWidth(true);
    }

    public void deleteAComponent() {
        passengersList.getChildren().clear();
        for (HBox passenger : passengersComponents) {
            VBox.setVgrow(passenger, Priority.ALWAYS);
            passengersList.getChildren().add(passenger);
        }
    }

    private void createPagination() {

        leftTenPaginationButton = generatePaginationButton("/com/example/programacion4proyectofinal/Icons/back.png");
        leftPaginationButton = generatePaginationButton("/com/example/programacion4proyectofinal/Icons/left-arrow.png");
        rightPaginationButton = generatePaginationButton("/com/example/programacion4proyectofinal/Icons/right-arrow.png");
        rightTenPaginationButton = generatePaginationButton("/com/example/programacion4proyectofinal/Icons/next.png");

        paginationField = new TextField();
        paginationField.setBorder(generatorBorders.createBorderRadiusSolid(10, 2, SKY_BLUE));
        paginationField.setBackground(backgroundGenerator.createBackgroundRadius(10, WHITE));
        paginationField.setPrefWidth(100);
        paginationField.setPrefHeight(60);
        paginationField.setEditable(false);
        paginationField.setText("1");
        paginationField.setAlignment(Pos.CENTER);
        paginationField.setFont(generateFont.latoLight(24));

        paginationContainer = new HBox(10);
        paginationContainer.prefWidthProperty().bind(stage.widthProperty());
        paginationContainer.setPrefHeight(100);
        paginationContainer.setAlignment(Pos.CENTER);
        paginationContainer.getChildren().addAll(leftTenPaginationButton, leftPaginationButton, paginationField, rightPaginationButton, rightTenPaginationButton);
    }

    private Button generatePaginationButton(String image) {
        Image paginationIconImage = new Image(getClass().getResourceAsStream(image));
        ImageView paginationIcon = new ImageView(paginationIconImage);
        Button paginationButton = new Button();
        paginationButton.setGraphic(paginationIcon);
        paginationButton.setPrefWidth(60);
        paginationButton.setPrefHeight(60);
        paginationButton.setBackground(backgroundGenerator.createBackgroundRadius(10, SKY_BLUE));
        paginationButton.setCursor(Cursor.HAND);
        return paginationButton;
    }

    public Scene getPassengersScene() {
        return passengersScene;
    }

    public VBox getPassengersList() {
        return passengersList;
    }

    public void setPassengersComponents(ObservableList<HBox> passengersComponents) {
        this.passengersComponents = passengersComponents;
    }

    public Button getLeftPaginationButton() {
        return leftPaginationButton;
    }

    public Button getLeftTenPaginationButton() {
        return leftTenPaginationButton;
    }

    public Button getRightTenPaginationButton() {
        return rightTenPaginationButton;
    }

    public Button getRightPaginationButton() {
        return rightPaginationButton;
    }

    public TextField getPaginationField() {
        return paginationField;
    }
}

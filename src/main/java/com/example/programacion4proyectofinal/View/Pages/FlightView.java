package com.example.programacion4proyectofinal.View.Pages;


import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.SKY_BLUE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;


/**
 * The FlightView class represents the view for flight information and components.
 */
public class FlightView {

    private Scene flightScene;
    private VBox generalContainer;
    private Header header;
    private Group root;
    private Stage stage;
    private ChangePropertiesStage changePropertiesStage;
    private BackgroundGenerator backgroundGenerator;
    private ScrollPane containerPassengers;
    private VBox flightList;
    private ObservableList<Button> flightComponent;

    public FlightView(Group root, Stage stage, ObservableList<Button> passengers) {
        this.backgroundGenerator = new BackgroundGenerator();
        this.root = root;
        this.stage = stage;
        this.changePropertiesStage = new ChangePropertiesStage();
        this.changePropertiesStage.changeToMaximizeSizeStage(950, 900, this.stage);
        this.stage.setTitle("FLIGHT - AEROLAB");
        this.flightScene = new Scene(root);
        this.header = new Header(root, stage, "flight");
        this.flightComponent = passengers;
        createGeneralContainer(this.flightScene);
        this.root.getChildren().add(generalContainer);
    }

    /**
     * Creates the general container for the flight view.
     *
     * @param scene The Scene in which the general container is created.
     */

    private void createGeneralContainer(Scene scene) {
        generalContainer = new VBox();
        generalContainer.prefHeightProperty().bind(stage.heightProperty());
        generalContainer.prefWidthProperty().bind(stage.widthProperty());
        generalContainer.setAlignment(Pos.CENTER);
        generalContainer.layoutXProperty().bind(scene.widthProperty().subtract(generalContainer.widthProperty()).divide(2));
        generalContainer.layoutYProperty().bind(scene.heightProperty().subtract(generalContainer.heightProperty()).divide(2));
        createFlightContainer();
        generalContainer.getChildren().addAll(header.getHeader(), containerPassengers);
    }

    /**
     * Creates the container for displaying flight components.
     */
    private void createFlightContainer() {
        containerPassengers = new ScrollPane();
        containerPassengers.prefWidthProperty().bind(generalContainer.widthProperty());
        containerPassengers.prefHeightProperty().bind(generalContainer.heightProperty().subtract(200));
        containerPassengers.setPadding(new Insets(20));
        containerPassengers.setBackground(backgroundGenerator.createBackground(WHITE));
        containerPassengers.setFitToWidth(true);
        containerPassengers.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        containerPassengers.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        containerPassengers.setPannable(true);

        ScrollBar vScrollBar = (ScrollBar) containerPassengers.lookup(".scroll-bar:vertical");
        if (vScrollBar != null) {
            vScrollBar.setStyle( SKY_BLUE );
        }

        createFlightList();

        containerPassengers.setContent(flightList);
    }

    /**
     * Creates the list of flight components.
     */
    private void createFlightList() {
        flightList = new VBox(10);
        flightList.prefWidthProperty().bind(containerPassengers.widthProperty().subtract(200));
        flightList.prefHeightProperty().bind(containerPassengers.heightProperty().subtract(60));
        flightList.setBackground(backgroundGenerator.createBackground(WHITE));
        deleteAComponent();
        flightList.setPadding(new Insets(40));
        flightList.setFillWidth(true);
    }

    /**
     * Clears and updates the flight list with new components.
     */
    public void deleteAComponent() {
        flightList.getChildren().clear();
        flightList.getChildren().clear();
        for (Button passenger : flightComponent) {
            VBox.setVgrow(passenger, Priority.ALWAYS);
            flightList.getChildren().add(passenger);
        }
    }

    /**
     * Get the FlightScene associated with the view.
     *
     * @return The FlightScene object.
     */
    public Scene getFlightScene() {
        return flightScene;
    }

}
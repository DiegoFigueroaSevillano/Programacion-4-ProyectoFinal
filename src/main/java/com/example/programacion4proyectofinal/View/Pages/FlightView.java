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


import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.ViewUtils.Colors;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.SKY_BLUE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;


/**
 * The FlightView class represents the view for flight information and components.
 */
public class FlightView {

    private Pane changeDispenserSection;
    private VBox changeDispenserPanel;
    private Header header;
    private BackgroundGenerator backgroundGenerator;
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox itemsContainer;
    private HBox flightInfoContainer;
    private Label originLabel , departureDateLabel,arrivalDAteLabel,costLAbel ,addLAbel;
    private Label destinyLabel;
    private Label airlineLabel;
    private final Scene passengerOfAFlightScene;



    public FlightView(Group root, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.stage = stage;
        this.stage.setTitle("FLIGHTS-AEROLAB");
        this.passengerOfAFlightScene = new Scene(root);
        this.header = new Header(root, stage, "flight");
        createChangeDispenser(this.passengerOfAFlightScene);
        root.getChildren().add(changeDispenserPanel);
    }

    /**
     * This method return us the scene
     *
     * @return the scene
     */
    public Scene getPassengerOfAFlightScene() {
        return passengerOfAFlightScene;
    }


    /**
     * Gets the ScrollPane used for displaying flight information.
     *
     * @return The ScrollPane instance.
     */
    public ScrollPane getScrollPane() {
        return scrollPane;
    }


    /**
     * Gets the VBox used for containing flight items.
     *
     * @return The VBox instance.
     */
    public VBox getItemsContainer() {
        return itemsContainer;
    }


    /**
     * Creates the change dispenser section within the scene.
     *
     * @param scene The JavaFX Scene where the change dispenser section is created.
     */

    private void createChangeDispenser(Scene scene){
        this.changeDispenserPanel = new VBox(0);
        this.changeDispenserPanel.prefWidthProperty().bind(stage.widthProperty());
        this.changeDispenserPanel.prefHeightProperty().bind(stage.heightProperty());

        createChangeDispenserSection(scene);

        this.changeDispenserPanel.getChildren().addAll(header.getHeader(), changeDispenserSection);
    }


    /**
     * Creates the change dispenser section within the scene.
     *
     * @param scene The JavaFX Scene where the change dispenser section is created.
     */

    private void createChangeDispenserSection(Scene scene){
        this.changeDispenserSection = new Pane();
        this.changeDispenserSection.prefWidthProperty().bind(scene.widthProperty());
        this.changeDispenserSection.prefHeightProperty().bind(scene.heightProperty());
        this.changeDispenserSection.setBackground(backgroundGenerator.createBackground(Colors.WHITE));

        createScrollPane(changeDispenserSection);
        createFlightInfoContainer(changeDispenserSection);

        this.changeDispenserSection.getChildren().addAll(scrollPane, flightInfoContainer);

    }

    /**
     * Creates the ScrollPane for displaying flight items within a specified pane.
     *
     * @param pane The parent pane where the ScrollPane is created.
     */
    public void createScrollPane(Pane pane){
        this.scrollPane = new ScrollPane();
        this.scrollPane.prefHeightProperty().bind(pane.heightProperty().multiply(0.7));
        this.scrollPane.prefWidthProperty().bind(pane.widthProperty().multiply(0.7));
        this.scrollPane.setStyle("-fx-background-color: rgba(0,138,157,0.5); -fx-background-radius: 10 10 10 10; -fx-border-radius: 10 10 10 10;");
        this.scrollPane.layoutXProperty().bind(pane.widthProperty().subtract(this.scrollPane.widthProperty()).divide(2));
        this.scrollPane.layoutYProperty().bind(pane.heightProperty().subtract(this.scrollPane.heightProperty()).multiply(0.65));
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        createItemContainer(scrollPane);
        this.scrollPane.setContent(this.itemsContainer);

    }

    /**

     * Creates the VBox for containing flight items within a ScrollPane.
     *
     * @param pane The parent ScrollPane where the VBox is created.
     */
    private void createItemContainer(ScrollPane pane){
        this.itemsContainer = new VBox(10);
        this.itemsContainer.prefHeightProperty().bind(pane.heightProperty());
        this.itemsContainer.prefWidthProperty().bind(pane.widthProperty());
    }

    /**
     * Creates the HBox for containing flight information within a specified pane.
     *
     * @param pane The parent pane where the HBox is created.
     */

    private void createFlightInfoContainer(Pane pane){
        this.flightInfoContainer = new HBox(5);
        this.flightInfoContainer.setAlignment(Pos.CENTER_LEFT);
        this.flightInfoContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.05));
        this.flightInfoContainer.prefWidthProperty().bind(this.scrollPane.widthProperty());
        this.flightInfoContainer.layoutXProperty().bind(pane.widthProperty().
                subtract(this.flightInfoContainer.widthProperty()).divide(2));
        this.flightInfoContainer.layoutYProperty().bind(pane.heightProperty().
                subtract(this.flightInfoContainer.heightProperty()).multiply(0.14));
        this.flightInfoContainer.setStyle("-fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, reflect, #00aed0, #00b7ad); -fx-background-radius: 10;");


        createDestinyLabel(this.flightInfoContainer);
        createAirlineLabel(this.flightInfoContainer);
        createOriginLabel(this.flightInfoContainer);
        createDepartureDateLabel(this.flightInfoContainer);
        createArrivalDAteLabel(this.flightInfoContainer);
        createCostLabel(this.flightInfoContainer);
        createAddButtonLabel(this.flightInfoContainer);


        this.flightInfoContainer.getChildren().addAll(airlineLabel, originLabel ,departureDateLabel,arrivalDAteLabel,destinyLabel,costLAbel,addLAbel);
    }


    /**
     * Creates and configures the airline Label for displaying airline information.
     *
     * @param container The parent HBox container where the airline Label is added.
     */

    private void createAirlineLabel(HBox container){
        this.airlineLabel = new Label();
        this.airlineLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.airlineLabel.setAlignment(Pos.CENTER);
        this.airlineLabel.setText("Airline");
        this.airlineLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.airlineLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.airlineLabel, 10);
    }

    /**
     * Creates and configures the departure date Label for displaying departure date information.
     *
     * @param container The parent HBox container where the departure date Label is added.
     */
    private void createDepartureDateLabel(HBox container) {
        this.departureDateLabel = new Label();
        this.departureDateLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.departureDateLabel.setAlignment(Pos.CENTER);
        this.departureDateLabel.setText("Departure Date");
        this.departureDateLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.departureDateLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.departureDateLabel, 10);
    }

    /**
     * Creates and configures the add button Label with empty text for layout purposes.
     *
     * @param container The parent HBox container where the add button Label is added.
     */
    private void createAddButtonLabel(HBox container) {
        this.addLAbel = new Label();
        this.addLAbel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.addLAbel.setAlignment(Pos.CENTER);
        this.addLAbel.setText("        ");
        this.addLAbel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.addLAbel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.addLAbel, 10);
    }

    /**
     * Creates and configures the destiny Label for displaying destination information.
     *
     * @param container The parent HBox container where the destiny Label is added.
     */
    private void createDestinyLabel(HBox container) {
        this.destinyLabel = new Label();
        this.destinyLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.destinyLabel.setAlignment(Pos.CENTER);
        this.destinyLabel.setText("Destination City");
        this.destinyLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.destinyLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.destinyLabel, 10);
    }

    /**
     * Creates and configures the arrival date Label for displaying arrival date information.
     *
     * @param container The parent HBox container where the arrival date Label is added.
     */
    private void createArrivalDAteLabel(HBox container) {
        this.arrivalDAteLabel = new Label();
        this.arrivalDAteLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.arrivalDAteLabel.setAlignment(Pos.CENTER);
        this.arrivalDAteLabel.setText("Arrival Date");
        this.arrivalDAteLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.arrivalDAteLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.arrivalDAteLabel, 10);
    }

    /**
     * Creates and configures the cost Label for displaying cost information.
     *
     * @param container The parent HBox container where the cost Label is added.
     */
    private void createCostLabel(HBox container) {
        this.costLAbel = new Label();
        this.costLAbel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.costLAbel.setAlignment(Pos.CENTER);
        this.costLAbel.setText("Cost");
        this.costLAbel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.costLAbel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.costLAbel, 10);
    }

    /**
     * Creates and configures the origin Label for displaying origin information.
     *
     * @param container The parent HBox container where the origin Label is added.
     */
    private void createOriginLabel(HBox container) {
        this.originLabel = new Label();
        this.originLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.originLabel.setAlignment(Pos.CENTER);
        this.originLabel.setText("Origin City");
        this.originLabel.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        this.originLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.18));
        setTextResponsiveLabel(this.originLabel, 10);
    }

    /**
     * Sets the text of a Label and adjusts its font size responsively based on its width.
     *
     * @param label   The Label to set text and adjust font size for.
     * @param divisor The divisor used to calculate the new font size.
     */

    private void setTextResponsiveLabel(Label label, int divisor){
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / divisor;
            label.setStyle(label.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

}
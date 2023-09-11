package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Controller.HeaderController;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.Colors;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class was created for show the passengers of a flight
 */
public class PassengerOfAFlight {
    private Pane changeDispenserSection;
    private VBox changeDispenserPanel;
    private HeaderController header;
    private BackgroundGenerator backgroundGenerator;
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox itemsContainer;
    private HBox flightInfoContainer;
    private Label originLabel;
    private Label destinyLabel;
    private Label airlineLabel;
    private final Scene passengerOfAFlightScene;


    /**
     * Constructs the page
     *
     * @param root  The root group for UI components.
     * @param stage The primary stage of the application.
     */
    public PassengerOfAFlight(Group root, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.stage = stage;
        this.stage.setTitle("PASSENGERS OF A FLIGHT");
        this.passengerOfAFlightScene = new Scene(root);
        this.header = new HeaderController(root, stage, "flight");
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
     * This method return us the scroll pane
     *
     * @return scroll pane
     */
    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * This method return us the items container
     *
     * @return items container
     */
    public VBox getItemsContainer() {
        return itemsContainer;
    }

    /**
     * This method return us the origin label
     *
     * @return origin label
     */
    public Label getOriginLabel() {
        return originLabel;
    }

    /**
     * this method return us the destiny label
     *
     * @return destiny label
     */
    public Label getDestinyLabel() {
        return destinyLabel;
    }

    /**
     * This method return us the airline label
     *
     * @return the airline label
     */
    public Label getAirlineLabel() {
        return airlineLabel;
    }

    /**
     * Method that creates the principal pane of the view
     *
     * @param scene the scene of the view
     */
    private void createChangeDispenser(Scene scene){
        this.changeDispenserPanel = new VBox(0);
        this.changeDispenserPanel.prefWidthProperty().bind(stage.widthProperty());
        this.changeDispenserPanel.prefHeightProperty().bind(stage.heightProperty());

        createChangeDispenserSection(scene);

        this.changeDispenserPanel.getChildren().addAll(header.getHeader().getHeader(), changeDispenserSection);
    }

    /**
     * Method that creates the pane were our items was created
     *
     * @param scene the scene of the view
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
     * Method that creates the fill of our basic pane
     *
     * @param pane the pane of were we introduced this fill
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
     * This method create the item container
     *
     * @param pane the scroll pane
     */
    private void createItemContainer(ScrollPane pane){
        this.itemsContainer = new VBox(10);
        this.itemsContainer.prefHeightProperty().bind(pane.heightProperty());
        this.itemsContainer.prefWidthProperty().bind(pane.widthProperty());
    }

    /**
     * This method create a flight info container
     *
     * @param pane the pane
     */
    private void createFlightInfoContainer(Pane pane){
        this.flightInfoContainer = new HBox(5);
        this.flightInfoContainer.setAlignment(Pos.CENTER);
        this.flightInfoContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.1));
        this.flightInfoContainer.prefWidthProperty().bind(this.scrollPane.widthProperty());
        this.flightInfoContainer.layoutXProperty().bind(pane.widthProperty().
                subtract(this.flightInfoContainer.widthProperty()).divide(2));
        this.flightInfoContainer.layoutYProperty().bind(pane.heightProperty().
                subtract(this.flightInfoContainer.heightProperty()).multiply(0.08));
        this.flightInfoContainer.setStyle("-fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, reflect, #00aed0, #00b7ad); -fx-background-radius: 10;");


        createDestinyLabel(this.flightInfoContainer);
        createAirlineLabel(this.flightInfoContainer);
        createOriginLabel(this.flightInfoContainer);

        this.flightInfoContainer.getChildren().addAll(airlineLabel, originLabel, destinyLabel);
    }

    /**
     * This method creates the airline label
     *
     * @param container their container
     */
    private void createAirlineLabel(HBox container){
        this.airlineLabel = new Label();
        this.airlineLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        this.airlineLabel.setAlignment(Pos.CENTER);
        this.airlineLabel.setText("DIEGO-AEORSUR");
        this.airlineLabel.prefHeightProperty().bind(container.heightProperty());
        this.airlineLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.4));
        setTextResponsiveLabel(this.airlineLabel, 15);
    }

    /**
     * This method create the destiny label
     *
     * @param container their container
     */
    private void createDestinyLabel(HBox container){
        this.destinyLabel = new Label();
        this.destinyLabel.setStyle("-fx-text-fill: white;");
        this.destinyLabel.setAlignment(Pos.CENTER);
        this.destinyLabel.setText("DESTINO: ORURO");
        this.destinyLabel.prefHeightProperty().bind(container.heightProperty());
        this.destinyLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.3));
        setTextResponsiveLabel(this.destinyLabel, 15);
    }

    /**
     * This method creates the origin container
     *
     * @param container their container
     */
    private void createOriginLabel(HBox container){
        this.originLabel = new Label();
        this.originLabel.setStyle("-fx-text-fill: white;");
        this.originLabel.setAlignment(Pos.CENTER);
        this.originLabel.setText("ORIGEN: COCHABAMBA");
        this.originLabel.prefHeightProperty().bind(container.heightProperty());
        this.originLabel.prefWidthProperty().bind(container.widthProperty().multiply(0.3));
        setTextResponsiveLabel(this.originLabel, 15);
    }

    /**
     * This method make a text label responsive
     *
     * @param label the label
     * @param divisor the divisor
     */
    private void setTextResponsiveLabel(Label label, int divisor){
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / divisor;
            label.setStyle(label.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }
}

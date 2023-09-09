package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.Colors;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PassengerOfAFlight {
    private final Scene changeDispenserScene;
    private Pane changeDispenserSection;
    private VBox changeDispenserPanel;
    private Header header;
    private BackgroundGenerator backgroundGenerator;
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox itemsContainer;


    /**
     * Constructs the Change Dispenser page.
     *
     * @param root  The root group for UI components.
     * @param stage The primary stage of the application.
     */
    public PassengerOfAFlight(Group root, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.stage = stage;
        this.stage.setTitle("CHANGE DISPENSER - AEROLAB");
        this.changeDispenserScene = new Scene(root);
        this.header = new Header(root, stage, "flight");
        createChangeDispenser(this.changeDispenserScene);
        root.getChildren().add(changeDispenserPanel);
    }

    public Scene getChangeDispenserScene() {
        return changeDispenserScene;
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

        this.changeDispenserPanel.getChildren().addAll(header.getHeader(), changeDispenserSection);
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

        createFillPane(changeDispenserSection);

        this.changeDispenserSection.getChildren().addAll(scrollPane);

    }

    /**
     * Method that creates the fill of our basic pane
     *
     * @param pane the pane of were we introduced this fill
     */
    public void createFillPane(Pane pane){
        this.scrollPane = new ScrollPane();
        this.scrollPane.prefHeightProperty().bind(pane.heightProperty().multiply(0.8));
        this.scrollPane.prefWidthProperty().bind(pane.widthProperty().multiply(0.8));
        this.scrollPane.setStyle("-fx-background-color: rgba(0,138,157,0.5); -fx-background-radius: 10 10 10 10; -fx-border-radius: 10 10 10 10;");
        this.scrollPane.layoutXProperty().bind(pane.widthProperty().subtract(this.scrollPane.widthProperty()).divide(2));
        this.scrollPane.layoutYProperty().bind(pane.heightProperty().subtract(this.scrollPane.heightProperty()).divide(2));

        createItemContainer(pane);
        this.scrollPane.setContent(this.itemsContainer);

    }

    private void createItemContainer(Pane pane){
        this.itemsContainer = new VBox(10);
        this.scrollPane.prefHeightProperty().bind(pane.heightProperty().multiply(0.8));
        this.scrollPane.prefWidthProperty().bind(pane.widthProperty().multiply(0.8));

       //insertButton();
    }

    /*
    public void insertButton(){
        for (int i = 0; i < 10; i++){
            Passenger passenger = new Passenger("Diego" , 12312);
            ClientInfoButton button = new ClientInfoButton(passenger, this.scrollPane);
            this.itemsContainer.getChildren().add(button.getButtonContainer());
        }
    }

     */
}

package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ChangePropertiesStage;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import com.example.programacion4proyectofinal.View.Components.ChangeDispenserComponents.BillSection;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.programacion4proyectofinal.Utils.Colors.*;

/**
 * This class represents the Change Dispenser page of the application.
 */
public class ChangeDispenserPage {

    private final Scene changeDispenserScene;
    private HBox changeDispenserForm;
    private Pane changeDispenserSection;
    private VBox changeDispenserPanel;
    private Header header;
    private BackgroundGenerator backgroundGenerator;
    private ChangePropertiesStage changePropertiesStage;
    private Button actionButton;
    private Stage stage;
    private Pane fill;
    private BillSection leftSection;
    private BillSection rightSection;


    /**
     * Constructs the Change Dispenser page.
     *
     * @param root  The root group for UI components.
     * @param stage The primary stage of the application.
     */
    public ChangeDispenserPage(Group root, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.changePropertiesStage = new ChangePropertiesStage();
        this.stage = stage;
        this.stage.setTitle("CHANGE DISPENSER - AEROLAB");
        this.changeDispenserScene = new Scene(root);
        this.header = new Header(stage, "home");
        createChangeDispenser(this.changeDispenserScene);
        root.getChildren().add(changeDispenserPanel);
    }

    /**
     * Method that return us the button of the view
     *
     * @return the action button
     */
    public Button getActionButton() {
        return actionButton;
    }

    /**
     * Method that return us the left section of the view
     *
     * @return the left section
     */
    public BillSection getLeftSection() {
        return leftSection;
    }

    /**
     * method that return us the right section of the view
     *
     * @return the right section
     */
    public BillSection getRightSection() {
        return rightSection;
    }

    /**
     * Method that return us the scene of the view
     *
     * @return the scene of the view
     */
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
        this.changeDispenserSection.setBackground(backgroundGenerator.createBackground(WHITE));

        createChangeDispenserForm(this.changeDispenserSection);
        createFillPane(changeDispenserSection);
        createActionButton(changeDispenserSection);


        this.changeDispenserSection.getChildren().addAll(fill, changeDispenserForm, actionButton);

    }

    /**
     * Method that creates the fill of our basic pane
     *
     * @param pane the pane of were we introduced this fill
     */
    public void createFillPane(Pane pane){
        this.fill = new Pane();
        this.fill.prefHeightProperty().bind(changeDispenserForm.heightProperty().multiply(1.06));
        this.fill.prefWidthProperty().bind(changeDispenserForm.widthProperty().multiply(1.03));
        this.fill.setStyle("-fx-background-color: rgba(0,138,157,0.5); -fx-background-radius: 10 10 10 10; -fx-border-radius: 10 10 10 10;");
        this.fill.layoutXProperty().bind(pane.widthProperty().subtract(this.fill.widthProperty()).divide(2));
        this.fill.layoutYProperty().bind(pane.heightProperty().subtract(this.fill.heightProperty()).divide(2));
    }

    /**
     * Method that creates the principal item container of the view
     *
     * @param pane the principal container of the money section
     */
    private void createChangeDispenserForm(Pane pane){
        this.changeDispenserForm = new HBox(10);
        this.changeDispenserForm.prefHeightProperty().bind(pane.heightProperty().subtract(300));
        this.changeDispenserForm.prefWidthProperty().bind(pane.widthProperty().subtract(600));
        this.changeDispenserForm.setStyle("-fx-background-color: linear-gradient(to bottom right, rgba(0,189,255,0.9), rgba(44,237,253,0.9)); -fx-background-radius: 10 10 10 10; -fx-border-radius: 10 10 10 10;");        this.changeDispenserForm.layoutXProperty().bind(pane.widthProperty().subtract(this.changeDispenserForm.widthProperty()).divide(2));
        this.changeDispenserForm.layoutYProperty().bind(pane.heightProperty().subtract(this.changeDispenserForm.heightProperty()).divide(2));
        this.changeDispenserForm.layoutXProperty().bind(pane.widthProperty().subtract(this.changeDispenserForm.widthProperty()).divide(2));
        this.changeDispenserForm.setAlignment(Pos.CENTER);

        this.changeDispenserForm.setBackground(backgroundGenerator.createBackground(SKY_BLUE));

        //INSERTAR LEFT Y RIGHT
        createComponent(this.changeDispenserForm);
        changeDispenserForm.getChildren().addAll(this.leftSection.getContainer(), this.rightSection.getContainer());

    }

    /**
     * Method that creates the action button of the view
     *
     * @param pane the pane of the view
     */
    private void createActionButton(Pane pane){
        this.actionButton = new Button();
        this.actionButton.prefWidthProperty().bind(pane.widthProperty().multiply(0.07));
        this.actionButton.prefHeightProperty().bind(pane.heightProperty().multiply(0.06));
        this.actionButton.setStyle("-fx-background-color: rgba(0,138,157,0.5); -fx-background-radius: 10 0 0 10; " +
                "-fx-border-radius: 10 10 10 10; -fx-font-weight: bold;");
        this.actionButton.layoutXProperty().bind(pane.widthProperty().subtract(this.actionButton.widthProperty()).subtract(10));
        this.actionButton.layoutYProperty().bind(pane.layoutYProperty().add(5));
        this.actionButton.setText("RECEIVE PAYMENT");
        this.actionButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / 10;
            this.actionButton.setStyle(this.actionButton.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

    /**
     * Method that initialize the left and right container of the money
     *
     * @param container the horizontal box container
     */
    private void createComponent(HBox container){
        this.leftSection = new BillSection("com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/200bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/100bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/50bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/20bsBill.jpg");
        this.rightSection = new BillSection("com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/10bsBill.jpg",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/5bsCoin.png",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/2bsCoin.png",
                "com/example/programacion4proyectofinal/Img/BillsAndCoinsImg/1bsCoin.png");
    }

}

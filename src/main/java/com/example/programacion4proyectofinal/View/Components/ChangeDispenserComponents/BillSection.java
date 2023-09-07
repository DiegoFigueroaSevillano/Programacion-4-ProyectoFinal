package com.example.programacion4proyectofinal.View.Components.ChangeDispenserComponents;

import com.example.programacion4proyectofinal.Utils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.Colors;
import com.example.programacion4proyectofinal.Utils.GenerateFont;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.programacion4proyectofinal.Utils.Colors.WHITE;

/**
 *  * This class represents a section with money values and images
 */
public class BillSection {

    private VBox container;
    private String firstImagePath, secondImagePath, thirdImagePath, fourthImagePath;
    private Button firstButton, secondButton, thirdButton, fourthButton;
    private HBox firstSection, secondSection, thirdSection, fourthSection;
    private Label firstLabel, secondLabel, thirdLabel, fourthLabel;
    private BackgroundGenerator backgroundGenerator;


    /**
     * Constructor method that initialize the container
     *
     * @param firstImagePath of the first bill
     * @param secondImagePath of the second bill
     * @param thirdImagePath of the third bill
     * @param fourthImagePath of the fourth bill
     */
    public BillSection(String firstImagePath, String secondImagePath, String thirdImagePath,
                       String fourthImagePath){
        this.backgroundGenerator = new BackgroundGenerator();
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.thirdImagePath = thirdImagePath;
        this.fourthImagePath = fourthImagePath;

        createContainer();
    }

    /**
     * Method that return us the first label of first data
     *
     * @return the first label
     */
    public Label getFirstLabel() {
        return firstLabel;
    }

    /**
     * Method that return us the second label of second data
     *
     * @return the second label
     */
    public Label getSecondLabel() {
        return secondLabel;
    }

    /**
     * Method that return us the third label of third data
     *
     * @return the third label
     */
    public Label getThirdLabel() {
        return thirdLabel;
    }

    /**
     * Method that return us the fourth label of fourth data
     *
     * @return the fourth label
     */
    public Label getFourthLabel() {
        return fourthLabel;
    }

    /**
     * Method that return us the container
     *
     * @return the container of the data
     */
    public VBox getContainer() {
        return container;
    }


    /**
     * This method create the container with its components
     */
    public void createContainer(){
        this.container = new VBox(10);
        this.container.setPadding(new Insets(30));
        this.container.setAlignment(Pos.CENTER);

        initializeFirstSection(container);
        initializeSecondSection(container);
        initializeThirdSection(container);
        initializeFourthSection(container);

        this.container.getChildren().addAll(firstSection, secondSection, thirdSection, fourthSection);
    }

    /**
     * Method that creates the first section
     *
     * @param container the vertical box container
     */
    private void initializeFirstSection(VBox container){
        this.firstSection = new HBox(10);
        this.firstSection.prefWidthProperty().bind(container.widthProperty());
        this.firstSection.prefHeightProperty().bind(container.heightProperty());
        this.firstSection.setAlignment(Pos.CENTER);

        this.firstButton = new Button();
        this.firstButton.setBackground(backgroundGenerator.createBackgroundImage(this.firstImagePath));
        this.firstButton.prefWidthProperty().bind(this.firstSection.widthProperty().multiply(0.52));
        this.firstButton.prefHeightProperty().bind(this.firstSection.heightProperty().subtract(10));
        firstButtonAction();

        this.firstLabel = new Label();
        this.firstLabel.setText("0");
        this.firstLabel.setAlignment(Pos.CENTER);
        this.firstLabel.setStyle("-fx-background-color: WHITE; -fx-border-color: black; -fx-border-radius: 10; " +
                "-fx-font-size: 20px;-fx-font-family: 'Arial'; " +
                "-fx-background-radius: 10 10 10 10;");
        this.firstLabel.prefHeightProperty().bind(this.firstSection.heightProperty().multiply(0.2));
        this.firstLabel.prefWidthProperty().bind(this.firstSection.widthProperty().multiply(0.1));

        this.firstSection.getChildren().addAll(this.firstButton, this.firstLabel);
    }

    /**
     * Method that set the action of the first button
     * This button increment the respective label in 1 with the left click of the mouse
     * This button decrement the respective label in 1 with the right click of the mouse
     */
    private void firstButtonAction(){
        this.firstButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY){
                if (Integer.parseInt(this.firstLabel.getText()) > 0) {
                    int newNum = Integer.parseInt(this.firstLabel.getText()) - 1;
                    this.firstLabel.setText(String.valueOf(newNum));
                }
            }
            if (event.getButton() == MouseButton.PRIMARY){
                int newNum = Integer.parseInt(this.firstLabel.getText()) + 1;
                this.firstLabel.setText(String.valueOf(newNum));
            }
        });
    }

    /**
     * Method that set the action of the second button
     * This button increment the respective label in 1 with the left click of the mouse
     * This button decrement the respective label in 1 with the right click of the mouse
     */
    private void secondButtonAction(){
        this.secondButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY){
                if (Integer.parseInt(this.secondLabel.getText()) > 0) {
                    int newNum = Integer.parseInt(this.secondLabel.getText()) - 1;
                    this.secondLabel.setText(String.valueOf(newNum));
                }
            }
            if (event.getButton() == MouseButton.PRIMARY){
                int newNum = Integer.parseInt(this.secondLabel.getText()) + 1;
                this.secondLabel.setText(String.valueOf(newNum));
            }
        });
    }

    /**
     * Method that set the action of the third button
     * This button increment the respective label in 1 with the left click of the mouse
     * This button decrement the respective label in 1 with the right click of the mouse
     */
    private void thirdButtonAction(){
        this.thirdButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY){
                if (Integer.parseInt(this.thirdLabel.getText()) > 0) {
                    int newNum = Integer.parseInt(this.thirdLabel.getText()) - 1;
                    this.thirdLabel.setText(String.valueOf(newNum));
                }
            }
            if (event.getButton() == MouseButton.PRIMARY){
                int newNum = Integer.parseInt(this.thirdLabel.getText()) + 1;
                this.thirdLabel.setText(String.valueOf(newNum));
            }
        });
    }

    /**
     * Method that set the action of the fourth button
     * This button increment the respective label in 1 with the left click of the mouse
     * This button decrement the respective label in 1 with the right click of the mouse
     */
    private void fourthButtonAction(){
        this.fourthButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY){
                if (Integer.parseInt(this.fourthLabel.getText()) > 0) {
                    int newNum = Integer.parseInt(this.fourthLabel.getText()) - 1;
                    this.fourthLabel.setText(String.valueOf(newNum));
                }
            }
            if (event.getButton() == MouseButton.PRIMARY){
                int newNum = Integer.parseInt(this.fourthLabel.getText()) + 1;
                this.fourthLabel.setText(String.valueOf(newNum));
            }
        });
    }

    /**
     * Method that creates the second section
     *
     * @param container the vertical box container
     */
    private void initializeSecondSection(VBox container){
        this.secondSection = new HBox(10);
        this.secondSection.prefWidthProperty().bind(container.widthProperty());
        this.secondSection.prefHeightProperty().bind(container.heightProperty());
        this.secondSection.setAlignment(Pos.CENTER);

        this.secondButton = new Button();
        this.secondButton.setBackground(backgroundGenerator.createBackgroundImage(this.secondImagePath));
        this.secondButton.prefWidthProperty().bind(this.secondSection.widthProperty().multiply(0.52));
        this.secondButton.prefHeightProperty().bind(this.secondSection.heightProperty().subtract(10));
        secondButtonAction();

        this.secondLabel = new Label();
        this.secondLabel.setText("0");
        this.secondLabel.setAlignment(Pos.CENTER);
        this.secondLabel.setStyle("-fx-background-color: WHITE; -fx-border-color: black; -fx-border-radius: 10; " +
                "-fx-font-size: 20px;-fx-font-family: 'Arial'; " +
                "-fx-background-radius: 10 10 10 10;");
        this.secondLabel.prefHeightProperty().bind(this.secondSection.heightProperty().multiply(0.2));
        this.secondLabel.prefWidthProperty().bind(this.secondSection.widthProperty().multiply(0.1));

        this.secondSection.getChildren().addAll(this.secondButton, this.secondLabel);
    }

    /**
     * Method that creates the third section
     *
     * @param container the vertical box container
     */
    private void initializeThirdSection(VBox container){
        this.thirdSection = new HBox(10);
        this.thirdSection.prefWidthProperty().bind(container.widthProperty());
        this.thirdSection.prefHeightProperty().bind(container.heightProperty());
        this.thirdSection.setAlignment(Pos.CENTER);

        this.thirdButton = new Button();
        this.thirdButton.setBackground(backgroundGenerator.createBackgroundImage(this.thirdImagePath));
        this.thirdButton.prefWidthProperty().bind(this.thirdSection.widthProperty().multiply(0.52));
        this.thirdButton.prefHeightProperty().bind(this.thirdSection.heightProperty().subtract(10));
        thirdButtonAction();

        this.thirdLabel = new Label();
        this.thirdLabel.setText("0");
        this.thirdLabel.setAlignment(Pos.CENTER);
        this.thirdLabel.setStyle("-fx-background-color: WHITE; -fx-border-color: black; -fx-border-radius: 10; " +
                "-fx-font-size: 20px;-fx-font-family: 'Arial'; " +
                "-fx-background-radius: 10 10 10 10;");
        this.thirdLabel.prefHeightProperty().bind(this.thirdSection.heightProperty().multiply(0.2));
        this.thirdLabel.prefWidthProperty().bind(this.thirdSection.widthProperty().multiply(0.1));

        this.thirdSection.getChildren().addAll(this.thirdButton, this.thirdLabel);
    }

    /**
     * Method that creates the fourth section
     *
     * @param container the vertical box container
     */
    private void initializeFourthSection(VBox container){
        this.fourthSection = new HBox(10);
        this.fourthSection.prefWidthProperty().bind(container.widthProperty());
        this.fourthSection.prefHeightProperty().bind(container.heightProperty());
        this.fourthSection.setAlignment(Pos.CENTER);

        this.fourthButton = new Button();
        this.fourthButton.setBackground(backgroundGenerator.createBackgroundImage(this.fourthImagePath));
        this.fourthButton.prefWidthProperty().bind(this.fourthSection.widthProperty().multiply(0.52));
        this.fourthButton.prefHeightProperty().bind(this.fourthSection.heightProperty().subtract(10));
        fourthButtonAction();

        this.fourthLabel = new Label();
        this.fourthLabel.setText("0");
        this.fourthLabel.setAlignment(Pos.CENTER);
        this.fourthLabel.setStyle("-fx-background-color: WHITE; -fx-border-color: black; -fx-border-radius: 10; " +
                "-fx-font-size: 20px;-fx-font-family: 'Arial'; " +
                "-fx-background-radius: 10 10 10 10;");
        this.fourthLabel.prefHeightProperty().bind(this.fourthSection.heightProperty().multiply(0.2));
        this.fourthLabel.prefWidthProperty().bind(this.fourthSection.widthProperty().multiply(0.1));

        this.fourthSection.getChildren().addAll(this.fourthButton, this.fourthLabel);
    }

}

package com.example.programacion4proyectofinal.View.Pages;

import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.View.Components.GeneralComponents.Header;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuyAFlight {

    private final Scene scene;
    private TextField ciTextField;
    private HBox userNameContainer;
    private HBox userCategoryContainer;
    private HBox userCountryContainer;
    private Label introduceCILabel;
    private Label userNameLabel;
    private Label countryLabel;
    private Label cateogryLabel;
    private Button searchButton;
    private Button payButton;
    private Button reserveButton;
    private Group group;
    private Stage stage;
    private Header header;
    private BackgroundGenerator backgroundGenerator;
    private VBox infoContainer;
    private HBox requestInfoContainer;
    private HBox buttonContainer;
    private VBox panel;
    private Pane section;

    public BuyAFlight(Group group, Stage stage){
        this.backgroundGenerator = new BackgroundGenerator();
        this.stage = stage;
        this.group = group;
        this.stage.setTitle("BUY A TICKET");
        this.scene = new Scene(group);
        this.header = new Header(group, stage, "butATicket");

        createBuyFlight();
        group.getChildren().add(panel);
    }

    public Scene getScene() {
        return scene;
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }

    public Label getCountryLabel() {
        return countryLabel;
    }

    public Label getCateogryLabel() {
        return cateogryLabel;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getPayButton() {
        return payButton;
    }

    public Button getReserveButton() {
        return reserveButton;
    }

    private void createBuyFlight(){
        this.panel = new VBox();
        this.panel.setAlignment(Pos.CENTER);
        this.panel.prefWidthProperty().bind(stage.widthProperty());
        this.panel.prefHeightProperty().bind(stage.heightProperty());
        createBuyAFlightSection(panel);
        this.panel.getChildren().addAll(header.getHeader(), this.section);
    }

    public void createBuyAFlightSection(VBox panel){
        this.section = new Pane();
        this.section.prefWidthProperty().bind(panel.widthProperty());
        this.section.prefHeightProperty().bind(panel.heightProperty().subtract(60));

        createInfoContainer(this.section);
        createRequestContainer(this.section);
        createButtonContainer(this.section);

        this.section.getChildren().addAll(this.infoContainer, this.requestInfoContainer, this.buttonContainer);

    }

    private void createButtonContainer(Pane pane){
        this.buttonContainer = new HBox(10);
        this.buttonContainer.setAlignment(Pos.CENTER);
        this.buttonContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.1));
        this.buttonContainer.prefWidthProperty().bind(pane.widthProperty().multiply(0.5));
        this.buttonContainer.layoutXProperty().bind(pane.widthProperty().
                subtract(this.buttonContainer.widthProperty()).divide(2));
        this.buttonContainer.layoutYProperty().bind(pane.heightProperty().
                subtract(this.buttonContainer.heightProperty()).multiply(0.9));

        createPayButton(this.buttonContainer);
        createReserveButton(this.buttonContainer);

        this.buttonContainer.getChildren().addAll(payButton, reserveButton);
    }

    private void createPayButton(HBox container){
        this.payButton = new Button("PAY");
        this.payButton.prefHeightProperty().bind(container.heightProperty().multiply(0.7));
        this.payButton.prefWidthProperty().bind(container.widthProperty().multiply(0.4));
        this.payButton.setStyle("-fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, reflect, #e1ce00, #cec92e); -fx-background-radius: 5;" +
                "-fx-text-fill: white; -fx-font-weight: bold;");
        setTextResponsiveLabel(this.payButton, 12);
    }

    private void createReserveButton(HBox container){
        this.reserveButton = new Button("RESERVE");
        this.reserveButton.prefHeightProperty().bind(container.heightProperty().multiply(0.7));
        this.reserveButton.prefWidthProperty().bind(container.widthProperty().multiply(0.4));
        this.reserveButton.setStyle("-fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, reflect, #0089ef, #00679d); -fx-background-radius: 5;" +
                "-fx-text-fill: white; -fx-font-weight: bold;");
        setTextResponsiveLabel(this.reserveButton, 12);
    }

    private void createInfoContainer(Pane pane){
        this.infoContainer = new VBox();
        this.infoContainer.setAlignment(Pos.CENTER);
        this.infoContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.5));
        this.infoContainer.prefWidthProperty().bind(pane.widthProperty().multiply(0.7));
        this.infoContainer.layoutXProperty().bind(pane.widthProperty().
                subtract(this.infoContainer.widthProperty()).divide(2));
        this.infoContainer.layoutYProperty().bind(pane.heightProperty().
                subtract(this.infoContainer.heightProperty()).divide(2));
        this.infoContainer.setStyle("-fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, reflect, #c2fffc, #00e0ff); -fx-background-radius: 5;");

        createNameContainer(this.infoContainer);
        createCountryContainer(this.infoContainer);
        createCategoryContainer(this.infoContainer);

        this.infoContainer.getChildren().addAll(this.userNameContainer, this.userCountryContainer,
                this.userCategoryContainer);
    }

    private void createRequestContainer(Pane pane){
        this.requestInfoContainer = new HBox();
        this.requestInfoContainer.setAlignment(Pos.CENTER);
        this.requestInfoContainer.prefHeightProperty().bind(pane.heightProperty().multiply(0.1));
        this.requestInfoContainer.prefWidthProperty().bind(pane.widthProperty().multiply(0.5));
        this.requestInfoContainer.layoutXProperty().bind(pane.widthProperty().
                subtract(this.requestInfoContainer.widthProperty()).divide(2));
        this.requestInfoContainer.layoutYProperty().bind(pane.heightProperty().
                subtract(this.requestInfoContainer.heightProperty()).multiply(0.08));
        this.requestInfoContainer.setStyle("-fx-background-color: " +
                "radial-gradient(center 50% 50%, radius 100%, reflect, #ff2828, #ad0101); -fx-background-radius: 5;");

        createValuesToRequestContainer(this.requestInfoContainer);
        this.requestInfoContainer.getChildren().addAll(this.introduceCILabel, this.ciTextField, this.searchButton);
    }

    private void createValuesToRequestContainer(HBox container){
        this.introduceCILabel = new Label("INTRODUCE THE CI OF THE CLIENT");
        this.introduceCILabel.setAlignment(Pos.CENTER);
        this.introduceCILabel.prefHeightProperty().bind(container.heightProperty());
        this.introduceCILabel.prefWidthProperty().bind(container.widthProperty().multiply(0.5));
        setTextResponsiveLabel(this.introduceCILabel, 25);
        this.introduceCILabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        this.ciTextField = new TextField();
        this.ciTextField.setAlignment(Pos.CENTER);
        this.ciTextField.prefHeightProperty().bind(container.heightProperty().multiply(0.3));
        this.ciTextField.prefWidthProperty().bind(container.widthProperty().multiply(0.3));
        setTextFieldValidations(this.ciTextField);

        this.searchButton = new Button("SEARCH");
        this.searchButton.setAlignment(Pos.CENTER);
        this.searchButton.setStyle("-fx-font-weight: bold; -fx-background-color: #a80000; -fx-text-fill: white;");
        this.searchButton.prefWidthProperty().bind(container.widthProperty().multiply(0.15));
        this.searchButton.prefHeightProperty().bind(container.heightProperty().multiply(0.3));
    }

    public void createNameContainer(VBox container){
        this.userNameContainer = new HBox();
        this.userNameContainer.setAlignment(Pos.CENTER_LEFT);
        this.userNameContainer.prefHeightProperty().bind(container.heightProperty().multiply(0.2));
        this.userNameContainer.prefWidthProperty().bind(container.widthProperty());

        Label auxLabel = new Label("NAME:   ");
        auxLabel.setAlignment(Pos.CENTER);
        auxLabel.setStyle("-fx-font-weight: bold;");
        auxLabel.prefWidthProperty().bind(this.userNameContainer.widthProperty().multiply(0.3));
        auxLabel.prefHeightProperty().bind(this.userNameContainer.heightProperty());
        setTextResponsiveLabel(auxLabel, 7);

        this.userNameLabel = new Label();
        this.userNameLabel.setAlignment(Pos.CENTER_LEFT);
        this.userNameLabel.prefWidthProperty().bind(this.userNameContainer.widthProperty().multiply(0.7));
        this.userNameLabel.prefHeightProperty().bind(this.userNameContainer.heightProperty().multiply(0.8));
        setTextResponsiveLabel(this.userNameLabel, 15);

        this.userNameContainer.getChildren().addAll(auxLabel, userNameLabel);
    }

    public void createCountryContainer(VBox container){
        this.userCountryContainer = new HBox();
        this.userCountryContainer.setAlignment(Pos.CENTER_LEFT);
        this.userCountryContainer.prefHeightProperty().bind(container.heightProperty().multiply(0.2));
        this.userCountryContainer.prefWidthProperty().bind(container.widthProperty());

        Label auxLabel = new Label("COUNTRY:   ");
        auxLabel.setAlignment(Pos.CENTER);
        auxLabel.setStyle("-fx-font-weight: bold;");
        auxLabel.prefWidthProperty().bind(this.userCountryContainer.widthProperty().multiply(0.3));
        auxLabel.prefHeightProperty().bind(this.userCountryContainer.heightProperty());
        setTextResponsiveLabel(auxLabel, 7);

        this.countryLabel = new Label();
        this.countryLabel.setAlignment(Pos.CENTER_LEFT);
        this.countryLabel.prefWidthProperty().bind(this.userCountryContainer.widthProperty().multiply(0.7));
        this.countryLabel.prefHeightProperty().bind(this.userCountryContainer.heightProperty().multiply(0.8));
        setTextResponsiveLabel(countryLabel, 15);

        this.userCountryContainer.getChildren().addAll(auxLabel, countryLabel);
    }

    public void createCategoryContainer(VBox container){
        this.userCategoryContainer = new HBox();
        this.userCategoryContainer.setAlignment(Pos.CENTER_LEFT);
        this.userCategoryContainer.prefHeightProperty().bind(container.heightProperty().multiply(0.2));
        this.userCategoryContainer.prefWidthProperty().bind(container.widthProperty());

        Label auxLabel = new Label("CATEGORY:   ");
        auxLabel.setAlignment(Pos.CENTER);
        auxLabel.setStyle("-fx-font-weight: bold;");
        auxLabel.prefWidthProperty().bind(this.userCategoryContainer.widthProperty().multiply(0.3));
        auxLabel.prefHeightProperty().bind(this.userCategoryContainer.heightProperty());
        setTextResponsiveLabel(auxLabel, 7);

        this.cateogryLabel = new Label();
        this.cateogryLabel.setAlignment(Pos.CENTER_LEFT);
        this.cateogryLabel.prefWidthProperty().bind(this.userCategoryContainer.widthProperty().multiply(0.7));
        this.cateogryLabel.prefHeightProperty().bind(this.userCategoryContainer.heightProperty().multiply(0.8));
        setTextResponsiveLabel(cateogryLabel, 15);

        this.userCategoryContainer.getChildren().addAll(auxLabel, cateogryLabel);
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

    private void setTextResponsiveLabel(Button button, int divisor){
        button.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() / divisor;
            button.setStyle(button.getStyle() + String.format("-fx-font-size: %.2fpx;", newFontSize));
        });
    }

    private void setTextFieldValidations(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (textField.getText().length() > 10) {
                    textField.setText(textField.getText().substring(0, 10));
                }
            }
        });
    }






}

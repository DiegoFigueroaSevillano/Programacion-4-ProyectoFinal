package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.*;

public class PassengersController {

    private Passengers passengers;
    public static ArrayList<Passenger> passengersList;
    public static ObservableList<HBox> passengersComponents;
    public static HashMap<Integer, ArrayList<Passenger>> paginationMap;
    private static int pagination = 1;

    public PassengersController(Group root, Stage stage) {
        if (paginationMap == null) {
            paginationMap = createPagination();
        }
        if (passengersComponents == null) {
            passengersComponents = createPassengersComponents();
        }
        if (root != null && stage != null) {
            this.passengers = new Passengers(root, stage, passengersComponents);
        }
        addActionToButtons();
        if (this.passengers.getPaginationField().getText().equals("1")) {
            pagination = 1;
        }
    }

    private ObservableList<HBox> createPassengersComponents() {
        ObservableList<HBox> passengersComponent = FXCollections.observableArrayList();
        for (int index = 0; index < paginationMap.get(pagination).size(); index++) {
            passengersComponent.add(generatePassenger(paginationMap.get(pagination).get(index), passengersComponent, index));
        }
        return passengersComponent;
    }

    private HBox generatePassenger(Passenger passenger, ObservableList<HBox> passengersComponents, int id) {
        int idPassenger = passenger.getId();
        GenerateFont generateFont = new GenerateFont();
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();

        Label passengerName = new Label(passenger.getFullName());
        passengerName.setFont(generateFont.latoLight(32));
        passengerName.setTextFill(Color.valueOf(WHITE));

        Button deleteButton = generateButton("/com/example/programacion4proyectofinal/Icons/delete-icon.png", RED);

        HBox nameContainer = new HBox();
        nameContainer.getChildren().add(passengerName);
        nameContainer.setAlignment(Pos.CENTER_LEFT);
        nameContainer.setId(idPassenger + "");
        HBox.setHgrow(nameContainer, Priority.ALWAYS);

        HBox buttonsContainer = new HBox(10);
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.getChildren().addAll(deleteButton);

        HBox passengerComponent = new HBox(10);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passengersComponents.remove(passengerComponent);
                passengers.setPassengersComponents(passengersComponents);
                passengers.deleteAComponent();
            }
        });

        passengerComponent.setPrefHeight(100);
        passengerComponent.setBackground(backgroundGenerator.createBackgroundRadius(10, SKY_BLUE));
        passengerComponent.setId(id + "");
        passengerComponent.setAlignment(Pos.CENTER);
        passengerComponent.setPadding(new Insets(20));
        passengerComponent.getChildren().addAll(nameContainer, buttonsContainer);

        nameContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(nameContainer.getId() + " | " + passengersList.get(id).getFullName());
            }
        });

        return passengerComponent;
    }

    private Button generateButton(String pathImage, String color) {
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        Image iconImage = new Image(getClass().getResourceAsStream(pathImage));
        ImageView icon = new ImageView(iconImage);

        Button button = new Button();
        button.setGraphic(icon);
        button.setPrefSize(60, 60);
        button.setMinSize(60, 60);
        button.setMaxSize(60, 60);
        button.setBackground(backgroundGenerator.createBackgroundRadius(10, color));
        button.setCursor(Cursor.HAND);
        return button;
    }

    private HashMap<Integer, ArrayList<Passenger>> createPagination() {
        int totalThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);

        int pageSize = 20;
        int totalPassengers = passengersList.size();
        int totalPages = (int) Math.ceil((double) totalPassengers / pageSize);
        HashMap<Integer, ArrayList<Passenger>> pagination = new HashMap<>();

        ArrayList<Callable<Void>> tasks = new ArrayList<>();

        for (int page = 1; page <= totalPages; page++) {
            final int currentPage = page;
            Callable<Void> task = () -> {
                int startIndex = (currentPage - 1) * pageSize;
                int endIndex = Math.min(currentPage * pageSize, totalPassengers);
                ArrayList<Passenger> pageList = new ArrayList<>(passengersList.subList(startIndex, endIndex));
                pagination.put(currentPage, pageList);
                return null;
            };
            tasks.add(task);
        }

        try {
            List<Future<Void>> futures = executorService.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return pagination;
    }

    private void changeToNextPagination() {
        pagination++;
        if (pagination > 1) {
            passengers.getLeftPaginationButton().setDisable(false);
        }

        if (pagination > 10) {
            passengers.getLeftTenPaginationButton().setDisable(false);
        }
        if (pagination == (paginationMap.size() - 1)) {
            passengers.getRightPaginationButton().setDisable(true);
            passengers.getRightTenPaginationButton().setDisable(true);
        }
        passengers.getPaginationField().setText("" + pagination);
    }

    private void changeTenPositionToNextPagination() {
        pagination+=10;
        if (pagination > 1) {
            passengers.getLeftPaginationButton().setDisable(false);
        }
        if (pagination > 10) {
            passengers.getLeftTenPaginationButton().setDisable(false);
        }
        if (pagination > (paginationMap.size() - 1)) {
            passengers.getPaginationField().setText("" + (paginationMap.size() - 1));
            pagination = paginationMap.size() - 1;
        }

        if (pagination == (paginationMap.size() - 1)) {
            passengers.getRightPaginationButton().setDisable(true);
            passengers.getRightTenPaginationButton().setDisable(true);
        }
        passengers.getPaginationField().setText("" + pagination);
    }

    private void changeToPreviousPagination() {
        pagination--;
        if (pagination == (paginationMap.size() - 2)) {
            passengers.getRightPaginationButton().setDisable(false);
        }
        if (pagination == (paginationMap.size() - 11)) {
            passengers.getRightTenPaginationButton().setDisable(false);
        }
        if (pagination == 1) {
            passengers.getLeftPaginationButton().setDisable(true);
            passengers.getLeftTenPaginationButton().setDisable(true);
        }
        passengers.getPaginationField().setText("" + pagination);
    }

    private void changeTenPositionToPreviousPagination() {
        pagination-=10;
        if (pagination <= (paginationMap.size() - 2)) {
            passengers.getRightPaginationButton().setDisable(false);
        }
        if (pagination <= (paginationMap.size() - 11)) {
            passengers.getRightTenPaginationButton().setDisable(false);
        }
        if (pagination < 11) {
            passengers.getPaginationField().setText("" + (1));
            pagination = 1;
        }

        if (pagination == 1) {
            passengers.getLeftPaginationButton().setDisable(true);
            passengers.getLeftTenPaginationButton().setDisable(true);
        }
        passengers.getPaginationField().setText("" + pagination);
    }

    private void addActionToButtons() {

        passengers.getLeftPaginationButton().setDisable(true);
        passengers.getLeftTenPaginationButton().setDisable(true);
        passengers.getRightPaginationButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeToNextPagination();
                updatePage();
            }
        });

        passengers.getRightTenPaginationButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeTenPositionToNextPagination();
                updatePage();
            }
        });

        passengers.getLeftPaginationButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeToPreviousPagination();
                updatePage();
            }
        });

        passengers.getLeftTenPaginationButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeTenPositionToPreviousPagination();
                updatePage();
            }
        });
    }

    private void updatePage() {
        passengers.getPassengersList().getChildren().clear();
        passengers.getPassengersList().getChildren().addAll(createPassengersComponents());
    }

    public Passengers getPassengers() {
        return passengers;
    }
}

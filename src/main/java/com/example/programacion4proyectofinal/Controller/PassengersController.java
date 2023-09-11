package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.Search;
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
import java.util.Objects;
import java.util.concurrent.*;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.*;

/**
 * The PassengersController class manages passenger data and interactions for the application.
 * It handles pagination, searching, and displaying passenger information.
 */
public class PassengersController {

    private Passengers passengers;
    private ArrayList<Passenger> passengersList;
    private ObservableList<HBox> passengersComponents;
    private HashMap<Integer, ArrayList<Passenger>> paginationMap;
    private int pagination = 1;
    private Search search;
    private static PassengersController passengersControllerInstance;
    private Stage stage;

    /**
     * Initializes a new instance of the PassengersController class.
     * It retrieves passenger data and creates passenger components for display.
     */
    public PassengersController() {
        search = new Search();
        try {
            passengersList = search.obtainAllPassengers();
            System.out.println(passengersList.size());
            createPassengers();
        } catch (ExecutionException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Starts the PassengersController by initializing the GUI and adding action handlers.
     *
     * @param root  The root Group for the GUI.
     * @param stage The primary Stage for the application.
     */
    public void start(Group root, Stage stage) {
        this.stage = stage;
        this.passengers = new Passengers(root, this.stage, passengersComponents);
        addActionToButtons();
        pagination = 1;
    }

    /**
     * Creates passenger components based on the current pagination.
     *
     * @throws ExecutionException   If an execution exception occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    private void createPassengers() throws ExecutionException, InterruptedException {
        paginationMap = createPagination();
        passengersComponents = createPassengersComponents();
    }

    /**
     * Creates HBox components for passengers based on the current pagination.
     *
     * @return The list of HBox passenger components.
     */
    private ObservableList<HBox> createPassengersComponents() {
        ObservableList<HBox> passengersComponent = FXCollections.observableArrayList();
        if (!paginationMap.isEmpty()) {
            if (paginationMap.get(pagination) != null) {
                for (int index = 0; index < paginationMap.get(pagination).size(); index++) {
                    passengersComponent.add(generatePassenger(paginationMap.get(pagination).get(index), passengersComponent, index));
                }
            }
        }
        return passengersComponent;
    }

    /**
     * Generates an HBox component for a passenger with specified details.
     *
     * @param passenger           The passenger object.
     * @param passengersComponents The list of passenger components.
     * @param id                   The unique ID of the passenger component.
     * @return The generated HBox passenger component.
     */
    private HBox generatePassenger(Passenger passenger, ObservableList<HBox> passengersComponents, int id) {
        int idPassenger = passenger.getId();
        GenerateFont generateFont = new GenerateFont();
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();

        Label passengerName = new Label(passenger.getFullName());
        passengerName.setFont(generateFont.latoLight(32));
        passengerName.setTextFill(Color.valueOf(WHITE));

        HBox nameContainer = new HBox();
        nameContainer.getChildren().add(passengerName);
        nameContainer.setAlignment(Pos.CENTER_LEFT);
        nameContainer.setId(idPassenger + "");

        HBox.setHgrow(nameContainer, Priority.ALWAYS);

        HBox passengerComponent = new HBox(10);
        passengerComponent.setPrefHeight(100);
        passengerComponent.setBackground(backgroundGenerator.createBackgroundRadius(10, SKY_BLUE));
        passengerComponent.setId(id + "");
        passengerComponent.setAlignment(Pos.CENTER);
        passengerComponent.setPadding(new Insets(20));
        passengerComponent.getChildren().addAll(nameContainer);

        nameContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(nameContainer.getId() + " | " + passengersList.get(id).getFullName());
            }
        });

        return passengerComponent;
    }

    /**
     * Generates a button with an icon and specified background color.
     *
     * @param pathImage The path to the icon image.
     * @param color     The background color of the button.
     * @return The generated button.
     */
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

    /**
     * Creates pagination for the list of passengers.
     *
     * @return A HashMap containing pagination information.
     */
    public HashMap<Integer, ArrayList<Passenger>> createPagination() {
        int totalThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);
        int pageSize = 20;
        final int[] counter = {0};
        int totalPassengers = passengersList.size();
        int totalPages = (int) Math.ceil((double) totalPassengers / pageSize);
        HashMap<Integer, ArrayList<Passenger>> pagination = new HashMap<>();
        ArrayList<Callable<Void>> tasks = new ArrayList<>();
        for (int page = 1; page <= totalPages; page++) {
            final int currentPage = page;
            Callable<Void> task = () -> {
                ArrayList<Passenger> pageList = new ArrayList<>();
                while (pageList.size() < pageSize) {
                    pageList.add(passengersList.get(counter[0]));
                    counter[0]++;
                }
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

    /**
     * Increments the pagination and updates GUI elements accordingly.
     */
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

    /**
     * Increments the pagination by ten and updates GUI elements accordingly.
     */
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

    /**
     * Decrements the pagination and updates GUI elements accordingly.
     */
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

    /**
     * Decrements the pagination by ten and updates GUI elements accordingly.
     */
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

    /**
     * Adds action handlers to various buttons and controls in the GUI.
     */
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

        passengers.getSearchButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String searchResult = passengers.getSearchField().getText();
                if (Objects.equals(searchResult, "")) {
                    try {
                        passengersList = search.obtainAllPassengers();
                    } catch (InterruptedException | ExecutionException exception) {
                        throw new RuntimeException(exception);
                    }
                } else {
                    passengersList = search.searchByName(searchResult);
                }
                pagination = 1;
                passengers.getPaginationField().setText("1");
                try {
                    createPassengers();
                } catch (ExecutionException | InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
                passengers.getLeftTenPaginationButton().setDisable(true);
                passengers.getLeftPaginationButton().setDisable(true);
                if (paginationMap.size() > 1) {
                    passengers.getRightPaginationButton().setDisable(false);
                    passengers.getRightTenPaginationButton().setDisable(false);
                } else {
                    passengers.getRightPaginationButton().setDisable(true);
                    passengers.getRightTenPaginationButton().setDisable(true);
                }
                updatePage();
            }
        });
    }

    /**
     * Updates the displayed passenger list on the GUI.
     */
    private void updatePage() {
        passengers.getPassengersList().getChildren().clear();
        passengers.getPassengersList().getChildren().addAll(createPassengersComponents());
    }

    /**
     * Gets the Passengers view.
     *
     * @return The Passengers view.
     */
    public Passengers getPassengers() {
        return passengers;
    }

    /**
     * Gets the instance of the PassengersController class.
     *
     * @return The PassengersController instance.
     */
    public static PassengersController getPassengersControllerInstance() {
        if (passengersControllerInstance == null) {
            passengersControllerInstance = new PassengersController();
        }
        return passengersControllerInstance;
    }
}

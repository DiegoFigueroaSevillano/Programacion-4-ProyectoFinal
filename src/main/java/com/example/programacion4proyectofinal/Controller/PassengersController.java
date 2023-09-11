package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Flight.Flight;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.Search;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import com.example.programacion4proyectofinal.View.Pages.Passengers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.SKY_BLUE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.WHITE;

/**
 * The PassengersController class manages passenger data and interactions for the application.
 * It handles pagination, searching, and displaying passenger information.
 */
public class PassengersController {

    private Passengers passengers;
    private ArrayList<Passenger> passengersList;
    private ObservableList<HBox> passengersComponents;
    private HashMap<Integer, ArrayList<Passenger>> paginationMap;
    private int pagination;
    private Search search;
    private static PassengersController passengersControllerInstance;
    private Stage stage;

    /**
     * Initializes a new instance of the PassengersController class.
     * It retrieves passenger data and creates passenger components for display.
     */
    public PassengersController() {
        pagination = 1;
        search = new Search();
        try {
            passengersList = search.obtainAllPassengers();
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
        System.out.println("PAGINATION: " + paginationMap.size());
        passengersComponents = createPassengersComponents();
        System.out.println("PASSENGERS: " + passengersComponents.size());
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
                if (paginationMap.containsKey(pagination)) {
                    ArrayList<Passenger> currentPagePassengers = paginationMap.get(pagination);
                    if (currentPagePassengers != null) {
                        for (int index = 0; index < currentPagePassengers.size(); index++) {
                            passengersComponent.add(generatePassenger(currentPagePassengers.get(index), index));
                        }
                    }
                }
            }
        }
        return passengersComponent;
    }

    /**
     * Generates an HBox component for a passenger with specified details.
     *
     * @param passenger           The passenger object.
     * @param id                   The unique ID of the passenger component.
     * @return The generated HBox passenger component.
     */
    private HBox generatePassenger(Passenger passenger, int id) {
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
        addActionForContainer(nameContainer, passenger);
        return passengerComponent;
    }

    /**
     * Adds an action handler to a passenger component.
     * @param nameContainer The container of the passenger component.
     * @param passenger The passenger object.
     */
    private void addActionForContainer(HBox nameContainer, Passenger passenger) {
        nameContainer.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                List<Flight> flights;
                try {
                    flights = UserFlightInfoOperations.getAllFlightOfTheUser(passenger.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/programacion4proyectofinal/Views/user-profile.fxml")));
                    Parent root = loader.load();
                    UserProfileController userProfileController = loader.getController();
                    userProfileController.setPassengerView(true);
                    userProfileController.setLabels(passenger);
                    userProfileController.loadInformationFlights(flights);
                    userProfileController.setStage(stage);
                    Image iconApp = new Image("/com/example/programacion4proyectofinal/Logo/logo-areolab.png");
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
                    newStage.getIcons().add(iconApp);
                    newStage.setMinWidth(1100);
                    newStage.setMinHeight(800);
                    newStage.setTitle("User Profile");
                    newStage.show();
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        if (totalPages == 0 && !passengersList.isEmpty()) {
            totalPages = 1;
        }
        HashMap<Integer, ArrayList<Passenger>> pagination = new HashMap<>();
        ArrayList<Callable<Void>> tasks = new ArrayList<>();
        for (int page = 0; page < totalPages; page++) {
            final int currentPage = page;
            Callable<Void> task = () -> {
                ArrayList<Passenger> pageList = new ArrayList<>();
                while (counter[0] < passengersList.size() && pageList.size() < pageSize) {
                    pageList.add(passengersList.get(counter[0]));
                    counter[0]++;
                }
                pagination.put(currentPage + 1, pageList);
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
        addNextPaginationAction();
        addNextTenPaginationAction();
        addPreviousPaginationAction();
        addPreviousTenPaginationAction();
        addSearchButtonAction();
    }

    /**
     * Adds action handler for the "Next Pagination" button.
     */
    private void addNextPaginationAction() {
        passengers.getRightPaginationButton().setOnAction(event -> {
            changeToNextPagination();
            updatePage();
        });
    }

    /**
     * Adds action handler for the "Next Ten Pagination" button.
     */
    private void addNextTenPaginationAction() {
        passengers.getRightTenPaginationButton().setOnAction(event -> {
            changeTenPositionToNextPagination();
            updatePage();
        });
    }

    /**
     * Adds action handler for the "Previous Pagination" button.
     */
    private void addPreviousPaginationAction() {
        passengers.getLeftPaginationButton().setOnAction(event -> {
            changeToPreviousPagination();
            updatePage();
        });
    }

    /**
     * Adds action handler for the "Previous Ten Pagination" button.
     */
    private void addPreviousTenPaginationAction() {
        passengers.getLeftTenPaginationButton().setOnAction(event -> {
            changeTenPositionToPreviousPagination();
            updatePage();
        });
    }

    /**
     * Adds action handler for the "Search" button.
     */
    private void addSearchButtonAction() {
        passengers.getSearchButton().setOnAction(event -> {
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

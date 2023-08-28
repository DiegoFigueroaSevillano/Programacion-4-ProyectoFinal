package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.View.Pages.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * Controller class for the Home view.
 */
public class HomeController {

    private Home homeView;

    /**
     * Constructor for the HomeController.
     *
     * @param root  The root Group node.
     * @param stage The primary Stage.
     */
    public HomeController(Group root, Stage stage) {
        homeView = new Home(root, stage);
        homeView.getOneWayOnly().getRadioButton().setSelected(true);
        homeView.getReturnDate().getDatePicker().setDisable(true);
        addActionToRadioButtons();
    }

    /**
     * Adds action listeners to the radio buttons.
     * Controls the enabling/disabling of the return date picker based on user selection.
     */
    private void addActionToRadioButtons() {
        homeView.getOneWayOnly().getRadioButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homeView.getReturnDate().getDatePicker().setDisable(true);
            }
        });
        homeView.getRoundTrip().getRadioButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homeView.getReturnDate().getDatePicker().setDisable(false);
            }
        });
    }

    /**
     * Retrieves the Home view associated with this controller.
     *
     * @return The Home view.
     */
    public Home getHomeView() {
        return homeView;
    }
}
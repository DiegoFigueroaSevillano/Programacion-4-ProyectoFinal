package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.Search;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.UserFlightInfo;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;
import com.example.programacion4proyectofinal.View.Pages.BuyAFlight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This class was created for the management of the buy flight view
 */
public class BuyAFlightController {
    BuyAFlight view;
    Group group;
    Stage stage;
    int flightID;
    int userCI;
    Search search;

    /**
     * Constructor method where we initialize all the component of the view
     *
     * @param group the group
     * @param stage the stage
     * @param flightID the flightID
     */
    public BuyAFlightController(Group group, Stage stage, int flightID){
        this.group = group;
        this.stage = stage;
        this.flightID = flightID;
        this.view = new BuyAFlight(group, stage);
        search = new Search();
        this.userCI = 0;

        view.getPayButton().setDisable(true);
        view.getReserveButton().setDisable(true);
        setTheSearchButtonAction();
        setThePayButtonAction();
        setTheReserveButtonAction();
    }

    /**
     * This method return us the view
     *
     * @return the view
     */
    public BuyAFlight getView() {
        return view;
    }

    /**
     * This method return us the group
     *
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * This method return us the stage
     *
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * This method set the search button of the view with her respective action
     */
    public void setTheSearchButtonAction(){
        view.getSearchButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Passenger passenger = search.searchById(Integer.parseInt(view.getCiTextField().getText()));
                if (passenger != null){
                    view.getErrorLabel().setVisible(false);
                    view.getUserNameLabel().setText(passenger.getFullName());
                    view.getCountryLabel().setText(passenger.getCountry());
                    view.getCateogryLabel().setText(passenger.getCategory().toString());
                    view.getPayButton().setDisable(false);
                    view.getReserveButton().setDisable(false);
                    userCI = Integer.parseInt(view.getCiTextField().getText());
                }else{
                    view.getErrorLabel().setVisible(true);
                }
            }
        });
    }

    /**
     * This method set the pay button of the view with her respective action
     */
    public void setThePayButtonAction(){
        view.getPayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    UserFlightInfoOperations.insert(new UserFlightInfo(userCI, flightID, Status.RESERVED,
                            LocalDateTime.now()));
                    group = new Group();
                    ChangeDispenserController changeDispenserController = new ChangeDispenserController(group, stage,
                            userCI, flightID, false);
                    Scene scene = changeDispenserController.getChangeDispenserView().getChangeDispenserScene();
                    stage.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * This button set the reserve button with her respective action
     */
    public void setTheReserveButtonAction(){
        view.getReserveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    UserFlightInfoOperations.insert(new UserFlightInfo(userCI, flightID, Status.RESERVED,
                            LocalDateTime.now()));
                    group = new Group();
                    HomeController home = new HomeController(group, stage);
                    Scene scene = home.getHomeView().getHomeScene();
                    stage.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }





}

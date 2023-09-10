package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Coin.Coin;
import com.example.programacion4proyectofinal.Model.Coin.CoinStock;
import com.example.programacion4proyectofinal.Model.CurrencyChange.ChangeDispenser;
import com.example.programacion4proyectofinal.Model.UserFlightInfo.Status;
import com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase.FlightJsonOperations;
import com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase.UserFlightInfoOperations;
import com.example.programacion4proyectofinal.View.Pages.ChangeDispenserPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Change Dispenser view.
 */
public class ChangeDispenserController {
    private ChangeDispenserPage changeDispenserView;
    private ChangeDispenser changeDispenserModel;
    private boolean isPressed;
    private int userID;
    private int flightID;
    private int cost;
    private boolean isListOfPassenger;
    private Group group;
    private Stage stage;

    /**
     * Constructor for the change dispenser controller.
     *
     * @param group  The root Group node.
     * @param stage The primary Stage.
     * @param userID the user id
     * @param flightID the flight id
     * @param isListOfPassenger true is the later view was the list of passengers
     */
    public ChangeDispenserController(Group group, Stage stage, int userID, int flightID, boolean isListOfPassenger){
        this.group = group;
        this.stage = stage;
        this.changeDispenserView = new ChangeDispenserPage(group, stage);
        this.changeDispenserModel = new ChangeDispenser();
        this.isPressed = false;
        this.userID = userID;
        this.flightID = flightID;
        this.isListOfPassenger = isListOfPassenger;
        try {
            this.cost = FlightJsonOperations.get(flightID).getCostOfTheFlight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addActionToActionButton();
    }

    /**
     * Method that return us the view
     *
     * @return the view
     */
    public ChangeDispenserPage getChangeDispenserView() {
        return changeDispenserView;
    }

    /**
     * Method that save the money entered by the operator
     *
     * @return the array of coins entered by the operator
     */
    public CoinStock[] saveTheMoney(){
        CoinStock[] coinStock = new CoinStock[]{
                new CoinStock(Coin.TWO_HUNDRED_BS, Integer.parseInt(changeDispenserView.getLeftSection().getFirstLabel().getText())),
                new CoinStock(Coin.ONE_HUNDRED_BS, Integer.parseInt(changeDispenserView.getLeftSection().getSecondLabel().getText())),
                new CoinStock(Coin.FIFTY_BS, Integer.parseInt(changeDispenserView.getLeftSection().getThirdLabel().getText())),
                new CoinStock(Coin.TWENTY_BS, Integer.parseInt(changeDispenserView.getLeftSection().getFourthLabel().getText())),
                new CoinStock(Coin.TEN_BS, Integer.parseInt(changeDispenserView.getRightSection().getFirstLabel().getText())),
                new CoinStock(Coin.FIVE_BS, Integer.parseInt(changeDispenserView.getRightSection().getSecondLabel().getText())),
                new CoinStock(Coin.TWO_BS, Integer.parseInt(changeDispenserView.getRightSection().getThirdLabel().getText())),
                new CoinStock(Coin.ONE_BS, Integer.parseInt(changeDispenserView.getRightSection().getFourthLabel().getText()))
        };
        changeDispenserModel.saveTheMoney(coinStock);
        return coinStock;
    }

    /**
     * Method that return the change of the client
     * Reset the values of the labels
     */
    public void returnChange(){
        CoinStock[] coinStock = changeDispenserModel.getTheChange(changeDispenserModel.sumTheArray(saveTheMoney()), cost);  //TODO: LA VISTA AL MOMENTO DE COMPARA EL TICKET DEBE MANDAR A ESTA EL COSTO DEL BOLETO
        resetLabels();
        chargeTheChange(coinStock);
    }

    /**
     * Method that reset the values of the labels
     */
    public void resetLabels(){
        changeDispenserView.getLeftSection().getFirstLabel().setText("0");
        changeDispenserView.getLeftSection().getSecondLabel().setText("0");
        changeDispenserView.getLeftSection().getThirdLabel().setText("0");
        changeDispenserView.getLeftSection().getFourthLabel().setText("0");
        changeDispenserView.getRightSection().getFirstLabel().setText("0");
        changeDispenserView.getRightSection().getSecondLabel().setText("0");
        changeDispenserView.getRightSection().getThirdLabel().setText("0");
        changeDispenserView.getRightSection().getFourthLabel().setText("0");
    }

    /**
     * Method that show the new values into her coins labels
     *
     * @param coinStock the change of the user
     */
    public void chargeTheChange(CoinStock[] coinStock){
        changeDispenserView.getLeftSection().getFirstLabel().setText(String.valueOf(coinStock[0].getQuantity()));
        changeDispenserView.getLeftSection().getSecondLabel().setText(String.valueOf(coinStock[1].getQuantity()));
        changeDispenserView.getLeftSection().getThirdLabel().setText(String.valueOf(coinStock[2].getQuantity()));
        changeDispenserView.getLeftSection().getFourthLabel().setText(String.valueOf(coinStock[3].getQuantity()));
        changeDispenserView.getRightSection().getFirstLabel().setText(String.valueOf(coinStock[4].getQuantity()));
        changeDispenserView.getRightSection().getSecondLabel().setText(String.valueOf(coinStock[5].getQuantity()));
        changeDispenserView.getRightSection().getThirdLabel().setText(String.valueOf(coinStock[6].getQuantity()));
        changeDispenserView.getRightSection().getFourthLabel().setText(String.valueOf(coinStock[7].getQuantity()));
    }

    /**
     * Method that set the action of the action button
     * It return the change with the first click
     * It reset all with the second click
     */
    public void addActionToActionButton(){
        changeDispenserView.getActionButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isPressed){
                    if (changeDispenserModel.sumTheArray(saveTheMoney()) < cost){
                        changeDispenserView.getErrorMessage().setText("THE AMOUNT OF MONEY ENTERED DOES NOT SATISFY THE TICKET PRICE");
                    } else if (changeDispenserModel.sumTheArray(changeDispenserModel.getTheListOfCoinsInStock())
                            < (changeDispenserModel.sumTheArray(saveTheMoney()) - cost)) {
                        changeDispenserView.getErrorMessage().setText("NOT ENOUGH CHANGE");
                    }else {
                        returnChange();
                        changeDispenserView.getActionTittle().setText("CURRENCY CHANGE");
                        changeDispenserView.getActionButton().setText("DONE");
                        changeDispenserView.getErrorMessage().setText(" ");
                        try {
                            UserFlightInfoOperations.changeStatus(userID, flightID, Status.BUY);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        isPressed = true;
                    }
                }else {
                    if (isListOfPassenger){
                        group = new Group();
                        PassengerOfAFlightController view = new PassengerOfAFlightController(group, stage, flightID);
                        Scene scene = view.getView().getPassengerOfAFlightScene();
                        stage.setScene(scene);
                    }else{
                        //QUE TE LLEVE A LA LISTA DE USUARIOS QUE FALTA IMPLEMENTAR
                    }
                }
            }
        });
    }

}

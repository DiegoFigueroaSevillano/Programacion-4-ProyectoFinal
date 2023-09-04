package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.Coin.Coin;
import com.example.programacion4proyectofinal.Model.Coin.CoinStock;
import com.example.programacion4proyectofinal.Model.CurrencyChange.ChangeDispenser;
import com.example.programacion4proyectofinal.View.Pages.ChangeDispenserPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * Controller class for the Change Dispenser view.
 */
public class ChangeDispenserController {
    ChangeDispenserPage changeDispenserView;
    ChangeDispenser changeDispenserModel;
    boolean isPressed;

    /**
     * Constructor for the change dispenser controller.
     *
     * @param group  The root Group node.
     * @param stage The primary Stage.
     */
    public ChangeDispenserController(Group group, Stage stage){
        this.changeDispenserView = new ChangeDispenserPage(group, stage);
        this.changeDispenserModel = new ChangeDispenser();
        this.isPressed = false;
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
        CoinStock[] coinStock = changeDispenserModel.getTheChange(changeDispenserModel.sumTheArray(saveTheMoney()), 300);  //TODO: LA VISTA AL MOMENTO DE COMPARA EL TICKET DEBE MANDAR A ESTA EL COSTO DEL BOLETO
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
                    returnChange();
                    changeDispenserView.getActionButton().setText("RELOAD");
                    isPressed = true;
                }else {
                    resetLabels();
                    changeDispenserView.getActionButton().setText("RECEIVE PAYMENT");
                    isPressed = false;
                }
            }
        });
    }

}

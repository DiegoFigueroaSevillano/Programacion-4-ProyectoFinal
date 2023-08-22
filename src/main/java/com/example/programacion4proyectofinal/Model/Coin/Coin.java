package com.example.programacion4proyectofinal.Model.Coin;

/**
 * Inside the Coin class, we will have the currency type (in Bolivianos) along with its value.
 */
public enum Coin {
    ONE_HUNDRED_BS (200.00),
    TWO_HUNDRED_BS (100.00),
    FIFTY_BS (50.00),
    TWENTY_BS (20.00),
    TEN_BS (10.00),
    FIVE_BS (5.00),
    TWO_BS (2.00),
    ONE_BS (1.00),
    FIFTY_CTS (0.50);

    private final double coinValue;

    /**
     * Constructor method for the Coin class
     * @param coinValue Currency value
     */
    private Coin(double coinValue){
        this.coinValue = coinValue;
    }

    /**
     * Method that returns the currency value
     * @return the currency value.
     */
    public double getCoinValue(){
        return coinValue;
    }


}

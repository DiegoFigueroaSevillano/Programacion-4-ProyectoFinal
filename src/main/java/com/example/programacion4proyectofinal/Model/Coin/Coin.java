package com.example.programacion4proyectofinal.Model.Coin;

/**
 * Inside the Coin class, we will have the currency type (in Bolivianos) along with its value.
 */
public enum Coin {
    TWO_HUNDRED_BS (200),
    ONE_HUNDRED_BS (100),
    FIFTY_BS (50),
    TWENTY_BS (20),
    TEN_BS (10),
    FIVE_BS (5),
    TWO_BS (2),
    ONE_BS (1);

    private final int coinValue;

    /**
     * Constructor method for the Coin class
     * @param coinValue Currency value
     */
    private Coin(int coinValue){
        this.coinValue = coinValue;
    }

    /**
     * Method that returns the currency value
     * @return the currency value.
     */
    public int getCoinValue(){
        return coinValue;
    }


}

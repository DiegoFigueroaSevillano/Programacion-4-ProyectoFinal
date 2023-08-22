package com.example.programacion4proyectofinal.Model.Coin;

/**
 * Class where we will manage the currency along with its stock quantity.
 */
public class CoinStock implements Comparable<CoinStock> {
    private Coin coin;
    private int quantity;

    /**
     * Constructor method where we will initialize the currency type.
     *
     * @param coin the currency type
     */
    public CoinStock(Coin coin) {
        this.coin = coin;
        this.quantity = 10;
    }

    /**
     * Constructor method where we will initialize the currency type and its quantity.
     *
     * @param coin the currency type
     * @param quantity the quantity of the coin
     */
    public CoinStock(Coin coin, int quantity) {
        this.coin = coin;
        this.quantity = quantity;
    }

    /**
     * Method that returns the currency type.
     *
     * @return the currency type of coin
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * Method that returns the quantity of that currency.
     *
     * @return the quantity of the coin
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method that sets the quantity of the currency.
     *
     * @param quantity the new quantity of the coin
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method that compares two objects of the currency type.
     *
     * @param o the object to be compared.
     * @return Returns 1 if the currency has a greater quantity and -1 if it has a smaller quantity.
     */
    @Override
    public int compareTo(CoinStock o) {
        if (this.coin.getCoinValue() > o.coin.getCoinValue()){
            return 1;
        } else if (this.coin.getCoinValue() < o.coin.getCoinValue()) {
            return -1;
        }
        return 0;
    }

    /**
     * Method that returns the currency values along with their stock.
     *
     * @return A string that returns the currency type and quantity.
     */
    @Override
    public String toString() {
        return "Coin: " + coin.getCoinValue() + " Quantity: " + quantity + " -> ";
    }
}

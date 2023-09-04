package com.example.programacion4proyectofinal.Model.CurrencyChange;

import com.example.programacion4proyectofinal.Model.Coin.CoinStock;
import com.example.programacion4proyectofinal.Model.Coin.Coin;
import com.example.programacion4proyectofinal.Model.DataStructure.MaxHeap;

/**
 * Class where all the logic for giving change will be managed.
 */
public class ChangeDispenser {
    private MaxHeap<CoinStock> changeHeap;
    private MaxHeap<CoinStock> auxChangeHeap;

    /**
     * Constructor method where we initialize the change coins stock heap, an auxiliary heap for the same purpose,
     * and input the relevant coins into this heap.
     */
    public ChangeDispenser(){
        this.changeHeap = new MaxHeap<>(9);
        this.auxChangeHeap = new MaxHeap<>(9);
        initializeChangeHeap();
    }

    /**
     * Method that return us the changeHeap
     *
     * @return the change heap
     */
    public MaxHeap<CoinStock> getChangeHeap() {
        return changeHeap;
    }

    public CoinStock[] getTheListOfCoinsInStock(){
        CoinStock coin;
        CoinStock[] result = new CoinStock[9];
        int index = 0;
        while (changeHeap.peek() != null){
            coin = changeHeap.remove();
            result[index] = coin;
            auxChangeHeap.add(coin);
            index++;
        }
        swapTheLists();
        return result;
    }

    /**
     * Method that initializes the coins within its heap.
     */
    public void initializeChangeHeap(){
        changeHeap.add(new CoinStock(Coin.TWO_HUNDRED_BS));
        changeHeap.add(new CoinStock(Coin.ONE_HUNDRED_BS));
        changeHeap.add(new CoinStock(Coin.FIFTY_BS));
        changeHeap.add(new CoinStock(Coin.TWENTY_BS));
        changeHeap.add(new CoinStock(Coin.TEN_BS));
        changeHeap.add(new CoinStock(Coin.FIVE_BS));
        changeHeap.add(new CoinStock(Coin.TWO_BS));
        changeHeap.add(new CoinStock(Coin.ONE_BS));
    }

    /**
     * Method that returns the change that needs to be given to the customer.
     *
     * @param moneyReceived Money received from the customer.
     * @param cost Ticket cost.
     * @return A list with the change to be given to the customer.
     */
    public CoinStock[] getTheChange(int moneyReceived, int cost){
        CoinStock coin;
        int change = moneyReceived - cost;
        int accumulatedMoney = 0;
        CoinStock auxCoin;
        int quantityOfAuxCoin;
        CoinStock[] changeList = new CoinStock[9];
        int index = 0;

        while (changeHeap.peek() != null){
            coin = changeHeap.remove();
            auxCoin = new CoinStock(coin.getCoin(), 0);
            quantityOfAuxCoin = 0;
            while (accumulatedMoney < change && (accumulatedMoney + coin.getCoin().getCoinValue()) <= change
                    && coin.getQuantity() > 0 && coin.getCoin().getCoinValue() <= change){
                accumulatedMoney += coin.getCoin().getCoinValue();
                coin.setQuantity(coin.getQuantity() - 1);
                quantityOfAuxCoin++;
            }
            auxChangeHeap.add(coin);
            auxCoin.setQuantity(quantityOfAuxCoin);
            changeList[index] = auxCoin;
            index++;
            quantityOfAuxCoin = 0;
        }
        swapTheLists();
        return changeList;
    }

    /**
     * Method that swaps the data between the auxiliary list and the original heap.
     */
    private void swapTheLists(){
        changeHeap = auxChangeHeap;
        auxChangeHeap = new MaxHeap<>(9);
    }

    /**
     * Method that sums the coins
     *
     * @param coinStock the list of coins with her quantity
     * @return the total money
     */
    public int sumTheArray(CoinStock[] coinStock){
        int money = 0;
        for (CoinStock stock : coinStock) {
            money += stock.getCoin().getCoinValue() * stock.getQuantity();
        }
        return money;
    }

    /**
     * Method that stores the money and increments its stock within the heap.
     *
     * @param moneyList List of money being entered.
     */
    public void saveTheMoney(CoinStock[] moneyList){
        CoinStock coin;
        while (changeHeap.peek() != null){
            coin = changeHeap.remove();
            for (CoinStock money : moneyList){
                if (money.getCoin().compareTo(coin.getCoin()) == 0){
                    coin.setQuantity(coin.getQuantity() + money.getQuantity());
                }
            }
            auxChangeHeap.add(coin);
        }
        swapTheLists();
    }
}

package com.example.programacion4proyectofinal.StagesAndLogicTest;

import com.example.programacion4proyectofinal.Model.Coin.Coin;
import com.example.programacion4proyectofinal.Model.Coin.CoinStock;
import com.example.programacion4proyectofinal.Model.CurrencyChange.ChangeDispenser;


// CLASE CREADA PARA LA AYUDA CON EL TESTEO DE LA LOGICA

public class ChangeDispenserMain {
    public static void main(String[] args) {
        ChangeDispenser changeDispenser = new ChangeDispenser();
        changeDispenser.getChangeHeap().print();

        CoinStock[] change = changeDispenser.getTheChange(300, 50);
        for (CoinStock coin : change){
            System.out.print(coin);
        }
        System.out.println(" ");

        changeDispenser.getChangeHeap().print();

    }
}

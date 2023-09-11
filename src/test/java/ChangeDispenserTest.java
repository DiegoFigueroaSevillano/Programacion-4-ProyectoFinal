import com.example.programacion4proyectofinal.Model.Coin.Coin;
import com.example.programacion4proyectofinal.Model.Coin.CoinStock;
import com.example.programacion4proyectofinal.Model.CurrencyChange.ChangeDispenser;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class ChangeDispenserTest {

    @Test
    public void changeDispenserNormalTest(){
        ChangeDispenser change = new ChangeDispenser();
        CoinStock[] changeResult = change.getTheChange(300, 100);

        CoinStock[] changeExpected = new CoinStock[]{
                new CoinStock(Coin.TWO_HUNDRED_BS, 1),
                new CoinStock(Coin.ONE_HUNDRED_BS, 0),
                new CoinStock(Coin.FIFTY_BS, 0),
                new CoinStock(Coin.TWENTY_BS, 0),
                new CoinStock(Coin.TEN_BS, 0),
                new CoinStock(Coin.FIVE_BS, 0),
                new CoinStock(Coin.TWO_BS, 0),
                new CoinStock(Coin.ONE_BS, 0)
        };


        Assert.assertEquals(changeExpected[0].getQuantity(), changeResult[0].getQuantity());
        Assert.assertEquals(changeExpected[1].getQuantity(), changeResult[1].getQuantity());
        Assert.assertEquals(changeExpected[2].getQuantity(), changeResult[2].getQuantity());
        Assert.assertEquals(changeExpected[3].getQuantity(), changeResult[3].getQuantity());
        Assert.assertEquals(changeExpected[4].getQuantity(), changeResult[4].getQuantity());
        Assert.assertEquals(changeExpected[5].getQuantity(), changeResult[5].getQuantity());
        Assert.assertEquals(changeExpected[6].getQuantity(), changeResult[6].getQuantity());
        Assert.assertEquals(changeExpected[7].getQuantity(), changeResult[7].getQuantity());
        Assert.assertEquals(changeExpected[8].getQuantity(), changeResult[8].getQuantity());

    }

    @Test
    public void changeDispenserHardTest(){
        ChangeDispenser change = new ChangeDispenser();
        CoinStock[] changeResult = change.getTheChange(1537, 1079);

        CoinStock[] changeExpected = new CoinStock[]{
                new CoinStock(Coin.TWO_HUNDRED_BS, 2),
                new CoinStock(Coin.ONE_HUNDRED_BS, 0),
                new CoinStock(Coin.FIFTY_BS, 1),
                new CoinStock(Coin.TWENTY_BS, 0),
                new CoinStock(Coin.TEN_BS, 0),
                new CoinStock(Coin.FIVE_BS, 1),
                new CoinStock(Coin.TWO_BS, 1),
                new CoinStock(Coin.ONE_BS, 1)};


        Assert.assertEquals(changeExpected[0].getQuantity(), changeResult[0].getQuantity());
        Assert.assertEquals(changeExpected[1].getQuantity(), changeResult[1].getQuantity());
        Assert.assertEquals(changeExpected[2].getQuantity(), changeResult[2].getQuantity());
        Assert.assertEquals(changeExpected[3].getQuantity(), changeResult[3].getQuantity());
        Assert.assertEquals(changeExpected[4].getQuantity(), changeResult[4].getQuantity());
        Assert.assertEquals(changeExpected[5].getQuantity(), changeResult[5].getQuantity());
        Assert.assertEquals(changeExpected[6].getQuantity(), changeResult[6].getQuantity());
        Assert.assertEquals(changeExpected[7].getQuantity(), changeResult[7].getQuantity());
        Assert.assertEquals(changeExpected[8].getQuantity(), changeResult[8].getQuantity());
    }

    @Test
    public void saveMoneyTest(){
        ChangeDispenser change = new ChangeDispenser();

        CoinStock[] moneyList = new CoinStock[]{
                new CoinStock(Coin.TWO_HUNDRED_BS, 5),
                new CoinStock(Coin.ONE_HUNDRED_BS, 5),
                new CoinStock(Coin.FIFTY_BS, 5),
                new CoinStock(Coin.TWENTY_BS, 5),
                new CoinStock(Coin.TEN_BS, 1),
                new CoinStock(Coin.FIVE_BS, 1),
                new CoinStock(Coin.TWO_BS, 1),
                new CoinStock(Coin.ONE_BS, 1)};

        change.saveTheMoney(moneyList);

        CoinStock[] resultMoneyLists = change.getTheListOfCoinsInStock();


        Assert.assertEquals(15, resultMoneyLists[0].getQuantity());
        Assert.assertEquals(15, resultMoneyLists[1].getQuantity());
        Assert.assertEquals(15, resultMoneyLists[2].getQuantity());
        Assert.assertEquals(15, resultMoneyLists[3].getQuantity());
        Assert.assertEquals(11, resultMoneyLists[4].getQuantity());
        Assert.assertEquals(11, resultMoneyLists[5].getQuantity());
        Assert.assertEquals(11, resultMoneyLists[6].getQuantity());
        Assert.assertEquals(11, resultMoneyLists[7].getQuantity());
        Assert.assertEquals(20, resultMoneyLists[8].getQuantity());
    }

}

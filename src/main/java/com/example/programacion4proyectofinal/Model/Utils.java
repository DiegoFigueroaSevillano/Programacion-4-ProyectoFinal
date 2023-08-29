package com.example.programacion4proyectofinal.Model;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * This class is used for comparate the clients' reservation time and priority.
 */
public class Utils implements Comparator{
    /**
     * This method is used for compare the clients' priority.
     * @param o1
     * @param o2
     * @return
     */
    public int compareEnum(PRIORITY o1, PRIORITY o2) {
        int returnValue = 0;
        if (o1.getIndex() > o2.getIndex()) {
            returnValue = 1;
        } else if (o1.getIndex() < o2.getIndex()) {
            returnValue = -1;
        }
        return returnValue;
    }

    /**
     * This method is used for return the comparator.
     * @return
     */
    public static  Comparator returnComparator(){
        Utils utilsComparator = new Utils();
        Comparator<Client> customComparator = new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return utilsComparator.compareClient(o1, o2);
            }
        };
        return customComparator;
    }

    /**
     * THis method is used for compare clients' priority.
     * @param o1
     * @param o2
     * @return
     */
    public int compareClient(Client o1,Client o2){
        return compare(o1.priority,o2.priority);
    }


    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }

    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }
}

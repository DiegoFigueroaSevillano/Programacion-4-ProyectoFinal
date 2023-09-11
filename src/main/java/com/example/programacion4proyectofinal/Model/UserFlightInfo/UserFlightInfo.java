package com.example.programacion4proyectofinal.Model.UserFlightInfo;

import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.Search;
import com.example.programacion4proyectofinal.Utils.Generators.UserGenerator.DataGenerator;

import java.time.LocalDateTime;

/**
 * Class created for the management to the passenger in a flight and the status of her buy
 */
public class UserFlightInfo implements Comparable<UserFlightInfo> {
    private Passenger passenger;
    private int flightID;
    private Status status;
    private LocalDateTime localDateTime;

    /**
     * Constructor method where we initialize all the values
     *
     * @param userCI the Ci of the user
     * @param flightID the flight ID
     * @param status the status of the buy
     * @param localDateTime the time were the passenger buy the ticket
     */
    public UserFlightInfo(int userCI, int flightID, Status status, LocalDateTime localDateTime) {
        BTree<Passenger> bTree = new BTree<>(10, new FileHandlerBTree());
        Search search = new Search();
        DataGenerator dataGenerator = new DataGenerator();
        this.passenger = search.searchById(userCI);
        this.flightID = flightID;
        this.status = status;
        this.localDateTime = localDateTime;
    }


    /**
     * Method that return us the passenger
     *
     * @return the passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Method that return us the flight ID
     *
     * @return the flightID
     */
    public int getFlightID() {
        return flightID;
    }

    /**
     * Method that return us the status
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Method that return us the local date time
     *
     * @return local date time
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }


    /**
     * Method that compare two user flight info items
     *
     * @param o the object to be compared.
     * @return the comparator value
     */
    @Override
    public int compareTo(UserFlightInfo o) {
        int statusComparison = this.status.compare(o.status);
        if (statusComparison == 0) {
            int passengerComparison = this.passenger.compareTo(o.passenger);
            if (passengerComparison == 0) {
                return this.localDateTime.compareTo(o.localDateTime);
            } else {
                return passengerComparison;
            }
        } else {
            return statusComparison;
        }
    }


}

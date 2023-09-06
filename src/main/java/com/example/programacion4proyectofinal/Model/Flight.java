package com.example.programacion4proyectofinal.Model;
/**
 * Represents a flight in the flight booking application.
 */
public class Flight {
    private String id;
    private String placeDeparture;
    private String placeArrival;
    private double price;
    private String departureTime;
    private String arrivalTime;

    /**
     * Constructs a new Flight object with the specified attributes.
     *
     * @param id             The unique identifier of the flight.
     * @param placeDeparture The departure place of the flight.
     * @param placeArrival   The arrival place of the flight.
     * @param price          The price of the flight.
     * @param departureTime  The departure time of the flight.
     * @param arrivalTime    The arrival time of the flight.
     */
    public Flight(String id, String placeDeparture, String placeArrival, double price, String departureTime, String arrivalTime) {
        this.id = id;
        this.placeDeparture = placeDeparture;
        this.placeArrival = placeArrival;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Get the unique identifier of the flight.
     *
     * @return The flight's unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the unique identifier of the flight.
     *
     * @param id The flight's unique identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the departure place of the flight.
     *
     * @return The departure place.
     */
    public String getPlaceDeparture() {
        return placeDeparture;
    }

    /**
     * Set the departure place of the flight.
     *
     * @param placeDeparture The departure place.
     */
    public void setPlaceDeparture(String placeDeparture) {
        this.placeDeparture = placeDeparture;
    }

    /**
     * Get the arrival place of the flight.
     *
     * @return The arrival place.
     */
    public String getPlaceArrival() {
        return placeArrival;
    }

    /**
     * Set the arrival place of the flight.
     *
     * @param placeArrival The arrival place.
     */
    public void setPlaceArrival(String placeArrival) {
        this.placeArrival = placeArrival;
    }

    /**
     * Get the price of the flight.
     *
     * @return The flight's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the flight.
     *
     * @param price The flight's price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the departure time of the flight.
     *
     * @return The departure time.
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Set the departure time of the flight.
     *
     * @param departureTime The departure time.
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Get the arrival time of the flight.
     *
     * @return The arrival time.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Set the arrival time of the flight.
     *
     * @param arrivalTime The arrival time.
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
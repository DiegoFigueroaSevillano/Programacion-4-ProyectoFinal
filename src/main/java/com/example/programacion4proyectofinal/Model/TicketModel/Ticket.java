package com.example.programacion4proyectofinal.Model.TicketModel;

import com.example.programacion4proyectofinal.Model.Person.Passenger;

import java.util.Date;

/**
 * The Ticket class represents a flight ticket reservation.
 */
public class Ticket {

    private String from, to;
    private boolean isRoundTrip;
    private Date startDate, returnDate;
    private int numberOfAdults, numberOfChildren, numberOfBabies;
    private Passenger passenger;

    /**
     * Get the departure city or location.
     *
     * @return The departure city or location.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set the departure city or location.
     *
     * @param from The departure city or location.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Get the destination city or location.
     *
     * @return The destination city or location.
     */
    public String getTo() {
        return to;
    }

    /**
     * Set the destination city or location.
     *
     * @param to The destination city or location.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Check if the ticket is for a round-trip.
     *
     * @return True if the ticket is for a round-trip, false otherwise.
     */
    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    /**
     * Set whether the ticket is for a round-trip.
     *
     * @param roundTrip True if the ticket is for a round-trip, false otherwise.
     */
    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }

    /**
     * Get the start date of the ticket's journey.
     *
     * @return The start date of the journey.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the ticket's journey.
     *
     * @param startDate The start date of the journey.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the return date for a round-trip ticket.
     *
     * @return The return date for a round-trip ticket.
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Set the return date for a round-trip ticket.
     *
     * @param returnDate The return date for a round-trip ticket.
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Get the number of adult passengers.
     *
     * @return The number of adult passengers.
     */
    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    /**
     * Set the number of adult passengers.
     *
     * @param numberOfAdults The number of adult passengers.
     */
    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    /**
     * Get the number of child passengers.
     *
     * @return The number of child passengers.
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * Set the number of child passengers.
     *
     * @param numberOfChildren The number of child passengers.
     */
    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    /**
     * Get the number of infant passengers.
     *
     * @return The number of infant passengers.
     */
    public int getNumberOfBabies() {
        return numberOfBabies;
    }

    /**
     * Set the number of infant passengers.
     *
     * @param numberOfBabies The number of infant passengers.
     */
    public void setNumberOfBabies(int numberOfBabies) {
        this.numberOfBabies = numberOfBabies;
    }

    /**
     * Get the passenger associated with the ticket.
     *
     * @return The passenger associated with the ticket.
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Set the passenger associated with the ticket.
     *
     * @param passenger The passenger associated with the ticket.
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
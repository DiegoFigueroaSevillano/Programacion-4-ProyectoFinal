package com.example.programacion4proyectofinal.Model.Flight;

import com.example.programacion4proyectofinal.Model.Person.Passenger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Flight {
    Integer idFlight;
    String origin;
    String destination;
    String airline;
    Date departureDate;
    Date departureTime;
    Date arrivalDate;
    Date arrivalTime;
    ArrayList<Seat> seatsFlight = new ArrayList();


    public Flight(Integer idFlight, String origin, String destination, String airline, Date departureDate, Date departureTime, Date arrivalDate, Date arrivalTime) {
        this.idFlight = idFlight;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }
    private void addSeat(Integer idSeat){
       Seat seat = new Seat(234,2,4,"regular", "Adolfo ","Gonzales");
       seatsFlight.add(seat);
    }

    private boolean deleteSeat(Integer idSeat){
        ArrayList<Seat> auxSeatList = new ArrayList<>();
        for(int i=0;i<seatsFlight.size();i++ ) {
            if (!seatsFlight.get(i).namePassenger.isEmpty() && seatsFlight.get(i).idSeat == idSeat) {
                seatsFlight.get(i).namePassenger = null;
                return true;
            }
        }
        return false;
}

    private boolean addPassenger(Integer idSeat, String namePassenger){
        ArrayList<Seat> auxSeatList = new ArrayList<>();
        for(int i=0;i<seatsFlight.size();i++ ) {
            if (seatsFlight.get(i).namePassenger.isEmpty() && seatsFlight.get(i).idSeat == idSeat) {
                seatsFlight.get(i).namePassenger = namePassenger;
                return true;
            }
        }
        return false;
    }

    private ArrayList getSeats(){
       ArrayList<Seat> auxSeatList = new ArrayList<>();
       for(int i=0;i<seatsFlight.size();i++ ){
           if( seatsFlight.get(i).namePassenger.isEmpty()){
               seatsFlight.get(i);
           }
       }
       return seatsFlight;
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getAirline() {
        return airline;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public ArrayList<Seat> getSeatsFlight() {
        return seatsFlight;
    }
}

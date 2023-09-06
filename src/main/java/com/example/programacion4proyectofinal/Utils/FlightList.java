package com.example.programacion4proyectofinal.Utils;


import com.example.programacion4proyectofinal.Model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightList {

    private List<Flight> listFlights ;

    public FlightList() {
        this.listFlights = new ArrayList<>();
        addFlightList();
    }

    public void addFlightList(){
        listFlights.add(new Flight("V1", "PERU", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "LA PAZ", "COCHABAMBA", 250.0, "14:10" , "15:10"));

        listFlights.add(new Flight("V1", "SANTA CRUZ", "BRASIL", 250.0, "14:10" , "15:10"));

        listFlights.add(new Flight("V1", "ORURO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "BENI", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "PANDO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "TARIJA", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "ORURO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "POTOSI", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "SUCRE", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "LA PAZ", "BRASIL", 250.0, "14:10" , "15:10"));

        listFlights.add(new Flight("V1", "SANTA CRUZ", "PERU", 250.0, "14:10" , "15:10"));

        listFlights.add(new Flight("V1", "ORURO", "ARGENTINA", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "BENI", "COLOMBIA", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "PANDO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "TARIJA", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "ORURO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "POTOSI", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "ORURO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "LA PAZ", "POTOSI", 250.0, "14:10" , "15:10"));

        listFlights.add(new Flight("V1", "SANTA CRUZ", "BRASIL", 250.0, "14:10" , "15:10"));

        listFlights.add(new Flight("V1", "ORURO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "BENI", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "PANDO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "TARIJA", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "ORURO", "SANTA CRUZ", 250.0, "14:10" , "15:10"));
        listFlights.add(new Flight("V1", "POTOSI", "SANTA CRUZ", 250.0, "14:10" , "15:10"));



    }


        public List<Flight> getListaVuelos(){
            return listFlights;
        }


}

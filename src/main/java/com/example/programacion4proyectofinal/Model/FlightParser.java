package com.example.programacion4proyectofinal.Model;


    /**
     * The FlightParser class represents flight data obtained from a JSON file.
     */
    public class FlightParser {
        private int idFlight;
        private String origin;
        private String destination;
        private String airline;
        private String departureDate;
        private String arrivalDate;
        private int price;

        /**
         * Constructor to initialize a FlightParser object.
         *
         * @param idFlight      The unique identifier for the flight.
         * @param origin        The place of departure for the flight.
         * @param destination   The destination place for the flight.
         * @param airline       The airline operating the flight.
         * @param departureDate The departure date and time of the flight.
         * @param arrivalDate   The arrival date and time of the flight.
         * @param price         The price of the flight ticket.
         */
        public FlightParser(int idFlight, String origin, String destination, String airline, String departureDate, String arrivalDate, int price) {
            this.idFlight = idFlight;
            this.origin = origin;
            this.destination = destination;
            this.airline = airline;
            this.departureDate = departureDate;
            this.arrivalDate = arrivalDate;
            this.price = price;
        }

        /**
         * Get the unique identifier for the flight.
         *
         * @return The flight's unique identifier.
         */
        public int getIdFlight() {
            return idFlight;
        }

        /**
         * Set the unique identifier for the flight.
         *
         * @param idFlight The new unique identifier for the flight.
         */
        public void setIdFlight(int idFlight) {
            this.idFlight = idFlight;
        }

        /**
         * Get the place of departure for the flight.
         *
         * @return The place of departure.
         */
        public String getOrigin() {
            return origin;
        }

        /**
         * Set the place of departure for the flight.
         *
         * @param origin The new place of departure for the flight.
         */
        public void setOrigin(String origin) {
            this.origin = origin;
        }

        /**
         * Get the destination place for the flight.
         *
         * @return The destination place.
         */
        public String getDestination() {
            return destination;
        }

        /**
         * Set the destination place for the flight.
         *
         * @param destination The new destination place for the flight.
         */
        public void setDestination(String destination) {
            this.destination = destination;
        }

        /**
         * Get the airline operating the flight.
         *
         * @return The airline's name.
         */
        public String getAirline() {
            return airline;
        }

        /**
         * Set the airline operating the flight.
         *
         * @param airline The new airline operating the flight.
         */
        public void setAirline(String airline) {
            this.airline = airline;
        }

        /**
         * Get the departure date and time of the flight.
         *
         * @return The departure date and time.
         */
        public String getDepartureDate() {
            return departureDate;
        }

        /**
         * Set the departure date and time of the flight.
         *
         * @param departureDate The new departure date and time.
         */
        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        /**
         * Get the arrival date and time of the flight.
         *
         * @return The arrival date and time.
         */
        public String getArrivalDate() {
            return arrivalDate;
        }

        /**
         * Set the arrival date and time of the flight.
         *
         * @param arrivalDate The new arrival date and time.
         */
        public void setArrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
        }

        /**
         * Get the price of the flight ticket.
         *
         * @return The price of the flight ticket.
         */
        public int getPrice() {
            return price;
        }

        /**
         * Set the price of the flight ticket.
         *
         * @param price The new price of the flight ticket.
         */
        public void setPrice(int price) {
            this.price = price;
        }
    }

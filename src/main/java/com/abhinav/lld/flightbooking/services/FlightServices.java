package com.abhinav.lld.flightbooking.services;

import com.abhinav.lld.flightbooking.core.Airplane;
import com.abhinav.lld.flightbooking.core.Airport;
import com.abhinav.lld.flightbooking.core.Flight;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FlightServices {
    private final Map<Integer, Flight> flights;
    private final AtomicInteger flightCounter;

    public FlightServices(Map<Integer, Flight> flights) {
        this.flights = flights;
        this.flightCounter = new AtomicInteger(0);
    }

    public Flight getFlight(int id) throws Exception {
        if (!flights.containsKey(id)) {
            throw new Exception("Flight with id: " + id + " not found");
        }
        return flights.get(id);
    }

    public Flight createFlight(Airplane airplane, Airport departure, Airport destination, Instant departureTime, Instant arrivalTime, int flightDuration) {
        int flightNumber = flightCounter.incrementAndGet();
        Flight flight = new Flight(flightNumber, airplane, departure, destination, departureTime, arrivalTime, flightDuration);
        flights.put(flightNumber, flight);
        return flight;
    }
}

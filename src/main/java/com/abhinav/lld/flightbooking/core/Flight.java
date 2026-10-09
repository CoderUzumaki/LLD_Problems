package com.abhinav.lld.flightbooking.core;

import java.time.Instant;

public class Flight {
    private final int flightNumber;
    private final Airplane airplane;
    private final Airport departure;
    private final Airport destination;
    private final Instant departureTime;
    private final Instant arrivalTime;
    private final int flightDuration;

    public Flight (int flightNumber, Airplane airplane, Airport departure, Airport destination, Instant departureTime, Instant arrivalTime, int flightDuration) {
        this.flightNumber = flightNumber;
        this.airplane = airplane;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
    }
}

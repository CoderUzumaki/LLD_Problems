package com.abhinav.lld.flightbooking.core;

import java.util.ArrayList;
import java.util.List;

public class Airplane {
    private final int id;
    private final String name;
    private final Airline airline;
    private final List<Seat>  seats;

    public Airplane(int id, String name, Airline airline) {
        this.id = id;
        this.name = name;
        this.airline = airline;
        this.seats = new ArrayList<>();
    }
}

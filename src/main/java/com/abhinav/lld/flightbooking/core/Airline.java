package com.abhinav.lld.flightbooking.core;

import java.util.ArrayList;
import java.util.List;

public class Airline {
    private final String id;
    private final String name;
    private List<Airplane> airplanes;

    public Airline (String id, String name) {
        this.id = id;
        this.name = name;
        airplanes = new ArrayList<Airplane>();
    }

    public void addAirplane (Airplane airplane) {
        airplanes.add(airplane);
    }

    // getters & setters...
}

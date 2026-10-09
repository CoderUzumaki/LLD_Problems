package com.abhinav.lld.flightbooking.core;

import com.abhinav.lld.flightbooking.enums.SeatCategory;

public class Seat {
    private final String seatNumber;
    private final SeatCategory seatCategory;

    public Seat(String seatNumber, SeatCategory seatCategory) {
        this.seatNumber = seatNumber;
        this.seatCategory = seatCategory;
    }
}

package com.abhinav.lld.parkinglot.core;

import java.util.Objects;

public class ExitGate {

    private final String gateId;
    private final ParkingLot parkingLot;

    public ExitGate(String gateId, ParkingLot parkingLot) {
        this.gateId = Objects.requireNonNull(gateId);
        this.parkingLot = Objects.requireNonNull(parkingLot);
    }

    public double exit(Vehicle vehicle, String ticketId) {
        return parkingLot.exitVehicle(vehicle, ticketId);
    }

    public String getGateId() {
        return gateId;
    }
}
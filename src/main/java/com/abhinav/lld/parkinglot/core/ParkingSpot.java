package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.SpotStatus;
import com.abhinav.lld.parkinglot.enums.VehicleType;

public class ParkingSpot {
    private final VehicleType spotType;
    private final String spotId;
    private SpotStatus spotStatus;

    public ParkingSpot(VehicleType spotType, String spotId) {
        this.spotType = spotType;
        this.spotId = spotId;
        this.spotStatus = SpotStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return spotStatus == SpotStatus.AVAILABLE;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean occupy(Vehicle vehicle) {
        if (spotStatus == SpotStatus.OCCUPIED || spotType != vehicle.getVehicleType()) {
            return false;
        }
        spotStatus = SpotStatus.OCCUPIED;
        return true;
    }

    public boolean release() {
        if (spotStatus == SpotStatus.AVAILABLE) {
            return false;
        }

        spotStatus = SpotStatus.AVAILABLE;
        return true;
    }
}



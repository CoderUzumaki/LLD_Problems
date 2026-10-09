package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.VehicleType;

import java.util.*;

public class ParkingFloor {
    private final String floorId;
    private final Map<String, ParkingSpot> parkingSpots;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        this.parkingSpots = new LinkedHashMap<>();
    }

    public void addParkingSpot(String spotId, VehicleType spotType) {
        Objects.requireNonNull(spotId);
        Objects.requireNonNull(spotType);

        if (parkingSpots.containsKey(spotId)) {
            throw new IllegalArgumentException("ParkingSpot already exists");
        }

        ParkingSpot parkingSpot = new ParkingSpot(spotType, spotId);
        this.parkingSpots.put(spotId, parkingSpot);
    }

    public int getAvailableSpotsCount(VehicleType vehicleType) {
        int availableSpotCount = 0;

        for (ParkingSpot parkingSpot : this.parkingSpots.values()) {
            if (parkingSpot.getSpotType().equals(vehicleType) && parkingSpot.isAvailable()) {
                availableSpotCount++;
            }
        }

        return availableSpotCount;
    }

    public Optional<ParkingSpot> getAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot parkingSpot : this.parkingSpots.values()) {
            if (parkingSpot.getSpotType().equals(vehicleType) && parkingSpot.isAvailable()) {
                return Optional.of(parkingSpot);
            }
        }

        return Optional.empty();
    }
}

package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public class FirstAvailableSpot implements SpotAllocationStrategy {
    @Override
    public Optional<ParkingSpot> getAvailableSpot(List<ParkingFloor> parkingFloors, VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            Optional<ParkingSpot> parkingSpot = parkingFloor.getAvailableSpot(vehicleType);
            if (parkingSpot.isPresent()) {
                return parkingSpot;
            }
        }
        return Optional.empty();
    }
}

package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public interface SpotAllocationStrategy {
    public Optional<ParkingSpot> getAvailableSpot(List<ParkingFloor> parkingFloors, VehicleType vehicleType);
}

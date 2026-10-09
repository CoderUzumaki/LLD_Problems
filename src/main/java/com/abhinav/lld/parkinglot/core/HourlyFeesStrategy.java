package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

public class HourlyFeeStrategy implements FeeCalculationStrategy {

    private final Map<VehicleType, Double> rates;

    public HourlyFeeStrategy() {
        rates = new EnumMap<>(VehicleType.class);

        rates.put(VehicleType.BIKE, 10.0);
        rates.put(VehicleType.CAR, 20.0);
        rates.put(VehicleType.TRUCK, 30.0);
    }

    @Override
    public double calculateFee(
            ParkingTicket ticket,
            LocalDateTime exitTime) {

        if (exitTime == null ||
                exitTime.isBefore(ticket.getEntryTime())) {
            throw new IllegalArgumentException("Invalid exit time");
        }

        long seconds = Duration.between(
                ticket.getEntryTime(),
                exitTime
        ).getSeconds();

        long hours = Math.max(1, (seconds + 3599) / 3600);

        VehicleType type = ticket.getVehicle().getVehicleType();

        return hours * rates.get(type);
    }
}
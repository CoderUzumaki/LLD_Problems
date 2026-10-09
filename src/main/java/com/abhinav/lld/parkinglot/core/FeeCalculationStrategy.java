package com.abhinav.lld.parkinglot.core;

import java.time.LocalDateTime;

public interface FeeCalculationStrategy {

    double calculateFee(
            ParkingTicket ticket,
            LocalDateTime exitTime
    );
}
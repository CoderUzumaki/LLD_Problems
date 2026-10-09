package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fare;
    private TicketStatus ticketStatus;

    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
        Objects.requireNonNull(vehicle, "vehicle cannot be null");
        Objects.requireNonNull(parkingSpot, "parkingSpot cannot be null");
        Objects.requireNonNull(entryTime, "entryTime cannot be null");

        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
        this.ticketStatus = TicketStatus.ACTIVE;
    }

    public void completeTicket(LocalDateTime exitTime, double fare) {
        if (this.ticketStatus == TicketStatus.COMPLETED) {
            throw new IllegalStateException("ticketStatus is already completed, can't change now.");
        }

        if (exitTime == null || exitTime.isBefore(this.entryTime)) {
            throw new IllegalArgumentException("exitTime is before entryTime is set.");
        }

        if (fare < 0 || !Double.isFinite(fare)) {
            throw new IllegalArgumentException("Invalid fare");
        }

        this.exitTime = exitTime;
        this.fare = fare;
        this.ticketStatus = TicketStatus.COMPLETED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getFare() {
        return fare;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }
}

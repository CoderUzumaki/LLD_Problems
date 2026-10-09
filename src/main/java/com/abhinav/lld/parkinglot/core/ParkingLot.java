package com.abhinav.lld.parkinglot.core;

import com.abhinav.lld.parkinglot.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {

    private final List<ParkingFloor> floors;
    private final SpotAllocationStrategy allocationStrategy;
    private final FeeCalculationStrategy feeStrategy;

    private final Map<String, ParkingTicket> activeTicketsByVehicle;
    private final Map<String, ParkingTicket> ticketsById;

    public ParkingLot(SpotAllocationStrategy allocationStrategy, FeeCalculationStrategy feeStrategy) {

        this.allocationStrategy = Objects.requireNonNull(allocationStrategy);
        this.feeStrategy = Objects.requireNonNull(feeStrategy);

        this.floors = new ArrayList<>();
        this.activeTicketsByVehicle = new HashMap<>();
        this.ticketsById = new HashMap<>();
    }

    public void addFloor(ParkingFloor floor) {
        Objects.requireNonNull(floor, "Floor cannot be null");

        floors.add(floor);
    }

    public boolean isVehicleParked(String licensePlate) {
        return activeTicketsByVehicle.containsKey(licensePlate);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle cannot be null");

        String licensePlate = vehicle.getLicensePlate();

        // 1. Prevent duplicate entry
        if (isVehicleParked(licensePlate)) {
            throw new IllegalStateException("Vehicle already parked");
        }

        // 2. Find a suitable parking spot
        ParkingSpot spot = allocationStrategy
                .getAvailableSpot(floors, vehicle.getVehicleType())
                .orElseThrow(() -> new IllegalStateException("no suitable spot found."));


        // 3. Generate ticket before modifying spot state
        ParkingTicket ticket = new ParkingTicket(
                vehicle,
                spot,
                LocalDateTime.now()
        );

        String ticketId = ticket.getTicketId();

        // 4. Verify uniqueness before registration
        if (ticketsById.containsKey(ticketId)) {
            throw new IllegalStateException("Duplicate ticket ID");
        }

        // 5. Reserve the spot
        if (!spot.occupy(vehicle)) {
            throw new IllegalStateException("Failed to occupy spot");
        }

        // 6. Register the parking session
        try {
            ticketsById.put(ticketId, ticket);
            activeTicketsByVehicle.put(licensePlate, ticket);

            return ticket;

        } catch (RuntimeException e) {

            // Roll back changes made during this operation
            activeTicketsByVehicle.remove(licensePlate);
            ticketsById.remove(ticketId);
            spot.release();

            throw e;
        }
    }

    public double exitVehicle(Vehicle vehicle, String ticketId) {

        Objects.requireNonNull(vehicle, "Vehicle cannot be null");
        Objects.requireNonNull(ticketId, "Ticket ID cannot be null");

        // 1. Retrieve ticket
        ParkingTicket ticket = ticketsById.get(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket ID");
        }

        if (ticket.getTicketStatus() != TicketStatus.ACTIVE) {
            throw new IllegalStateException("Ticket already completed");
        }

        String licensePlate = vehicle.getLicensePlate();

        if (!ticket.getVehicle().getLicensePlate().equals(licensePlate)
                || ticket.getVehicle().getVehicleType() != vehicle.getVehicleType()) {
            throw new IllegalArgumentException(
                    "Ticket does not belong to this vehicle"
            );
        }

        if (activeTicketsByVehicle.get(licensePlate) != ticket) {
            throw new IllegalStateException(
                    "No matching active parking session"
            );
        }
        LocalDateTime exitTime = LocalDateTime.now();

        double fee = feeStrategy.calculateFee(ticket, exitTime);
        // Assuming Payment is Successful
        ParkingSpot spot = ticket.getParkingSpot();

        if (!spot.release()) {
            throw new IllegalStateException(
                    "Parking spot could not be released"
            );
        }
        ticket.completeTicket(exitTime, fee);

        activeTicketsByVehicle.remove(licensePlate);

        return fee;
    }
}
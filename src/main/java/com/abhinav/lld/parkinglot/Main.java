package com.abhinav.lld.parkinglot;

import com.abhinav.lld.parkinglot.core.*;
import com.abhinav.lld.parkinglot.enums.VehicleType;

public class Main {
    public static void main(String[] args) {

        SpotAllocationStrategy strategy = new FirstAvailableSpot();
        FeeCalculationStrategy pricing = new HourlyFeeStrategy();
        ParkingLot lot = new ParkingLot(strategy, pricing);

        ParkingFloor floor1 = new ParkingFloor("F1");
        ParkingFloor floor2 = new ParkingFloor("F2");

        // Using your revised ParkingFloor.addParkingSpot()
        // which creates its own ParkingSpot objects.
        floor1.addParkingSpot("C101", VehicleType.CAR);
        floor1.addParkingSpot("B101", VehicleType.BIKE);

        floor2.addParkingSpot("C201", VehicleType.CAR);
        floor2.addParkingSpot("T201", VehicleType.TRUCK);

        lot.addFloor(floor1);
        lot.addFloor(floor2);

        Vehicle car = new Vehicle("CG04AB1234", VehicleType.CAR);

        ParkingTicket ticket = lot.parkVehicle(car);

        System.out.println("Ticket: " + ticket.getTicketId());
        System.out.println("Spot: " + ticket.getParkingSpot().getSpotId());

        System.out.println(
                "Is parked: " + lot.isVehicleParked("CG04AB1234")
        );

        // Attempt duplicate entry
        try {
            lot.parkVehicle(car);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
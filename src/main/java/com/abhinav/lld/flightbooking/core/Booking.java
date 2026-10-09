package com.abhinav.lld.flightbooking.core;

import com.abhinav.lld.flightbooking.enums.BookingStatus;

import java.util.List;

public class Booking {
    private final String id;
    private final Flight flight;
    private final List<Seat> seatsBooked;
    private final User user;
    private BookingStatus bookingStatus;

    public Booking(String id, Flight flight, List<Seat> seatsBooked, User user) {
        this.id = id;
        this.flight = flight;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public boolean isConfirmed() {
        return bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws Exception {
        if (bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot confirm a booking that is not in Created state.");
        }
        bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws Exception {
        if (bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot expire a booking that is not in Created state.");
        }
        bookingStatus = BookingStatus.EXPIRED;
    }
}

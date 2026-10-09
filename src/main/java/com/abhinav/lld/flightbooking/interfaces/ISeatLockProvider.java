package com.abhinav.lld.flightbooking.interfaces;

import com.abhinav.lld.flightbooking.core.Flight;
import com.abhinav.lld.flightbooking.core.Seat;
import com.abhinav.lld.flightbooking.core.User;

import java.util.List;

public interface ISeatLockProvider {
    void lockSeats(Flight flight, List<Seat> seat, User user);
    void unlockSeats(Flight flight, List<Seat> seat, User user);
    boolean verifyLock(Flight flight, Seat seat, User user);
    List<Seat> getLockedSeats(Flight flight);
}

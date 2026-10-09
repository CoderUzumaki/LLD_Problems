package com.abhinav.lld.flightbooking.core;

import com.abhinav.lld.flightbooking.interfaces.ISeatLockProvider;
import kotlin.jvm.Synchronized;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SeatLockProvider implements ISeatLockProvider {
    private final Integer lockTimeoutInSeconds;
    private final Map<Flight, Map<Seat, SeatLock>> locks;

    public SeatLockProvider(Integer lockTimeoutInSeconds) {
        this.lockTimeoutInSeconds = lockTimeoutInSeconds;
        this.locks = new ConcurrentHashMap<>();
    }

    @Override
    public void lockSeats(Flight flight, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLocks = locks.computeIfAbsent(flight, f -> new ConcurrentHashMap<>());

        synchronized (seatLocks) {
            for (Seat seat : seats) {
                if (seatLocks.containsKey(seat)) {
                    SeatLock existingLock = seatLocks.get(seat);
                    if (!existingLock.isLockExpired()) {
                        throw new Exception("Seat " + seat.getSeatId() + " is already locked");
                    }
                }
            }

            Date now = new Date();
            for (Seat seat : seats) {
                SeatLock lock = new SeatLock(seat, flight, lockTimeoutInSeconds, now, user);
                seatLocks.put(seat, lock);
            }
        }
    }

    @Override
    public void unlockSeats(Flight flight, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(flight);
        if (seatLocks == null) return;

        synchronized (seatLocks) {
            for (Seat seat : seats) {
                SeatLock lock = seatLocks.get(seat);
                if (lock != null && lock.getLockedBy().equals(user)) {
                    seatLocks.remove(seat);
                }
            }
        }
    }

    @Override
    public boolean verifyLock(Flight flight, Seat seat, User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(flight);
        if (seatLocks == null) return false;
        synchronized (seatLocks) {
            SeatLock lock = seatLocks.get(seat);
            return lock != null && !lock.isLockExpired() && lock.getLockedBy().equals(user);
        }
    }

    @Override
    public List<Seat> getLockedSeats(Flight flight) {
        Map<Seat, SeatLock> seatLocks = locks.get(flight);
        if (seatLocks == null) return Collections.emptyList();

        synchronized (seatLocks) {
            return seatLocks.entrySet().stream()
                    .filter(entry -> !entry.getValue().isLockExpired())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }
}

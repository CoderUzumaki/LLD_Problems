package com.abhinav.lld.flightbooking.core;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Flight flight;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private User user;

    public SeatLock (Seat seat, Flight flight, Integer timeoutInSeconds, Date lockTime, User user) {
        this.seat = seat;
        this.flight = flight;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.user = user;
    }

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant now = Instant.now();
        return now.isAfter(lockInstant);
    }

    public User getLockedBy() {
        return user;
    }
}

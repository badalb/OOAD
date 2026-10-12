package org.example.car_rental_system;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private final String id;
    private final Customer customer;
    private final Vehicle vehicle;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private ReservationStatus status;

    Reservation(Customer customer, Vehicle vehicle,
                LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("Invalid rental period");
        }

        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.vehicle = vehicle;
        this.start = start;
        this.end = end;
        this.status = ReservationStatus.CONFIRMED;
    }

    boolean overlaps(LocalDateTime requestedStart,
                     LocalDateTime requestedEnd) {
        return start.isBefore(requestedEnd)
                && requestedStart.isBefore(end);
    }

    void cancel() {
        if (status != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException(
                    "Reservation cannot be cancelled");
        }
        status = ReservationStatus.CANCELLED;
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    ReservationStatus getStatus() {
        return status;
    }

    String getId() {
        return id;
    }
}

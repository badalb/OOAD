package org.example.car_rental_system;

import java.time.LocalDateTime;
import java.util.UUID;

public class Rental {
    private final String id;
    private final Reservation reservation;
    private final LocalDateTime pickupTime;
    private LocalDateTime returnTime;
    private RentalStatus status = RentalStatus.ACTIVE;

    Rental(Reservation reservation) {
        this.id = UUID.randomUUID().toString();
        this.reservation = reservation;
        this.pickupTime = LocalDateTime.now();
    }

    void complete() {
        if (status != RentalStatus.ACTIVE) {
            throw new IllegalStateException("Rental is not active");
        }

        returnTime = LocalDateTime.now();
        status = RentalStatus.COMPLETED;
        reservation.getVehicle().setStatus(VehicleStatus.AVAILABLE);
    }

    String getId() {
        return id;
    }

    RentalStatus getStatus() {
        return status;
    }

    Reservation getReservation() {
        return  this.reservation;
    }
}

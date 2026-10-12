package org.example.car_rental_system;

import java.time.LocalDateTime;
import java.util.*;

public class ReservationService {
    private final List<Reservation> reservations =
            new ArrayList<>();

    synchronized Reservation reserve(
            Customer customer,
            Vehicle vehicle,
            LocalDateTime start,
            LocalDateTime end) {
        if (vehicle.getStatus() == VehicleStatus.RENTED
                || vehicle.getStatus()
                == VehicleStatus.MAINTENANCE) {
            throw new IllegalStateException(
                    "Vehicle is not available");
        }

        boolean overlapping = reservations.stream()
                .anyMatch(r ->
                        r.getVehicle() == vehicle
                                && r.getStatus()
                                == ReservationStatus.CONFIRMED
                                && r.overlaps(start, end)
                );

        if (overlapping) {
            throw new IllegalStateException(
                    "Vehicle already reserved for this period");
        }

        Reservation reservation =
                new Reservation(customer, vehicle, start, end);

        reservations.add(reservation);
        return reservation;
    }

    synchronized void cancel(String reservationId) {
        Reservation reservation = reservations.stream()
                .filter(r -> r.getId().equals(reservationId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Reservation not found"));

        reservation.cancel();
    }

    synchronized List<Reservation> getReservations() {
        return List.copyOf(reservations);
    }
}

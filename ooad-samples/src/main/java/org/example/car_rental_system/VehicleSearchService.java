package org.example.car_rental_system;


import java.time.LocalDateTime;
import java.util.*;

public class VehicleSearchService {

    List<Vehicle> search(
            Branch branch,
            VehicleType type,
            LocalDateTime start,
            LocalDateTime end,
            List<Reservation> reservations
    ) {
        if (start == null || end == null
                || !start.isBefore(end)) {
            throw new IllegalArgumentException(
                    "Invalid rental period");
        }

        List<Vehicle> available = new ArrayList<>();

        for (Vehicle vehicle : branch.getVehicles()) {
            if (vehicle.getType() != type
                    || vehicle.getStatus()
                    == VehicleStatus.RENTED
                    || vehicle.getStatus()
                    == VehicleStatus.MAINTENANCE) {
                continue;
            }

            boolean overlapping = reservations.stream()
                    .anyMatch(r ->
                            r.getVehicle() == vehicle
                                    && r.getStatus()
                                    == ReservationStatus.CONFIRMED
                                    && r.overlaps(start, end)
                    );

            if (!overlapping) {
                available.add(vehicle);
            }
        }

        return available;
    }
}


package org.example.parking_lot;

import java.time.Instant;

public class ParkingTicket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final Instant entryTime;
    private Instant exitTime;

    ParkingTicket(String id, Vehicle vehicle, ParkingSpot spot) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = Instant.now();
    }

    void close() {
        if (exitTime != null) {
            throw new IllegalStateException("Ticket already closed");
        }
        exitTime = Instant.now();
    }

    String getId() {
        return id;
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    ParkingSpot getSpot() {
        return spot;
    }

    Instant getEntryTime() {
        return entryTime;
    }

    Instant getExitTime() {
        return exitTime;
    }
}
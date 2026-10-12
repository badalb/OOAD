package org.example.parking_lot;

import java.util.Objects;

public class Vehicle {
    private final String licensePlate;
    private final VehicleType type;

    Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = Objects.requireNonNull(licensePlate);
        this.type = Objects.requireNonNull(type);
    }

    String getLicensePlate() {
        return licensePlate;
    }

    VehicleType getType() {
        return type;
    }
}

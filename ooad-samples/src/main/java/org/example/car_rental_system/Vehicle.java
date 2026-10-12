package org.example.car_rental_system;

public class Vehicle {
    private final String registrationNumber;
    private final VehicleType type;
    private final double dailyRate;
    private VehicleStatus status = VehicleStatus.AVAILABLE;

    Vehicle(String registrationNumber,
            VehicleType type, double dailyRate) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.dailyRate = dailyRate;
    }

    String getRegistrationNumber() {
        return registrationNumber;
    }

    VehicleType getType() {
        return type;
    }

    double getDailyRate() {
        return dailyRate;
    }

    VehicleStatus getStatus() {
        return status;
    }

    void setStatus(VehicleStatus status) {
        this.status = status;
    }
}

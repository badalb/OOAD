package org.example.parking_lot;

public class ParkingSpot {

    private final String id;
    private final SpotType type;
    private SpotStatus status = SpotStatus.AVAILABLE;
    private Vehicle vehicle;

    ParkingSpot(String id, SpotType type) {
        this.id = id;
        this.type = type;
    }

    boolean canFit(Vehicle v) {
        if (status != SpotStatus.AVAILABLE) {
            return false;
        }

        return switch (v.getType()) {
            case MOTORCYCLE -> true;
            case CAR -> type != SpotType.MOTORCYCLE;
            case TRUCK -> type == SpotType.LARGE;
        };
    }

    synchronized boolean park(Vehicle v) {
        if (!canFit(v)) {
            return false;
        }

        vehicle = v;
        status = SpotStatus.OCCUPIED;
        return true;
    }

    synchronized Vehicle removeVehicle() {
        if (status != SpotStatus.OCCUPIED) {
            throw new IllegalStateException("Spot is not occupied");
        }

        Vehicle removed = vehicle;
        vehicle = null;
        status = SpotStatus.AVAILABLE;
        return removed;
    }

    String getId() {
        return id;
    }

    SpotType getType() {
        return type;
    }

    SpotStatus getStatus() {
        return status;
    }

}

package org.example.parking_lot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>(spots);
    }

    synchronized ParkingSpot findAndPark(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.park(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    synchronized ParkingSpot findSpot(String spotId) {
        return spots.stream()
                .filter(s -> s.getId().equals(spotId))
                .findFirst()
                .orElseThrow();
    }

    int getFloorNumber() {
        return floorNumber;
    }

    List<ParkingSpot> getSpots() {
        return Collections.unmodifiableList(spots);
    }
}

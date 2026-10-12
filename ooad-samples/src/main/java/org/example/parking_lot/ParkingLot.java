package org.example.parking_lot;

import java.util.*;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final Map<String, ParkingTicket> activeTickets =
            new HashMap<>();

    ParkingLot(List<ParkingFloor> floors) {
        this.floors = new ArrayList<>(floors);
    }

    public synchronized ParkingTicket park(Vehicle vehicle) {
        if (activeTickets.values().stream()
                .anyMatch(t -> t.getVehicle().getLicensePlate()
                        .equals(vehicle.getLicensePlate()))) {
            throw new IllegalStateException(
                    "Vehicle already has an active ticket");
        }

        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAndPark(vehicle);

            if (spot != null) {
                ParkingTicket ticket = new ParkingTicket(
                        UUID.randomUUID().toString(), vehicle, spot);

                activeTickets.put(ticket.getId(), ticket);
                return ticket;
            }
        }

        throw new IllegalStateException("Parking lot is full");
    }

    public synchronized void exit(String ticketId) {
        ParkingTicket ticket = activeTickets.remove(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Invalid or inactive ticket");
        }

        ticket.close();
        ticket.getSpot().removeVehicle();
    }
}
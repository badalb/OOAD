package org.example.car_rental_system;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String id;
    private final String name;
    private final String location;
    private final List<Vehicle> vehicles = new ArrayList<>();

    Branch(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    synchronized void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    synchronized List<Vehicle> getVehicles() {
        return List.copyOf(vehicles);
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getLocation() {
        return location;
    }
}
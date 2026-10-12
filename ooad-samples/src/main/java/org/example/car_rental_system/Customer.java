package org.example.car_rental_system;

import java.util.UUID;

public class Customer {
    private final String id;
    private final String name;
    private final String drivingLicense;

    public Customer(String name, String drivingLicense) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.drivingLicense = drivingLicense;
    }

    String getId() {
        return id;
    }
}
package org.example.car_rental_system;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class RentalService {
    private final PricingStrategy pricingStrategy;
    private final PaymentService paymentService;
    private final Map<String, Rental> activeRentals =
            new HashMap<>();

    RentalService(
            PricingStrategy pricingStrategy,
            PaymentService paymentService
    ) {
        this.pricingStrategy = pricingStrategy;
        this.paymentService = paymentService;
    }

    synchronized Rental pickup(Reservation reservation) {
        if (reservation.getStatus()
                != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException(
                    "Reservation is not confirmed");
        }

        Vehicle vehicle = reservation.getVehicle();

        if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new IllegalStateException(
                    "Vehicle is not available for pickup");
        }

        Rental rental = new Rental(reservation);
        vehicle.setStatus(VehicleStatus.RENTED);
        activeRentals.put(rental.getId(), rental);

        return rental;
    }

    synchronized Payment returnVehicle(
            String rentalId,
            LocalDateTime pickup,
            LocalDateTime dropoff
    ) {
        Rental rental = activeRentals.get(rentalId);

        if (rental == null
                || rental.getStatus() != RentalStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Active rental not found");
        }

        Vehicle vehicle =
                rental.getReservation().getVehicle();

        BigDecimal amount = pricingStrategy.calculatePrice(
                vehicle, pickup, dropoff);

        Payment payment =
                paymentService.processPayment(rentalId, amount);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            rental.complete();
            activeRentals.remove(rentalId);
        }

        return payment;
    }
}


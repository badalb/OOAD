package org.example.car_rental_system;


import java.math.BigDecimal;

public class PaymentService {

    Payment processPayment(String rentalId, BigDecimal amount) {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException(
                    "Invalid payment amount");
        }

        Payment payment = new Payment(rentalId, amount);

        // Simulated gateway response
        payment.markSuccessful();

        return payment;
    }
}


package org.example.car_rental_system;

public class Payment {
    private final String id;
    private final String rentalId;
    private final java.math.BigDecimal amount;
    private PaymentStatus status;

    Payment(String rentalId, java.math.BigDecimal amount) {
        this.id = java.util.UUID.randomUUID().toString();
        this.rentalId = rentalId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    void markSuccessful() {
        if (status != PaymentStatus.PENDING) {
            throw new IllegalStateException(
                    "Payment is not pending");
        }
        status = PaymentStatus.SUCCESS;
    }

    void markFailed() {
        if (status != PaymentStatus.PENDING) {
            throw new IllegalStateException(
                    "Payment is not pending");
        }
        status = PaymentStatus.FAILED;
    }

    String getId() {
        return id;
    }

    String getRentalId() {
        return rentalId;
    }

    java.math.BigDecimal getAmount() {
        return amount;
    }

    PaymentStatus getStatus() {
        return status;
    }
}

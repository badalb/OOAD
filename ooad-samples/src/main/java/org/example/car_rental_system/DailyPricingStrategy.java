package org.example.car_rental_system;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class DailyPricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculatePrice(
            Vehicle vehicle,
            LocalDateTime pickup,
            LocalDateTime dropoff
    ) {
        if (pickup == null || dropoff == null
                || !pickup.isBefore(dropoff)) {
            throw new IllegalArgumentException(
                    "Invalid rental period");
        }

        long hours = Duration.between(pickup, dropoff)
                .toHours();

        long days = Math.max(1, (hours + 23) / 24);

        return BigDecimal.valueOf(vehicle.getDailyRate())
                .multiply(BigDecimal.valueOf(days));
    }
}
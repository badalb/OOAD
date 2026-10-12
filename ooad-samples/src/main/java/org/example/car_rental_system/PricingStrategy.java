package org.example.car_rental_system;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PricingStrategy {
    BigDecimal calculatePrice(
            Vehicle vehicle,
            LocalDateTime pickup,
            LocalDateTime dropoff
    );
}
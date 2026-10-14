package org.example.ratelimiter;

public class RateLimitPolicy {
    private final int capacity;
    private final double refillTokensPerSecond;

    public RateLimitPolicy(
            int capacity, double refillTokensPerSecond) {
        if (capacity <= 0 || refillTokensPerSecond <= 0) {
            throw new IllegalArgumentException(
                    "Capacity and refill rate must be positive");
        }
        this.capacity = capacity;
        this.refillTokensPerSecond = refillTokensPerSecond;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRefillTokensPerSecond() {
        return refillTokensPerSecond;
    }
}

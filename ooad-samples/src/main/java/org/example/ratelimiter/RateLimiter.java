package org.example.ratelimiter;

public class RateLimiter {
    private final RateLimitStrategy strategy;

    public RateLimiter(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    public RateLimitResult allowRequest(
            String clientId, RateLimitPolicy policy) {
        return strategy.tryAcquire(clientId, policy);
    }
}
package org.example.ratelimiter;

public interface RateLimitStrategy {
    RateLimitResult tryAcquire(
            String key, RateLimitPolicy policy);
}

package org.example.ratelimiter;

public interface RateLimitStore {
    RateLimitResult consumeToken(
            String key, RateLimitPolicy policy);
}

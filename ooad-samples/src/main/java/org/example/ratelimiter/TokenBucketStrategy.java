package org.example.ratelimiter;

public class TokenBucketStrategy implements RateLimitStrategy {
    private final RateLimitStore store;

    public TokenBucketStrategy(RateLimitStore store) {
        this.store = store;
    }

    @Override
    public RateLimitResult tryAcquire(
            String key, RateLimitPolicy policy) {
        return store.consumeToken(key, policy);
    }
}

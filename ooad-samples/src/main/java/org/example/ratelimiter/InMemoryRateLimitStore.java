package org.example.ratelimiter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRateLimitStore implements RateLimitStore {

    private static class Bucket {
        double tokens;
        long lastRefillNanos;

        Bucket(double tokens, long now) {
            this.tokens = tokens;
            this.lastRefillNanos = now;
        }
    }

    private final Map<String, Bucket> buckets = new HashMap<>();

    @Override
    public synchronized RateLimitResult consumeToken(
            String key, RateLimitPolicy policy) {

        long now = System.nanoTime();

        Bucket bucket = buckets.computeIfAbsent(
                key,
                ignored -> new Bucket(policy.getCapacity(), now));

        double elapsedSeconds =
                (now - bucket.lastRefillNanos) / 1_000_000_000.0;

        bucket.tokens = Math.min(
                policy.getCapacity(),
                bucket.tokens
                        + elapsedSeconds
                        * policy.getRefillTokensPerSecond());

        bucket.lastRefillNanos = now;

        if (bucket.tokens >= 1.0) {
            bucket.tokens -= 1.0;

            return new RateLimitResult(
                    true, (int) bucket.tokens, Duration.ZERO);
        }

        double secondsToNextToken =
                (1.0 - bucket.tokens)
                        / policy.getRefillTokensPerSecond();

        return new RateLimitResult(
                false,
                0,
                Duration.ofNanos(
                        (long) (secondsToNextToken * 1_000_000_000L)));
    }
}

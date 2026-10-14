package org.example.ratelimiter;

import java.time.Duration;

public class RateLimitResult {
    private final boolean allowed;
    private final int remainingTokens;
    private final Duration retryAfter;

    public RateLimitResult(
            boolean allowed, int remainingTokens,
            Duration retryAfter) {
        this.allowed = allowed;
        this.remainingTokens = remainingTokens;
        this.retryAfter = retryAfter;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public int getRemainingTokens() {
        return remainingTokens;
    }

    public Duration getRetryAfter() {
        return retryAfter;
    }
}

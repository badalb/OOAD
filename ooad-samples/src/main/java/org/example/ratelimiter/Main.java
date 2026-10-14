package org.example.ratelimiter;

public class Main {

    public static void main(String[] args) {
        RateLimitPolicy policy =
                new RateLimitPolicy(5, 1.0);

        RateLimitStore store =
                new InMemoryRateLimitStore();

        RateLimitStrategy strategy =
                new TokenBucketStrategy(store);

        RateLimiter limiter = new RateLimiter(strategy);

        for (int i = 1; i <= 7; i++) {
            RateLimitResult result =
                    limiter.allowRequest("user-101", policy);

            System.out.println(
                    "Request " + i
                            + " | Allowed: " + result.isAllowed()
                            + " | Remaining: "
                            + result.getRemainingTokens()
                            + " | Retry after: "
                            + result.getRetryAfter().toMillis()
                            + " ms");
        }
    }
}

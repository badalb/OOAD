package org.example.snake_n_ladder;

public record Ladder(int bottom, int top) {
    public Ladder {
        if (bottom >= top) {
            throw new IllegalArgumentException(
                    "Ladder top must be above its bottom");
        }
    }
}
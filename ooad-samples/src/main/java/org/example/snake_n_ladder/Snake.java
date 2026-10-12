package org.example.snake_n_ladder;

public record Snake(int head, int tail) {
    public Snake {
        if (head <= tail) {
            throw new IllegalArgumentException(
                    "Snake head must be above its tail");
        }
    }
}
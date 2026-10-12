package org.example.snake_n_ladder;

import java.util.Random;

public class Dice {

    private final Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1;
    }
}

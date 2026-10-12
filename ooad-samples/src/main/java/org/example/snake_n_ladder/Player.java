package org.example.snake_n_ladder;

public class Player {

    private final String name;
    private  int position = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void moveTo(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

}

package org.example.snake_n_ladder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final int size;

    private final Map<Integer, Integer> transitions =
            new HashMap<>();

    public Board(int size, List<Snake> snakes,
          List<Ladder> ladders) {
        this.size = size;

        for (Snake s : snakes) {
            addTransition(s.head(), s.tail());
        }
        for (Ladder l : ladders) {
            addTransition(l.bottom(), l.top());
        }
    }

    private void addTransition(int from, int to) {
        if (from < 1 || from >= size ||
                to < 1 || to > size || from == to ||
                transitions.putIfAbsent(from, to) != null) {
            throw new IllegalArgumentException(
                    "Invalid or duplicate transition");
        }
    }

    int resolve(int position) {
        return transitions.getOrDefault(position, position);
    }

    int getSize() {
        return size;
    }

}

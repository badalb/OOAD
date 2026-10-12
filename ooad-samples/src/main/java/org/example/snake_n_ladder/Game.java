package org.example.snake_n_ladder;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Board board;
    private final Dice dice;
    private final List<Player> players;
    private int currentPlayerIndex = 0;
    private boolean finished = false;

    Game(Board board, Dice dice, List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one player is required");
        }
        this.board = board;
        this.dice = dice;
        this.players = new ArrayList<>(players);
    }

    String playTurn() {
        if (finished) {
            throw new IllegalStateException("Game has ended");
        }

        Player player = players.get(currentPlayerIndex);
        int roll = dice.roll();
        int current = player.getPosition();
        int target = current + roll;

        String result = player.getName() +
                " rolled " + roll;

        if (target > board.getSize()) {
            result += "; move skipped";
        } else {
            int resolved = board.resolve(target);
            player.moveTo(resolved);

            result += "; moved to " + target;

            if (resolved != target) {
                result += "; moved to " + resolved;
            }

            if (resolved == board.getSize()) {
                finished = true;
                return result + "; " +
                        player.getName() + " wins!";
            }
        }

        currentPlayerIndex =
                (currentPlayerIndex + 1) % players.size();

        return result;
    }


}

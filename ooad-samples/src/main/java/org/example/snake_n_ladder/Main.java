package org.example.snake_n_ladder;


import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Snake> snakes = List.of(
                new Snake(99, 54),
                new Snake(70, 55),
                new Snake(52, 42),
                new Snake(25, 2),
                new Snake(95, 72));

        List<Ladder> ladders = List.of(
                new Ladder(6, 25),
                new Ladder(11, 40),
                new Ladder(46, 90),
                new Ladder(60, 85),
                new Ladder(17, 69));

        Board board = new Board(100, snakes, ladders);
        Dice dice = new Dice();

        List<Player> players = List.of(
                new Player("Alice"),
                new Player("Bob")
        );

        Game game = new Game(board, dice, players);
        System.out.println("Snake and Ladder started!");
        while (true) {
            String result = game.playTurn();
            System.out.println(result);
            if (result.contains("wins!")) {
                break;
            }
        }
    }
}

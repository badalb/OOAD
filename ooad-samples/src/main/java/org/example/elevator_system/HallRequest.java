package org.example.elevator_system;

public class HallRequest {
    final int floor;
    final Direction direction;

    HallRequest(int floor, Direction direction) {
        if (direction == Direction.IDLE) {
            throw new IllegalArgumentException(
                    "Hall request direction cannot be IDLE");
        }
        this.floor = floor;
        this.direction = direction;
    }
}

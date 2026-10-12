package org.example.elevator_system;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction direction = Direction.IDLE;
    private ElevatorState state = ElevatorState.STOPPED;
    private final Door door = new Door();

    // Sorted stops allow efficient next-stop selection.
    private final NavigableSet<Integer> stops =
            new TreeSet<>();

    Elevator(int id, int initialFloor) {
        this.id = id;
        this.currentFloor = initialFloor;
    }

    void addStop(int floor) {
        if (state == ElevatorState.MAINTENANCE) {
            throw new IllegalStateException(
                    "Elevator is under maintenance");
        }
        stops.add(floor);
    }

    void selectDestination(ElevatorRequest request) {
        addStop(request.destinationFloor);
    }

    void step() {
        if (state == ElevatorState.MAINTENANCE) {
            return;
        }

        // Serve a request at the current floor.
        if (stops.remove(currentFloor)) {
            direction = Direction.IDLE;
            state = ElevatorState.STOPPED;
            door.open();
            System.out.println(
                    "Elevator " + id
                            + " stopped at floor " + currentFloor);
            door.close();
        }

        if (stops.isEmpty()) {
            direction = Direction.IDLE;
            state = ElevatorState.STOPPED;
            return;
        }

        // Choose an initial direction when idle.
        if (direction == Direction.IDLE) {
            int next = stops.first();
            direction = next > currentFloor
                    ? Direction.UP : Direction.DOWN;
        }

        // Continue in the current direction if possible.
        Integer nextStop = direction == Direction.UP
                ? stops.ceiling(currentFloor + 1)
                : stops.floor(currentFloor - 1);

        // Reverse when no more stops exist in this direction.
        if (nextStop == null) {
            direction = direction == Direction.UP
                    ? Direction.DOWN : Direction.UP;
            return;
        }

        currentFloor += direction == Direction.UP ? 1 : -1;
        state = ElevatorState.MOVING;

        System.out.println(
                "Elevator " + id + " at floor "
                        + currentFloor + " moving " + direction);
    }

    int getId() {
        return id;
    }

    int getCurrentFloor() {
        return currentFloor;
    }

    Direction getDirection() {
        return direction;
    }

    ElevatorState getState() {
        return state;
    }

    int getPendingStopCount() {
        return stops.size();
    }
}

package org.example.elevator_system;

import java.util.Comparator;
import java.util.List;

public class NearestElevatorStrategy
        implements SchedulingStrategy {

    @Override
    public Elevator selectElevator(
            List<Elevator> elevators,
            HallRequest request) {

        return elevators.stream()
                .filter(e -> e.getState()
                        != ElevatorState.MAINTENANCE)
                .min(Comparator.comparingInt(e ->
                        score(e, request)))
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No elevator available"));
    }

    private int score(Elevator elevator,
                      HallRequest request) {

        int distance = Math.abs(
                elevator.getCurrentFloor() - request.floor);

        boolean movingTowardRequest =
                elevator.getDirection() == request.direction
                        && (request.direction == Direction.UP
                        ? elevator.getCurrentFloor() <= request.floor
                        : elevator.getCurrentFloor() >= request.floor);

        int directionPenalty =
                elevator.getDirection() == Direction.IDLE
                        || movingTowardRequest ? 0 : 5;

        return distance + directionPenalty
                + elevator.getPendingStopCount();
    }
}

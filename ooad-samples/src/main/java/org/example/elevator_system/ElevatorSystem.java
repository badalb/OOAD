package org.example.elevator_system;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private final List<Elevator> elevators;
    private final SchedulingStrategy scheduler;
    private final int minFloor;
    private final int maxFloor;

    ElevatorSystem(int count, int minFloor, int maxFloor,
                   SchedulingStrategy scheduler) {
        if (count <= 0 || minFloor > maxFloor) {
            throw new IllegalArgumentException(
                    "Invalid building configuration");
        }

        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.scheduler = scheduler;
        this.elevators = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            elevators.add(new Elevator(i, minFloor));
        }
    }

    void requestElevator(HallRequest request) {
        validateFloor(request.floor);

        Elevator elevator =
                scheduler.selectElevator(elevators, request);

        elevator.addStop(request.floor);

        System.out.println(
                "Assigned elevator " + elevator.getId()
                        + " to floor " + request.floor
                        + " (" + request.direction + ")");
    }

    void selectFloor(int elevatorId, int destinationFloor) {
        validateFloor(destinationFloor);

        Elevator elevator = elevators.stream()
                .filter(e -> e.getId() == elevatorId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unknown elevator"));

        elevator.selectDestination(
                new ElevatorRequest(destinationFloor));
    }

    void step() {
        elevators.forEach(Elevator::step);
    }

    void runSteps(int count) {
        for (int i = 0; i < count; i++) {
            step();
        }
    }

    private void validateFloor(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            throw new IllegalArgumentException(
                    "Floor outside building range: " + floor);
        }
    }
}


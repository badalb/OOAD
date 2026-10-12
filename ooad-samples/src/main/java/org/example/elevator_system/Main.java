package org.example.elevator_system;

public class Main {

    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(
                3, 0, 10,
                new NearestElevatorStrategy());

        // A passenger requests an elevator at floor 5.
        system.requestElevator(
                new HallRequest(5, Direction.UP));

        // Simulate elevator movement.
        system.runSteps(5);

        // Passenger selects destination floor 9
        // in the elevator assigned above.
        system.selectFloor(1, 9);

        system.runSteps(10);
    }
}

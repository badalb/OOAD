package org.example.elevator_system;

public class Door {
    private DoorState state = DoorState.CLOSED;

    void open() {
        state = DoorState.OPEN;
        System.out.println("Door opened");
    }

    void close() {
        state = DoorState.CLOSED;
        System.out.println("Door closed");
    }

    DoorState getState() {
        return state;
    }
}
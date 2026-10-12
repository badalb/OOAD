package org.example.elevator_system;

import java.util.List;

public  interface SchedulingStrategy {
    Elevator selectElevator(
            List<Elevator> elevators,
            HallRequest request);
}

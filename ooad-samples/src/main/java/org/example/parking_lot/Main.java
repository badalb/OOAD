package org.example.parking_lot;


import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Create parking spots for Floor 1
        List<ParkingSpot> floor1Spots = List.of(
                new ParkingSpot("M1", SpotType.MOTORCYCLE),
                new ParkingSpot("C1", SpotType.COMPACT),
                new ParkingSpot("L1", SpotType.LARGE),
                new ParkingSpot("C2", SpotType.COMPACT)
        );

        // Create parking spots for Floor 2
        List<ParkingSpot> floor2Spots = List.of(
                new ParkingSpot("M2", SpotType.MOTORCYCLE),
                new ParkingSpot("C3", SpotType.COMPACT),
                new ParkingSpot("L2", SpotType.LARGE)
        );

        // Create floors
        ParkingFloor floor1 = new ParkingFloor(1, floor1Spots);
        ParkingFloor floor2 = new ParkingFloor(2, floor2Spots);

        // Create parking lot
        ParkingLot parkingLot = new ParkingLot(
                List.of(floor1, floor2)
        );

        // Create vehicles
        Vehicle car = new Vehicle("WB01AB1234", VehicleType.CAR);
        Vehicle bike = new Vehicle("WB02CD5678", VehicleType.MOTORCYCLE);
        Vehicle truck = new Vehicle("WB03EF9012", VehicleType.TRUCK);

        // Park vehicles and issue tickets
        ParkingTicket carTicket = parkingLot.park(car);
        ParkingTicket bikeTicket = parkingLot.park(bike);
        ParkingTicket truckTicket = parkingLot.park(truck);

        System.out.println("Car parked at: "
                + carTicket.getSpot().getId());
        System.out.println("Car ticket: "
                + carTicket.getId());

        System.out.println("Bike parked at: "
                + bikeTicket.getSpot().getId());
        System.out.println("Bike ticket: "
                + bikeTicket.getId());

        System.out.println("Truck parked at: "
                + truckTicket.getSpot().getId());
        System.out.println("Truck ticket: "
                + truckTicket.getId());

        // Exit the car
        System.out.println("\nCar exiting...");
        parkingLot.exit(carTicket.getId());

        System.out.println("Car exited successfully.");

        // Verify the car's spot is available again
        System.out.println("Car's spot status: "
                + carTicket.getSpot().getStatus());

        // Exit remaining vehicles
        parkingLot.exit(bikeTicket.getId());
        parkingLot.exit(truckTicket.getId());

        System.out.println("All vehicles exited successfully.");
    }
}

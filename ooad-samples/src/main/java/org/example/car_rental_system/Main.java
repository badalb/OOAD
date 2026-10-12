package org.example.car_rental_system;


import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. Create customers
        Customer customer = new Customer("Rahul Sharma", "DL123456");

        // 2. Create vehicles
        Vehicle car1 = new Vehicle(
                "WB01AB1234",
                VehicleType.SEDAN,
                2500.00);

        Vehicle car2 = new Vehicle(
                "WB01CD5678",
                VehicleType.SUV, 4000.00);

        // 3. Create a branch and add vehicles
        Branch branch = new Branch(
                "B101",
                "Kolkata Central",
                "Kolkata"
        );

        branch.addVehicle(car1);
        branch.addVehicle(car2);

        // 4. Create services
        VehicleSearchService searchService =
                new VehicleSearchService();

        ReservationService reservationService =
                new ReservationService();

        PaymentService paymentService =
                new PaymentService();

        RentalService rentalService =
                new RentalService(
                        new DailyPricingStrategy(),
                        paymentService
                );

        // 5. Search for available vehicles
        LocalDateTime pickup =
                LocalDateTime.now().plusDays(1);

        LocalDateTime dropoff =
                pickup.plusDays(3);

        List<Vehicle> availableVehicles =
                searchService.search(
                        branch,
                        VehicleType.SEDAN,
                        pickup,
                        dropoff,
                        reservationService.getReservations()
                );

        System.out.println("Available vehicles:");

        for (Vehicle vehicle : availableVehicles) {
            System.out.println(
                    vehicle.getRegistrationNumber()
                            + " | Rate per day: "
                            + vehicle.getDailyRate()
            );
        }

        // 6. Reserve a vehicle
        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        Vehicle selectedVehicle = availableVehicles.get(0);

        Reservation reservation =
                reservationService.reserve(
                        customer,
                        selectedVehicle,
                        pickup,
                        dropoff
                );

        System.out.println(
                "Reservation created: " + reservation.getId()
        );

        // 7. Pick up the vehicle
        Rental rental = rentalService.pickup(reservation);

        System.out.println(
                "Rental started: " + rental.getId()
        );

        // 8. Return the vehicle and process payment
        Payment payment = rentalService.returnVehicle(
                rental.getId(),
                pickup,
                dropoff
        );

        System.out.println(
                "Payment status: " + payment.getStatus()
        );

        System.out.println(
                "Vehicle status after return: "
                        + selectedVehicle.getStatus()
        );
    }
}

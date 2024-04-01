package ParkingLot.payment;

import ParkingLot.parkingLot.Ticket;

public interface PaymentStrategy {
    public double calculateCost(Ticket ticket);
}

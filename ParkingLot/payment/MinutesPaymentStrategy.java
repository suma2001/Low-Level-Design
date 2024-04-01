package ParkingLot.payment;

import ParkingLot.parkingLot.Ticket;

import java.time.temporal.ChronoUnit;

public class MinutesPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateCost(Ticket ticket) {
        int price = ticket.getParkingSpace().getPrice();
        double parkedTime = ChronoUnit.MINUTES.between(ticket.getEntryTime(), ticket.getExitTime());
        return parkedTime * price;
    }
}

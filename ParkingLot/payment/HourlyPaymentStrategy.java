package ParkingLot.payment;

import ParkingLot.parkingLot.Ticket;

import java.time.temporal.ChronoUnit;

public class HourlyPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateCost(Ticket ticket) {
        int price = ticket.getParkingSpace().getPrice();
        double parkedTime = ChronoUnit.HOURS.between(ticket.getEntryTime(), ticket.getExitTime());
        return parkedTime * price;
    }
}

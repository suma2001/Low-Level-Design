package ParkingLot.payment;

import ParkingLot.parkingLot.Ticket;

import java.time.temporal.ChronoUnit;

public class SecondsPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateCost(Ticket ticket) {
        int price = ticket.getParkingSpace().getPrice();
        double parkedTime = ChronoUnit.SECONDS.between(ticket.getEntryTime(), ticket.getExitTime());
        System.out.println(STR."Parked Time: \{parkedTime}");
        return parkedTime * price;
    }
}

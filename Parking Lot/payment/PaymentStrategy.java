package payment;

import parkingLot.Ticket;

public interface PaymentStrategy {
    public double calculateCost(Ticket ticket);
}

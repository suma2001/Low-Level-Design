package ParkingLot.parkingLot;

import ParkingLot.payment.PaymentInfo;
import ParkingLot.payment.PaymentStrategy;
import ParkingLot.payment.PaymentType;

import java.time.LocalDateTime;

public class ExitGate {
    private int gateId;

    public ExitGate(int id) {
        this.gateId = id;
    }

    public double calculatePrice(PaymentStrategy paymentStrategy, Ticket ticket) {
        return paymentStrategy.calculateCost(ticket);
    }

    public PaymentInfo generatePaymentInfo(Ticket ticket, PaymentStrategy paymentStrategy, PaymentType paymentType) {
        // Set Exit Time
        ticket.setExitTime(LocalDateTime.now());

        // Set amount
        double amount = calculatePrice(paymentStrategy, ticket);
        ticket.setPrice(amount);

        // Unpark vehicle
        ticket.getParkingSpace().unParkVehicle();

        return new PaymentInfo(amount, ticket, paymentType, paymentStrategy);
    }
}


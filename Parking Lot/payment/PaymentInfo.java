package payment;

import parkingLot.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentInfo {
    private String transactionId;
    private double amount;
    private LocalDateTime date;
    private Ticket ticket;
    private PaymentType paymentType;
    private PaymentStrategy paymentStrategy;

    public PaymentInfo(double amount, Ticket ticket, PaymentType paymentType, PaymentStrategy paymentStrategy) {
        this.transactionId = UUID.randomUUID().toString();
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.ticket = ticket;
        this.paymentType = paymentType;
        this.paymentStrategy = paymentStrategy;
    }

    public double getAmount() {
        return amount;
    }
}

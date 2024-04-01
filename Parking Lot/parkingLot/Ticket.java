package parkingLot;

import parkingSpace.ParkingSpace;
import vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String ticketId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Vehicle vehicle;
    private ParkingSpace parkingSpace;
    private double price;

    public Ticket(Vehicle vehicle, ParkingSpace parkingSpace) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
        this.parkingSpace = parkingSpace;
    }
    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return STR."parkingLot.Ticket{ticketId=\{ticketId}, entryTime=\{entryTime}, exitTime=\{exitTime}, vehicle=\{vehicle}, parkingSpace=\{parkingSpace}, price=\{price}\{'}'}";
    }
}

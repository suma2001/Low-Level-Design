package ParkingLot.parkingLot;

import ParkingLot.parkingSpace.ParkingSpace;
import ParkingLot.parkingSpace.ParkingSpaceFactory;
import ParkingLot.vehicle.Vehicle;

public class EntryGate {
    private int gateId;
    private ParkingLot parkingLot;

    public EntryGate(int id) {
        this.gateId = id;
    }
    public ParkingSpace findSpace() {
        // TODO: Use object of parkingLot to find Space
        return null;
    }

    public void updateParkingSpace(ParkingSpace space) {}
    public Ticket generateTicket(Vehicle vehicle) {
        System.out.println(vehicle.toString());
        // TODO: Check if parking space available or not

        // If space available
        ParkingSpaceFactory parkingSpaceFactory = new ParkingSpaceFactory();
        ParkingSpace parkingSpace = parkingSpaceFactory.getParkingSpace(vehicle);

        parkingSpace.parkVehicle(vehicle);
        return new Ticket(vehicle, parkingSpace);
    }

    @Override
    public String toString() {
        return STR."parkingLot.EntryGate{gateId=\{gateId}, parkingLot=\{parkingLot}\{'}'}";
    }
}

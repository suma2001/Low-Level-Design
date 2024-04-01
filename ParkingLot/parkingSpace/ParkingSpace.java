package ParkingLot.parkingSpace;

import ParkingLot.vehicle.Vehicle;

import java.util.UUID;

public class ParkingSpace {
    private String spaceId;
    private Vehicle vehicle;
    protected int price;
    private boolean isEmpty;

    public ParkingSpace() {
        this.spaceId = UUID.randomUUID().toString();
        this.isEmpty = true;
    }
    public String getSpaceId() {
        return spaceId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getPrice() {
        return price;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = false;
    };
    public void unParkVehicle() {
        this.vehicle = null;
        this.isEmpty = true;
    };
}

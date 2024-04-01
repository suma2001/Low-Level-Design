package ParkingLot.parkingLot;

import ParkingLot.parkingSpace.ParkingSpace;
import ParkingLot.parkingSpace.ParkingSpaceFactory;
import ParkingLot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
public class ParkingLot {
    private List<Floor> floorList;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;
    private ParkingSpaceFactory parkingSpaceFactory;
    private ParkingStrategy parkingStrategy;

    public ParkingLot() {
        floorList = new ArrayList<>();
        entryGates = new ArrayList<>();
        exitGates = new ArrayList<>();
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void addFloor(Floor floor) {
        floorList.add(floor);
    }
    public void removeFloor(Floor floor) {
        floorList.remove(floor);
    }
    public void addEntryGate(EntryGate entryGate) {
        entryGates.add(entryGate);
    }
    public void removeEntryGate(EntryGate entryGate) {
        floorList.remove(entryGate);
    }
    public void addExitGate(ExitGate exitGate) {
        exitGates.add(exitGate);
    }
    public void removeExitGate(ExitGate exitGate) {
        exitGates.remove(exitGate);
    }
    public ParkingSpace findParkingSpace(EntryGate entryGate, Vehicle vehicle) {
        return null;
    }

    public static interface ParkingStrategy {
        public ParkingSpace park(List<ParkingSpace> availableSpaces);
    }
}

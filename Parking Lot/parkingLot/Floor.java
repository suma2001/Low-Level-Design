package parkingLot;

import parkingSpace.ParkingSpace;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorId;
    private boolean isEmpty;
    private List<ParkingSpace> parkingSpaceList;

    public Floor(int id) {
        this.floorId = id;
        parkingSpaceList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return STR."parkingLot.Floor{floorId=\{floorId}, isEmpty=\{isEmpty}, parkingSpaceList=\{parkingSpaceList}\{'}'}";
    }

    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaceList.add(parkingSpace);
    }
    public void removeParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaceList.remove(parkingSpace);
    }
}

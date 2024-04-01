package parkingLot;

import parkingSpace.ParkingSpace;

import java.util.List;

public class NormalOrderParking implements ParkingLot.ParkingStrategy {
    @Override
    public ParkingSpace park(List<ParkingSpace> availableSpaces) {
        // Get first parking space available
        return availableSpaces.get(0);
    }
}

package parkingSpace;

import vehicle.Vehicle;
import vehicle.VehicleType;

public class ParkingSpaceFactory {
    private ParkingSpace parkingSpace;
    public ParkingSpace getParkingSpace(Vehicle vehicle) {
        // TODO: return parking space based on vehicle type
        if(vehicle.getVehicleType().equals(VehicleType.TWO)) {
            return new TwoWheelerSpace();
        }
        else if(vehicle.getVehicleType().equals(VehicleType.FOUR)) {
            return new FourWheelerSpace();
        }
        else if(vehicle.getVehicleType().equals(VehicleType.HEAVY)) {
            return new HeavyWheelerSpace();
        }
        return null;
    };
}

package vehicle;

public class Vehicle {
    private String registrationNumber;
    private VehicleType vehicleType;

    public Vehicle(String regNum, VehicleType vehicleType) {
        this.registrationNumber = regNum;
        this.vehicleType = vehicleType;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return STR."vehicle.Vehicle{registrationNumber='\{registrationNumber}\{'\''}, vehicleType=\{vehicleType}\{'}'}";
    }
}


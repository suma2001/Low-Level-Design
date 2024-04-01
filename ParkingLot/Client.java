package ParkingLot;

import ParkingLot.parkingLot.*;
import ParkingLot.parkingSpace.FourWheelerSpace;
import ParkingLot.parkingSpace.ParkingSpace;
import ParkingLot.parkingSpace.TwoWheelerSpace;
import ParkingLot.payment.PaymentInfo;
import ParkingLot.payment.PaymentType;
import ParkingLot.payment.SecondsPaymentStrategy;
import ParkingLot.vehicle.TwoWheelerVehicle;
import ParkingLot.vehicle.Vehicle;

import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        // Initialise parking lot
        ParkingLot parkingLot = new ParkingLot();

        // Create floors, entry gates and exit gates
        Floor f1 = new Floor(1);
        EntryGate entry1 = new EntryGate(1);
        ExitGate exit1 = new ExitGate(1);

        // Add floors, entry gates and exit gates in parking lot
        parkingLot.addFloor(f1);
        parkingLot.addEntryGate(entry1);
        parkingLot.addExitGate(exit1);

        // Add Parking Strategy
        ParkingLot.ParkingStrategy parkingStrategy = new NormalOrderParking();
        parkingLot.setParkingStrategy(parkingStrategy);

        // Add Parking Spaces to floors
        ParkingSpace bikeSpace1 = new TwoWheelerSpace();
        ParkingSpace carSpace1 = new FourWheelerSpace();

        f1.addParkingSpace(bikeSpace1);
        f1.addParkingSpace(carSpace1);

        // Create vehicle
        Vehicle bike = new TwoWheelerVehicle("KA05MR2311");

        // Get Parking space for vehicle - KA05MR2311 entering from entry gate - 1
        Ticket ticket1 = entry1.generateTicket(bike);
        System.out.println(ticket1.toString());

        TimeUnit.SECONDS.sleep(10);

        // vehicle.Vehicle - KA05MR2311 exits from exit gate - 1, Make payment
        PaymentInfo paymentInfo = exit1.generatePaymentInfo(ticket1, new SecondsPaymentStrategy(), PaymentType.UPI);
        System.out.println(ticket1.toString());
        System.out.println(STR."Amount to be paid: \{paymentInfo.getAmount()}");


    }
}

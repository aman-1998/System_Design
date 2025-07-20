package personal.learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import personal.learning.parking.Bike;
import personal.learning.parking.Car;
import personal.learning.parking.ParkingFloor;
import personal.learning.parking.ParkingLot;
import personal.learning.parking.Truck;
import personal.learning.parking.Vehicle;
import personal.learning.parking.payment.PaymentType;

@Component
public class MainClient {
	
	@Autowired
	private ParkingLot parkingLot;
	
	public void run() {
		
		List<ParkingFloor> parkingFloorList = parkingLot.getParkingFloorList();
		
		System.out.println("Parking Lot: ");
		for(ParkingFloor floor : parkingFloorList) {
			System.out.println("Floor No. " + floor.getFloorNumber());
			System.out.println("Available bike spots : " + floor.getAvailableBikeSpots());
			System.out.println("Available car Spots : " + floor.getAvailableCarSpots()); 
			System.out.println("Available truck Spots : " + floor.getAvailableTruckSpots());
			System.out.println("--------------");
		}
		
		System.out.println("===========================================================================");
		
		Vehicle vehicle1 = new Bike("WB12345");
		Vehicle vehicle2 = new Bike("WB38366");
		Vehicle vehicle3 = new Car("WB92615");
		Vehicle vehicle4 = new Truck("WB61523");
		Vehicle vehicle5 = new Truck("WB71725");
		Vehicle vehicle6 = new Bike("WB71725");
		
		String parkingTicket1 = parkingLot.parkVehicle(vehicle1);
		String parkingTicket2 = parkingLot.parkVehicle(vehicle2);
		String parkingTicket3 = parkingLot.parkVehicle(vehicle3);
		String parkingTicket4 = parkingLot.parkVehicle(vehicle4);
		String parkingTicket5 = parkingLot.parkVehicle(vehicle5);
		String parkingTicket6 = parkingLot.parkVehicle(vehicle6);
		
		System.out.println("===========================================================================");
		
		System.out.println("Parking Lot: ");
		for(ParkingFloor floor : parkingFloorList) {
			System.out.println("Floor No. " + floor.getFloorNumber());
			System.out.println("Available bike spots : " + floor.getAvailableBikeSpots());
			System.out.println("Available car Spots : " + floor.getAvailableCarSpots()); 
			System.out.println("Available truck Spots : " + floor.getAvailableTruckSpots());
			System.out.println("--------------");
		}
		
		System.out.println("===========================================================================");
		
		parkingLot.unparkVehicle(parkingTicket6, PaymentType.CARD);
		
		System.out.println("===========================================================================");
		
		System.out.println("Parking Lot: ");
		for(ParkingFloor floor : parkingFloorList) {
			System.out.println("Floor No. " + floor.getFloorNumber());
			System.out.println("Available bike spots : " + floor.getAvailableBikeSpots());
			System.out.println("Available car Spots : " + floor.getAvailableCarSpots()); 
			System.out.println("Available truck Spots : " + floor.getAvailableTruckSpots());
			System.out.println("--------------");
		}
		
		System.out.println("===========================================================================");
		
		Vehicle vehicle7 = new Truck("WB38816");
		
		String parkingTicket7 =  parkingLot.parkVehicle(vehicle7);
		
		Vehicle vehicle8 = new Car("WB18125");
		
		String parkingTicket8 = parkingLot.parkVehicle(vehicle8);
		
		System.out.println("===========================================================================");
		
		System.out.println("Parking Lot: ");
		for(ParkingFloor floor : parkingFloorList) {
			System.out.println("Floor No. " + floor.getFloorNumber());
			System.out.println("Available bike spots : " + floor.getAvailableBikeSpots());
			System.out.println("Available car Spots : " + floor.getAvailableCarSpots()); 
			System.out.println("Available truck Spots : " + floor.getAvailableTruckSpots());
			System.out.println("--------------");
		}
		
		System.out.println("===========================================================================");
		
		Vehicle vehicle9 = new Bike("WB77712");
		
		String parkingTicket9 = parkingLot.parkVehicle(vehicle9);
		
		System.out.println("===========================================================================");
		
		System.out.println("Parking Lot: ");
		for(ParkingFloor floor : parkingFloorList) {
			System.out.println("Floor No. " + floor.getFloorNumber());
			System.out.println("Available bike spots : " + floor.getAvailableBikeSpots());
			System.out.println("Available car Spots : " + floor.getAvailableCarSpots()); 
			System.out.println("Available truck Spots : " + floor.getAvailableTruckSpots());
			System.out.println("--------------");
		}
		
		System.out.println("===========================================================================");
		
		Vehicle vehicle10 = new Car("WB99725");
		
		String parkingTicket10 = parkingLot.parkVehicle(vehicle10);
	}
}

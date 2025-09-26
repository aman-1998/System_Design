package personal.learning.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import personal.learning.parking.payment.PaymentService;
import personal.learning.parking.payment.PaymentStrategy;
import personal.learning.parking.rate.RateStrategy;

public class ParkingLot {
	
	@Autowired
	private PaymentService paymentService;
	
	private List<BikeSpot> bikeSpotList = new ArrayList<>();
	private List<CarSpot> carSpotList = new ArrayList<>();
	private List<TruckSpot> truckSpotList = new ArrayList<>();
	
	private int availableBikeSpots;
	private int availableCarSpots;
	private int availableTruckSpots;
	
	private Map<String, ParkingTicket> activeTickets = new HashMap<>();
	
	private RateStrategy rateStrategy;
	
	public ParkingLot(RateStrategy rateStrategy, 
					  int availableBikeSpots, 
			          int availableCarSpots, 
			          int availableTruckSpots) {
		
		this.rateStrategy = rateStrategy;
		this.availableBikeSpots = availableBikeSpots;
		this.availableCarSpots = availableCarSpots;
		this.availableTruckSpots = availableTruckSpots;
	}
	
	//Park
	public String parkVehicle(Vehicle vehicle) {
		
		System.out.println("Checking parking spot availability for Vehicle[" + vehicle.getVehicleNumber() + "] "
						   + "of type " + vehicle.getVehicleType());
		
		ParkingTicket parkingTicket = null;
		
		if(vehicle.getVehicleType() == VehicleType.TRUCK) {
			if(availableTruckSpots != 0) {
				parkingTicket = parkTruck(vehicle);
			} else {
				System.out.println("Oops! all truck spots are full");
			}
		} else if(vehicle.getVehicleType() == VehicleType.CAR) {
			if(availableCarSpots != 0) {
				parkingTicket = parkCar(vehicle);
			} else if(availableTruckSpots != 0) {
				parkingTicket = parkTruck(vehicle);
			} else {
				System.out.println("Oops! all car & truck spots are full");
			}
		} else if(vehicle.getVehicleType() == VehicleType.BIKE) {
			if(availableBikeSpots != 0) {
				parkingTicket = parkBike(vehicle);
			} else if(availableCarSpots != 0) {
				parkingTicket = parkCar(vehicle);
			} else if(availableTruckSpots != 0) {
				parkingTicket = parkTruck(vehicle);
			} else {
				System.out.println("Oops! all parking spots are full");
			}
		}
		
		if(parkingTicket != null) {
			activeTickets.put(parkingTicket.getTicketId(), parkingTicket);
			return parkingTicket.getTicketId();
		}
		
		return StringUtils.EMPTY;
	}
	
	private ParkingTicket parkTruck(Vehicle vehicle) {
		TruckSpot truckSpot = new TruckSpot();
		truckSpot.setAvailable(false);
		truckSpot.setVehicle(vehicle);
		LocalDateTime currentTime = LocalDateTime.now();
		truckSpot.setStartTime(currentTime);
		vehicle.setParkingSpot(truckSpot);
		truckSpotList.add(truckSpot);
		availableTruckSpots--;
		
		ParkingTicket parkingTicket = new ParkingTicket(generateTicketId(vehicle.getVehicleNumber()), currentTime);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(truckSpot);
		
		System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is parked successfully in a Truck spot");
		
		return parkingTicket;
	}
	
	private ParkingTicket parkCar(Vehicle vehicle) {
		CarSpot carSpot = new CarSpot();
		carSpot.setAvailable(false);
		carSpot.setVehicle(vehicle);
		LocalDateTime currentTime = LocalDateTime.now();
		carSpot.setStartTime(currentTime);
		vehicle.setParkingSpot(carSpot);
		carSpotList.add(carSpot);
		availableCarSpots--;
		
		ParkingTicket parkingTicket = new ParkingTicket(generateTicketId(vehicle.getVehicleNumber()), currentTime);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(carSpot);
		
		System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
		           + vehicle.getVehicleType() + " is parked successfully in a Car spot");
		
		return parkingTicket;
	}
	
	private ParkingTicket parkBike(Vehicle vehicle) {
		BikeSpot bikeSpot = new BikeSpot();
		bikeSpot.setAvailable(false);
		bikeSpot.setVehicle(vehicle);
		LocalDateTime currentTime = LocalDateTime.now();
		bikeSpot.setStartTime(currentTime);
		vehicle.setParkingSpot(bikeSpot);
		bikeSpotList.add(bikeSpot);
		availableBikeSpots--;
		
		ParkingTicket parkingTicket = new ParkingTicket(generateTicketId(vehicle.getVehicleNumber()), currentTime);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(bikeSpot);
		
		System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
		           + vehicle.getVehicleType() + " is parked successfully in a Bike spot");
		
		return parkingTicket;
	}
	
	private String generateTicketId(String vehicleNumber) {
		Random random = new Random();
        int randomNum = 1000 + random.nextInt(1001);
        return randomNum + vehicleNumber;
	}
	
	//Unpark
	public void unparkVehicle(String parkingTicketId, PaymentStrategy paymentStrategy) {
		
		ParkingTicket parkingTicket = activeTickets.get(parkingTicketId);
		
		if(parkingTicket == null) {
			System.out.println("Invalid ticketId : " + parkingTicketId);
			return;
		}
		
		Vehicle vehicle = parkingTicket.getVehicle();
		ParkingSpot parkingSpot = vehicle.getParkingSpot();
		parkingSpot.setAvailable(true);
		
		if(parkingSpot.getParkingSpotType() == ParkingSpotType.TRUCKSPOT) {
			boolean removed = truckSpotList.remove(parkingSpot);
			if(removed) {
				availableTruckSpots++;
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is unparked successfully from a Truck spot");
				
				paymentService.payment(parkingTicket, rateStrategy, paymentStrategy);
				
			} else {
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is not available in Truck spot");
			}
		} else if(parkingSpot.getParkingSpotType() == ParkingSpotType.CARSPOT) {
			boolean removed = carSpotList.remove(parkingSpot);
			if(removed) {
				availableCarSpots++;
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is unparked successfully from a Car spot");
				
				paymentService.payment(parkingTicket, rateStrategy, paymentStrategy);
				
			} else {
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is not available in Car spot");
			}
		} else if(parkingSpot.getParkingSpotType() == ParkingSpotType.BIKESPOT) {
			boolean removed = bikeSpotList.remove(parkingSpot);
			if(removed) {
				availableBikeSpots++;
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is unparked successfully from a Bike spot");
				
				paymentService.payment(parkingTicket, rateStrategy, paymentStrategy);
				
			} else {
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
				           + vehicle.getVehicleType() + " is not available in Bike spot");
			}
		}
	}

	public List<BikeSpot> getBikeSpotList() {
		return bikeSpotList;
	}

	public void setBikeSpotList(List<BikeSpot> bikeSpotList) {
		this.bikeSpotList = bikeSpotList;
	}

	public List<CarSpot> getCarSpotList() {
		return carSpotList;
	}

	public void setCarSpotList(List<CarSpot> carSpotList) {
		this.carSpotList = carSpotList;
	}

	public List<TruckSpot> getTruckSpotList() {
		return truckSpotList;
	}

	public void setTruckSpotList(List<TruckSpot> truckSpotList) {
		this.truckSpotList = truckSpotList;
	}
	
	public int getAvailableBikeSpots() {
		return availableBikeSpots;
	}

	public void setAvailableBikeSpots(int availableBikeSpots) {
		this.availableBikeSpots = availableBikeSpots;
	}

	public int getAvailableCarSpots() {
		return availableCarSpots;
	}

	public void setAvailableCarSpots(int availableCarSpots) {
		this.availableCarSpots = availableCarSpots;
	}

	public int getAvailableTruckSpots() {
		return availableTruckSpots;
	}

	public void setAvailableTruckSpots(int availableTruckSpots) {
		this.availableTruckSpots = availableTruckSpots;
	}

	@Override
	public String toString() {
		return "ParkingLot [bikeSpotList=" + bikeSpotList + ", carSpotList=" + carSpotList + ", truckSpotList="
				+ truckSpotList + ", availableBikeSpots=" + availableBikeSpots + ", availableCarSpots="
				+ availableCarSpots + ", availableTruckSpots=" + availableTruckSpots + "]";
	}
	
}

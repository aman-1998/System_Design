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
import personal.learning.parking.payment.PaymentType;

public class ParkingLot {
	
	@Autowired
	private PaymentService paymentService;
	
	private int totalNumberOfFloors;
	private List<ParkingFloor> parkingFloorList = new ArrayList<>();
	private Map<String, ParkingTicket> activeTickets = new HashMap<>();
	
	public ParkingLot(int totalNumberOfFloors) {
		this.totalNumberOfFloors = totalNumberOfFloors;
	}
	
	//Park
	public String parkVehicle(Vehicle vehicle) {
		
		System.out.println("Checking parking spot availability for Vehicle[" + vehicle.getVehicleNumber() + "] "
						   + "of type " + vehicle.getVehicleType());
		
		ParkingTicket parkingTicket = null;
		
		if(vehicle.getVehicleType() == VehicleType.TRUCK) {
			boolean spotFound = false;
			for(ParkingFloor floor : parkingFloorList) {
				if(floor.getAvailableTruckSpots() != 0) {
					parkingTicket = parkTruck(vehicle, floor);
					spotFound = true;
					break;
				}
			}
			
			if(!spotFound) {
				System.out.println("Oops! all truck spots are full");
			}
		} else if(vehicle.getVehicleType() == VehicleType.CAR) {
			boolean spotFound = false;
			for(ParkingFloor floor : parkingFloorList) {
				if(floor.getAvailableCarSpots() != 0) {
					parkingTicket = parkCar(vehicle, floor);
					spotFound = true;
					break;
				}
			}
			
			if(!spotFound) {
				for(ParkingFloor floor : parkingFloorList) {
					if(floor.getAvailableTruckSpots() != 0) {
						parkingTicket = parkTruck(vehicle, floor);
						spotFound = true;
						break;
					}
				}
			}
			
			if(!spotFound) {
				System.out.println("Oops! all car & truck spots are full");
			}
		} else if(vehicle.getVehicleType() == VehicleType.BIKE) {
			boolean spotFound = false;
			for(ParkingFloor floor : parkingFloorList) {
				if(floor.getAvailableBikeSpots() != 0) {
					parkingTicket = parkBike(vehicle, floor);
					spotFound = true;
					break;
				}
			}
			
			if(!spotFound) {
				for(ParkingFloor floor : parkingFloorList) {
					if(floor.getAvailableCarSpots() != 0) {
						parkingTicket = parkCar(vehicle, floor);
						spotFound = true;
						break;
					}
				}
			}
			
			if(!spotFound) {
				for(ParkingFloor floor : parkingFloorList) {
					if(floor.getAvailableTruckSpots() != 0) {
						parkingTicket = parkTruck(vehicle, floor);
						spotFound = true;
						break;
					}
				}
			}
			
			
			if(!spotFound) {
				System.out.println("Oops! all parking spots are full");
			}
		}
		
		if(parkingTicket != null) {
			activeTickets.put(parkingTicket.getTicketId(), parkingTicket);
			return parkingTicket.getTicketId();
		}
		
		return StringUtils.EMPTY;
	}
	
	private ParkingTicket parkTruck(Vehicle vehicle, ParkingFloor floor) {
		TruckSpot truckSpot = new TruckSpot(floor.getFloorNumber());
		truckSpot.setAvailable(false);
		truckSpot.setVehicle(vehicle);
		LocalDateTime currentTime = LocalDateTime.now();
		truckSpot.setStartTime(currentTime);
		vehicle.setParkingSpot(truckSpot);
		floor.getTruckSpotList().add(truckSpot);
		floor.setAvailableTruckSpots(floor.getAvailableTruckSpots() - 1);
		
		ParkingTicket parkingTicket = new ParkingTicket(generateTicketId(vehicle.getVehicleNumber()), currentTime);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(truckSpot);
		
		System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
		                   + " is parked successfully in a Truck spot on floor " + floor.getFloorNumber());
		
		return parkingTicket;
	}
	
	private ParkingTicket parkCar(Vehicle vehicle, ParkingFloor floor) {
		CarSpot carSpot = new CarSpot(floor.getFloorNumber());
		carSpot.setAvailable(false);
		carSpot.setVehicle(vehicle);
		LocalDateTime currentTime = LocalDateTime.now();
		carSpot.setStartTime(currentTime);
		vehicle.setParkingSpot(carSpot);
		floor.getCarSpotList().add(carSpot);
		floor.setAvailableCarSpots(floor.getAvailableCarSpots() - 1);
		
		ParkingTicket parkingTicket = new ParkingTicket(generateTicketId(vehicle.getVehicleNumber()), currentTime);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(carSpot);
		
		System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
        					+ " is parked successfully in a Car spot on floor " + floor.getFloorNumber());
		
		return parkingTicket;
	}
	
	private ParkingTicket parkBike(Vehicle vehicle, ParkingFloor floor) {
		BikeSpot bikeSpot = new BikeSpot(floor.getFloorNumber());
		bikeSpot.setAvailable(false);
		bikeSpot.setVehicle(vehicle);
		LocalDateTime currentTime = LocalDateTime.now();
		bikeSpot.setStartTime(currentTime);
		vehicle.setParkingSpot(bikeSpot);
		floor.getBikeSpotList().add(bikeSpot);
		floor.setAvailableBikeSpots(floor.getAvailableBikeSpots() - 1);
		
		ParkingTicket parkingTicket = new ParkingTicket(generateTicketId(vehicle.getVehicleNumber()), currentTime);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(bikeSpot);
		
		System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " 
		           + vehicle.getVehicleType() + " is parked successfully in a Bike spot on floor " + floor.getFloorNumber());
		
		return parkingTicket;
	}
	
	private String generateTicketId(String vehicleNumber) {
		Random random = new Random();
        int randomNum = 1000 + random.nextInt(1001);
        return randomNum + vehicleNumber;
	}
	
	//Unpark
	public void unparkVehicle(String parkingTicketId, PaymentType paymentType) {
		
		ParkingTicket parkingTicket = activeTickets.get(parkingTicketId);
		
		if(parkingTicket == null) {
			System.out.println("Invalid ticketId : " + parkingTicketId);
			return;
		}
		
		Vehicle vehicle = parkingTicket.getVehicle();
		ParkingSpot parkingSpot = vehicle.getParkingSpot();
		parkingSpot.setAvailable(true);
		ParkingFloor floor = parkingFloorList.get(parkingSpot.getFloorNumber());
		
		if(parkingSpot.getParkingSpotType() == ParkingSpotType.TRUCKSPOT) {
			boolean removed = floor.getTruckSpotList().remove(parkingSpot);
			if(removed) {
				floor.setAvailableTruckSpots(floor.getAvailableTruckSpots() + 1);
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
				                 + " is unparked successfully from a Truck spot on floor " + floor.getFloorNumber());
				
				paymentService.payment(parkingSpot, paymentType);
				
			} else {
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
				                   + " is not available in Truck spot on floor " + floor.getFloorNumber());
			}
		} else if(parkingSpot.getParkingSpotType() == ParkingSpotType.CARSPOT) {
			boolean removed = floor.getCarSpotList().remove(parkingSpot);
			if(removed) {
				floor.setAvailableCarSpots(floor.getAvailableCarSpots() + 1);
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
				                   + " is unparked successfully from a Car spot on floor " + floor.getFloorNumber());
				
				paymentService.payment(parkingSpot, paymentType);
				
			} else {
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
				                    + " is not available in Car spot on floor " + floor.getFloorNumber());
			}
		} else if(parkingSpot.getParkingSpotType() == ParkingSpotType.BIKESPOT) {
			boolean removed = floor.getBikeSpotList().remove(parkingSpot);
			if(removed) {
				floor.setAvailableBikeSpots(floor.getAvailableBikeSpots() + 1);
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
				                  + " is unparked successfully from a Bike spot on floor " + floor.getFloorNumber());
				
				paymentService.payment(parkingSpot, paymentType);
				
			} else {
				System.out.println("Vehicle[" + vehicle.getVehicleNumber() + "] of type " + vehicle.getVehicleType() 
				                    + " is not available in Bike spot on floor " + floor.getFloorNumber());
			}
		}
	}

	public int getTotalNumberOfFloors() {
		return totalNumberOfFloors;
	}

	public void setTotalNumberOfFloors(int totalNumberOfFloors) {
		this.totalNumberOfFloors = totalNumberOfFloors;
	}

	public List<ParkingFloor> getParkingFloorList() {
		return parkingFloorList;
	}

	public void setParkingFloorList(List<ParkingFloor> parkingFloorList) {
		this.parkingFloorList = parkingFloorList;
	}

	public Map<String, ParkingTicket> getActiveTickets() {
		return activeTickets;
	}

	public void setActiveTickets(Map<String, ParkingTicket> activeTickets) {
		this.activeTickets = activeTickets;
	}

	@Override
	public String toString() {
		return "ParkingLot [paymentService=" + paymentService + ", totalNumberOfFloors=" + totalNumberOfFloors
				+ ", parkingFloorList=" + parkingFloorList + ", activeTickets=" + activeTickets + "]";
	}
	
}

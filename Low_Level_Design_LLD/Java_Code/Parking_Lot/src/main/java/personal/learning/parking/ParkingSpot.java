package personal.learning.parking;

import java.time.LocalDateTime;

public abstract class ParkingSpot {
	
	private ParkingSpotType parkingSpotType;
	private Vehicle vehicle;
	private boolean available;
	private LocalDateTime startTime;
	
	public ParkingSpot(ParkingSpotType parkingSpotType) {
		this.parkingSpotType = parkingSpotType;
	}
	
	public abstract double calculateFees(double hours);

	public ParkingSpotType getParkingSpotType() {
		return parkingSpotType;
	}

	public void setParkingSpotType(ParkingSpotType parkingSpotType) {
		this.parkingSpotType = parkingSpotType;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "ParkingSpot [parkingSpotType=" + parkingSpotType + ", vehicle=" + vehicle + ", available=" + available
				+ ", startTime=" + startTime + "]";
	}

}

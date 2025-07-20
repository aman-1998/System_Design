package personal.learning.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
	
	private int floorNumber;
	private List<BikeSpot> bikeSpotList = new ArrayList<>();
	private List<CarSpot> carSpotList = new ArrayList<>();
	private List<TruckSpot> truckSpotList = new ArrayList<>();
	
	private int availableBikeSpots;
	private int availableCarSpots;
	private int availableTruckSpots;
	
	public ParkingFloor(int floorNumber, int availableBikeSpots, 
			            int availableCarSpots, int availableTruckSpots) {
		this.floorNumber = floorNumber;
		this.availableBikeSpots = availableBikeSpots;
		this.availableCarSpots = availableCarSpots;
		this.availableTruckSpots = availableTruckSpots;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
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
		return "ParkingFloor [floorNumber=" + floorNumber + ", bikeSpotList=" + bikeSpotList + ", carSpotList="
				+ carSpotList + ", truckSpotList=" + truckSpotList + ", availableBikeSpots=" + availableBikeSpots
				+ ", availableCarSpots=" + availableCarSpots + ", availableTruckSpots=" + availableTruckSpots + "]";
	}
	
}

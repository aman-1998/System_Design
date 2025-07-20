package personal.learning.parking;

public class TruckSpot extends ParkingSpot {
	
	public TruckSpot(int floorNumber) {
		super(floorNumber, ParkingSpotType.TRUCKSPOT);
	}
	
	@Override
	public double calculateFees(double hours) {
		return getParkingSpotType().getRatePerHour() * hours;
	}
}

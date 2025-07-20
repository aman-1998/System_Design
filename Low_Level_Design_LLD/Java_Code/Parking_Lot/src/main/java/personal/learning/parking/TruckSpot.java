package personal.learning.parking;

public class TruckSpot extends ParkingSpot {
	
	public TruckSpot() {
		super(ParkingSpotType.TRUCKSPOT);
	}
	
	@Override
	public double calculateFees(double hours) {
		return getParkingSpotType().getRatePerHour() * hours;
	}
}

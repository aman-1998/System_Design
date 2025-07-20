package personal.learning.parking;

public class BikeSpot extends ParkingSpot {

	public BikeSpot(int floorNumber) {
		super(floorNumber, ParkingSpotType.BIKESPOT);
	}
	
	@Override
	public double calculateFees(double hours) {
		return getParkingSpotType().getRatePerHour() * hours;
	}

}

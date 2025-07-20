package personal.learning.parking;

public class CarSpot extends ParkingSpot {
	
	public CarSpot() {
		super(ParkingSpotType.CARSPOT);
	}
	
	@Override
	public double calculateFees(double hours) {
		return getParkingSpotType().getRatePerHour() * hours;
	}
}

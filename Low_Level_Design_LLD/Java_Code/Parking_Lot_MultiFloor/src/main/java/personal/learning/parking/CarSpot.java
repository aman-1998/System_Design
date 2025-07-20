package personal.learning.parking;

public class CarSpot extends ParkingSpot {
	
	public CarSpot(int floorNumber) {
		super(floorNumber, ParkingSpotType.CARSPOT);
	}
	
	@Override
	public double calculateFees(double hours) {
		return getParkingSpotType().getRatePerHour() * hours;
	}
}

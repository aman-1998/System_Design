package personal.learning.parking.rate;

import java.time.Duration;
import java.time.LocalDateTime;

import personal.learning.parking.ParkingSpot;
import personal.learning.parking.ParkingTicket;

public class FlateRateStrategy implements RateStrategy {

	private double ratePerHour = 20.0;
	
	public FlateRateStrategy() { }
	
	public FlateRateStrategy(double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	
	@Override
	public double calculatePayableAmount(ParkingTicket parkingTicket) {
		
		ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
		LocalDateTime startTime = parkingSpot.getStartTime();
		LocalDateTime endTime = LocalDateTime.now();
		double hours = Duration.between(startTime, endTime).toMinutes() / 60.0;
		
		return ratePerHour * hours;
	}

}

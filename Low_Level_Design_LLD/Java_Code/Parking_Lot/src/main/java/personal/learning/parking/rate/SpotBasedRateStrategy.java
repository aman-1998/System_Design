package personal.learning.parking.rate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import personal.learning.parking.ParkingSpot;
import personal.learning.parking.ParkingSpotType;
import personal.learning.parking.ParkingTicket;

public class SpotBasedRateStrategy implements RateStrategy {
	
	private Map<ParkingSpotType, Double> rateMap = new HashMap<>();
	
	{
		rateMap.put(ParkingSpotType.BIKESPOT, 30.0);
		rateMap.put(ParkingSpotType.CARSPOT, 30.0);
		rateMap.put(ParkingSpotType.BIKESPOT, 30.0);
	}
	
	public SpotBasedRateStrategy() { }
	
	public SpotBasedRateStrategy(Map<ParkingSpotType, Double> rateMap) {
		this.rateMap = rateMap;
	}

	@Override
	public double calculatePayableAmount(ParkingTicket parkingTicket) {
		
		ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
		LocalDateTime startTime = parkingSpot.getStartTime();
		LocalDateTime endTime = LocalDateTime.now();
		double hours = Duration.between(startTime, endTime).toMinutes() / 60.0;
		
		return rateMap.get(parkingSpot.getParkingSpotType()) * hours;
	}
}

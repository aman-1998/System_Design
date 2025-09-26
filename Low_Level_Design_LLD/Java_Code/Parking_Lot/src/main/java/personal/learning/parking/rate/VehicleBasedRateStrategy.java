package personal.learning.parking.rate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import personal.learning.parking.ParkingSpot;
import personal.learning.parking.ParkingTicket;
import personal.learning.parking.VehicleType;

public class VehicleBasedRateStrategy implements RateStrategy {
	
	private Map<VehicleType, Double> rateMap = new HashMap<>();
	
	{
		rateMap.put(VehicleType.BIKE, 30.0);
		rateMap.put(VehicleType.CAR, 60.0);
		rateMap.put(VehicleType.TRUCK, 100.0);
	}
	
	public VehicleBasedRateStrategy() { }
	
	public VehicleBasedRateStrategy(Map<VehicleType, Double> rateMap) {
		this.rateMap = rateMap;
	}

	@Override
	public double calculatePayableAmount(ParkingTicket parkingTicket) {
		
		ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
		LocalDateTime startTime = parkingSpot.getStartTime();
		LocalDateTime endTime = LocalDateTime.now();
		double hours = Duration.between(startTime, endTime).toMinutes() / 60.0;
		
		return rateMap.get(parkingSpot.getVehicle().getVehicleType()) * hours;
	}

}

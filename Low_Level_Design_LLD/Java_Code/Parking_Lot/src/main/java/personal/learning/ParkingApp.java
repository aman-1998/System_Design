package personal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import personal.learning.parking.ParkingLot;
import personal.learning.parking.rate.FlateRateStrategy;
import personal.learning.parking.rate.RateStrategy;
import personal.learning.parking.rate.SpotBasedRateStrategy;
import personal.learning.parking.rate.VehicleBasedRateStrategy;

@SpringBootApplication
public class ParkingApp {
	
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ParkingApp.class, args);
        MainClient client = context.getBean(MainClient.class);
        client.run();
    }
    
    @Bean
    public ParkingLot parkingLot() {
    	
    	RateStrategy flateRateStrategy = new FlateRateStrategy(50.0);
    	
    	RateStrategy vehicleBasedRateStrategy = new VehicleBasedRateStrategy();
    	
    	RateStrategy spotBasedRateStrategy = new SpotBasedRateStrategy();
    	
    	ParkingLot parkingLot = new ParkingLot(spotBasedRateStrategy, 2, 2, 2);
    	return parkingLot;
    }
}

package personal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import personal.learning.parking.ParkingLot;

@SpringBootApplication
public class ParkingApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ParkingApp.class, args);
        MainClient client = context.getBean(MainClient.class);
        client.run();
    }
    
    @Bean
    public ParkingLot parkingLot() {
    	ParkingLot parkingLot = new ParkingLot(2, 2, 2);
    	return parkingLot;
    }
}

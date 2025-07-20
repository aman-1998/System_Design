package personal.learning;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import personal.learning.parking.ParkingFloor;
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
    	int floorNumber = 3;
    	ParkingLot parkingLot = new ParkingLot(floorNumber);
    	List<ParkingFloor> parkingFloorList = new ArrayList<>();
    	
    	ParkingFloor parkingFloor0 = new ParkingFloor(0, 2, 2, 2);
    	ParkingFloor parkingFloor1 = new ParkingFloor(1, 2, 2, 2);
    	ParkingFloor parkingFloor2 = new ParkingFloor(2, 2, 2, 2);
    	
    	parkingFloorList.add(parkingFloor0);
    	parkingFloorList.add(parkingFloor1);
    	parkingFloorList.add(parkingFloor2);
    	
    	parkingLot.setParkingFloorList(parkingFloorList);
    	
    	return parkingLot;
    }
}

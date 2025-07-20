package personal.learning.parking.payment;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import personal.learning.parking.ParkingSpot;

@Service
public class PaymentService {
	
	@Autowired
	@Qualifier("cardPayment")
	private PaymentStrategy cardPayment;
	
	@Autowired
	@Qualifier("upiPayment")
	private PaymentStrategy upiPayment;
	
	@Autowired
	@Qualifier("cashPayment")
	private PaymentStrategy cashPayment;
	
	public void payment(ParkingSpot parkingSpot, PaymentType paymentType) {
		
		LocalDateTime startTime = parkingSpot.getStartTime();
		LocalDateTime endTime = LocalDateTime.now();
		double hours = Duration.between(startTime, endTime).toMinutes() / 60.0;
		
		double amount = parkingSpot.calculateFees(hours);
		
		if(paymentType == PaymentType.CARD) {
			cardPayment.processPayment(amount);
		} else if(paymentType == PaymentType.CASH) {
			cashPayment.processPayment(amount);
		} else if(paymentType == PaymentType.UPI) {
			upiPayment.processPayment(amount);
		}
	}
}

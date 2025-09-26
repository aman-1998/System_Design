package personal.learning.parking.payment;

import org.springframework.stereotype.Service;

import personal.learning.parking.ParkingTicket;
import personal.learning.parking.rate.RateStrategy;

@Service
public class PaymentService {
	
	public void payment(ParkingTicket parkingTicket, RateStrategy rateStrategy, PaymentStrategy paymentStrategy) {
		
		double amount = rateStrategy.calculatePayableAmount(parkingTicket);
		paymentStrategy.processPayment(amount);
	}
}

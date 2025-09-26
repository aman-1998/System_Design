package personal.learning.parking.payment;

import org.springframework.stereotype.Service;

public class CashPaymentStrategy implements PaymentStrategy {
	
	@Override
	public void processPayment(double amount) {
		System.out.println("Pay Rs. " + amount + " using cash");
	}
}

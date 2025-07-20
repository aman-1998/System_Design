package personal.learning.parking.payment;

import org.springframework.stereotype.Service;

@Service
public class UpiPayment implements PaymentStrategy {
	
	@Override
	public void processPayment(double amount) {
		System.out.println("[Payment Mode : UPI] Rs. " + amount + " deducted");
		// logic for money deduction via UPI
	}
}

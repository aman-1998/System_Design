package personal.learning.parking.payment;

import org.springframework.stereotype.Service;

@Service
public class CashPayment implements PaymentStrategy {
	
	@Override
	public void processPayment(double amount) {
		System.out.println("[Payment Mode : Cash] Rs. " + amount + " deducted");
		// logic for money deduction via cash
	}
}

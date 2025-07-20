package personal.learning.parking.payment;

import org.springframework.stereotype.Service;

@Service
public class CardPayment implements PaymentStrategy {

	@Override
	public void processPayment(double amount) {
		System.out.println("[Payment Mode : Card] Rs. " + amount + " deducted");
		// logic for money deduction via card
	}

}

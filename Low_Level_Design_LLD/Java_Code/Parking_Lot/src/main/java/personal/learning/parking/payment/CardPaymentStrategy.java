package personal.learning.parking.payment;

import org.springframework.beans.factory.annotation.Autowired;

public class CardPaymentStrategy implements PaymentStrategy {
	
	private Card card;

	public CardPaymentStrategy(Card card) {
		this.card = card;
	}
	
	@Override
	public void processPayment(double amount) {
		boolean isAuthenticated = authenticateUsingCard(card);
		
		if(isAuthenticated) {
			System.out.println("Payed Rs. " + amount + " using Card");
		} else {
			System.out.println("Incorrect PIN");
		}
	}

	private boolean authenticateUsingCard(Card card) {
		// TODO Auto-generated method stub
		return true;
	}

}

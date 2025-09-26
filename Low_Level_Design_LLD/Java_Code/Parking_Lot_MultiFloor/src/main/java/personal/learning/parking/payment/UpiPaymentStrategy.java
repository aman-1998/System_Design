package personal.learning.parking.payment;

import org.springframework.stereotype.Service;

public class UpiPaymentStrategy implements PaymentStrategy {
	
	private String mobileNumber;
	private int pin;
	
	public UpiPaymentStrategy(String mobileNumber, int pin) {
		this.mobileNumber = mobileNumber;
		this.pin = pin;
	}

	@Override
	public void processPayment(double amount) {
		boolean isAuthenticated = authenticateUsingPIN(mobileNumber, pin);
		
		if(isAuthenticated) {
			System.out.println("Pay Rs. " + amount + " using UPI");
		} else {
			System.out.println("Incorrect PIN");
		}
	}

	private boolean authenticateUsingPIN(String mobileNumber, int pin) {
		// TODO Auto-generated method stub
		return true;
	}
}

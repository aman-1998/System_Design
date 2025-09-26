package personal.learning.parking.payment;

public class Card {
	
	private String cardNumber;
	private int cvv;
	private String expiry;
	
	public Card(String cardNumber, int cvv, String expiry) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiry = expiry;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	
	
}

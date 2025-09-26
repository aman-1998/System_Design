package personal.learning.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member {
	
	private String memberId;
	private String name;
	private String contactInfo;
	private List<Loan> activeLoans = new ArrayList<>();
	private double amountToBePaid;
	
	public Member(String name, String contactInfo) {
        this.memberId = UUID.randomUUID().toString();
        this.name = name;
        this.contactInfo = contactInfo;
        amountToBePaid = 0.0;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<Loan> getActiveLoans() {
		return activeLoans;
	}

	public void setActiveLoans(List<Loan> activeLoans) {
		this.activeLoans = activeLoans;
	}

	public double getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", contactInfo=" + contactInfo + ", activeLoans="
				+ activeLoans + ", amountToBePaid=" + amountToBePaid + "]";
	}
	
}

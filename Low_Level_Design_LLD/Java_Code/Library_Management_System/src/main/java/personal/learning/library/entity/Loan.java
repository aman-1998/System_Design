package personal.learning.library.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
	
	private String loanId;
	private BookCopy bookCopy;
	private Member member;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private boolean active;
	
	public Loan(BookCopy bookCopy, Member member, int maxBorrowDays) {
		this.loanId = UUID.randomUUID().toString();
		this.bookCopy = bookCopy;
		this.member = member;
		this.borrowDate = LocalDate.now();
		this.dueDate = borrowDate.plusDays(maxBorrowDays);
		this.active = true;
	}
	
	public void closeLoan() {
		bookCopy.markAvailable();
		member.getActiveLoans().remove(this);
		active = false;
		
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", bookCopy=" + bookCopy + ", member=" + member + ", borrowDate=" + borrowDate
				+ ", dueDate=" + dueDate + ", active=" + active + "]";
	}
	
}

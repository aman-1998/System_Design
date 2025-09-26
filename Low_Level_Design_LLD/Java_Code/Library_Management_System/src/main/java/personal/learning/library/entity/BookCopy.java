package personal.learning.library.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import personal.learning.library.enums.BookStatus;
import personal.learning.library.enums.BookType;

public class BookCopy extends Book {
	
	private String barCode;
	private LocalDate publicationDate;
	private Slot slot;
	private BookStatus bookStatus;
	
	public BookCopy(String bookName, List<Author> author, BookType bookType) {
		
		super(bookName, author, bookType);
		this.barCode = UUID.randomUUID().toString();
		this.bookStatus = BookStatus.AVAILABLE;
	}
	
	public boolean isAvailable() {
		if(bookStatus == BookStatus.AVAILABLE) {
			return true;
		}
		return false;
	}
	
	public synchronized void markIssued() {
		if(isAvailable()) {
			bookStatus = BookStatus.ISSUED;
		} else {
			throw new IllegalArgumentException("Book is already issued");
		}
	}
	
	public synchronized void markAvailable() {
		bookStatus = BookStatus.AVAILABLE;
	}

	public String getBarCode() {
		return barCode;
	}

	public synchronized void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public synchronized void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public synchronized void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Slot getSlot() {
		return slot;
	}

	public synchronized void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "BookCopy [barCode=" + barCode + ", publicationDate=" + publicationDate + ", slot=" + slot
				+ ", bookStatus=" + bookStatus + "]";
	}
	
}

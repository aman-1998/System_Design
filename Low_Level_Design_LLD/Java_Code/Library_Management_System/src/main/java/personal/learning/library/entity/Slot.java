package personal.learning.library.entity;

public class Slot {
	
	private int slotNum;
	private Book book;
	private boolean isAvailable;
	
	public Slot(int slotNum, Book book, boolean isAvailable) {
		this.slotNum = slotNum;
		this.book = book;
		this.isAvailable = isAvailable;
	}

	public int getSlotNum() {
		return slotNum;
	}

	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Slot [slotNum=" + slotNum + ", book=" + book + ", isAvailable=" + isAvailable + "]";
	}
	
}

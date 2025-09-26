package personal.learning.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import personal.learning.library.enums.BookType;

public abstract class Book {
	
	private String bookId;
	private String bookName;
	private List<Author> author = new ArrayList<>();
    private BookType bookType;
    
    public Book(String bookName, List<Author> author, BookType bookType) {
    	this.bookId = UUID.randomUUID().toString();
    	this.bookName = bookName;
    	this.author = author;
    	this.bookType = bookType;
    }
    
    public abstract boolean isAvailable();
    
    public abstract void markIssued();
    
    public abstract void markAvailable();

	public String getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public List<Author> getAuthor() {
		return author;
	}

	public BookType getBookType() {
		return bookType;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", bookType=" + bookType
				+ "]";
	}
    
}

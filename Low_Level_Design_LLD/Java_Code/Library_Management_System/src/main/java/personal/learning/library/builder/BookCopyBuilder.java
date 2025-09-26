package personal.learning.library.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import personal.learning.library.entity.Author;
import personal.learning.library.entity.BookCopy;
import personal.learning.library.enums.BookType;

public class BookCopyBuilder {
	
	private String bookName;
	private List<Author> author = new ArrayList<>();
    private BookType bookType;
    private LocalDate publicationDate;
    
	public BookCopyBuilder setBookName(String bookName) {
		this.bookName = bookName;
		return this;
	}
	
	public BookCopyBuilder setAuthor(List<Author> author) {
		this.author = author;
		return this;
	}
	
	public BookCopyBuilder setBookType(BookType bookType) {
		this.bookType = bookType;
		return this;
	}
	
	public BookCopyBuilder setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}
    
    public BookCopy builder() {
    	
    	BookCopy bookCopy = new BookCopy(bookName, author, bookType);
    	bookCopy.setPublicationDate(publicationDate);
    	
    	return bookCopy;
    }
}

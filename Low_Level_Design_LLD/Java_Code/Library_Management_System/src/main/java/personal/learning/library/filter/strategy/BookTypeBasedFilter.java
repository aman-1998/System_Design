package personal.learning.library.filter.strategy;

import java.util.List;
import java.util.stream.Collectors;

import personal.learning.library.entity.Book;
import personal.learning.library.enums.BookType;

public class BookTypeBasedFilter implements FilterStrategy {
	
	private BookType bookType;
	
	public BookTypeBasedFilter(BookType bookType) {
		this.bookType = bookType;
	}

	@Override
	public List<Book> filter(List<Book> allBooks) {
	
		List<Book> filteredResult = allBooks.stream().filter(book -> book.getBookType() == bookType)
		                 							 .collect(Collectors.toList());
		
		return filteredResult;
	}

}

package personal.learning.library.filter.strategy;

import java.util.List;

import personal.learning.library.entity.Book;

public interface FilterStrategy {
	
	List<Book> filter(List<Book> allBooks);
}

package personal.learning.library.filter.strategy;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import personal.learning.library.entity.Book;

public class TitleBasedFilterStrategy implements FilterStrategy {
	
	private String title;
	
	public TitleBasedFilterStrategy(String title) {
		this.title = title;
	}

	@Override
	public List<Book> filter(List<Book> allBooks) {
		
		List<Book> filteredResult = allBooks.stream().filter(book -> StringUtils.equalsIgnoreCase(title, book.getBookName()))
		                 							 .collect(Collectors.toList());
		
		return filteredResult;
	}

}

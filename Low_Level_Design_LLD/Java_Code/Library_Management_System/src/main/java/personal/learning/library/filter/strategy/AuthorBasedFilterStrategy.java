package personal.learning.library.filter.strategy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import personal.learning.library.entity.Author;
import personal.learning.library.entity.Book;

public class AuthorBasedFilterStrategy implements FilterStrategy {
	
	private String authorName;
	
	public AuthorBasedFilterStrategy(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public List<Book> filter(List<Book> allBooks) {
		
		List<Book> filteredResult =  allBooks.stream().filter(book -> {
			
			Optional<Author> authorOp = book.getAuthor().stream().filter(author -> StringUtils.equalsIgnoreCase(authorName, author.getName()))
									 						     .findFirst();
			
			if(authorOp.isPresent()) {
				return true;
			} 
			
			return false;
		}).collect(Collectors.toList());
		
		return filteredResult;
	}
}

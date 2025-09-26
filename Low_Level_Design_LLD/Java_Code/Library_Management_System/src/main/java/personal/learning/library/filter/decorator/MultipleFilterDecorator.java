package personal.learning.library.filter.decorator;

import java.util.ArrayList;
import java.util.List;

import personal.learning.library.entity.Book;
import personal.learning.library.filter.strategy.FilterStrategy;

public class MultipleFilterDecorator implements BookFilterDecorator {
	
	List<FilterStrategy> filterStrategies = new ArrayList<>();
	
	public MultipleFilterDecorator(List<FilterStrategy> filterStrategies) {
		this.filterStrategies = filterStrategies;
	}

	@Override
	public List<Book> filter(List<Book> allBooks) {
		
		List<Book> result = allBooks;
		
		for(FilterStrategy filterStrategy : filterStrategies) {
			result = filterStrategy.filter(result);
		}
		return result;
	}

}

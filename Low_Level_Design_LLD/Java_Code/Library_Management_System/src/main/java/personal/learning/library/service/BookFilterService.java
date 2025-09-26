package personal.learning.library.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.library.entity.Book;
import personal.learning.library.filter.decorator.MultipleFilterDecorator;
import personal.learning.library.filter.strategy.AuthorBasedFilterStrategy;
import personal.learning.library.filter.strategy.FilterStrategy;
import personal.learning.library.filter.strategy.TitleBasedFilterStrategy;
import personal.learning.library.repository.LibraryRepository;

@Service
public class BookFilterService {
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	public List<Book> showBooks() {
		
		System.out.println("------------- Show All Books -----------------------");
		
		List<Book> allBooks = libraryRepository.bookByBookIdMap
				                               .entrySet().stream()
		                                       .map(entry -> entry.getValue())
		                                       .collect(Collectors.toList());
		
		allBooks.stream().forEach(book -> {
			
			System.out.println("Book Name : " + book.getBookName());
			System.out.print("Authors : ");
			book.getAuthor().stream()
			.forEach(author -> System.out.print(author.getName() + " | "));
			System.out.print("Book Type : " + book.getBookType().name());
		});
		
		return allBooks;
	}
	
	
	public List<Book> searchByTitle(String title) {
		
		System.out.println("------------- Title based filter -----------------------");
		
		List<Book> allBooks = libraryRepository.bookByBookIdMap
							                   .entrySet().stream()
							                   .map(entry -> entry.getValue())
							                   .collect(Collectors.toList());
		
		TitleBasedFilterStrategy titleBasedStrategy = new TitleBasedFilterStrategy(title);
		
		List<Book> result = titleBasedStrategy.filter(allBooks);
		
		result.stream().forEach(book -> {
			
			System.out.println("Book Name : " + book.getBookName());
			System.out.print("Authors : ");
			book.getAuthor().stream()
			.forEach(author -> System.out.print(author.getName() + " | "));
			System.out.print("Book Type : " + book.getBookType().name());
		});
		
		return result;
    }
	
	
	public List<Book> searchByAuthor(String authorName) {
		
		System.out.println("------------- Author based filter -----------------------");
		
		List<Book> allBooks = libraryRepository.bookByBookIdMap
							                   .entrySet().stream()
							                   .map(entry -> entry.getValue())
							                   .collect(Collectors.toList());
		
		AuthorBasedFilterStrategy authorBasedFilterStrategy = new AuthorBasedFilterStrategy(authorName);
		
		List<Book> result = authorBasedFilterStrategy.filter(allBooks);
		
		result.stream().forEach(book -> {
			
			System.out.println("Book Name : " + book.getBookName());
			System.out.print("Authors : ");
			book.getAuthor().stream()
			.forEach(author -> System.out.print(author.getName() + " | "));
			System.out.print("Book Type : " + book.getBookType().name());
		});
		
		return result;
	}
	
	
	public List<Book> searchByTitleAndAuthor(String title, String authorName) {
		
		System.out.println("------------- Filter by title and authorName -----------------------");
		
		List<Book> allBooks = libraryRepository.bookByBookIdMap
								               .entrySet().stream()
								               .map(entry -> entry.getValue())
								               .collect(Collectors.toList());
		
		TitleBasedFilterStrategy titleBasedStrategy = new TitleBasedFilterStrategy(title);
		AuthorBasedFilterStrategy authorBasedFilterStrategy = new AuthorBasedFilterStrategy(authorName);
		
		List<FilterStrategy> filterStrategies = Arrays.asList(titleBasedStrategy, authorBasedFilterStrategy);
		
		MultipleFilterDecorator multipleFilterDecorator = new MultipleFilterDecorator(filterStrategies);
		
		List<Book> result = multipleFilterDecorator.filter(allBooks);
		
		result.stream().forEach(book -> {
			
			System.out.println("Book Name : " + book.getBookName());
			System.out.print("Authors : ");
			book.getAuthor().stream()
			.forEach(author -> System.out.print(author.getName() + " | "));
			System.out.print("Book Type : " + book.getBookType().name());
		});
		
		return result;
	}
	
}

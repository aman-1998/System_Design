package personal.learning.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.library.entity.Book;
import personal.learning.library.service.BookFilterService;
import personal.learning.library.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryManagementController {
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private BookFilterService bookFilterService;
	
	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		
		List<Book> allBooks = bookFilterService.showBooks();
		if(allBooks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(allBooks);
	}

}

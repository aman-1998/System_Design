package personal.learning.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Author {
	
	private String authorId;
	private String name;
	private List<Book> publishedBooks = new ArrayList<>();
	
	public Author(String name, List<Book> publishedBooks) {
		this.authorId = UUID.randomUUID().toString();
		this.name = name;
		this.publishedBooks = publishedBooks;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getPublishedBooks() {
		return publishedBooks;
	}

	public void setPublishedBooks(List<Book> publishedBooks) {
		this.publishedBooks = publishedBooks;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", name=" + name + ", publishedBooks=" + publishedBooks + "]";
	}
	
}

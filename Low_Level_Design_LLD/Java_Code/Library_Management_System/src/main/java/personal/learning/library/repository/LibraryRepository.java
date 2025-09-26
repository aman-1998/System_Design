package personal.learning.library.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import personal.learning.library.entity.Book;
import personal.learning.library.entity.Loan;
import personal.learning.library.entity.Member;

@Repository
public class LibraryRepository {
	
	public static int slotNumber = 1;
	
	public Map<String, Book> bookByBookIdMap = new HashMap<>();
	public Map<String, Book> bookByBarCodeMap = new HashMap<>();
	public Map<String, Book> bookByTitleMap = new HashMap<>();
	
	public Map<String, Double> penaltyRateByBookIdMap = new HashMap<>();
	
	public Map<String, Member> memberByIdMap = new HashMap<>(); 
	
	public Map<String, Loan> loanByBarCodeMap = new HashMap<>();
	public Map<String, Loan> loanByIdMap = new HashMap<>(); 
	
}

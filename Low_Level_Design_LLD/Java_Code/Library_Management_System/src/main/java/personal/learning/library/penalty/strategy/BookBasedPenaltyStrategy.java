package personal.learning.library.penalty.strategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;

import personal.learning.library.entity.Book;
import personal.learning.library.entity.Loan;
import personal.learning.library.repository.LibraryRepository;

public class BookBasedPenaltyStrategy implements PenaltyStrategy {
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	public BookBasedPenaltyStrategy() {}

	@Override
	public double calculatePenalty(Loan loan) {
		
		double penalty = 0;
		
		Book book = loan.getBookCopy();
		double penaltyRate = libraryRepository.penaltyRateByBookIdMap.get(book.getBookId());
		LocalDate currentDate = LocalDate.now();
		LocalDate dueDate = loan.getDueDate();
		if(dueDate.isBefore(currentDate)) {
			long numberOfDays = ChronoUnit.DAYS.between(dueDate, currentDate);
			if(numberOfDays <= 5) {
				penalty = numberOfDays * penaltyRate;
			} else if(numberOfDays <= 10) {
				penalty = numberOfDays * 2 * penaltyRate;
			} else if(numberOfDays <= 30) {
				penalty = numberOfDays * 5 * penaltyRate;
			} else {
				penalty = numberOfDays * 10 * penaltyRate;
			}
		}
		
		return penalty;
	}
	
	
}

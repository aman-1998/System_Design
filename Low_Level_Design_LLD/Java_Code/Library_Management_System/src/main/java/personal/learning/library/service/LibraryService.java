package personal.learning.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.library.builder.BookCopyBuilder;
import personal.learning.library.entity.Author;
import personal.learning.library.entity.BookCopy;
import personal.learning.library.entity.Loan;
import personal.learning.library.entity.Member;
import personal.learning.library.entity.Slot;
import personal.learning.library.enums.BookStatus;
import personal.learning.library.enums.BookType;
import personal.learning.library.penalty.strategy.PenaltyStrategy;
import personal.learning.library.repository.LibraryRepository;

@Service
public class LibraryService {
	
	@Autowired
	private LibraryRepository libRepository;
	
	@Autowired
	private PenaltyService penaltyService;
	
	public void addBookInLibrary(String title, BookType bookType, 
			                     List<Author> authors, LocalDate publicationDate, 
			                     double penaltyRatePerDay) {
		
		BookCopyBuilder bookCopyBuilder = new BookCopyBuilder();
		
		BookCopy bookCopy = bookCopyBuilder.setBookName(title)
							               .setAuthor(authors)
							               .setBookType(bookType)
							               .setPublicationDate(publicationDate)
										   .builder();	
		
		Slot slot = new Slot(LibraryRepository.slotNumber++, bookCopy, true);
		bookCopy.setSlot(slot);
		slot.setBook(bookCopy);
		
		for(Author author : authors) {
			author.getPublishedBooks().add(bookCopy);
		}
		
		libRepository.penaltyRateByBookIdMap.put(bookCopy.getBookId(), penaltyRatePerDay);
		
		libRepository.bookByBarCodeMap.put(bookCopy.getBarCode(), bookCopy);
		libRepository.bookByTitleMap.put(title, bookCopy);	
	}
	
	public String registerMember(String name, String contactInfo) {
		
		Member member = new Member(name, contactInfo);
		
		libRepository.memberByIdMap.put(member.getMemberId(), member);
		
		return member.getMemberId();
	}
	
	public synchronized boolean borrowBook(String memberId, String barCode, int days) {
		
		BookCopy bookCopy = (BookCopy) libRepository.bookByBarCodeMap.get(barCode);
		Member member = libRepository.memberByIdMap.get(memberId);
		
		if(member == null) {
			System.out.println("Member with member id: " + memberId + " does not exist");
			return false;
		}
		
		if(bookCopy == null) {
			System.out.println("Book with barcode: " + barCode + " does not exist in library");
			return false;
		}
		
		if(bookCopy.getBookStatus() == BookStatus.LOST) {
			System.out.println("Book with barcode: " + barCode + " is lost");
			return false;
		}
		
		if(member.getAmountToBePaid() > 0) {
			System.out.println("Please pay Rs. " + member.getAmountToBePaid() + " before borrowing");
			return false;
		}
		
		if(bookCopy.isAvailable()) {
			bookCopy.markIssued();
		} else {
			return false;
		}
		
		Loan loan = new Loan(bookCopy, member, days);
		member.getActiveLoans().add(loan);
		
		libRepository.loanByIdMap.put(loan.getLoanId(), loan);
		libRepository.loanByBarCodeMap.put(loan.getBookCopy().getBarCode(), loan);
		
		System.out.printf("Successfully borrowed " + bookCopy.getBookName() + " by "+  member.getName());
		
		return true;
	}
	
	public synchronized boolean returnBook(String memberId, String barCode, PenaltyStrategy penaltyStrategy) {
		
		BookCopy bookCopy = (BookCopy) libRepository.bookByBarCodeMap.get(barCode);
		Member member = libRepository.memberByIdMap.get(memberId);
		
		if(member == null) {
			System.out.println("Member with member id: " + memberId + " does not exist");
			return false;
		}
		
		if(bookCopy == null) {
			System.out.println("Book with barcode: " + barCode + " does not exist in library");
			return false;
		}
		
		if(bookCopy.getBookStatus() == BookStatus.ISSUED) {
			
			Optional<Loan> loanOp = member.getActiveLoans().stream().filter(loan -> StringUtils.equalsIgnoreCase(memberId, loan.getMember().getMemberId()))
			                                				        .findFirst();
			if(loanOp.isPresent()) {
				Loan loan = loanOp.get();
				
				double penalty = penaltyService.amountToBePaid(loan, penaltyStrategy);
				if(penalty > 0) {
					member.setAmountToBePaid(penalty);
					System.out.println("Please pay Rs. " + penalty);
				}
				
				member.getActiveLoans().remove(loan);
				loan.setActive(false);
				bookCopy.markAvailable();
				
				return true;
			}
		}
		
		return false;
	}
	
}

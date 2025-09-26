package personal.learning.library.service;

import org.springframework.stereotype.Service;

import personal.learning.library.entity.Loan;
import personal.learning.library.penalty.strategy.PenaltyStrategy;

@Service
public class PenaltyService {
	
	public double amountToBePaid(Loan loan, PenaltyStrategy penaltyStrategy) {
		double penalty = penaltyStrategy.calculatePenalty(loan);
		return penalty;
	}
}

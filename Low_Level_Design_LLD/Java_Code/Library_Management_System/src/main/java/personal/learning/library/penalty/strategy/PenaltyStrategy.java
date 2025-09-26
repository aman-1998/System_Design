package personal.learning.library.penalty.strategy;

import personal.learning.library.entity.Loan;

public interface PenaltyStrategy {
	
	double calculatePenalty(Loan loan);
}

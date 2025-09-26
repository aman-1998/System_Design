package personal.learning.library.penalty.strategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import personal.learning.library.entity.Loan;

public class FlatPenaltyStrategy implements PenaltyStrategy {

	private double penaltyRate = 20; // per day
	
	public FlatPenaltyStrategy() {}
	
	public FlatPenaltyStrategy(double penaltyRate) {
		this.penaltyRate = penaltyRate;
	}
	
	@Override
	public double calculatePenalty(Loan loan) {
		
		double penalty = 0;
		
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

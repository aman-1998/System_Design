package personal.learning.sovf.decorator;

import java.util.ArrayList;
import java.util.List;

import personal.learning.sovf.entity.Question;
import personal.learning.sovf.strategy.FeedFilterStrategy;

public class MultipleFeedFilter implements FeedFilterDecorator {
	
	List<FeedFilterStrategy> filterStrategies = new ArrayList<>();
	
	public MultipleFeedFilter(List<FeedFilterStrategy> filterStrategies) {
        this.filterStrategies = filterStrategies;
    }

	@Override
	public List<Question> filter(List<Question> allQuestions) {
		
		List<Question> result = allQuestions;
		
		for(FeedFilterStrategy filterStrategy : filterStrategies) {
			result = filterStrategy.filter(result);
		}
		
		return result;
	}

}

package personal.learning.sovf.strategy;

import java.util.List;

import personal.learning.sovf.entity.Question;

public interface FeedFilterStrategy {
	
	 List<Question> filter(List<Question> allQuestions);
}

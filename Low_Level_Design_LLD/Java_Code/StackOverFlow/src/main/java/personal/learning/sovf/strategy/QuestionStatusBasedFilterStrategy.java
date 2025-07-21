package personal.learning.sovf.strategy;

import java.util.List;
import java.util.stream.Collectors;

import personal.learning.sovf.entity.Question;
import personal.learning.sovf.enums.QuestionStatus;

public class QuestionStatusBasedFilterStrategy implements FeedFilterStrategy {
	
	private QuestionStatus questionStatus;
	
	public QuestionStatusBasedFilterStrategy(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }

	@Override
	public List<Question> filter(List<Question> allQuestions) {
		return allQuestions.stream().filter(question -> question.getQuestionStatus() == questionStatus)
							        .collect(Collectors.toList());
	}

}

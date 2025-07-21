package personal.learning.sovf.strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import personal.learning.sovf.entity.Question;
import personal.learning.sovf.entity.Tag;

public class TagBasedFilterStrategy implements FeedFilterStrategy {
	
	private List<Tag> tags;
	
	public TagBasedFilterStrategy(List<Tag> tags) {
        this.tags = tags;
    }

	@Override
	public List<Question> filter(List<Question> allQuestions) {
		return allQuestions.stream().filter(question -> !Collections.disjoint(question.getAssociatedTags(), tags))
							        .collect(Collectors.toList());
	}

}

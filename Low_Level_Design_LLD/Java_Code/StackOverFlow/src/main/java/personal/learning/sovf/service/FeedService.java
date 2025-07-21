package personal.learning.sovf.service;

import java.util.List;
import java.util.stream.Collectors;

import personal.learning.sovf.entity.Question;
import personal.learning.sovf.entity.Tag;
import personal.learning.sovf.enums.QuestionStatus;
import personal.learning.sovf.strategy.QuestionStatusBasedFilterStrategy;
import personal.learning.sovf.strategy.TagBasedFilterStrategy;

public class FeedService {
	
	public void showFeed() {
		
		System.out.println("---- Feed started ----");
		Database.questions.entrySet().stream()
		          				  .map(entry -> entry.getValue())
		          				  .forEach(question -> {
		          					  System.out.println("Question : " + question.getText());
		          					  System.out.println("Upvotes: " + question.getNoOfUpVotes());
		          					  System.out.println("Upvotes: " + question.getNoOfDownVotes());
		          					  System.out.println("Posted by: " + question.getAuthor());
		          					  System.out.println("Posted by: " + question.getAuthor());
		          					  System.out.println("Question Status: " + question.getQuestionStatus().name());
		          					  System.out.print("Tags: " );
		          					  question.getAssociatedTags().stream().forEach(tag -> System.out.print(tag + " "));
		          				  });
		System.out.println("---- Feed ended ----");
	}
	
	public void showFeedByTag(List<String> tagNames) {
		 System.out.println("---- Feed by tags started ----");
		 
		 List<Tag> tags = tagNames.stream().map(name -> TagService.getOrCreateTopic(name))
				 						   .collect(Collectors.toList());
		 
		 TagBasedFilterStrategy tagBasedFilterStrategy = new TagBasedFilterStrategy(tags);
		 
		 List<Question> allQuestions = Database.questions.entrySet().stream()
			  						           .map(entry -> entry.getValue()).collect(Collectors.toList());
		 
		 List<Question> result = tagBasedFilterStrategy.filter(allQuestions);
		 
		 result.stream().forEach(question -> {
						   System.out.println("Question : " + question.getText());
						   System.out.println("Upvotes: " + question.getNoOfUpVotes());
						   System.out.println("Upvotes: " + question.getNoOfDownVotes());
						   System.out.println("Posted by: " + question.getAuthor());
						   System.out.println("Posted by: " + question.getAuthor());
						   System.out.println("Question Status: " + question.getQuestionStatus().name());
						   System.out.print("Tags: " );
						   question.getAssociatedTags().stream().forEach(tag -> System.out.print(tag + " "));
			   			});	 
		 
		 System.out.println("---- Feed ended ----");
	}
	
	public void showFeedByQuestionStatus(QuestionStatus questionStatus) {
		System.out.println("---- Feed by question status ----");
		
		QuestionStatusBasedFilterStrategy questionStatusBasedFilterStrategy = new QuestionStatusBasedFilterStrategy(questionStatus);
		
		List<Question> allQuestions = Database.questions.entrySet().stream()
		           							.map(entry -> entry.getValue()).collect(Collectors.toList());
		
		List<Question> result = questionStatusBasedFilterStrategy.filter(allQuestions);
		
		result.stream().forEach(question -> {
			   System.out.println("Question : " + question.getText());
			   System.out.println("Upvotes: " + question.getNoOfUpVotes());
			   System.out.println("Upvotes: " + question.getNoOfDownVotes());
			   System.out.println("Posted by: " + question.getAuthor());
			   System.out.println("Posted by: " + question.getAuthor());
			   System.out.println("Question Status: " + question.getQuestionStatus().name());
			   System.out.print("Tags: " );
			   question.getAssociatedTags().stream().forEach(tag -> System.out.print(tag + " "));
			});	 
		
		System.out.println("---- Feed ended ----");
	}
}

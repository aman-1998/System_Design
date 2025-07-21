package personal.learning.sovf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import personal.learning.sovf.entity.Answer;
import personal.learning.sovf.entity.Question;
import personal.learning.sovf.entity.Tag;
import personal.learning.sovf.enums.PostType;
import personal.learning.sovf.enums.QuestionStatus;
import personal.learning.sovf.enums.VoteType;
import personal.learning.sovf.factory.PostFactory;

public class PostService {
	
	private static int questionCounter = 0;
	
	public void postQuestion(String questionText, List<String> tags) {
		
		 Optional<Question> questionOp = Database.questions.entrySet().stream()
															          .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
															          .map(entry -> entry.getValue())
															          .findFirst();
		 if(questionOp.isPresent()) {
			 System.out.println("This question is already posted.");
			 return;
		 }
		
		 List<Tag> listOfTags = new ArrayList<>();
		 listOfTags = tags.stream().map(t -> TagService.getOrCreateTopic(t))
			    	               .collect(Collectors.toList());
		
		 Question question = (Question) PostFactory.getPost(questionText, Database.currentUser, PostType.QUESTION, listOfTags);
		
		 questionCounter++;
		 Database.questions.put(questionCounter, question);
         Database.currentUser.getQuestionsAsked().add(question);
        
         System.out.println("Question posted: " + questionText);
	}
	
	public void answerQuestion(String questionText, String answerText) {
		
		Optional<Question> questionOp = Database.questions.entrySet()
										                  .stream()
										                  .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
										                  .map(entry -> entry.getValue())
										                  .findFirst();
		
		if(questionOp.isPresent()) {
			
			Question question = questionOp.get();
			Answer answer = (Answer) PostFactory.getPost(answerText, Database.currentUser, PostType.ANSWER, question);
			
		    question.getListOfAnswers().add(answer);
		    question.setQuestionStatus(QuestionStatus.ANSWERED);
		    Database.currentUser.getAnswersGiven().add(answer);
		    
		    System.out.println("Answer added: " + answerText + " for a question: " + questionText);
		    
		    // Notify
		    question.getUsersSubscribedToThisQuestion().stream()
		     										   .forEach(user -> user.notify(questionText, answerText));

		} else {
			throw new IllegalArgumentException("Question not found : " + questionText);
		}                                    
	}
	
	public void showQuestionAndAnswer(String questionText) {
		
		Optional<Question> questionOp = Database.questions.entrySet()
										                  .stream()
										                  .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
										                  .map(entry -> entry.getValue())
										                  .findFirst();
		
		if(questionOp.isPresent()) {
			Question question = questionOp.get();
			question.getListOfAnswers().stream().forEach(ans -> System.out.println(" - " + ans.getText() + " by " 
			                                             + ans.getAuthor().getName() 
			                                             + (ans.isAccepted() ? " [Accepted]" : ""
			                                             + " No. of upvotes : " + question.getNoOfUpVotes()
			                                             + " No. of downvotes : " + question.getNoOfDownVotes())));
			
		} else {
			throw new IllegalArgumentException("Question not found : " + questionText);
		}  
	}
	
	public void acceptAnswer(String questionText, String answerText) {
		
		Optional<Question> questionOp = Database.questions.entrySet()
										                  .stream()
										                  .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
										                  .map(entry -> entry.getValue())
										                  .findFirst();
		
		if(questionOp.isPresent()) {
			Question question = questionOp.get();
			
			if(StringUtils.equalsIgnoreCase(question.getAuthor().getName(), 
					                        Database.currentUser.getName())) {
				
				Optional<Answer> answerOp = question.getListOfAnswers().stream()
							                        .filter(ans -> StringUtils.equals(ans.getText(), answerText))
							                        .findFirst();
				
				if(answerOp.isPresent()) {
					Answer answer = answerOp.get();
					answer.setAccepted(true);
					question.setAcceptedAnswer(answer);
					System.out.println("Answer is accepted");
				} else {
					throw new IllegalArgumentException("Answer not found : " + answerText);
				}
			} else {
				System.out.println(Database.currentUser.getName() + " can't accept the answer");
			}
		} else {
			throw new IllegalArgumentException("Question not found : " + questionText);
		}
	}
	
	public void upvoteOrDownVoteAnswer(VoteType voteType, String questionText, String answerText) {
		
		Optional<Question> questionOp = Database.questions.entrySet()
										                  .stream()
										                  .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
										                  .map(entry -> entry.getValue())
										                  .findFirst();
		
		if(questionOp.isPresent()) {
			Question question = questionOp.get();
			Optional<Answer> answerOp = question.getListOfAnswers().stream()
					                                               .filter(ans -> StringUtils.equals(ans.getText(), answerText))
			                                                       .findFirst();
			
			if(answerOp.isPresent()) {
				Answer answer = answerOp.get();
				if(voteType == VoteType.DOWN) {
					answer.setNoOfUpVotes(answer.getNoOfDownVotes() + 1);
				} else if(voteType == VoteType.UP) {
					answer.setNoOfUpVotes(answer.getNoOfUpVotes() + 1);
				}
			} else {
				throw new IllegalArgumentException("Answer not found : " + answerText);
			}
		} else {
			throw new IllegalArgumentException("Question not found : " + questionText);
		}
	}
	
	public void upvoteOrDownVoteQuestion(VoteType voteType, String questionText) {
		
		Optional<Question> questionOp = Database.questions.entrySet()
										                  .stream()
										                  .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
										                  .map(entry -> entry.getValue())
										                  .findFirst();
		
		if(questionOp.isPresent()) {
			Question question = questionOp.get();
			if(voteType == VoteType.DOWN) {
				question.setNoOfUpVotes(question.getNoOfDownVotes() + 1);
			} else if(voteType == VoteType.UP) {
				question.setNoOfUpVotes(question.getNoOfUpVotes() + 1);
			}
		} else {
			throw new IllegalArgumentException("Question not found : " + questionText);
		}
	}
	
	public void subscribeToQuestion(VoteType voteType, String questionText) {
		
		Optional<Question> questionOp = Database.questions.entrySet()
										                  .stream()
										                  .filter(entry -> StringUtils.equals(entry.getValue().getText(), questionText))
										                  .map(entry -> entry.getValue())
										                  .findFirst();
		
		if(questionOp.isPresent()) {
			Question question = questionOp.get();
			question.getUsersSubscribedToThisQuestion().add(Database.currentUser);
			Database.currentUser.getSubscribedQuestions().add(question);
		} else {
			throw new IllegalArgumentException("Question not found : " + questionText);
		}
	}
}

package personal.learning.sovf.entity;

import java.util.ArrayList;
import java.util.List;

import personal.learning.sovf.enums.PostType;
import personal.learning.sovf.enums.QuestionStatus;

public class Question extends Post {
	
	private List<Tag> associatedTags = new ArrayList<>();
	private List<Answer> listOfAnswers = new ArrayList<>();
	private Answer acceptedAnswer;
	private QuestionStatus questionStatus;
	private List<User> usersSubscribedToThisQuestion = new ArrayList<>();

	public Question(String text, User author, List<Tag> associatedTags) {
		super(text, author, PostType.QUESTION);
		this.associatedTags = associatedTags;
		this.questionStatus = QuestionStatus.OPEN;
	}

	public List<Tag> getAssociatedTags() {
		return associatedTags;
	}

	public void setAssociatedTags(List<Tag> associatedTags) {
		this.associatedTags = associatedTags;
	}

	public List<Answer> getListOfAnswers() {
		return listOfAnswers;
	}

	public void setListOfAnswers(List<Answer> listOfAnswers) {
		this.listOfAnswers = listOfAnswers;
	}

	public Answer getAcceptedAnswer() {
		return acceptedAnswer;
	}

	public void setAcceptedAnswer(Answer acceptedAnswer) {
		this.acceptedAnswer = acceptedAnswer;
	}

	public QuestionStatus getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(QuestionStatus questionStatus) {
		this.questionStatus = questionStatus;
	}
	
	public List<User> getUsersSubscribedToThisQuestion() {
		return usersSubscribedToThisQuestion;
	}

	public void setUsersSubscribedToThisQuestion(List<User> usersSubscribedToThisQuestion) {
		this.usersSubscribedToThisQuestion = usersSubscribedToThisQuestion;
	}

	@Override
	public String toString() {
		return "Question [associatedTags=" + associatedTags + ", listOfAnswers=" + listOfAnswers + ", acceptedAnswer="
				+ acceptedAnswer + ", questionStatus=" + questionStatus + ", usersSubscribedToThisQuestion="
				+ usersSubscribedToThisQuestion + "]";
	}
	
}

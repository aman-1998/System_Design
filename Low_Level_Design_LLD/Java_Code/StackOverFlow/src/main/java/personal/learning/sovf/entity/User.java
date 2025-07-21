package personal.learning.sovf.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	private String role;
	private List<Question> questionsAsked = new ArrayList<>();
	private List<Answer> answersGiven = new ArrayList<>();
	private List<Question> subscribedQuestions = new ArrayList<>();
	
	public User(String name, String role) {
		this.name = name;
		this.role = role;
	}
	
	public void notify(String questionText, String answerText) {
		System.out.println("Someone answered for question " + questionText);
		System.out.println("Answer : " + answerText);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Question> getQuestionsAsked() {
		return questionsAsked;
	}

	public void setQuestionsAsked(List<Question> questionsAsked) {
		this.questionsAsked = questionsAsked;
	}

	public List<Answer> getAnswersGiven() {
		return answersGiven;
	}

	public void setAnswersGiven(List<Answer> answersGiven) {
		this.answersGiven = answersGiven;
	}
	
	public List<Question> getSubscribedQuestions() {
		return subscribedQuestions;
	}

	public void setSubscribedQuestions(List<Question> subscribedQuestions) {
		this.subscribedQuestions = subscribedQuestions;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", role=" + role + ", questionsAsked=" + questionsAsked
				+ ", answersGiven=" + answersGiven + ", subscribedQuestions=" + subscribedQuestions + "]";
	}
	
}

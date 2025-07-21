package personal.learning.sovf.entity;

import personal.learning.sovf.enums.PostType;

public class Answer extends Post {
	
	private Question question;
	private boolean accepted;
	
	public Answer(String text, User author, Question question) {
		super(text, author, PostType.ANSWER);
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "Answer [question=" + question + ", accepted=" + accepted + "]";
	}
	
}

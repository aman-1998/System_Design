package personal.learning.sovf.service;

import personal.learning.sovf.entity.Question;
import personal.learning.sovf.entity.User;

public class UserService {
	
	public void subscribe(Question question) {
		Database.currentUser.getSubscribedQuestions().add(question);
	}
	
	public void showProfile(String name) {
		User user = Database.users.get(name);
		if(user != null) {
			System.out.println("User name : " + name);
			System.out.println("Questions asked : " + user.getQuestionsAsked());
			System.out.println("Answers given : " + user.getAnswersGiven());
			
		}
	}
}

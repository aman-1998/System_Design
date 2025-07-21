package personal.learning.sovf.factory;

import java.util.List;

import personal.learning.sovf.entity.Answer;
import personal.learning.sovf.entity.Post;
import personal.learning.sovf.entity.Question;
import personal.learning.sovf.entity.Tag;
import personal.learning.sovf.entity.User;
import personal.learning.sovf.enums.PostType;

public class PostFactory {
	
	public static Post getPost(String text, User author, PostType postType, Object arg) {
		
		switch(postType) {
			case QUESTION : 
				List<Tag> listOfTag = (List<Tag>) arg;
				return new Question(text, author, listOfTag);
			case ANSWER:
				Question question = (Question) arg;
				return new Answer(text, author, question);
		    default:
		    	throw new IllegalArgumentException("Invalid post type");
		}
	}
}

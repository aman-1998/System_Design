package personal.learning.sovf.service;

import java.util.HashMap;
import java.util.Map;

import personal.learning.sovf.entity.Question;
import personal.learning.sovf.entity.Tag;
import personal.learning.sovf.entity.User;

public class Database {
	
	public static Map<String, User> users = new HashMap<>();
	public static Map<String, Tag> tags = new HashMap<>();
	public static Map<Integer, Question> questions = new HashMap<>();
	public static User currentUser;
	
}

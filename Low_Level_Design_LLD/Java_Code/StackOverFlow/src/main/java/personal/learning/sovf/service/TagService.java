package personal.learning.sovf.service;

import org.springframework.stereotype.Service;

import personal.learning.sovf.entity.Tag;

@Service
public class TagService {
	
	public static Tag getOrCreateTopic(String name) {
        name = name.toLowerCase();
        if (!Database.tags.containsKey(name)) {
            Database.tags.put(name, new Tag(name));
        }
        return Database.tags.get(name);
    }
}

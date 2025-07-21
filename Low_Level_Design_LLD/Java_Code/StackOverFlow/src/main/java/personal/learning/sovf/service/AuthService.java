package personal.learning.sovf.service;

import personal.learning.sovf.entity.User;

public class AuthService {
	
	public void signup(String name, String role) {
        if (Database.users.containsKey(name)) {
            System.out.println("User already exists.");
            return;
        }
        User user = new User(name, role);
        Database.users.put(name, user);
        Database.currentUser = user;
        System.out.println("----------------------------------------------------------------");
        System.out.println("Signed up and logged in: " + name);
    }

    public void logout() {
        System.out.println("Logged out: " + Database.currentUser.getName());
        System.out.println("----------------------------------------------------------------");
        Database.currentUser = null;
    }

    public void login(String name) {
        if (!Database.users.containsKey(name)) {
            System.out.println("User does not exist.");
            return;
        }
        Database.currentUser = Database.users.get(name);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Logged in: " + name);
    }
}

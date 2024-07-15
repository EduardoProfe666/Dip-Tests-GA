package src.main.java.org.example;

import java.util.ArrayList;
import java.util.List;


public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        users.add(user);
    }

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }
}
package src.main.java.org.example;

import java.util.List;

public class UserController {
    private final UserService userService;
    private final UserStatistics userStatistics;
    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;

    public UserController(UserService userService, UserStatistics userStatistics, AuthenticationService authenticationService, AuthorizationService authorizationService) {
        this.userService = userService;
        this.userStatistics = userStatistics;
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public double getAverageAge() {
        List<User> users = userService.getAllUsers();
        return userStatistics.calculateAverageAge(users);
    }

    public int getTotalAge() {
        List<User> users = userService.getAllUsers();
        return userStatistics.calculateTotalAge(users);
    }

    public boolean login(String username, String password) {
        return authenticationService.authenticate(username, password);
    }

    public boolean checkAuthorization(String username, String role) {
        return authorizationService.authorize(username, role);
    }
}
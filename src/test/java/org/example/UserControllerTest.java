package org.example;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private UserRepository userRepository;
    private UserStatistics userStatistics;
    private CalculatorService calculator;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
        calculator = new CalculatorService();
        userStatistics = new UserStatistics(calculator);
        authenticationService = new AuthenticationService(userRepository);
        authorizationService = new AuthorizationService(userRepository);
        userController = new UserController(userService, userStatistics, authenticationService, authorizationService);
    }

    @Test
    public void testAddUser() {
        User user = new User("john", "john@example.com", 30, "password");
        userController.addUser(user);
        assertEquals(user, userController.getUserByUsername("john"));
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User("john", "john@example.com", 30, "password");
        userController.addUser(user);
        assertEquals(user, userController.getUserByUsername("john"));
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        assertNull(userController.getUserByUsername("jane"));
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("john", "john@example.com", 30, "password");
        User user2 = new User("jane", "jane@example.com", 25, "password");
        userController.addUser(user1);
        userController.addUser(user2);
        List<User> users = userController.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    public void testGetAverageAge() {
        User user1 = new User("john", "john@example.com", 30, "password");
        User user2 = new User("jane", "jane@example.com", 25, "password");
        userController.addUser(user1);
        userController.addUser(user2);
        assertEquals(27.5, userController.getAverageAge(), 0.0001);
    }

    @Test
    public void testGetTotalAge() {
        User user1 = new User("john", "john@example.com", 30, "password");
        User user2 = new User("jane", "jane@example.com", 25, "password");
        userController.addUser(user1);
        userController.addUser(user2);
        assertEquals(55, userController.getTotalAge());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User("john", "john@example.com", 30, "password");
        userController.addUser(user);
        assertTrue(userController.login("john", "password"));
    }

    @Test
    public void testLoginFailure() {
        User user = new User("john", "john@example.com", 30, "password");
        userController.addUser(user);
        assertFalse(userController.login("john", "wrongpassword"));
    }

    @Test
    public void testLoginUserNotFound() {
        assertFalse(userController.login("jane", "password"));
    }

    @Test
    public void testCheckAuthorizationSuccess() {
        User user = new User("john", "john@example.com", 30, "password");
        userController.addUser(user);
        assertTrue(userController.checkAuthorization("john", "admin"));
    }

    @Test
    public void testCheckAuthorizationFailure() {
        User user = new User("john", "john@example.com", 30, "password");
        userController.addUser(user);
        assertFalse(userController.checkAuthorization("john", "user"));
    }

    @Test
    public void testCheckAuthorizationUserNotFound() {
        assertFalse(userController.checkAuthorization("jane", "admin"));
    }

    @AfterEach
    public void tearDown() {
        userController = null;
        userService = null;
        userRepository = null;
        userStatistics = null;
        calculator = null;
        authenticationService = null;
        authorizationService = null;
    }
}
package src.test.java.org.example;

import org.junit.jupiter.api.*;
import src.main.java.org.example.User;
import src.main.java.org.example.UserRepository;
import src.main.java.org.example.UserService;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser() {
        User user = new User("john", "john@example.com", 30, "password");
        userService.addUser(user);
        assertEquals(user, userService.getUserByUsername("john"));
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User("john", "john@example.com", 30, "password");
        userService.addUser(user);
        assertEquals(user, userService.getUserByUsername("john"));
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        assertNull(userService.getUserByUsername("jane"));
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("john", "john@example.com", 30, "password");
        User user2 = new User("jane", "jane@example.com", 25, "password");
        userService.addUser(user1);
        userService.addUser(user2);
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @AfterEach
    public void tearDown() {
        userService = null;
        userRepository = null;
    }
}
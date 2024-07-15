package src.test.java.org.example;

import org.junit.jupiter.api.*;
import src.main.java.org.example.AuthorizationService;
import src.main.java.org.example.User;
import src.main.java.org.example.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {

    private AuthorizationService authorizationService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        authorizationService = new AuthorizationService(userRepository);
    }

    @Test
    public void testAuthorizeSuccess() {
        User user = new User("john", "john@example.com", 30, "password");
        userRepository.save(user);
        assertTrue(authorizationService.authorize("john", "admin"));
    }

    @Test
    public void testAuthorizeFailure() {
        User user = new User("john", "john@example.com", 30, "password");
        userRepository.save(user);
        assertFalse(authorizationService.authorize("john", "user"));
    }

    @Test
    public void testAuthorizeUserNotFound() {
        assertFalse(authorizationService.authorize("jane", "admin"));
    }

    @AfterEach
    public void tearDown() {
        authorizationService = null;
        userRepository = null;
    }
}
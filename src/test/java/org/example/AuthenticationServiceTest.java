package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        authenticationService = new AuthenticationService(userRepository);
    }

    @Test
    public void testAuthenticateSuccess() {
        User user = new User("jo", "john@example.com", 30, "password");
        userRepository.save(user);
        assertTrue(authenticationService.authenticate("john", "password"));
    }

    @Test
    public void testAuthenticateFailure() {
        User user = new User("john", "john@example.com", 30, "password");
        userRepository.save(user);
        assertFalse(authenticationService.authenticate("john", "wrongpassword"));
    }

    @Test
    public void testAuthenticateUserNotFound() {
        assertFalse(authenticationService.authenticate("jane", "password"));
    }

    @AfterEach
    public void tearDown() {
        authenticationService = null;
        userRepository = null;
    }
}
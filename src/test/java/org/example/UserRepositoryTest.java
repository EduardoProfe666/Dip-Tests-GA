package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testSaveUser() {
        User user = new User("john", "john@example.com", 30, "password");
        userRepository.save(user);
        assertEquals(user, userRepository.findByUsername("john"));
    }

    @Test
    public void testSaveUserNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.save(null);
        });
        assertEquals("User cannot be null.", exception.getMessage());
    }

    @Test
    public void testFindByUsername() {
        User user = new User("john", "john@example.com", 30, "password");
        userRepository.save(user);
        assertEquals(user, userRepository.findByUsername("john"));
    }

    @Test
    public void testFindByUsernameNotFound() {
        assertNull(userRepository.findByUsername("jane"));
    }

    @Test
    public void testFindAll() {
        User user1 = new User("john", "john@example.com", 30, "password");
        User user2 = new User("jane", "jane@example.com", 25, "password");
        userRepository.save(user1);
        userRepository.save(user2);
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @AfterEach
    public void tearDown() {
        userRepository = null;
    }
}
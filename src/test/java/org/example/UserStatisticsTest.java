package src.test.java.org.example;

import org.junit.jupiter.api.*;
import src.main.java.org.example.Calculator;
import src.main.java.org.example.User;
import src.main.java.org.example.UserStatistics;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class UserStatisticsTest {

    private UserStatistics userStatistics;
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        userStatistics = new UserStatistics(calculator);
    }

    @Test
    public void testCalculateAverageAge() {
        List<User> users = Arrays.asList(
                new User("john", "john@example.com", 30, "password"),
                new User("jane", "jane@example.com", 25, "password")
        );
        assertEquals(27.5, userStatistics.calculateAverageAge(users), 0.0001);
    }

    @Test
    public void testCalculateAverageAgeNullList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStatistics.calculateAverageAge(null);
        });
        assertEquals("User list cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testCalculateAverageAgeEmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStatistics.calculateAverageAge(Arrays.asList());
        });
        assertEquals("User list cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testCalculateTotalAge() {
        List<User> users = Arrays.asList(
                new User("john", "john@example.com", 30, "password"),
                new User("jane", "jane@example.com", 25, "password")
        );
        assertEquals(55, userStatistics.calculateTotalAge(users));
    }

    @Test
    public void testCalculateTotalAgeNullList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStatistics.calculateTotalAge(null);
        });
        assertEquals("User list cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testCalculateTotalAgeEmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStatistics.calculateTotalAge(Arrays.asList());
        });
        assertEquals("User list cannot be null or empty.", exception.getMessage());
    }

    @AfterEach
    public void tearDown() {
        userStatistics = null;
        calculator = null;
    }
}
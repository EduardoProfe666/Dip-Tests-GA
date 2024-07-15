package org.example;

import java.util.List;

public class UserStatistics {
    private final Calculator calculator;

    public UserStatistics(Calculator calculator) {
        this.calculator = calculator;
    }

    public double calculateAverageAge(List<User> users) {
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("User list cannot be null or empty.");
        }
        int totalAge = 0;
        for (User user : users) {
            totalAge = calculator.add(totalAge, user.getAge());
        }
        return calculator.divide(totalAge, users.size());
    }

    public int calculateTotalAge(List<User> users) {
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("User list cannot be null or empty.");
        }
        int totalAge = 0;
        for (User user : users) {
            totalAge = calculator.add(totalAge, user.getAge());
        }
        return totalAge;
    }
}
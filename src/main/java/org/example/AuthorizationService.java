package org.example;

public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authorize(String username, String role) { 
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        // Implementación simplificada de autorización basada en roles
        return role.equals("admin");
    }
}
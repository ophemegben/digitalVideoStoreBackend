package StreamX.DigitalVideoStoreBackend.controller;

import StreamX.DigitalVideoStoreBackend.model.UserModel;
import StreamX.DigitalVideoStoreBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to register a user
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        // Validate user input
        if (isNullOrEmpty(user.getFirstName()) ||
                isNullOrEmpty(user.getLastName()) ||
                isNullOrEmpty(user.getEmail()) ||
                isNullOrEmpty(user.getPassword())) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        // Validate email format
        if (!isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }

        // Check if email already exists
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }

        // Validate password strength
        if (!isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
        UserModel createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(Map.of("message", "User registered successfully", "user", createdUser));
    }

    // Endpoint to login a user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        String email = creds.get("email");
        String password = creds.get("password");
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        // Authenticate user
        Optional<UserModel> user = userService.authenticate(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "user", user.get()));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Helper method to check for empty or null strings
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Helper method to validate email using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    // Helper method to validate password strength (at least 8 characters, 1 uppercase, 1 lowercase, 1 digit)
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }
    
}
package com.sydormyk.wandermap.service;

import com.sydormyk.wandermap.model.User;
import com.sydormyk.wandermap.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(String email, String password){
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public String login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return jwtService.generateToken(user.getEmail());

    }
}

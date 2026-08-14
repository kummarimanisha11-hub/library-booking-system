package com.example.librarybookingsystem.service;

import com.example.librarybookingsystem.entity.User;
import com.example.librarybookingsystem.repository.UserRepository;
import com.example.librarybookingsystem.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // REGISTER
    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // LOGIN
    public String login(User user) {

        User existingUser = userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                user.getPassword(),
                existingUser.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        return jwtService.generateToken(existingUser.getEmail(), existingUser.getRole());
    }
}
package com.app.quantityMeasurementApp.service;

import com.app.quantityMeasurementApp.entity.User;
import com.app.quantityMeasurementApp.repository.UserRepository;
import com.app.quantityMeasurementApp.security.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(User user) {

//        User existingUser = userRepository.findByUsername(user.getUsername())
//                .orElseThrow(()->new UsernameNotFoundException("user not found"));
//
//        return authUtils.generateJwtToken(existingUser);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));

        return authUtils.generateJwtToken(user);
    }

    public String signup(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }
}
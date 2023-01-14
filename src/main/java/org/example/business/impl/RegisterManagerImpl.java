package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.RegisterManager;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RegisterManagerImpl implements RegisterManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long register(User user) {
        if(Objects.equals(user.getEmail(), "homeowner@gmail.com")){
            User admin = User.builder()
                    .email("admin@gmail.com")
                    .firstName("Admin")
                    .lastName("Admin")
                    .role(Role.ADMIN)
                    .password(passwordEncoder.encode("12345678"))
                    .build();
            userRepository.saveUser(admin);
            User renter = User.builder()
                    .email("renter@gmail.com")
                    .firstName("Renter")
                    .lastName("Renter")
                    .role(Role.RENTER)
                    .password(passwordEncoder.encode("12345678"))
                    .build();
            userRepository.saveUser(renter);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveUser(user);
    }
}

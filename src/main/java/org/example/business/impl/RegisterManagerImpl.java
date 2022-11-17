package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.RegisterManager;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterManagerImpl implements RegisterManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveUser(user);
    }
}

package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.UserManager;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;
    @Override
    public Optional<User> getUser(Long id) {

        return userRepository.findUserById(id);

    }
}

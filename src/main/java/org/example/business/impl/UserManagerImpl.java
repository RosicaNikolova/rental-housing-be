package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.Exceptions.UnauthorizedDataAccessException;
import org.example.business.UserManager;
import org.example.domain.AccessToken;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;
    private AccessToken requestAccessToken;
    @Override
    public Optional<User> getUser(Long id) {
        if(requestAccessToken.getUserId() != id){
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        return userRepository.findUserById(id);

    }
}

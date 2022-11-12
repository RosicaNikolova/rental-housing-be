package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.UserManager;
import org.example.domain.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {
    @Override
    public User getUser() {

        return new User();

    }
}

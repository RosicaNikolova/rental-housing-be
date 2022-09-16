package org.example.business.impl;

import org.example.business.UserManager;
import org.example.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager {
    @Override
    public User getUser() {
        User user = new User(1, "rositsa@mail.com", "Rositsa", "Nikolova");
        return user;

    }
}

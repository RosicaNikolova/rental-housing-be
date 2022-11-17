package org.example.persistence;

import org.example.domain.User;
import org.example.persistence.entity.UserEntity;

public interface UserRepository {

    User findUserByEmail(String email);

    Long saveUser(User user);
}

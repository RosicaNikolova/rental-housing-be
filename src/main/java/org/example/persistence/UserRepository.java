package org.example.persistence;

import org.example.domain.User;
import org.example.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    User findUserByEmail(String email);

    Long saveUser(User user);

    Optional<User> findUserById(Long id);
}

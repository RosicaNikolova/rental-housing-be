package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.User;
import org.example.persistence.JPAUserRepository;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JPAUserRepository jpaUserRepository;

    @Override
    public User findUserByEmail(String email) {
        UserEntity user = jpaUserRepository.findFirstByEmail(email);
        if(user != null) {
            return User.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .id(user.getId())
                    .role(user.getRole())
                    .build();
        }
            return null;

    }

    @Override
    public Long saveUser(User user) {
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

       return jpaUserRepository.save(userEntity).getId();
    }
}

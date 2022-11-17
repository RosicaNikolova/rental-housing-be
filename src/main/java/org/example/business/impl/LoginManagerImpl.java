package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.AccessTokenEncoder;
import org.example.business.Exceptions.CreatePropertyException;
import org.example.business.Exceptions.InvalidCredentialsException;
import org.example.business.LoginManager;
import org.example.controller.DTO.LoginRequest;
import org.example.controller.DTO.LoginResponse;
import org.example.domain.AccessToken;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginManagerImpl implements LoginManager {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        if(user == null){
            throw new InvalidCredentialsException();
        }
        if(!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();

        }
        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
        //return null;
    }

    private String generateAccessToken(User user) {

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .role(user.getRole())
                        .userId(user.getId())
                        .build());
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.business.LoginManager;
import org.example.business.RegisterManager;
import org.example.controller.DTO.LoginRequest;
import org.example.controller.DTO.LoginResponse;
import org.example.controller.DTO.RegisterRequest;
import org.example.controller.DTO.RegisterResponse;
import org.example.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {
    private final RegisterManager registerManager;

    @PostMapping
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();
        RegisterResponse registerResponse = RegisterResponse.builder()
                .id(registerManager.register(user))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }
}

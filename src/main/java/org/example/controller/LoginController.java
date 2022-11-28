package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.business.LoginManager;
import org.example.controller.DTO.LoginRequest;
import org.example.controller.DTO.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final LoginManager loginManager;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginManager.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}

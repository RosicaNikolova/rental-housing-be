package org.example.business;


import org.example.controller.DTO.LoginRequest;
import org.example.controller.DTO.LoginResponse;

public interface LoginManager {

    LoginResponse login(LoginRequest loginRequest);
}

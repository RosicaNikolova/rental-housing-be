package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.business.UserManager;
import org.example.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserManager userManager;

    @GetMapping("user")
    public ResponseEntity<User> getUser(){
        final User user = userManager.getUser();
        return ResponseEntity.ok().body(user);
    }
}

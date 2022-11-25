package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.business.UserManager;
import org.example.controller.DTO.UserDTO;
import org.example.domain.Property;
import org.example.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserManager userManager;
    private final ModelMapper modelMapper;

    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long userId){

        final Optional<User> userOptional = userManager.getUser(userId);

        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Optional<UserDTO> userDTO = Optional.of(modelMapper.map(userOptional.get(), UserDTO.class));
            return ResponseEntity.ok().body(userDTO.get());
        }
    }
}

package io.github.amoghk0216.stock_backend.controller;

import io.github.amoghk0216.stock_backend.dto.UserDto;
import io.github.amoghk0216.stock_backend.model.User;
import io.github.amoghk0216.stock_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

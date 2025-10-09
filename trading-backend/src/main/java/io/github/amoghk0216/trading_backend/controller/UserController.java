package io.github.amoghk0216.trading_backend.controller;

import io.github.amoghk0216.trading_backend.model.User;
import io.github.amoghk0216.trading_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}

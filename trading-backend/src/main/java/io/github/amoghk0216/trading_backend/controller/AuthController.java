package io.github.amoghk0216.trading_backend.controller;

import io.github.amoghk0216.trading_backend.dto.UserDto;
import io.github.amoghk0216.trading_backend.model.User;
import io.github.amoghk0216.trading_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        String token = authService.login(userDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        try {
            User user = authService.register(userDto);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}

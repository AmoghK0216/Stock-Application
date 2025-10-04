package io.github.amoghk0216.stock_backend.service;

import io.github.amoghk0216.stock_backend.dto.UserDto;
import io.github.amoghk0216.stock_backend.model.User;
import io.github.amoghk0216.stock_backend.repository.UserRepository;
import io.github.amoghk0216.stock_backend.security.UserDetailsServiceImpl;
import io.github.amoghk0216.stock_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    public String login(UserDto userDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());

        return jwtUtil.generateToken(userDetails);
    }

    public User register(UserDto userDto) throws Exception{
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        Optional<User> check = userRepository.findByEmail(userDto.getEmail());

        if(check.isPresent())
            throw new Exception("email already exists");

        return userRepository.save(user);
    }
}

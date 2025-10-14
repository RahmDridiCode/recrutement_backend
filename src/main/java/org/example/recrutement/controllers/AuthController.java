package org.example.recrutement.controllers;

import org.example.recrutement.dto.JwtRequest;
import org.example.recrutement.dto.JwtResponse;
import org.example.recrutement.entities.User;
import org.example.recrutement.mappers.UserMapper;
import org.example.recrutement.repositories.UserRepository;
import org.example.recrutement.services.impl.MyUserDetailsService;
import org.example.recrutement.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authmanager;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) {
        authmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        User user = userRepo.findByUsername(jwtRequest.getUsername());
        return new JwtResponse(token, userMapper.toDTO(user));
    }
}

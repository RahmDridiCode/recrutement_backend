package org.example.recrutement.services.impl;

import org.example.recrutement.entities.User;
import org.example.recrutement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}

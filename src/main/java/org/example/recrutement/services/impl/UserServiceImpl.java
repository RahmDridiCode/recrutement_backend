package org.example.recrutement.services.impl;

import org.example.recrutement.dto.RestUserDTO;
import org.example.recrutement.dto.UserDTO;
import org.example.recrutement.entities.User;
import org.example.recrutement.enums.Role;
import org.example.recrutement.mappers.UserMapper;
import org.example.recrutement.repositories.UserRepository;
import org.example.recrutement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(RestUserDTO restUserDTO) {
        User user = userMapper.toEntity(restUserDTO);
        user.setPassword(passwordEncoder.encode(restUserDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(User user) {
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public List<UserDTO> listUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listUserByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listEveryUser() {
        return userRepository.findByRoleNot(Role.ADMIN)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    /*
    @Override
    public UserDTO findUserDTO(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
     */
    public User findUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id " + id));
    }


    @Override
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByUsername(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
    }

    // Recherche un utilisateur par email
    @Override
    public User findByEmail(String email) {
        return userRepository.findByUsername(email);
    }





}

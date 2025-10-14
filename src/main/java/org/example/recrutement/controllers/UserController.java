package org.example.recrutement.controllers;

import org.example.recrutement.dto.RestUserDTO;
import org.example.recrutement.dto.UserDTO;
import org.example.recrutement.enums.Role;
import org.example.recrutement.repositories.UserRepository;
import org.example.recrutement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RestUserDTO restUserDTO) {
        UserDTO createdUser = userService.createUser(restUserDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<UserDTO>> listUser() {
        List<UserDTO> users = userService.listEveryUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/list/{role}")
    public ResponseEntity<List<UserDTO>> listUserByRole(@PathVariable Role role) {
        List<UserDTO> users = userService.listUserByRole(role);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<UserDTO> editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

}

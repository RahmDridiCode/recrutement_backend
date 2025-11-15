package org.example.recrutement.services;

import org.example.recrutement.dto.RestUserDTO;
import org.example.recrutement.dto.UserDTO;
import org.example.recrutement.entities.User;
import org.example.recrutement.enums.Role;

import java.util.List;

public interface UserService {
    UserDTO createUser(RestUserDTO restUserDTO);
    UserDTO updateUser(User user);
    void deleteUser(Long userId);
    List<UserDTO> listUser();
    List<UserDTO> listUserByRole(Role role);
    List<UserDTO> listEveryUser();
   // UserDTO findUserDTO(Long id);
    User findUser(Long id);
    void updatePassword(String email, String newPassword);
    User findByEmail(String email);
}

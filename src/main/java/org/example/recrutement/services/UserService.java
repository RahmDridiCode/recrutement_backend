package org.example.recrutement.services;

import org.example.recrutement.dto.RestUserDTO;
import org.example.recrutement.dto.UserDTO;
import org.example.recrutement.enums.Role;

import java.util.List;

public interface UserService {
    UserDTO createUser(RestUserDTO restUserDTO);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(Long userId);
    List<UserDTO> listUser();
    List<UserDTO> listUserByRole(Role role);
    List<UserDTO> listEveryUser();
    UserDTO findUser(Long id);
}

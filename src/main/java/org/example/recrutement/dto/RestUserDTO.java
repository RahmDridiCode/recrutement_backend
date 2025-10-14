package org.example.recrutement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.recrutement.enums.Role;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String phone;
    private String image;
    private Role role;
}

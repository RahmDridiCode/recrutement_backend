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
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String phone;
    private String image;
    private Role role;
}

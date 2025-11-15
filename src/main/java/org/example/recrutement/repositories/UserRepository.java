package org.example.recrutement.repositories;

import org.example.recrutement.entities.User;
import org.example.recrutement.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRole(Role role);

    List<User> findByRoleNot(Role role);


}

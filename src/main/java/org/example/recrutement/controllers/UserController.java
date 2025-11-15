package org.example.recrutement.controllers;

import org.example.recrutement.dto.ApiResponseDTO;
import org.example.recrutement.dto.ForgotPasswordRequestDTO;
import org.example.recrutement.dto.ResetPasswordRequestDTO;
import org.example.recrutement.dto.RestUserDTO;
import org.example.recrutement.dto.UserDTO;
import org.example.recrutement.entities.User;
import org.example.recrutement.enums.Role;
import org.example.recrutement.mappers.UserMapper;
import org.example.recrutement.repositories.PasswordResetTokenRepository;
import org.example.recrutement.repositories.UserRepository;
import org.example.recrutement.services.PasswordResetTokenService;
import org.example.recrutement.services.UserService;
import org.example.recrutement.services.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserMapper userMapper;


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


    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponseDTO> forgotPassword(@RequestBody ForgotPasswordRequestDTO request) {
        User user = userService.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO("Email not found"));
        }

        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createToken(user.getUsername(), token);

        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(user.getUsername(), token);

        return ResponseEntity.ok(new ApiResponseDTO("Password reset link sent"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponseDTO> resetPassword(@RequestBody ResetPasswordRequestDTO request) {
        String email = passwordResetTokenService.validateToken(request.getToken());
        if (email == null) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO("Invalid or expired token"));
        }
        userService.updatePassword(email, request.getPassword());
        return ResponseEntity.ok(new ApiResponseDTO("Password updated successfully"));
    }
    @PostMapping("/user/{id}")

    public UserDTO editUser(@PathVariable Long id, @RequestParam(value = "image", required = false) MultipartFile image, @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName, String password, @RequestParam("username") String username,
                         @RequestParam("address") String address, @RequestParam("phone") String phone, @RequestParam("role") String role) throws IOException {
        User user = userService.findUser(id);
        if(image!=null && !image.isEmpty()){
            File file = new File(System.getProperty("user.dir")).getCanonicalFile();
            System.out.println("Parent directory : " + file.getParent());
            File file2 = new File(file.getParent()+ "/Recrutement Front/recrutement-front/src/assets/images/users/"+image.getOriginalFilename());
            file2.createNewFile();
            FileOutputStream fout = new FileOutputStream(file2);
            fout.write(image.getBytes());
            user.setImage("/assets/images/users/"+image.getOriginalFilename());
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setAddress(address);
        user.setPhone(phone);
        return userService.updateUser( user);
    }


}

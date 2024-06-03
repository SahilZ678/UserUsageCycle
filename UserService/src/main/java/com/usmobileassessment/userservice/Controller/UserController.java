package com.usmobileassessment.userservice.Controller;

import com.usmobileassessment.userservice.Dto.UserRequestDTO;
import com.usmobileassessment.userservice.Dto.UserResponseDTO;
import com.usmobileassessment.userservice.Exception.UserNotFoundException;
import com.usmobileassessment.userservice.Model.User;
import com.usmobileassessment.userservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(userService.convertToDto(user));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(user -> userService.convertToDto(user)).collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid User user) {
        User newUser = userService.createUser(user);
        UserResponseDTO userDTO = userService.convertToDto(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody @Valid UserRequestDTO user) {
        User updatedUser = userService.updateUser(id, user);
        UserResponseDTO userDTO = userService.convertToDto(updatedUser);
        return ResponseEntity.ok(userDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
